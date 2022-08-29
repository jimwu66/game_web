package jimwu.itest.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jimwu.itest.portal.model.UserTopScore;
import jimwu.itest.portal.vo.RankingVo;
import jimwu.itest.portal.vo.TopScoreVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTopScoreMapper extends BaseMapper<UserTopScore> {

    @Select("SELECT RANK () OVER ( " +
            " PARTITION BY game_name ORDER BY score DESC) " +
            " ranking,key_value AS 'key_name'" +
            " FROM" +
            " user_top_score;")
    List<RankingVo> getRankingVoList();

    @Insert("INSERT INTO user_top_score(nickname, game_name,key_value,score) " +
            "VALUES(#{nickname},#{game_name},#{key_value},#{score})" +
            "ON DUPLICATE KEY UPDATE score = VALUES(score); ")
    Integer saveTopScore(@Param("nickname") String nickname,
                         @Param("game_name") String gameName,
                         @Param("key_value") String keyValue,
                         @Param("score") Integer score);

    @Select("SELECT ranking,nickname,game_name,score FROM " +
            "(" +
            "SELECT *,dense_rank() over(PARTITION BY game_name ORDER BY score DESC) " +
            "as ranking FROM user_top_score" +
            ")T WHERE T.ranking<=3")
    List<TopScoreVo> getEachGameTop3Nickname();
}
