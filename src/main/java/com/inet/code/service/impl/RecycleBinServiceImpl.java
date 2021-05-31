package com.inet.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inet.code.entity.po.NetworkDisk_Content;
import com.inet.code.entity.po.RecycleBin;
import com.inet.code.mapper.RecycleBinMapper;
import com.inet.code.service.IRecycleBinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author drh
 * @since 2021-04-24
 */
@Service
public class RecycleBinServiceImpl extends ServiceImpl<RecycleBinMapper, RecycleBin> implements IRecycleBinService {
    @Resource
    private RecycleBinMapper recycleBinMapper;

    @Override
    public void addFile(NetworkDisk_Content file) {
        RecycleBin rb=new RecycleBin();
        rb.setFileName(file.getFileName());
        rb.setId(file.getId());
        rb.setDeleteTime(new Date());
        rb.setFolderName(file.getFolderName());
        rb.setUrl(file.getUrl());
        rb.setMemory(file.getMemory());
        recycleBinMapper.insert(rb);
    }

    @Override
    public List<RecycleBin> showRecyleFile(String folderName, String userId) {
        QueryWrapper q=new QueryWrapper();
        q.eq("id",userId);
        q.eq("folderName",folderName);
       return recycleBinMapper.selectList(q);
    }

    @Override
    public void deleteFile(String id,String fileName) {
        QueryWrapper q=new QueryWrapper();
        q.eq("id",id);
        q.eq("fileName",fileName);
        recycleBinMapper.delete(q);
    }

    @Override
    public RecycleBin getFile(String id,String fileName) {
        QueryWrapper q=new QueryWrapper();
        q.eq("id",id);
        q.eq("fileName",fileName);
        return recycleBinMapper.selectOne(q);
    }
}
