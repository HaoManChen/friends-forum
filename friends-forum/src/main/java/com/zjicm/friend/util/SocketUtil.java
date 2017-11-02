package com.zjicm.friend.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SocketUtil {
    /**
     * 发送请求
     * @param httpsUrl
     *            请求的地址
     * @param requestStr
     *            请求的数据
     */
    public static String httpsPost(String httpsUrl, String requestStr,String accept,String contentType,String authorization ) {
        HttpsURLConnection urlCon = null;
        String out ="";
        try {
            urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();
            urlCon.setDoInput(true);
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod("POST");
            urlCon.setRequestProperty("Accept",accept);
            urlCon.setRequestProperty("Content-Type",contentType);
            urlCon.setRequestProperty("Authorization",authorization );
            urlCon.setRequestProperty("Content-Length",
                    String.valueOf(requestStr.getBytes().length));
            urlCon.setUseCaches(false);
            urlCon.getOutputStream().write(requestStr.getBytes("utf8"));
            urlCon.getOutputStream().flush();
            urlCon.getOutputStream().close();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlCon.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                out = out+line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
