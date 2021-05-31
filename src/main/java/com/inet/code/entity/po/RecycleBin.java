package com.inet.code.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
@TableName("recycleBin")
@Data
public class RecycleBin implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    @TableField(value = "folderName")
    private String folderName;
    @TableField(value = "fileName")
    private String fileName;
    @TableField(value = "url")
    private String url;
    @TableField(value = "deleteTime")
    private Date deleteTime;
    @TableField(value = "memory")
    private String memory;
}

