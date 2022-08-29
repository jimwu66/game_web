package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.UserTopScore;
import jimwu.itest.portal.vo.ScoreVo;
import jimwu.itest.portal.vo.TopScoreVo;

import java.util.List;
import java.util.Map;

public interface IUserTopScoreService extends IService<UserTopScore> {

    Integer getRankingByNickname(String nickname);

    Integer saveTopScore(ScoreVo scoreVo);

    List<TopScoreVo> getEachGameTop3Nickname();
}
