package jimwu.itest.portal.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.ScoreMapper;
import jimwu.itest.portal.mapper.UserMapper;
import jimwu.itest.portal.mapper.UserTopScoreMapper;
import jimwu.itest.portal.model.Score;
import jimwu.itest.portal.model.User;
import jimwu.itest.portal.model.UserCoin;
import jimwu.itest.portal.model.UserTopScore;
import jimwu.itest.portal.service.IScoreService;
import jimwu.itest.portal.service.IUserCoinService;
import jimwu.itest.portal.service.IUserTopScoreService;
import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.vo.CoinVo;
import jimwu.itest.portal.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    @Resource
    UserMapper userMapper;

    @Resource
    ScoreMapper scoreMapper;

    @Resource
    IUserTopScoreService userTopScoreService;

    @Resource
    IUserCoinService userCoinService;

    @Override
    @Transactional
    public void saveScore(ScoreVo scoreVo) {
        if(scoreVo == null){
            throw ServiceException.unprocessableEntity("data invalid!");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname",scoreVo.getNickname());
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            throw ServiceException.notFound("nickname not found!");
        }
        //check high score and store before save score
        Boolean checkResult = checkTopScore(scoreVo);
        if(checkResult){
            userTopScoreService.saveTopScore(scoreVo);
            userCoinService.addCoin(scoreVo.getNickname(),UserCoin.BREAK_PERSONAL_BEST);
        }
        userCoinService.addCoin(scoreVo.getNickname(),UserCoin.PLAY_GAME_AWARD);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Score s = new Score()
                .setNickname(scoreVo.getNickname())
                .setScore(scoreVo.getScore())
                .setIcon(scoreVo.getIcon())
                .setDuration(scoreVo.getDuration())
                .setGameName(scoreVo.getGameName())
                .setPlayDate(now.format(formatter));
        //log.debug("new score data is {}",score);
        int row = scoreMapper.insert(s);
        if(row!=1){
            throw ServiceException.failed("server busy , try later");
        }

    }

    @Override
    public List<Score> getScore(String gameName) {
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("score");
        queryWrapper.eq("game_name",gameName);
        queryWrapper.last("limit 5");
        List<Score> scoreList = scoreMapper.selectList(queryWrapper);
        int i = 1;
        for(Score score:scoreList){
            score.setRanking(i);
            i++;
        }
        log.debug("scorelist is {}");
        return scoreList;
    }
    public boolean checkTopScore(ScoreVo scoreVo){
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("game_name",scoreVo.getGameName())
                .eq("nickname",scoreVo.getNickname())
                .orderByDesc("score")
                .last("limit 1");
        log.info("checkTopScore");
        //select db nickname/game_name highest score
        Score scoreRecord = scoreMapper.selectOne(queryWrapper);
        if(scoreRecord == null){
            log.info("no record exist!!");
            return true;
        }
        //if current play score > highest score => true
        return scoreVo.getScore() > scoreRecord.getScore();
    }
}
