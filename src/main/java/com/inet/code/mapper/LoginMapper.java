package com.inet.code.mapper;

import com.inet.code.entity.po.Login;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
@Mapper
public interface LoginMapper extends BaseMapper<Login> {
    public Login selectByid(int id);
}
