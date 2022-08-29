package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.UserCoinMapper;
import jimwu.itest.portal.model.User;
import jimwu.itest.portal.model.UserCoin;
import jimwu.itest.portal.service.IUserCoinService;
import jimwu.itest.portal.service.IUserService;
import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.vo.CoinVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserCoinServiceImpl extends ServiceImpl<UserCoinMapper, UserCoin> implements IUserCoinService {

    @Autowired
    private UserCoinMapper userCoinMapper;
    
    @Transactional
    protected Integer getCoinByNicknameImpl(String nickname) {
        if(nickname==null){
            throw ServiceException.notFound("nickname not found!");
        }
        log.debug("getCoinByNicknameImpl {}",nickname);
        QueryWrapper<UserCoin> query = new QueryWrapper<>();
        query.eq("nickname",nickname);
        Integer coin = userCoinMapper.getCoinFromNickname(nickname);
        return coin;
    }
    protected synchronized Integer getCoinByNicknameSync(String nickname) {
        log.debug("getCoinByNicknameSync {}",nickname);
        Integer coin = getCoinByNicknameImpl(nickname);
        return coin;
    }
    @Override
    public Integer getCoinByNickname(String nickname) {
        log.debug("getCoinByNickname {}",nickname);
        Integer coin = getCoinByNicknameSync(nickname);
        return coin;
    }
    @Transactional
    protected void registerCoinImpl(String nickname) throws ServiceException{
        if(nickname==null){
            throw ServiceException.notFound("nickname not found!");
        }
        UserCoin userCoin = new UserCoin()
                .setNickname(nickname)
                .setCoin(UserCoin.REGISTER_AWARD);
        int row = userCoinMapper.insert(userCoin);
        if(row!=1){
            throw ServiceException.failed("server busy , try later");
        }
    }
    protected synchronized void registerCoinSync(String nickname) {
        registerCoinImpl(nickname);
    }
    @Override
    public void registerCoin(String nickname) {
        registerCoinSync(nickname);
    }

    @Transactional
    protected Integer addCoinImpl(String nickname,Integer coin) throws ServiceException{
        if(nickname==null){
            throw ServiceException.notFound("nickname not found!");
        }
        if(coin==null){
            throw ServiceException.notFound("no coin data!");
        }
        QueryWrapper<UserCoin> query = new QueryWrapper<>();
        query.eq("nickname",nickname);
        UserCoin userCoin = userCoinMapper.selectOne(query);
        if(userCoin==null){
            UserCoin userCoinNew = new UserCoin().setCoin(coin).setNickname(nickname);
	    int row = userCoinMapper.insert(userCoinNew);
	    if(row!=1){
 	        throw ServiceException.failed("server busy , try later");
	      }
	      return coin;
        }
        Integer currentCoin = userCoin.getCoin();
        Integer finalCoin = currentCoin+coin;
        int row = userCoinMapper.updateCoin(finalCoin, nickname);
        if(row!=1){
            throw ServiceException.failed("server busy , try later");
        }
        return finalCoin;

    }
    protected synchronized Integer addCoinSync(String nickname,Integer coin){
        Integer finalCoin = addCoinImpl(nickname,coin);
        return finalCoin;
    }
    @Override
    public Integer addCoin(String nickname,Integer coin) {
        Integer finalCoin = addCoinSync(nickname,coin);
        return finalCoin;
    }
    @Transactional
    protected Integer subCoinImpl(String nickname,Integer coin) throws ServiceException{
        if(nickname==null){
            throw ServiceException.notFound("nickname not found!");
        }
        if(coin==null){
            throw ServiceException.notFound("no coin data!");
        }
        QueryWrapper<UserCoin> query = new QueryWrapper<>();
        query.eq("nickname",nickname);
        UserCoin userCoin = userCoinMapper.selectOne(query);
        if(userCoin==null){
            throw ServiceException.notFound("nickname not found!");
        }
        Integer currentCoin = userCoin.getCoin();
        if(currentCoin<coin){
            throw ServiceException.unprocessableEntity("Insufficient coin!");
        }
        Integer finalCoin = currentCoin-coin;
        int row = userCoinMapper.updateCoin(finalCoin, nickname);
        if(row!=1){
            throw ServiceException.failed("server busy , try later");
        }
        return finalCoin;

    }
    protected synchronized Integer subCoinSync(String nickname,Integer coin){
        Integer finalCoin = subCoinImpl(nickname,coin);
        return finalCoin;
    }
    @Override
    public Integer subCoin(String nickname,Integer coin) {
        Integer finalCoin = subCoinSync(nickname,coin);
        return finalCoin;
    }
}
