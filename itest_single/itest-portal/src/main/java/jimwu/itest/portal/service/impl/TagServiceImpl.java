package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.TagMapper;
import jimwu.itest.portal.model.Tag;
import jimwu.itest.portal.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getTags() {
        List<Tag> tags = tagMapper.getTags();
        return tags;
    }
}
