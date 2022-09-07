package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import jimwu.itest.portal.model.Question;
import jimwu.itest.portal.vo.QuestionVo;

import java.util.List;

public interface IQuestionService extends IService<Question> {

    void createQuestion(QuestionVo questionVo);

    List<Question> loadQuestions();

    void updateQuestionStatus(Integer questionId , Integer status);

    PageInfo<Question> loadQuestions(Integer pageNum, Integer pageSize);
}
