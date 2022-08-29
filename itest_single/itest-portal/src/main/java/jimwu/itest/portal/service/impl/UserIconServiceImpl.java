package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import jimwu.itest.portal.mapper.UserIconMapper;
import jimwu.itest.portal.model.UserIcon;
import jimwu.itest.portal.service.IUserIconService;
import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.utils.FieldValue;
import jimwu.itest.portal.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserIconServiceImpl extends ServiceImpl<UserIconMapper, UserIcon> implements IUserIconService {

    @Autowired
    private UserIconMapper userIconMapper;

    @Autowired
    private FieldValue fieldValue;

    @Override
    public Boolean inputUserIcon(String nickname, String productName,String productTitle) {
        String keyValue = nickname+"_"+productName;
        QueryWrapper<UserIcon> query = new QueryWrapper<>();
        query.eq("key_value",keyValue);
        int row = userIconMapper.selectCount(query);
        if(row==1){
            throw ServiceException.badRequest("You already has this product!");
        }
        UserIcon userIcon = new UserIcon()
                .setNickname(nickname)
                .setKeyValue(keyValue)
                .setProductName(productName)
                .setProductTitle(productTitle);
        row = userIconMapper.insert(userIcon);
        return row == 1;
    }

    @Override
    public List<String> getProductsByNickname(String nickname) {
        List<String> products = userIconMapper.getProductsByNickname(nickname);
        return products;
    }
    @Override
    public List<ProductVo> getProductListByNickname(String nickname){
        List<ProductVo> list = userIconMapper.getProductListByNickname(nickname);
        return list;
        /*
        Map<String,Object> productsMap = userIconMapper.getProductByNickname(nickname);
        HashMap<String,ProductVo> products = new HashMap<>();
        productsMap.forEach((k,v)->{
            ObjectMapper objectMapper = new ObjectMapper();
            ProductVo vo = objectMapper.convertValue(v,ProductVo.class);
            products.put(k,vo);
        });

        Map<String,String> product = new HashMap<>();
        products.forEach((k,v)->{
            product.put(k,v.getProductTitle());
        });
        return null;
        */
    }

}
