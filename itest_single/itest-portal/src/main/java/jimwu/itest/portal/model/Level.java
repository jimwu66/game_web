package jimwu.itest.portal.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("level")
public class Level implements Serializable {

    public final static Integer LEVEL5 = 5;
    public final static Integer LEVEL4 = 4;
    public final static Integer LEVEL3 = 3;
    public final static Integer LEVEL2 = 2;
    public final static Integer LEVEL1 = 1;
    public final static String[] GAME = {"Tetris","Snake"};

    @TableField("level")
    private Integer level;

    @TableField("title")
    private String title;
}
