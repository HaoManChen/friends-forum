package com.zjicm.friend.web;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ChenHao on 2017/10/24
 * 登陆权限后台判断拦截控制器
 */
@RestController
@RequestMapping("/session")
public class SessionJudgeController {
    /**
     * @param request
     * @return session是否过时
     */
    @RequestMapping("/initMenu")
    public JSONObject loginJudge(HttpServletRequest request){
        JSONObject json = new JSONObject();
        json.put("errCode","000000");
        json.put("errMessage","OK");
        return json;
    }
}
