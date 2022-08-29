package jimwu.itest.portal;

import jimwu.itest.portal.service.impl.UserTopScoreServiceImpl;
import jimwu.itest.portal.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
@Slf4j
public class UserTopScoreServiceTest {

    /*
    @Resource
    private UserTopScoreServiceImpl userTopScoreService;


    @Test
    void userTopScoreServiceTest(){
        Map<Integer,String> gameRankingMap = userTopScoreService.getRankingMapByGameName("Snake");
        log.debug("snake game ranking map is {}",gameRankingMap);
    }

    @Test
    void saveTopScoreTest(){
        ScoreVo scoreVo = new ScoreVo()
                .setGameName("Tetris")
                .setNickname("jimwu")
                .setScore(320);
        int row = userTopScoreService.saveTopScore(scoreVo);
        log.debug("save success? {}",row);
    }

    @Test
    void getRankingMapByGameNameTest(){
        Map<Integer,String> gameMap = userTopScoreService.getRankingMapByGameName("Tetris");
        log.debug("snake map is {}",gameMap);
    }

    @Test
    void getRankingByNicknameTest(){
        int ranking = userTopScoreService.getRankingByNickname("onlyone");
        log.debug("only one ranking is {}",ranking);
    }



     */
}
