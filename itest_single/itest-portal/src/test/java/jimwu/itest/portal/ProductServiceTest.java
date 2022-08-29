package jimwu.itest.portal;

import jimwu.itest.portal.model.Product;
import jimwu.itest.portal.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ProductServiceTest {

    /*
    @Autowired
    private IProductService productService;

    @Test
    void getTop3ProductTest(){
        List<Product> list = productService.getTop3Product();
        list.forEach(product -> log.debug("product is :{}",product));
    }

     */
}
