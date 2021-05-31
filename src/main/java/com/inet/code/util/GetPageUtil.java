package com.inet.code.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetPageUtil {
    public String getPageResource(String pageURL){

        StringBuffer sbBuffer = new StringBuffer();
        try {
            URL url = new URL(pageURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String lineString;
            while((lineString=in.readLine())!=null)
            {
                sbBuffer.append(lineString);
                sbBuffer.append("\n");
            }
            in.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return sbBuffer.toString();

    }

}
