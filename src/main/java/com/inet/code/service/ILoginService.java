package com.inet.code.service;

import com.inet.code.entity.po.Login;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
public interface ILoginService extends IService<Login> {
    public Boolean IsLogin(String username,String password);
}
