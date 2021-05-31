package com.inet.code.controller;

import com.inet.code.custom.UserCustom;

import com.inet.code.entity.dto.login;
import com.inet.code.entity.po.Login;
import com.inet.code.mapper.PermissionMapper;
import com.inet.code.result.Result;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"通用用户模块"})
public class UserController {
    @Resource
    private UserCustom userCustom;
    @Resource
    private PermissionMapper permissionMapper;
    @ApiOperation("用户登录,不需要token")
    @PostMapping("/login")
    public Result getlogin(String username,String password){
        login login=new login();
        login.setUserName(username);
        login.setPassword(password);
        System.out.println(username);
        return userCustom.isLogin(login,"/login");
    }

    @ApiOperation("上传文件,需要token")
    @PostMapping("/upload")
    public Result upload(@RequestBody MultipartFile file,String folderName,@RequestHeader String token){
        return userCustom.upload(file,folderName,token,"/upload");
    }

    @ApiOperation("在文件夹中显示文件，需要token")
    @GetMapping("/showFile")
    @RequiresPermissions(value = {"dai"})
    public Result showFile(@RequestParam String folderName,@RequestHeader String token){
        return userCustom.showFile(folderName,token,"/showFile");
    }

    @ApiOperation("单个文件展示，需要token")
    @GetMapping("/getFile")
    public Result getFile(@RequestParam String folderName,String fileName, @RequestHeader String token){
        return userCustom.getFile(folderName,fileName,token,"/getFile");
    }

    @ApiOperation("文件下载，需要token")
    @GetMapping("/downLoad")
    public Result downLouad(HttpServletResponse response, @RequestParam String url,@RequestHeader String token){
        return userCustom.downLouad(url,response,token,"/downLoad");
    }

    @ApiOperation("删除文件，需要token")
    @GetMapping("/deleteFile")
    public Result deleteFile(@RequestParam String fileName,@RequestHeader String token){
        return userCustom.deleteFile(fileName,token,"/deleteFile");
    }

    @ApiOperation("展示回收站内容，需要token")
    @GetMapping("/showRecyleFile")
    public Result showRecyleFile(@RequestParam String folderName,@RequestHeader String token){
        return userCustom.showRecyleFile(folderName,token,"/showRecyleFile");
    }

    @ApiOperation("还原文件，需要token")
    @GetMapping("/restoreFile")
    public Result restoreFile(@RequestParam String fileName,@RequestHeader String token){
        return userCustom.restoreFile(fileName,token,"/restoreFile");
    }

    @ApiOperation("上传网页地址,获取网页的源码,需要token")
    @GetMapping("/getPage")
    public Result getPage(@RequestParam String url,@RequestHeader String token){
        return userCustom.getPage(url,token,"/getPage");
    }



    @Value("${posyspath}")
    private String poSysPath;
    @Value("${popassword}")
    private String poPassWord;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        com.zhuozhengsoft.pageoffice.poserver.Server poserver = new com.zhuozhengsoft.pageoffice.poserver.Server();
        //设置PageOffice注册成功后,license.lic文件存放的目录
        poserver.setSysPath(poSysPath);
        ServletRegistrationBean srb = new ServletRegistrationBean(poserver);
        srb.addUrlMappings("/poserver.zz");
        srb.addUrlMappings("/posetup.exe");
        srb.addUrlMappings("/pageoffice.js");
        srb.addUrlMappings("/jquery.min.js");
        srb.addUrlMappings("/pobstyle.css");
        srb.addUrlMappings("/sealsetup.exe");
        return srb;
    }


    //获取并编辑word

    @RequestMapping(value="/word", method=RequestMethod.GET)
    public ModelAndView showWord(HttpServletRequest request, Map<String,Object> map){
        String filePath = (String) request.getParameter("FilePath");
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序servlet
        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl.setSaveFilePage("/save");//设置保存的action
//        poCtrl.webOpen("test.doc",OpenModeType.docAdmin,"张三");
        poCtrl.webOpen(filePath, OpenModeType.docAdmin,"张三");
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //--- PageOffice的调用代码 结束 -----
        ModelAndView mv = new ModelAndView("Word");
        return mv;
    }

    //获取并编辑excel
    @GetMapping("excel")
    public ModelAndView showExcel(HttpServletRequest request, Map<String, Object> map) {
        String filePath = (String) request.getParameter("FilePath");
        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        // 设置服务页面
        poCtrl.setServerPage("/poserver.zz");
        // 添加自定义保存按钮
        poCtrl.addCustomToolButton("保存", "Save", 1);
        poCtrl.setSaveFilePage("/save");//设置保存的action1
        // 打开word
//        poCtrl.webOpen("D://test.xls", OpenModeType.xlsNormalEdit, "张三");
        poCtrl.webOpen(filePath, OpenModeType.xlsNormalEdit, "张三");
        map.put("pageoffice", poCtrl.getHtmlCode("PageOfficeCtrl1"));

        ModelAndView mv = new ModelAndView("Excel");
        return mv;
    }

    //保存
    @RequestMapping("/save")
    public void saveFile(HttpServletRequest request, HttpServletResponse response){
        String folderPath = (String) request.getAttribute("FolderPath");
        FileSaver fs = new FileSaver(request, response);
//        fs.saveToFile("D://" + fs.getFileName());
        fs.saveToFile(folderPath + fs.getFileName());
        fs.close();
    }


}
