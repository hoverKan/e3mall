package cn.e3mall.sso.controller;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 根据token查询用户信息Controller
 *
 * @author hoverkan
 * @create 2018-10-13 11:06
 */
@Controller
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/user/token/{token}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE /*"application/json;charset=UTF-8"*/)
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback){
        E3Result result = tokenService.getUserByToken(token);
        // 响应结果之前，判断是否为jsonp请求
        if (StringUtils.isNotBlank(callback)){
            // 把结果封装成一个js响应
            // return callback + "(" + JsonUtils.objectToJson(result) + ");";
            // Spring4.1以后的
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;

        }
        return result;
    }
}
