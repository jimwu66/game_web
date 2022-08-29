package jimwu.itest.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class RegisterVo implements Serializable {

    @NotBlank(message = "username is empty!!")
    @Pattern(regexp = "^.{2,30}$",message="username format:2-30 char")
    private String username;

    @NotBlank(message = "nickname is empty!!")
    @Pattern(regexp = "^.{2,20}$",message="nickname format:2-20 char")
    private String nickname;

    @NotBlank(message = "email is empty!!")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank(message = "date is empty!!")
    private String birthday;

    @NotBlank(message = "password cannot be null")
    @Pattern(regexp = "^\\w{6,20}$",message = "password format:6-20 letter/number/_")
    private String password;
}
