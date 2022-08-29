package jimwu.itest.portal;

import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.service.impl.UserCoinServiceImpl;
import jimwu.itest.portal.vo.CoinVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserCoinServiceTest {

    /*
    @Autowired
    private UserCoinServiceImpl userCoinService;

    @Test
    void registerCoinTest(){
            userCoinService.registerCoin("onlytwo");
    }
    @Test
    void getCoinTest(){
            Integer coin = userCoinService.getCoinByNickname(null);
            log.debug("coin is :{}",coin);

    }
    @Test
    void addCoinTest(){
        Integer finalCoin = userCoinService.addCoin("wu jim",-35);
        log.debug("final coin is {}",finalCoin);
    }
    @Test
    void subCoinTest(){
        Integer finalCoin = userCoinService.subCoin("wu jim",20);
        log.debug("final coin is {}",finalCoin);
    }

     */
}
