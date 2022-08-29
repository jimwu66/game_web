package jimwu.itest.portal;

import jimwu.itest.portal.mapper.UserIconMapper;
import jimwu.itest.portal.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class UserIconMapperTest {

    /*
    @Autowired
    private UserIconMapper userIconMapper;

    @Test
    void getProductsByNicknameTest(){
        List<String> list = userIconMapper.getProductsByNickname("onlyone");
        list.forEach(t-> log.debug("product is {}",t));
    }

    @Test
    void getProductListByNicknameTest(){
        List<ProductVo> list = userIconMapper.getProductListByNickname("onlyone");
        list.forEach(object-> log.debug("product is {}",object));
    }

     */
}
