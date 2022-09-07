package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.Tag;

import java.util.List;

public interface ITagService extends IService<Tag> {

    List<Tag> getTags();
}
