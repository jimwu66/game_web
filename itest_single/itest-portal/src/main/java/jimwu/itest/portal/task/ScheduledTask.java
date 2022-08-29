package jimwu.itest.portal.task;

import jimwu.itest.portal.model.UserCoin;
import jimwu.itest.portal.service.IUserCoinService;
import jimwu.itest.portal.service.IUserTopScoreService;
import jimwu.itest.portal.vo.CoinVo;
import jimwu.itest.portal.vo.TopScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    private IUserCoinService coinService;

    @Autowired
    private IUserTopScoreService scoreService;

    @Scheduled(cron = "0 0 0 * * *")
    public void dailyRankingAward(){
        List<TopScoreVo> top3 = scoreService.getEachGameTop3Nickname();
        top3.forEach(t -> {
            coinService.addCoin(t.getNickname(),UserCoin.awardMap.get(t.getRanking()));
        });
    }
}
