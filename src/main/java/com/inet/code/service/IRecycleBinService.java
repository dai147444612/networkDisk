package com.inet.code.service;

import com.inet.code.entity.po.NetworkDisk_Content;
import com.inet.code.entity.po.RecycleBin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
public interface IRecycleBinService extends IService<RecycleBin> {

    void addFile(NetworkDisk_Content file);

    List<RecycleBin> showRecyleFile(String folderName, String userId);

    void deleteFile(String id,String fileName);

    RecycleBin getFile(String id,String fileName);
}
