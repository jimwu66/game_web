package jimwu.itest.portal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("comment")
public class Comment implements Serializable {

    public final static Integer WAITING = 0;
    public final static Integer ACCEPT = 1;

    @TableId( value="id" , type = IdType.AUTO)
    private Integer id;

    @TableField("nickname")
    private String nickname;

    @TableField("question_id")
    private Integer questionId;

    @TableField("content")
    private String content;

    @TableField("publish_time")
    private LocalDateTime publishTime;

    @TableField("modify_time")
    private LocalDateTime modifyTime;

    @TableField("status")
    private Integer status;

    @TableField("delete_status")
    private Integer deleteStatus;

    @TableField("icon")
    private String icon;
}
