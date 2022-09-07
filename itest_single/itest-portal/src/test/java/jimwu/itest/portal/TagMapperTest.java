package jimwu.itest.portal;

import jimwu.itest.portal.mapper.TagMapper;
import jimwu.itest.portal.model.Tag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TagMapperTest {

    /*
    @Autowired
    private TagMapper tagMapper;

    @Test
    void tagTest(){
        List<Tag> tags = tagMapper.getTags();
        tags.forEach(t->log.debug("tag is {}",t));
    }

     */

}
