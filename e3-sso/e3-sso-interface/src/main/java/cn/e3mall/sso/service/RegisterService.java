package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbUser;

/**
 * @author hoverkan
 * @create 2018-10-12 17:31
 */
public interface RegisterService {
    E3Result checkData(String param, int type);
    E3Result register(TbUser user);
}
