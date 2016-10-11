package com.example.kirito.chinesedictionary.support;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by kirito on 2016.10.06.
 */

public class Http {
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/29.0.1547.66 Safari/537.36";
    private static final String TAG = "Http";

    public static String getDataFromUrl(String path){
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s = "";
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();
            if (connection != null){
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "getDataFromUrl: data---"+sb.toString() );
        return sb.toString();
    }

    public static String getData(String strUrl,Map parms,String method){
        HttpURLConnection con = null;
        StringBuilder sbg = null;
        String s = "";
        if (method == null || method.equals("GET")){
            strUrl = strUrl + "?" + urlEncode(parms);
        }
        try {
            URL url = new URL(strUrl);
            con  = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-agent",userAgent);
            con.setUseCaches(false);
            con.setConnectTimeout(8000);
            con.setReadTimeout(8000);
            con.setInstanceFollowRedirects(false);
            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String temp;
            sbg = new StringBuilder();
            while ((temp = br.readLine()) != null){
                sbg.append(temp + "/n");
            }
            br.close();
            s = sbg.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (con != null){
                con.disconnect();
            }
        }
        Log.e(TAG, "getData: data---"+s );
        return s;
    }

    public static String urlEncode(Map<String,Object> data){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i:data.entrySet()
                ) {
            try {
                //对查询的字进行编码
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //Log.e(TAG, "urlEncode: urlencode---"+sb.toString() );
        return sb.toString();
    }

}
