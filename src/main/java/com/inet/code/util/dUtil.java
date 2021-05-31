package com.inet.code.util;

import com.inet.code.result.Result;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Objects;

public class dUtil {
    public void downLoad(HttpServletResponse response,String downloadUrl) throws Throwable {
        if (Objects.isNull(downloadUrl)) {
            // 如果接收参数为空则抛出异常，由全局异常处理类去处理。
            throw new NullPointerException("下载地址为空");
        }
        File packetFile = new File(downloadUrl);
        String fileName = packetFile.getName(); //下载的文件名
        File file = new File(downloadUrl);
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the song successfully!");
            } catch (Exception e) {
                System.out.println("Download the song failed!");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {//对应文件不存在
            try {
                //设置响应的数据类型是html文本，并且告知浏览器，使用UTF-8 来编码。
                response.setContentType("text/html;charset=UTF-8");

                //String这个类里面， getBytes()方法使用的码表，是UTF-8，  跟tomcat的默认码表没关系。 tomcat iso-8859-1
                String csn = Charset.defaultCharset().name();

                System.out.println("默认的String里面的getBytes方法使用的码表是： " + csn);

                //1. 指定浏览器看这份数据使用的码表
                response.setHeader("Content-Type", "text/html;charset=UTF-8");
                OutputStream os = response.getOutputStream();

                os.write("对应文件不存在".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
