package com.suneee.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/jingdong")
public class RestTempleteController {

    private String param ="?token={token}&app_key={app_key}&v={v}&timestamp={timestamp}&sign={sign}&format={format}&jd_param_json={jd_param_json}";
    private  String url = "https://u.ifeige.cn/api/message/send";

    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/newOrder")
    @ResponseBody
    public  String getResponse(HttpServletRequest httpServletRequest){
        String result = "";
        Map<String, String> stringStringMap = parseHttpRequest(httpServletRequest);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url+param, String.class, stringStringMap);
        if(forEntity.getBody() != null){
            result =forEntity.getBody();
        }
        return result;

    }


    private Map<String, String> parseHttpRequest(HttpServletRequest request) {
        String token = request.getParameter("token");
        String app_key = request.getParameter("app_key");
        String timestamp = request.getParameter("timestamp");
        String sign = request.getParameter("sign");
        String format = request.getParameter("format");
        String v = request.getParameter("v");
        String jd_param_json = request.getParameter("jd_param_json");
        Map parameter = new HashMap();
        parameter.put("token", token);
        parameter.put("app_key", app_key);
        parameter.put("format", format);
        parameter.put("v", v);
        parameter.put("sign", sign);
        parameter.put("timestamp", timestamp);
        parameter.put("jd_param_json", jd_param_json);
        return parameter;
    }

    @RequestMapping("/feige")
    @ResponseBody
    public  String feige(){
        String result ="";
        String title ="nihao";
        String content ="nihao";
        String remark ="nihao";

        Map<String,String> param = new HashMap<>();
        param.put("secret","0d57cee56bebd70eb73334aeb78f1c02");
        param.put("app_key","47c37c6af56ed374cf334903c149b1e4");
        param.put("template_id","5uZIvSm5GAdUR1X25HNpjuOp6jSiL88v4opn4a4GLa0");
        param.put("data",jsonData(title,content,remark));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        /*HttpEntity<String> entity = new HttpEntity<>(request, httpHeaders);*/
        HttpEntity<Map<String, String>> request = new HttpEntity<>(param, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, request, String.class);
        if(stringResponseEntity.getBody()!=null){
            result=stringResponseEntity.getBody();
        }
        return  result;
    }


    public static String jsonData(String title,String content,String remark){
        JSONObject titleJson = new JSONObject();
        titleJson.put("value", title);
        titleJson.put("color", "#173177");
        JSONObject contentJson = new JSONObject();
        contentJson.put("value", content);
        contentJson.put("color", "#173177");
        JSONObject topicJson = new JSONObject();
        topicJson.put("value", "WTC EPortal 平台自动巡检");
        topicJson.put("color", "#173177");
        JSONObject notifyTimeJson = new JSONObject();
        notifyTimeJson.put("value", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        notifyTimeJson.put("color", "#173177");
        JSONObject remarkJson = new JSONObject();
        remarkJson.put("value", remark);
        remarkJson.put("color", "#173177");
        JSONObject data = new JSONObject();
        data.put("first", titleJson);
        data.put("keyword1", contentJson);
        data.put("keyword2", topicJson);
        data.put("keyword3", notifyTimeJson);
        data.put("remark", remarkJson);
        return data.toJSONString();
    }
}
