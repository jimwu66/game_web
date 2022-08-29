package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.LevelMapper;
import jimwu.itest.portal.model.Level;
import jimwu.itest.portal.model.UserTopScore;
import jimwu.itest.portal.service.ILevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class LevelServiceImpl extends ServiceImpl<LevelMapper, Level> implements ILevelService {

    @Autowired
    private LevelMapper levelMapper;

    @Autowired
    private UserTopScoreServiceImpl userTopScoreService;

    private final Map<Integer , String> levelMap = new ConcurrentHashMap<>();

    @Override
    public Map<Integer, String> getLevelMap() {
        if (levelMap.isEmpty()) {
            synchronized (levelMap) {
                if (levelMap.isEmpty()) {
                    QueryWrapper<Level> queryWrapper = new QueryWrapper<>();
                    queryWrapper.orderByDesc("level");
                    List<Level> levelList = levelMapper.selectList(queryWrapper);
                    levelList.forEach(level -> levelMap.put(level.getLevel(), level.getTitle()));
                    return levelMap;
                }
            }
        }
        return levelMap;
    }

    @Override
    public Level getUserLevel(Integer ranking) {
        Map<Integer,String> levelMap = getLevelMap();
        String title;
        Integer lv;
        if(ranking >4){
            title = levelMap.get(Level.LEVEL5);
            lv = Level.LEVEL5;
            return new Level().setLevel(lv).setTitle(title);
        }
        title = levelMap.get(ranking);
        lv = ranking;
        Level level = new Level().setLevel(lv).setTitle(title);
        return level;
    }
}
