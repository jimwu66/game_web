package jimwu.itest.portal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("user_coin")
public class UserCoin {

    public static final int REGISTER_AWARD = 100;
    public static final int DAILY_1ST = 20;
    public static final int DAILY_2ND = 10;
    public static final int DAILY_3RD = 5;
    public static final int BREAK_PERSONAL_BEST = 5;

    public static final int PLAY_GAME_AWARD =1;
    public static final int DAILY_LOGIN_AWARD = 10;
    public static final int FIRST= 1;
    public static final int SECOND= 2;
    public static final int THIRD= 3;

    public static final Map<Integer, Integer> awardMap;
    static {
        awardMap = new HashMap<>();
        awardMap.put(FIRST, DAILY_1ST);
        awardMap.put(SECOND, DAILY_2ND);
        awardMap.put(THIRD, DAILY_3RD);
    }

    @TableId(value="id" , type = IdType.AUTO)
    private Integer id;

    @TableField(value="nickname")
    private String nickname;

    @TableField(value="coin")
    private Integer coin;
}
