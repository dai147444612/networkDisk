package com.inet.code.service.impl;


import com.inet.code.entity.po.UserPermission;
import com.inet.code.mapper.PermissionMapper;
import com.inet.code.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, UserPermission> implements IPermissionService {

}
