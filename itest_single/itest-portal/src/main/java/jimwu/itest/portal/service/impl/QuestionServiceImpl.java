package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jimwu.itest.portal.mapper.QuestionMapper;
import jimwu.itest.portal.model.Comment;
import jimwu.itest.portal.model.Question;
import jimwu.itest.portal.service.ICommentService;
import jimwu.itest.portal.service.IQuestionService;
import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.vo.QuestionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private ICommentService commentService;

    @Override
    public void createQuestion(QuestionVo questionVo) {
        Question question = new Question()
                .setNickname(questionVo.getNickname())
                .setIcon(questionVo.getIcon())
                .setTitle(questionVo.getTitle())
                .setContent(questionVo.getContent())
                .setTagNames(questionVo.getSelectedTags())
                .setPublishTime(LocalDateTime.now())
                .setDeleteStatus(0)
                .setPublishStatus(1)
                .setStatus(Question.PUBLISH)
                .setView(0);
        int row = questionMapper.insert(question);
        if(row!=1){
            throw ServiceException.failed("server busy , try later");
        }
    }

    @Override
    public List<Question> loadQuestions() {
        QueryWrapper<Question> query = new QueryWrapper<>();
        query.eq("delete_status",0);
        List<Question> questions = questionMapper.selectList(query);
        log.debug("questions:{}",questions);
        if(questions.isEmpty()){
            log.error("questions is null");
            throw ServiceException.notFound("no question data!");
        }
        questions.forEach(question -> {
            List<String> tags = tagNamesToTags(question.getTagNames());
            question.setTags(tags);
            List<Comment> comments = commentService.getComments(question.getId());
            question.setComments(comments);
        });
        return questions;
    }

    @Override
    public PageInfo<Question> loadQuestions(Integer pageNum, Integer pageSize) {
        if(pageNum==null||pageSize==null){
            throw ServiceException.unprocessableEntity("page data is null");
        }
        QueryWrapper<Question> query = new QueryWrapper<>();
        query.eq("delete_status",0);
        query.orderByDesc("publish_time");
        PageHelper.startPage(pageNum,pageSize);
        List<Question> questions = questionMapper.selectList(query);
        log.debug("questions:{}",questions);
        if(questions.isEmpty()){
            log.error("questions is null");
            throw ServiceException.notFound("no question data!");
        }
        questions.forEach(question -> {
            List<String> tags = tagNamesToTags(question.getTagNames());
            question.setTags(tags);
            List<Comment> comments = commentService.getComments(question.getId());
            question.setComments(comments);
        });
        return new PageInfo<>(questions);
    }

    private List<String> tagNamesToTags(String tagNames){
        String[] names = tagNames.split(",\\s?");
        List<String> tags = new ArrayList<>();
        for(String name:names){
            tags.add(name);
        }
        return tags;
    }

    @Override
    public void updateQuestionStatus(Integer status, Integer questionId) {
        if(questionId==null||status==null){
            throw ServiceException.notFound("question not found!");
        }
        int row = questionMapper.updateStatus(status,questionId);
        if(row!=1){
            throw ServiceException.failed("server busy , try later");
        }
    }
}
