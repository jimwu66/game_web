package jimwu.itest.portal.controller;

import jimwu.itest.portal.model.User;
import jimwu.itest.portal.security.UserDetailsServiceImpl;
import jimwu.itest.portal.service.IUserService;
import jimwu.itest.portal.vo.OAuth2RegisterVo;
import jimwu.itest.portal.vo.PurchaseVo;
import jimwu.itest.portal.vo.R;
import jimwu.itest.portal.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequestMapping("/v1/users")
public class UserController {

    //@Autowired
    //UserDetailsServiceImpl userDetailsService;

    @Autowired
    IUserService userService;

    @PostMapping("/me")
    public R getUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                         @AuthenticationPrincipal OAuth2User oAuth2User){
        if(oAuth2User==null) {
            String username = userDetails.getUsername();
            log.debug("user from web : {}",username);
            UserVo userVo = userService.getUserVoByUsername(username);
            return R.ok(userVo);
        }
        String oauth2Name = oAuth2User.getName();
        log.debug("user from oauth2 : {}",oauth2Name);
        UserVo oauth2Vo = userService.getUserVoByUsername(oauth2Name);
        return R.ok(oauth2Vo);
    }
    @PostMapping("/google")
    public R getGoogleUser(@AuthenticationPrincipal OAuth2User oAuth2User){
        String username = oAuth2User.getName();
        if(userService.checkUserExisted(username)){
            log.debug("oauth2 username exist!");
            return R.ok("user already exist , auto redirect to home page...");
        }
        String email=oAuth2User.getAttribute("email");
        String nickname = oAuth2User.getAttribute("name");
        OAuth2RegisterVo registerVo = new OAuth2RegisterVo()
                .setEmail(email).setNickname(nickname).setUsername(username);
        try{
            userService.OAuth2Register(registerVo);
        }catch(Exception e){
            return R.failed(e);
        }
        log.debug("oauth2 user register finish! auto redirect to home page...");
        return R.ok("User register ok!");
    }
    @GetMapping("/buy")
    public R buyProduct(String productName,String productTitle,Integer price,String nickname){
        if(productName==null||price==null||nickname==null||productTitle==null){
            return R.unprocessableEntity("data invalid!");
        }
        try {
            PurchaseVo purchaseVo = userService.buyProduct(productName,productTitle, price, nickname);
            return R.ok(purchaseVo);
        }catch (Exception e){
            return R.unprocessableEntity(e.getMessage());
        }
    }
    @GetMapping("/icon")
    public R changeIcon(String nickname,String productName,String productTitle){
        if(nickname==null||productName==null||productTitle==null){
            return R.unprocessableEntity("data invalid!");
        }
        userService.changeIcon(nickname,productName,productTitle);
        return R.ok("change icon ok");
    }
}
