package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.QuestionTagMapper;
import jimwu.itest.portal.model.QuestionTag;
import jimwu.itest.portal.service.IQestionTagService;
import org.springframework.stereotype.Service;

@Service
public class QuestionTagServiceImpl extends ServiceImpl<QuestionTagMapper, QuestionTag> implements IQestionTagService {
}
