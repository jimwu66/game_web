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


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    public static final int ENABLED = 1;
    public static final int DISABLE = 0;

    @TableId(value="id" , type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("nickname")
    private String nickname;

    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    @TableField("birthday")
    private LocalDate birthday;

    @TableField("enabled")
    private Integer enabled;

    @TableField("locked")
    private Integer locked;

    @TableField("reset_token")
    private String resetToken;

    @TableField("token_create_time")
    private long tokenCreateTime;

    @TableField("last_login_date")
    private LocalDate lastLoginDate;

    @TableField("title")
    private String title;

    @TableField("icon")
    private String icon;
}
