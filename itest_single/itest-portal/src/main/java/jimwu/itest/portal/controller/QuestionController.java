package jimwu.itest.portal.controller;

import com.github.pagehelper.PageInfo;
import jimwu.itest.portal.model.Question;
import jimwu.itest.portal.model.Tag;
import jimwu.itest.portal.service.IQuestionService;
import jimwu.itest.portal.service.ITagService;
import jimwu.itest.portal.vo.QuestionVo;
import jimwu.itest.portal.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private ITagService tagService;

    @GetMapping("/tags")
    public R<List<Tag>> getTags(){
        List<Tag> tags = tagService.getTags();
        if(tags==null){
            return R.unprocessableEntity("get tags failed");
        }
        return R.ok(tags);
    }
    @PostMapping("/create")
    public R createQuestion(@Validated QuestionVo questionVo, BindingResult result){
        if(result.hasErrors()){
            String message = result.getFieldError().getDefaultMessage();
            return R.unprocessableEntity(message);
        }
        log.debug("receive question {}",questionVo);
        questionService.createQuestion(questionVo);
        return R.created("leave message success!");
    }
    @GetMapping("/questions")
    public R<PageInfo<Question>> loadQuestions(Integer pageNum){
        log.debug("load question");
        if(pageNum==null){
            pageNum=1;
        }
        Integer pageSize = 5;
        try{
            PageInfo<Question> questions = questionService.loadQuestions(pageNum,pageSize);
            return R.ok(questions);
        }catch (Exception e){
            return R.notFound(e.getMessage());
        }
    }
}
