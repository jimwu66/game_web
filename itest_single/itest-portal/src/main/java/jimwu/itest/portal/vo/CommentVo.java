package jimwu.itest.portal.vo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Validated
@Accessors( chain = true)
public class CommentVo implements Serializable {

    @NotBlank(message="no nickname!")
    private String nickname;

    @NotNull(message="no question id!")
    private Integer questionId;

    @NotBlank(message="no content!")
    private String content;

    @NotNull(message="no post status!")
    private Boolean postStatus;

    @NotBlank(message="no icon name!" )
    private String icon;


}
