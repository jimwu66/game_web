package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.UserTopScoreMapper;
import jimwu.itest.portal.model.Level;
import jimwu.itest.portal.model.UserTopScore;
import jimwu.itest.portal.service.IUserTopScoreService;
import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.vo.RankingVo;
import jimwu.itest.portal.vo.ScoreVo;
import jimwu.itest.portal.vo.TopScoreVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserTopScoreServiceImpl extends ServiceImpl<UserTopScoreMapper, UserTopScore> implements IUserTopScoreService {

    @Resource
    private UserTopScoreMapper userTopScoreMapper;

    @Override
    public Integer saveTopScore(ScoreVo scoreVo) {
        if(scoreVo==null){
            throw ServiceException.notFound("no score data !");
        }
        String nickname = scoreVo.getNickname();
        String gameName = scoreVo.getGameName();
        String keyValue = nickname +"-"+gameName;
        Integer score = scoreVo.getScore();
        int row = userTopScoreMapper.saveTopScore(nickname,gameName,keyValue,score);
        if(row == 0){
            throw ServiceException.failed("server busy , try later!");
        }
        return row;
    }

    @Override
    public Integer getRankingByNickname(String nickname){
        if(nickname==null){
            throw ServiceException.notFound("no username data!");
        }
        List<RankingVo> gameRankingList = userTopScoreMapper.getRankingVoList();
        List<Integer> rankings = new ArrayList<>();
        gameRankingList.forEach(ranking ->{
            String name = ranking.getKeyName();
            if(name.substring(0,name.indexOf("-")).equals(nickname)){
                rankings.add(ranking.getRanking());
            }
        });
        if(rankings.isEmpty()){
            return 6; //hard code first , modify later
        }
        int ranking = Collections.min(rankings);
        return ranking;
    }

    @Override
    public List<TopScoreVo> getEachGameTop3Nickname() {
        List<TopScoreVo> list = userTopScoreMapper.getEachGameTop3Nickname();
        return list;
    }
}
