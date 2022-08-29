package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.User;
import jimwu.itest.portal.vo.*;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService extends IService<User> {

    UserDetails getUserDetails(String username);

    void Register(RegisterVo registerVo);

    void OAuth2Register(OAuth2RegisterVo registerVo);

    User getUserByUsername(String username);

    UserVo getUserVoByUsername(String username);

    Boolean checkUserExisted(String username);

    User getUserByEmail(String email);

    String getResetToken(String email);

    Boolean resetPassword(ResetVo resetVo);

    PurchaseVo buyProduct(String productName, String productTitle, Integer price, String nickname);

    void changeIcon(String nickname,String productName,String productTitle);
}
