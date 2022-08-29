package jimwu.itest.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CoinVo implements Serializable {

    String nickname;
    Integer coin;
}
