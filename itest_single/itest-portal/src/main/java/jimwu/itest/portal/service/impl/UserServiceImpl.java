package jimwu.itest.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimwu.itest.portal.mapper.UserMapper;
import jimwu.itest.portal.model.Level;
import jimwu.itest.portal.model.User;
import jimwu.itest.portal.model.UserCoin;
import jimwu.itest.portal.service.*;
import jimwu.itest.portal.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTopScoreServiceImpl userTopScoreService;

    @Autowired
    private ILevelService levelService;

    @Autowired
    private IUserCoinService userCoinService;

    @Autowired
    private IUserIconService userIconService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String getResetToken(String email) throws ServiceException{
        User user = getUserByEmail(email);
        log.debug("user is {}",user);
        if(user == null){
            return "email not found!";
        }
        String token = RandomStringUtils.randomAlphabetic(45);
        long createTime = System.currentTimeMillis();
        user.setResetToken(token).setTokenCreateTime(createTime);
        userMapper.updateById(user);
        return token;
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException{
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("email",email);
        User user = userMapper.selectOne(query);
        if(user==null){
            throw ServiceException.notFound("email not found!");
        }
        return user;
    }

    @Override
    @Transactional
    public UserVo getUserVoByUsername(String username) {
        if(username==null){
            throw ServiceException.unprocessableEntity("user data invalid");
        }
        User user = getUserByUsername(username);
        if(user==null){
            throw ServiceException.unprocessableEntity("username not found!");
        }
        String nickname=user.getNickname();
        Boolean dailyLogin = dailyLoginCheck(user);
        if(dailyLogin){
            userCoinService.addCoin(nickname,UserCoin.DAILY_LOGIN_AWARD);
        }
        user.setLastLoginDate(LocalDate.now());
        userMapper.updateById(user);
        Level level = getLevelByNickname(nickname);
        List<ProductVo> products = getProductsList(nickname,level);
        Integer coin = userCoinService.getCoinByNickname(nickname);
        UserVo userVo = new UserVo()
                .setUsername(nickname)
                .setNickname(user.getNickname())
                //2022-08-07 modify to string to let index.html user dropdown selector wording better
                .setLevel(user.getIcon()==null?"Ranking"+level.getLevel().toString():user.getIcon())
                .setTitle(user.getTitle()==null?level.getTitle():user.getTitle())
                .setProducts(products)
                .setDailyLogin(dailyLogin)
                .setCoin(coin);
        return userVo;
    }
    @Override
    public User getUserByUsername(String username) {
        if(username==null){
            throw ServiceException.badRequest("user data invalid");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            throw ServiceException.notFound("username not found!");
        }
        return user;
    }
    @Override
    public UserDetails getUserDetails(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            throw ServiceException.unprocessableEntity("user data invalid!");
        }
        UserDetails u = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .disabled(user.getEnabled()==0)
                .accountLocked(user.getLocked()==1)
                .roles("USER")
                .build();
        return u;
    }
    @Override
    @Transactional
    public void Register(RegisterVo registerVo) throws ServiceException{
        checkRegister(registerVo.getUsername(),registerVo.getNickname(),registerVo.getEmail());
        String pwd = passwordEncoder.encode(registerVo.getPassword());
        User user = new User()
                .setUsername(registerVo.getUsername())
                .setNickname(registerVo.getNickname())
                .setBirthday(LocalDate.parse(registerVo.getBirthday()))
                .setEmail(registerVo.getEmail())
                .setEnabled(User.ENABLED)
                .setLastLoginDate(LocalDate.now())
                .setLocked(0)
                .setPassword(pwd);
        int rows = userMapper.insert(user);
        if(rows != 1){
            throw ServiceException.unprocessableEntity("server busy , try later!");
        }
        userCoinService.registerCoin(registerVo.getNickname());
        log.debug("register ok");
    }
    @Override
    public void OAuth2Register(OAuth2RegisterVo registerVo) {
        checkRegister(registerVo.getUsername(),registerVo.getNickname(),registerVo.getEmail());
        User user = new User()
                .setUsername(registerVo.getUsername())
                .setNickname(registerVo.getNickname())
                .setEmail(registerVo.getEmail())
                .setEnabled(User.ENABLED)
                //OAuth2 don't need password but mysql pwd not null so put username again
                .setLastLoginDate(LocalDate.now())
                .setPassword(registerVo.getUsername())
                .setLocked(0);
        int rows = userMapper.insert(user);
        if(rows != 1){
            throw ServiceException.unprocessableEntity("server busy , try later!");
        }
        userCoinService.registerCoin(registerVo.getNickname());
        log.debug("register ok");
    }
    @Override
    public Boolean checkUserExisted(String username) {
        if(username==null){
            throw ServiceException.unprocessableEntity("username is null!");
        }
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username",username);
        log.info("selectCount start");
        int row = userMapper.selectCount(query);
        log.debug("selectCount = {}",row);
        return row == 1;
    }
    @Override
    public Boolean resetPassword(ResetVo resetVo) {
        User user = getUserByToken(resetVo.getToken());
        long currentTime = System.currentTimeMillis();
        //token >10min invalid
        log.debug("token duration is {}",currentTime-user.getTokenCreateTime());
        if(currentTime-user.getTokenCreateTime()>1000*60*10){
            throw ServiceException.badRequest("request time out (>10min),please try again");
        }
        String password = passwordEncoder.encode(resetVo.getPassword());
        user.setPassword(password);
        user.setResetToken("");
        int row = userMapper.updateById(user);
        if(row!=1){
            throw ServiceException.failed("server busy , please try later!");
        }
        return row == 1;
    }
    public User getUserByToken(String token){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("reset_token",token);
        User user = userMapper.selectOne(query);
        if(user == null){
            throw ServiceException.notFound("user token not found!");
        }
        return user;
    }
    protected void checkRegister(String username,String nickname,String email) throws ServiceException{
        QueryWrapper<User> queryUsername = new QueryWrapper<>();
        queryUsername.eq("username",username);
        int rows = userMapper.selectCount(queryUsername);
        if(rows == 1){
            throw ServiceException.unprocessableEntity("username already use!");
        }
        QueryWrapper<User> queryNickname = new QueryWrapper<>();
        queryNickname.eq("nickname",nickname);
        rows = userMapper.selectCount(queryNickname);
        if(rows == 1){
            throw ServiceException.unprocessableEntity("nickname already use!");
        }
        QueryWrapper<User> queryMail = new QueryWrapper<>();
        queryMail.eq("email",email);
        rows = userMapper.selectCount(queryMail);
        if(rows == 1){
            throw ServiceException.unprocessableEntity("email already use!");
        }
    }
    protected Boolean dailyLoginCheck(User user){
        String lastTime = user.getLastLoginDate().toString();
        String nowTime = LocalDate.now().toString();
        if(!nowTime.equals(lastTime)){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public PurchaseVo buyProduct(String productName,String productTile, Integer price, String nickname) {
        Integer coin = userCoinService.subCoin(nickname,price);
        Boolean result = userIconService.inputUserIcon(nickname,productName,productTile);
        if(!result){
            throw ServiceException.failed("server busy , try later");
        }
        List<ProductVo> products =getProductsList(nickname,getLevelByNickname(nickname));
        PurchaseVo purchaseVo = new PurchaseVo().setCoin(coin).setProducts(products);
        return purchaseVo;
    }
    protected Level getLevelByNickname(String nickname){
        Integer ranking = userTopScoreService.getRankingByNickname(nickname);
        Level level = levelService.getUserLevel(ranking);
        return level;
    }
    protected List<ProductVo> getProductsList(String nickname , Level level){
        List<ProductVo> list = userIconService.getProductListByNickname(nickname);
        ProductVo productVo = new ProductVo()
                .setProductName("Ranking"+level.getLevel().toString())
                .setProductTitle(level.getTitle());
        list.add(productVo);
        return list;
    }
    @Override
    public void changeIcon(String nickname, String productName,String productTitle) {
        User user = userMapper.getUserByNickname(nickname);
        user.setIcon(productName).setTitle(productTitle);
        int row = userMapper.updateById(user);
        if(row!=1){
            throw ServiceException.failed("server busy , try later");
        }
    }
}
