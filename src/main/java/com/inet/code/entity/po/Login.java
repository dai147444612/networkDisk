package com.inet.code.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * <p>
 * 
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
@TableName("login")
@Data
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.UUID)
    private Integer id;
    @TableField(value = "userName")
    private String userName;
    @TableField(value = "password")
    private String password;


}
