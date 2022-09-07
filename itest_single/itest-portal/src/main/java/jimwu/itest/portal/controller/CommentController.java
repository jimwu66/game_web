package jimwu.itest.portal.controller;

import jimwu.itest.portal.model.Comment;
import jimwu.itest.portal.model.Question;
import jimwu.itest.portal.service.ICommentService;
import jimwu.itest.portal.service.IQuestionService;
import jimwu.itest.portal.vo.CommentVo;
import jimwu.itest.portal.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @Resource
    private IQuestionService questionService;

    @PostMapping("/postComment")
    public R postComment(@Validated CommentVo commentVo,
                                        BindingResult result){
        if(result.hasErrors()){
            String message = result.getFieldError().getDefaultMessage();
            return R.unprocessableEntity(message);
        }
        log.debug("receive comment {}",commentVo);
        if(commentVo.getPostStatus()){
            questionService.updateQuestionStatus(Question.REPLIED,commentVo.getQuestionId());
        }
        Comment comment = commentService.postComment(commentVo);
        return R.created(comment);
    }
}
