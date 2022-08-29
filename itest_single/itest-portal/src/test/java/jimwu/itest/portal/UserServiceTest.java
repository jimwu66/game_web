package jimwu.itest.portal;

import jimwu.itest.portal.mapper.UserMapper;
import jimwu.itest.portal.model.User;
import jimwu.itest.portal.service.IUserService;
import jimwu.itest.portal.service.impl.UserServiceImpl;
import jimwu.itest.portal.vo.RegisterVo;
import jimwu.itest.portal.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    /*
    @Resource
    private IUserService userService;

    @Resource
    private UserServiceImpl userServiceImpl;

    @Resource
    private UserMapper userMapper;

    @Test
    void getUserVoByUsernameTest(){
        UserVo userVo = userService.getUserVoByUsername("jimwu");
        log.debug("jimwu userVo is {}",userVo);
    }
    @Test
    void getResetTokenTest(){
        String email = "nine@gmail.co";
        String token = userService.getResetToken(email);
        log.debug("token is : {}",token);
    }
    @Test
    void checkRegisterTest(){
        RegisterVo registerVo = new RegisterVo()
                .setUsername("one1222")
                .setNickname("onlyone111")
                .setEmail("jimwu665@566.com")
                .setPassword("111111");
        userService.Register(registerVo);
    }

    @Test
    void dailyAwardTest(){
        userServiceImpl.dailyaward();
    }

    @Test
    void getUserVoSetDateTest(){
        User user = userServiceImpl.getUserByUsername("jimwu");
        String jimwuDate = user.getLastLoginDate().toString();
        System.out.println("jimwu login is : "+jimwuDate);
        System.out.println(LocalDate.now());
        String now = LocalDate.now().toString();
        System.out.println("now is :"+now);
        user.setLastLoginDate(LocalDate.now());
        userMapper.updateById(user);
    }
    */
}
