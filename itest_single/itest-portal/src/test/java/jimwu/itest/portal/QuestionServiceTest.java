package jimwu.itest.portal;

import com.github.pagehelper.PageInfo;
import jimwu.itest.portal.model.Question;
import jimwu.itest.portal.service.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QuestionServiceTest {

    /*
    @Autowired
    private IQuestionService questionService;

    @Test
    void loadQuestionsTest(){
        PageInfo<Question> questions = questionService.loadQuestions(1,3);
        log.debug("page info is {}",questions);
    }

     */
}

