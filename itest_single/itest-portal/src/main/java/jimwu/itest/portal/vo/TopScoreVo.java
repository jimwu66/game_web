package jimwu.itest.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class TopScoreVo implements Serializable {

    private Integer ranking;
    private String nickname;
    private Integer score;
    private String game_name;
}
