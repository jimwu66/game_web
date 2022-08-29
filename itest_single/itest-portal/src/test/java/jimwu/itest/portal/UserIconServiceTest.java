package jimwu.itest.portal;

import jimwu.itest.portal.service.impl.UserIconServiceImpl;
import jimwu.itest.portal.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class UserIconServiceTest {

    /*
    @Autowired
    private UserIconServiceImpl userIconService;

    @Test
    void insertIconTest(){
        Boolean result = userIconService.inputUserIcon("one","cowboy","cowwww");
        log.debug("result is {}",result);
    }
    /*
    @Test
    void getMapByNickname(){
        Map<String,String> products = userIconService.getProductMapByNickname("onlyone");
        products.forEach((k,v)->{
            System.out.println("key is:"+k+", value is:"+v);
        });
    }
     */
}
