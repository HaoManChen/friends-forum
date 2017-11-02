package com.zjicm.friend.web;

import com.zjicm.friend.dto.MetadataSaveDTO;
import com.zjicm.friend.dto.MetadataSelectDTO;
import com.zjicm.friend.service.SaveMetadataService;
import com.zjicm.friend.util.ImageUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

@RestController
public class SchoolWorkSaveController {
    @Autowired
    SaveMetadataService saveMetadataService;

    @RequestMapping("saveMetadata")
    public JSONObject saveMetadata(MetadataSaveDTO metadataSaveDTO) throws JSONException {
        String[] date = metadataSaveDTO.getData();
//        System.err.println(date[0]);
        JSONObject json = new JSONObject();
        json = saveMetadataService.saveService(metadataSaveDTO);
        return json;
    }

    @RequestMapping("upload")
    public void upload(@RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(value = "location")String location, HttpServletResponse response)throws JSONException,IOException,Exception{
        JSONObject json = new JSONObject();
        String picName = file.getOriginalFilename();
        String path="/Users/haoman/Documents/pic/"+new Date().getTime()+picName;
        String pathSmall = "/Users/haoman/Documents/picSmall/"+new Date().getTime()+picName;
//        String path="/usr/local/pic/"+new Date().getTime()+picName;
//        String pathSmall = "/usr/local/picSmall/"+new Date().getTime()+picName;
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        // 将图片信息存入数据库
        json = saveMetadataService.saveImg(path,location);
        //返回图片预览
        File fileOut=new File(path);
        ImageUtil.zoomImage(fileOut,pathSmall);
        fileOut = new File(pathSmall);
        InputStream is=new FileInputStream(fileOut);
        BufferedImage bi=ImageIO.read(is);
        BufferedImage image = bi;
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);

    }
    @RequestMapping("getImg")
    public void  getImg(String path,HttpServletResponse response) throws IOException{
        //返回图片预览
        try {

            File fileOut = new File(path);
            InputStream is = new FileInputStream(fileOut);
            BufferedImage bi = ImageIO.read(is);
            BufferedImage image = bi;
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        }catch (IOException e){
            System.err.println("No such file");
        }
    }
    @RequestMapping("getImgPath")
    public JSONObject getImgPath() throws JSONException{
        JSONObject json = new JSONObject();
        json = saveMetadataService.getImgPathService();
        return json;
    }
    @RequestMapping("selectByMetadata")
    public JSONObject selectByMetadata(MetadataSelectDTO metadataSelectDTO){
        JSONObject json = new JSONObject();
        json = saveMetadataService.selectByMetadataService(metadataSelectDTO);
        return json;
    }

    @RequestMapping("getMetadataType")
    public JSONObject getMetadataType()throws JSONException{
        JSONObject json = new JSONObject();
        json = saveMetadataService.getMetadataTypeService();
        return json;
    }

}
