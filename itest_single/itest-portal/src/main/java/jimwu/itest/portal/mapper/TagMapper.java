package jimwu.itest.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jimwu.itest.portal.model.Tag;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    @Select("SELECT id,name FROM tag;")
    public List<Tag> getTags();
}
