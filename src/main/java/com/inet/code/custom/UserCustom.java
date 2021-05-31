package com.inet.code.custom;


import com.inet.code.entity.dto.login;
import com.inet.code.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserCustom {
    public Result isLogin(login login,String path);
    public Result upload(MultipartFile file,String folderName,String token,String path);

    Result showFile(String folderName, String token, String path);

    Result getFile(String folderName,String fileName, String token, String path);

    Result downLouad(String url, HttpServletResponse response, String token, String path);

    Result deleteFile(String fileName,String token,String path);

    Result showRecyleFile(String folderName, String token, String path);

    Result restoreFile(String fileName, String token, String path);

    Result getPage(String url,String token,String path);
}
