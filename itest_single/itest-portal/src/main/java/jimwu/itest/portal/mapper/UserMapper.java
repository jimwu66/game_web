package jimwu.itest.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jimwu.itest.portal.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Update("UPDATE user SET last_login_date=#{last_login_date} WHERE username=#{username}")
    void updateLoginDate(@Param("username") String username,
                         @Param("last_login_date") LocalDate loginDate);

    @Select("SELECT * FROM user WHERE nickname=#{nickname}")
    User getUserByNickname(String nickname);
}
