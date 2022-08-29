package jimwu.itest.portal.controller;

import jimwu.itest.portal.model.Score;
import jimwu.itest.portal.service.IScoreService;
import jimwu.itest.portal.vo.R;
import jimwu.itest.portal.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/score")
public class ScoreController {

    @Autowired
    IScoreService scoreService;

    @GetMapping("/save")
    public R saveScore(@Validated ScoreVo scoreVo , BindingResult result){
        if(result.hasErrors()){
            String message = result.getFieldError().getDefaultMessage();
            return R.unprocessableEntity(message);
        }
        log.debug("scoreVo {}",scoreVo);
        scoreService.saveScore(scoreVo);
        return R.ok("score save ok!");
    }
    @GetMapping("/{gameName}")
    public R<List<Score>> getScore(@PathVariable String gameName){
        log.debug("received getScore request!");
        List<Score> scores = scoreService.getScore(gameName);
        log.debug("scores data is {}",scores);
        return R.ok(scores);
    }
}
