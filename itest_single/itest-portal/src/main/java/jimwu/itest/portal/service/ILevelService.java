package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.Level;

import java.util.List;
import java.util.Map;

public interface ILevelService extends IService<Level> {

    Map<Integer , String> getLevelMap();

    Level getUserLevel(Integer ranking);
}
