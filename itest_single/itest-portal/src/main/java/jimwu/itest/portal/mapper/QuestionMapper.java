package jimwu.itest.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jimwu.itest.portal.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    @Update("UPDATE question SET status=#{status} WHERE id = #{questionId}")
    Integer updateStatus(@Param("status") Integer status,
                         @Param("questionId") Integer questionId);

}
