package com.inet.code.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inet.code.entity.po.NetworkDisk_Content;
import com.inet.code.entity.po.RecycleBin;
import com.inet.code.mapper.NetworkDisk_ContentMapper;
import com.inet.code.service.INetworkDisk_ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
@Service
public class NetworkDisk_ContentServiceImpl extends ServiceImpl<NetworkDisk_ContentMapper, NetworkDisk_Content> implements INetworkDisk_ContentService {
    @Resource
    private NetworkDisk_ContentMapper networkDisk_contentMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public void addFile(String fileName, String url,int id,String size,String folderName) {
        redisTemplate.opsForValue().get("userId");

        NetworkDisk_Content networkDisk_content=new NetworkDisk_Content();
        networkDisk_content.setFolderName(folderName);
        networkDisk_content.setFileName(fileName);
        networkDisk_content.setCreatData(new Date());
        networkDisk_content.setUrl(url);
        networkDisk_content.setId(id);
        networkDisk_content.setMemory(size);
        networkDisk_contentMapper.insert(networkDisk_content);
    }

    @Override
    public List<NetworkDisk_Content> showFile(int userId, String folderName) {
        QueryWrapper q=new QueryWrapper();
        q.eq("id",userId);
        q.eq("folderName",folderName);
        return networkDisk_contentMapper.selectList(q);
    }

    @Override
    public String getFile(int userId, String folderName, String fileName) {
        QueryWrapper q=new QueryWrapper();
        q.eq("id",userId);
        q.eq("fileName",fileName);
        q.eq("folderName",folderName);
        return networkDisk_contentMapper.selectOne(q).getUrl();
    }

    @Override
    public void deleteFile(String id,String fileName) {
        QueryWrapper q=new QueryWrapper();
        q.eq("id",id);
        q.eq("fileName",fileName);
        networkDisk_contentMapper.delete(q);
    }

    @Override
    public NetworkDisk_Content getFileOne(String id,String fileName) {
        QueryWrapper q=new QueryWrapper();
        q.eq("id",id);
        q.eq("fileName",fileName);
        return networkDisk_contentMapper.selectOne(q);
    }

    @Override
    public void addFile1(RecycleBin file) {
        NetworkDisk_Content networkDisk_content=new NetworkDisk_Content();
        networkDisk_content.setCreatData(new Date());
        networkDisk_content.setId(file.getId());
        networkDisk_content.setUrl(file.getUrl());
        networkDisk_content.setMemory(file.getMemory());
        networkDisk_content.setFolderName(file.getFolderName());
        networkDisk_content.setFileName(file.getFileName());
        networkDisk_contentMapper.insert(networkDisk_content);
    }
}
