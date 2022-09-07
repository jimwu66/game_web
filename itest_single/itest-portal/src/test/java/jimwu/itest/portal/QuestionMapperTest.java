package jimwu.itest.portal;

import jimwu.itest.portal.mapper.QuestionMapper;
import jimwu.itest.portal.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QuestionMapperTest {

    /*
    @Autowired
    private QuestionMapper questionMapper;

    @Test
    void questionMapperTest(){
        Question question = questionMapper.selectById(1);
        log.debug("question is {}",question);
    }
    @Test
    void updateStatusTest(){
        int row = questionMapper.updateStatus(Question.SOLVED,5);
        log.debug("update stauts: {}",row);
    }

     */
}
