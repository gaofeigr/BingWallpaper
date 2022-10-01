package com.gaof.bingwallpaper.controllers;

import com.alibaba.fastjson2.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/bingWallpaper")
public class BingWallpaperController {

    private static final String baseUrl = "https://cn.bing.com";
    private static final String imageInterfaceUrl = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN";

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .readTimeout(8000, TimeUnit.MILLISECONDS)
            .build();

    @GetMapping("getImageUrl")
    public String getImageUrl() throws Exception {
        String result = "";
        Request.Builder builder = new Request.Builder().url(imageInterfaceUrl).get();
        Response response = okHttpClient.newCall(builder.build()).execute();
        String imageBody = response.body().string();
        JSONObject imageBodyJson = JSONObject.parseObject(imageBody);
        result = imageBodyJson.getJSONArray("images").getJSONObject(0).getString("url");
        result = result.replaceAll("1920x1080", "UHD");
        result = baseUrl + result;
        return result;
    }

    @GetMapping("getFileName")
    public String getFileName() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + ".jpg";
    }
}
