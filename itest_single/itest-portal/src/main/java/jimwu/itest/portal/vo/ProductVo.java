package jimwu.itest.portal.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductVo {

    @TableField("product_name")
    private String productName;

    @TableField("product_title")
    private String productTitle;
}
