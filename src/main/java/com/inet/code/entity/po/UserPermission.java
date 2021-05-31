package com.inet.code.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
@Data
@TableName("permission")
public class UserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String permission;


}
