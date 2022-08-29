package jimwu.itest.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jimwu.itest.portal.model.UserCoin;
import jimwu.itest.portal.vo.CoinVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCoinMapper extends BaseMapper<UserCoin> {

    @Select("SELECT coin FROM user_coin WHERE nickname=#{nickname}")
    Integer getCoinFromNickname(String nickname);

    @Update("UPDATE user_coin SET coin =#{coin} WHERE nickname=#{nickname}")
    Integer updateCoin(@Param("coin") Integer coin,
                       @Param("nickname") String nickname);

}
