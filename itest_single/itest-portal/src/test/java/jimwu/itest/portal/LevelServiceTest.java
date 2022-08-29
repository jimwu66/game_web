package jimwu.itest.portal;

import jimwu.itest.portal.model.Level;
import jimwu.itest.portal.service.impl.LevelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class LevelServiceTest {

    /*
    @Autowired
    private LevelServiceImpl levelService;

    @Test
    public void levelListTest(){
        Map<Integer , String> levelMap = levelService.getLevelMap();
        levelMap.forEach((key,value) ->{
            log.info("key is :" + key +",value is :"+ value);
        });
    }
    @Test
    void getLevelTest(){
        Level level = levelService.getUserLevel(1);
        log.debug("level is {}",level);
    }

     */
}
