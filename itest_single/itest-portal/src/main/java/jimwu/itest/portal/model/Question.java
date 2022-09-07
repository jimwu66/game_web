package jimwu.itest.portal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@TableName("question")
public class Question implements Serializable {

    public final static Integer PUBLISH = 0;
    public final static Integer REPLIED = 1;
    public final static Integer SOLVED = 2;

    @TableId(value ="id" , type = IdType.AUTO)
    private Integer id;

    @TableField("nickname")
    private String nickname;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("publish_time")
    private LocalDateTime publishTime;

    @TableField("tag_names")
    private String tagNames;

    @TableField("modify_time")
    private LocalDate modifyTime;

    @TableField("status")
    private Integer status;

    @TableField("delete_status")
    private Integer deleteStatus;

    @TableField("publish_status")
    private Integer publishStatus;

    @TableField("view")
    private Integer view;

    @TableField("icon")
    private String icon;

    @TableField(exist = false)
    private List<String> tags;

    @TableField(exist = false)
    private List<Comment> comments;
}
