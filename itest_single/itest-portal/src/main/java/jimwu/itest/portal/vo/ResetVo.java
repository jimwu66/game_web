package jimwu.itest.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResetVo implements Serializable {

    @NotNull( message = " token can not be null !")
    private String token ;

    @NotNull( message = " password can not be null !")
    private String password;

}
