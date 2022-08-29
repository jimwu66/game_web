package jimwu.itest.portal.vo;

import jimwu.itest.portal.model.UserIcon;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class UserVo implements Serializable {

    private String username;
    private String nickname;
    private String level;
    private String title;
    private Integer coin;
    private List<ProductVo> products;
    private Boolean dailyLogin;
}
