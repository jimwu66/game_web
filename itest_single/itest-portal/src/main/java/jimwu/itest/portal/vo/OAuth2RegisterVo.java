package jimwu.itest.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OAuth2RegisterVo implements Serializable {

    private String username;
    private String nickname;
    private String email;
}
