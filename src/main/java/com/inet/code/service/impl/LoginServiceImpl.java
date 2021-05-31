package com.inet.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inet.code.entity.po.Login;
import com.inet.code.mapper.LoginMapper;
import com.inet.code.service.ILoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {
    @Resource
    private LoginMapper loginMapper;

    @Override
    public Boolean IsLogin(String username, String password) {
        QueryWrapper<Login> q=new QueryWrapper();
        q.eq("userName",username);
        q.eq("password",password);
        loginMapper.selectList(q);
        return true;
    }
}
