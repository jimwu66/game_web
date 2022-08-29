package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.ProductMapper;
import jimwu.itest.portal.model.Product;
import jimwu.itest.portal.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getTop3Product() {
        QueryWrapper<Product> query = new QueryWrapper<>();
        query.orderByAsc("recommendation");
        query.last("limit 3");
        List<Product> list = productMapper.selectList(query);
        return list;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> list = productMapper.getAllProduct();
        return list;
    }
}
