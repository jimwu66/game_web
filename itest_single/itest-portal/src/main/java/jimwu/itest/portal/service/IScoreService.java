package jimwu.itest.portal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.Score;
import jimwu.itest.portal.vo.ScoreVo;

import java.util.List;

public interface IScoreService extends IService<Score> {

    void saveScore(ScoreVo scoreVo);

    List<Score> getScore(String gameName);
}
