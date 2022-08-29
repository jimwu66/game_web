package jimwu.itest.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Validated
@Accessors(chain = true)
public class ScoreVo {

    @NotBlank( message=" nickname can not be blank!")
    private String nickname;

    @NotNull( message = " score can not be null !")
    private Integer score;

    @NotNull( message = " duration can not be null !")
    private Integer duration;

    @NotNull( message = " icon can not be null !")
    private String icon;

    @NotBlank( message=" gamename can not be blank!")
    private String gameName;

}
