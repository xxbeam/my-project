package com.xxbeam.controller;

import com.xxbeam.vo.ResultVO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @RequestMapping("/sendMsg")
    public ResultVO sendMsg(@RequestBody String message){
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8080/sendMsg";
        okhttp3.RequestBody body = okhttp3.RequestBody.create(MediaType.get("application/json; charset=utf-8"),message);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVO.ok();
    }

}
