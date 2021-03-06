package utf8.citicup.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utf8.citicup.domain.entity.Option;
import utf8.citicup.domain.entity.ResponseMsg;
import utf8.citicup.domain.entity.User;
import utf8.citicup.service.RecommendService;
import utf8.citicup.service.UserService;

import java.util.Map;

import static utf8.citicup.service.util.JsonParse.objectToAnyType;

@RestController
@RequestMapping(value = "recommend", method = RequestMethod.POST)
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private UserService userService;

    @PostMapping("recommendPortfolio")
    public ResponseMsg recommendPortfolio(@RequestBody Map<String, Object> params) {
        User user = userService.getUser(SecurityUtils.getSubject().getPrincipal().toString());
        return recommendService.recommendPortfolio(Double.parseDouble((String) params.get("m0")), Double.parseDouble((String) params.get("k")),
                params.get("t").toString(), ((String) params.get("combination")).charAt(0),
                Double.parseDouble(params.get("p1").toString()), Double.parseDouble(params.get("p2").toString()), Double.parseDouble(params.get("sigma1").toString()),
                Double.parseDouble(params.get("sigma2").toString()), user.getW1(), user.getW2());
    }

    @PostMapping("hedging")
    public ResponseMsg hedging(@RequestBody Map<String, Object> params) {
        return recommendService.hedging(Integer.parseInt(params.get("n0").toString()), Double.parseDouble(params.get("a").toString()),
                Double.parseDouble(params.get("s_exp").toString()), params.get("t").toString());
    }

    @PostMapping("customPortfolio")
    @ResponseBody
    public ResponseMsg customPortfolio(@RequestBody Map<String, Object> params) {
        Option[] options = objectToAnyType(params.get("options"), Option[].class);
        return recommendService.customPortfolio(options);
    }
}
