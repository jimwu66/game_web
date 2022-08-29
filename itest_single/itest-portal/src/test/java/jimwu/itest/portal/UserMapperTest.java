package jimwu.itest.portal;

import jimwu.itest.portal.mapper.UserMapper;
import jimwu.itest.portal.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class UserMapperTest {

    /*
    @Resource
    UserMapper userMapper;

    @Test
    void userMapperTest(){
        User user = userMapper.selectById(1);
        log.debug("user {}",user);
    }
    @Test
    void updateLoginTest(){
        userMapper.updateLoginDate("one", LocalDate.now());
    }

    @Test
    void getUserByNicknameTest(){
        User user = userMapper.getUserByNickname("onlyone");
        log.debug("user is {}",user);
    }

     */
}
