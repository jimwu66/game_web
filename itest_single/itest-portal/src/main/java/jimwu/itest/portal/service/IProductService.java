package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.Product;

import java.util.List;

public interface IProductService extends IService<Product> {

    List<Product> getTop3Product();

    List<Product> getAllProduct();
}
