package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.CommentMapper;
import jimwu.itest.portal.model.Comment;
import jimwu.itest.portal.service.ICommentService;
import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.vo.CommentVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private CommentMapper commentMapper;


    @Override
    @Transactional
    public List<Comment> postComment(CommentVo commentVo) {
        Comment comment = new Comment()
                .setNickname(commentVo.getNickname())
                .setContent(commentVo.getContent())
                .setQuestionId(commentVo.getQuestionId())
                .setIcon(commentVo.getIcon())
                .setPublishTime(LocalDateTime.now());
                //status and deleteStatus data table default already set to "0"
        int row = commentMapper.insert(comment);
        if(row!=1){
            throw ServiceException.failed("server busy , try later!");
        }
        List<Comment> list = getComments(commentVo.getQuestionId());
        if(list.isEmpty()){
            throw ServiceException.failed("server busy , try later!");
        }
        return list;
    }
    public List<Comment> getComments(Integer questionId){
        QueryWrapper<Comment> query = new QueryWrapper<>();
        query.eq("delete_status",0);
        query.eq("question_id",questionId);
        List<Comment> list = commentMapper.selectList(query);
        return list;
    }
}
