package com.inet.code.service;

import com.inet.code.entity.po.NetworkDisk_Content;
import com.baomidou.mybatisplus.extension.service.IService;
import com.inet.code.entity.po.RecycleBin;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
public interface INetworkDisk_ContentService extends IService<NetworkDisk_Content> {
    public void addFile(String fileName,String url,int id,String size,String folderName);

    List<NetworkDisk_Content> showFile(int userId, String folderName);

    String getFile(int userId, String folderName, String fileName);

    void deleteFile(String id,String url);
    NetworkDisk_Content getFileOne(String id,String fileName);

    void addFile1(RecycleBin file);
}
