package jimwu.itest.portal;

import jimwu.itest.portal.mapper.UserTopScoreMapper;
import jimwu.itest.portal.vo.RankingVo;
import jimwu.itest.portal.vo.ScoreVo;
import jimwu.itest.portal.vo.TopScoreVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class UserTopScoreMapperTest {

    /*
    @Resource
    UserTopScoreMapper userTopScoreMapper;


    @Test
    void setUserTopScoreMapperTest(){
        List<RankingVo> utsList = userTopScoreMapper.getRankingVoList();
        utsList.forEach(rank -> {
            log.debug("key is {}",rank.getKeyName());
            log.debug("rank1 is {}",rank.getRanking());
        });
    }
    @Test
    void getTop3ScoreTest(){
        List<TopScoreVo> topThree = userTopScoreMapper.getEachGameTop3Nickname();
        topThree.forEach(score -> score.toString());
    }


     */
}
