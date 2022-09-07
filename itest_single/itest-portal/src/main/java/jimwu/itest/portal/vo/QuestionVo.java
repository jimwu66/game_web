package jimwu.itest.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Validated
@Accessors(chain = true)
public class QuestionVo implements Serializable {

    @NotBlank(message="no title!")
    private String title;

    @NotBlank(message="no nickname!")
    private String nickname;

    @NotBlank(message="no icon!")
    private String icon;

    @NotBlank(message="no selected tag!")
    private String selectedTags;

    @NotBlank(message="no content!")
    private String content;
}
