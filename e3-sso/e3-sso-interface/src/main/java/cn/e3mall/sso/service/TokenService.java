package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

/**
 * 根据 token查询用户信息
 * @author hoverkan
 * @create 2018-10-13 10:43
 */
public interface TokenService {

    E3Result getUserByToken(String token);
}
