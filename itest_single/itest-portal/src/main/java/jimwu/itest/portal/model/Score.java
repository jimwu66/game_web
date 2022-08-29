package jimwu.itest.portal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("score")
public class Score implements Serializable {

    @TableId(value="id" , type = IdType.AUTO)
    private Integer id;

    @TableField("nickname")
    private String nickname;

    @TableField("game_name")
    private String gameName;

    @TableField("play_date")
    private String playDate;

    @TableField("duration")
    private Integer duration;

    @TableField("score")
    private Integer score;

    @TableField(exist = false)
    private Integer ranking;

    @TableField("icon")
    private String icon;
}
