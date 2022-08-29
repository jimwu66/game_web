package jimwu.itest.portal;

import jimwu.itest.portal.model.Score;
import jimwu.itest.portal.service.IScoreService;
import jimwu.itest.portal.service.impl.ScoreServiceImpl;
import jimwu.itest.portal.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ScoreServiceTest {

    /*
    @Autowired
    ScoreServiceImpl scoreService;

    @Test
    public void getScoreTest(){
        List<Score> scores = scoreService.getScore("tetris");
        scores.forEach(score -> log.debug("score is :::{}",score));

    }
    @Test
    public void checkTopScoreTest(){
        ScoreVo scoreVo = new ScoreVo()
                .setNickname("jimwu66")
                .setScore(300)
                .setDuration(0)
                .setGameName("Snake");
        Boolean result = scoreService.checkTopScore(scoreVo);
        log.debug("my score :{}",scoreVo.getScore());
        log.debug("my score higher than record :{}",result);
    }

     */
}
