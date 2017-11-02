package com.zjicm.friend.web;

import com.zjicm.friend.config.MsgConstant;
import com.zjicm.friend.dto.AdminLoginDTO;
import com.zjicm.friend.service.AdminLoginService;
import com.zjicm.friend.util.AuthCodeImageUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 管理员登陆控制类
 */
@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @RequestMapping("/authCodeImg")
    public void authCodeImg(HttpServletResponse response, HttpSession session) throws IOException{
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = AuthCodeImageUtil.createImage();
        //将验证码存入Session
        session.setAttribute("authCodeImg",objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request, AdminLoginDTO adminLoginDTO)throws JSONException{
        JSONObject json = new JSONObject();
        String authCodeImgSession = (String)request.getSession().getAttribute("authCodeImg");

        if (authCodeImgSession==null||"".equals(authCodeImgSession)||!authCodeImgSession.toUpperCase().equals(adminLoginDTO.getAuthCode().toUpperCase())){
            json =  MsgConstant.getJsonMsg(MsgConstant.MSG_ERROR_AUTH_CODE);
        }else{
            json = adminLoginService.adminLogin(adminLoginDTO);
        }
        return json.toString();
    }
}
