package jimwu.itest.portal.controller;

import jimwu.itest.portal.service.IUserCoinService;
import jimwu.itest.portal.vo.R;
import jimwu.itest.portal.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1/coin")
public class CoinController {

    @Autowired
    private IUserCoinService userCoinService;

    @GetMapping("/{nickname}")
    public R getCoin(@PathVariable String nickname){
        if(nickname==null) {
            return R.notFound("nickname not found!");
        }
        Integer coin = userCoinService.getCoinByNickname(nickname);
        return R.ok(coin);
    }
}
