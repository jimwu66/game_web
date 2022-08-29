package jimwu.itest.portal.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("user_icon")
public class UserIcon {

    @TableField("nickname")
    private String nickname;

    @TableField("product_name")
    private String productName;

    @TableField("product_title")
    private String productTitle;

    @TableField("key_value")
    private String keyValue;

}
