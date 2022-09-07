package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.Comment;
import jimwu.itest.portal.vo.CommentVo;

import java.util.List;

public interface ICommentService extends IService<Comment> {

    List<Comment> postComment(CommentVo commentVo);

    List<Comment> getComments(Integer questionId);
}
