package jimwu.itest.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jimwu.itest.portal.model.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends BaseMapper<Product> {

    @Select("SELECT * FROM product;")
    List<Product> getAllProduct();
}
