package jimwu.itest.portal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("product")
public class Product implements Serializable {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    @TableField("product_name")
    private String productName;

    @TableField("price")
    private Integer price;

    @TableField("description")
    private String description;

    @TableField("recommendation")
    private Integer recommendation;

    @TableField("sell_count")
    private Integer sellCount;

    @TableField("product_title")
    private String productTitle;
}
