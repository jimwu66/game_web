package jimwu.itest.portal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public class EncoderTests {

    /*
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void setPasswordEncoderTest(){
        String pass = passwordEncoder.encode("12345678");
        log.debug("password is {}",pass);
    }

     */
}
