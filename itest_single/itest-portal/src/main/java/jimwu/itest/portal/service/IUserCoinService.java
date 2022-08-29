package jimwu.itest.portal.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import jimwu.itest.portal.model.UserCoin;
import jimwu.itest.portal.vo.CoinVo;

public interface IUserCoinService extends IService<UserCoin> {

    Integer getCoinByNickname(String nickname);

    Integer addCoin(String nickname,Integer coin);

    Integer subCoin(String nickname,Integer coin);

    void registerCoin(String nickname);

}
