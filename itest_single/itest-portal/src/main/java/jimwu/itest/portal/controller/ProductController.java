package jimwu.itest.portal.controller;

import jimwu.itest.portal.model.Product;
import jimwu.itest.portal.service.IProductService;
import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/shop")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/recommend")
    public R<List<Product>> getRecommendProducts(){
        List<Product> list = productService.getTop3Product();
        if(list==null){
         return R.notFound("no products!");
        }
        return R.ok(list);
    }
    @GetMapping("/all")
    public R<List<Product>> getAllProducts(){
        List<Product> list = productService.getAllProduct();
        if(list==null){
            return R.notFound("no products!");
        }
        return R.ok(list);
    }
}
