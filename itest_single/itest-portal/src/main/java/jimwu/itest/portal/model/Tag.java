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
@TableName("tag")
public class Tag implements Serializable {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;
}
