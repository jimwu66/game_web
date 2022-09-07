package jimwu.itest.portal.controller;

import jimwu.itest.portal.service.EmailSenderService;
import jimwu.itest.portal.service.IUserService;
import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.vo.R;
import jimwu.itest.portal.vo.RegisterVo;
import jimwu.itest.portal.vo.ResetVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
public class SystemController {

    @Autowired
    private IUserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/login.html")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/reset.html")
    public ModelAndView reset() {
        return new ModelAndView("reset");
    }

    @GetMapping("/forgot.html")
    public ModelAndView forgot() {
        return new ModelAndView("forgot");
    }

    @GetMapping("/index.html")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/oauth2.html")
    public ModelAndView oauth2() {
        return new ModelAndView("oauth2");
    }

    @GetMapping("/register.html")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @GetMapping("/tetris.html")
    public ModelAndView tetris() {
        return new ModelAndView("tetris");
    }

    @GetMapping("/snake.html")
    public ModelAndView snake() {
        return new ModelAndView("snake");
    }

    @GetMapping("/shop.html")
    public ModelAndView shop() {
        return new ModelAndView("shop");
    }

    @GetMapping("/question.html")
    public ModelAndView question() {
        return new ModelAndView("question");
    }

    @PostMapping("/register")
    public R register(@Validated RegisterVo registerVo, BindingResult result) {
        log.debug("receive registerVo!!!!");
        if (result.hasErrors()) {
            String error = result.getFieldError().getDefaultMessage();
            return R.unprocessableEntity(error);
        }
        try{
            userService.Register(registerVo);
        }catch (Exception e){
            return R.unprocessableEntity(e.getMessage());
        }
        return R.created("register ok!");
    }
    @PostMapping("/resetRequest")
    public R resetPwd(HttpServletRequest request,String email){
        if(email==null){
            return R.unprocessableEntity("email address can not be null!!");
        }
        try {
            String token = userService.getResetToken(email);
            String resetLink = request.getScheme() + "://" +
                    request.getServerName() + ":" + request.getServerPort() + "/reset.html?token=" + token;
            emailSenderService.sendEmail(email, resetLink);
        } catch (ServiceException e){
            return R.unprocessableEntity(e.getMessage());
        } catch (MessagingException e) {
            return R.unprocessableEntity(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return R.unprocessableEntity(e.getMessage());
        }
        log.debug("email is {}",email);
        return R.ok("An email has been sent.");
    }
    @PostMapping("/reset")
    public R resetPassword(@Validated ResetVo resetVo,BindingResult result){
        if(result.hasErrors()){
            String message = result.getFieldError().getDefaultMessage();
            return R.unprocessableEntity(message);
        }
        try{
            userService.resetPassword(resetVo);
        }catch(Exception e){
            return R.failed(e);
        }
        return R.ok("reset password successful");
    }
}


