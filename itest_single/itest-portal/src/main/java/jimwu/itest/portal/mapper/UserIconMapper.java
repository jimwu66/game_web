package jimwu.itest.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jimwu.itest.portal.model.UserIcon;
import jimwu.itest.portal.vo.ProductVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserIconMapper extends BaseMapper<UserIcon> {

    @Select("SELECT product_name FROM user_icon WHERE nickname=#{nickname}")
    List<String> getProductsByNickname(String nickname);


    //@MapKey("product_name")
    //Map<String, Object> getProductByNickname(String nickname);

    @Select("SELECT product_name,product_title FROM user_icon WHERE nickname=#{nickname}")
    List<ProductVo> getProductListByNickname(String nickname);
}
