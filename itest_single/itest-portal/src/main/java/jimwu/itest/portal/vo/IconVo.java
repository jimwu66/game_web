package jimwu.itest.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class IconVo {

    @NotBlank( message=" product can not be blank!")
    private String productName;

    @NotBlank( message=" product can not be blank!")
    private String productTitle;

    @NotNull( message=" no price data!")
    private Integer price;

    @NotBlank( message=" nickname can not be blank!")
    private String nickname;
}
