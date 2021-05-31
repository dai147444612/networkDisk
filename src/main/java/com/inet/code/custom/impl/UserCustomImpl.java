package com.inet.code.custom.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inet.code.custom.UserCustom;
import com.inet.code.entity.dto.login;
import com.inet.code.entity.po.Login;
import com.inet.code.entity.po.NetworkDisk_Content;
import com.inet.code.entity.po.RecycleBin;
import com.inet.code.mapper.LoginMapper;
import com.inet.code.result.Result;
import com.inet.code.service.ILoginService;
import com.inet.code.service.INetworkDisk_ContentService;
import com.inet.code.service.IRecycleBinService;
import com.inet.code.util.GetPageUtil;
import com.inet.code.util.JwtUtils;
import com.inet.code.util.dUtil;
import com.inet.code.util.uploadUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserCustomImpl implements UserCustom {
    @Resource
    private ILoginService iLoginService;
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private IRecycleBinService iRecycleBinService;
    @Resource
    private INetworkDisk_ContentService iNetworkDisk_contentService;


    @Override
    public Result isLogin(login login,String path) {
        System.out.println(login);
        if (iLoginService.IsLogin(login.getUserName(),login.getPassword())){
            QueryWrapper q=new QueryWrapper();
            q.eq("userName",login.getUserName());
            q.eq("password",login.getPassword());
            Login login1 = loginMapper.selectOne(q);
            System.out.println(login1.getId());
            Map<String,String> map=new HashMap<>();
            map.put("userId", String.valueOf(login1.getId()));
            map.put("userName",login1.getUserName());

            String token= JwtUtils.getToken(map);
            redisTemplate.opsForValue().set(token,login1,7, TimeUnit.DAYS);

            Map<String,Object> result=new HashMap<>();
            result.put("info","登录成功");
            result.put("token",token);
            return new Result().result200(result,path);
        }
        return new Result().result200("账号或者密码错误",path);
    }

    @Override
    public Result upload(MultipartFile file, String folderName,String token, String path) {
        if (redisTemplate.opsForValue().get(token) == null) {
            return new Result().result404("未登录,无法上传图片", path);
        }
        String userId = JwtUtils.getString(token, "userId");
        uploadUtil up=new uploadUtil();
        Map<String,String> upload = up.upload(file);
        iNetworkDisk_contentService.addFile(upload.get("fileName"),upload.get("url"), Integer.parseInt(userId),upload.get("size"),folderName);
        return new Result().result200("文件上传成功",path);
    }

    @Override
    public Result showFile(String folderName, String token, String path) {
        if (redisTemplate.opsForValue().get(token) == null) {
            return new Result().result404("未登录,无法展示内容", path);
        }
        String userId = JwtUtils.getString(token, "userId");
        List<NetworkDisk_Content> networkDisk_contents = iNetworkDisk_contentService.showFile(Integer.parseInt(userId), folderName);
        return new Result().result200(networkDisk_contents,path);
    }

    @Override
    public Result getFile(String folderName,String fileName, String token, String path) {
        if (redisTemplate.opsForValue().get(token) == null) {
            return new Result().result404("未登录,未登录无法获取文件", path);
        }
        String userId = JwtUtils.getString(token, "userId");

        String url = iNetworkDisk_contentService.getFile(Integer.parseInt(userId),folderName,fileName);
        return new Result().result200(url,path);
    }

    @Override
    public Result downLouad(String url, HttpServletResponse response,String token, String path) {
        if (redisTemplate.opsForValue().get(token) == null) {
            return new Result().result404("未登录,无法下载", path);
        }
        dUtil d=new dUtil();
        try {
            d.downLoad(response,url);
            return new Result().result200("下载成功",path);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return new Result().result200("下载失败请检查路径",path
        );
    }

    @Override
    public Result deleteFile(String fileName,String token,String path) {
        if (redisTemplate.opsForValue().get(token) == null) {
            return new Result().result404("未登录,无法下载", path);
        }
        String userId = JwtUtils.getString(token, "userId");
        System.out.println(fileName);
        NetworkDisk_Content file = iNetworkDisk_contentService.getFileOne(userId,fileName);
        System.out.println(file);
        iRecycleBinService.addFile(file);
        iNetworkDisk_contentService.deleteFile(userId,fileName);

        return new Result().result200("删除成功",path);
    }

    @Override
    public Result showRecyleFile(String folderName, String token, String path) {
        if (redisTemplate.opsForValue().get(token) == null) {
            return new Result().result404("未登录,无法下载", path);
        }
        String userId = JwtUtils.getString(token, "userId");
        List<RecycleBin> recycleBins = iRecycleBinService.showRecyleFile(folderName, userId);
        return new Result().result200(recycleBins,path);
    }

    @Override
    public Result restoreFile(String fileName, String token, String path) {
        if (redisTemplate.opsForValue().get(token) == null) {
            return new Result().result404("未登录,无法下载", path);
        }
        String userId = JwtUtils.getString(token, "userId");
        System.out.println(fileName);
        RecycleBin file = iRecycleBinService.getFile(userId,fileName);
        System.out.println(file);
        iNetworkDisk_contentService.addFile1(file);
        iRecycleBinService.deleteFile(userId,fileName);
        return new Result().result200("还原成功",path);
    }

    @Override
    public Result getPage(String url, String token,String path) {
        if (redisTemplate.opsForValue().get(token) == null) {
            return new Result().result404("未登录,无法下载", path);
        }
        GetPageUtil gt=new GetPageUtil();
        String pageResource = gt.getPageResource(url);
        return new Result().result200(pageResource,path);
    }


}
