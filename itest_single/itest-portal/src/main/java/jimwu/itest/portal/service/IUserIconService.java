package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.UserIcon;
import jimwu.itest.portal.vo.ProductVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUserIconService extends IService<UserIcon> {

    Boolean inputUserIcon(String nickname,String productName,String productTitle);

    List<String> getProductsByNickname(String nickname);

    List<ProductVo> getProductListByNickname(String nickname);
}
