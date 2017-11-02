package com.zjicm.friend.service;

import com.zjicm.friend.dto.MetadataSaveDTO;
import com.zjicm.friend.dto.MetadataSelectDTO;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public interface SaveMetadataService {
    //保存元数据
    JSONObject saveService(MetadataSaveDTO metadataSaveDTO) throws JSONException;
    // 保存图片至图片库
    JSONObject saveImg(String path,String user)throws JSONException;
    //保存12张缩略图
    JSONObject saveZoomImg(String path,String location)throws JSONException;
    //获取该用户的所存图片
    JSONObject getImgPathService()throws JSONException;
    //获取可能的图片
    JSONObject selectByMetadataService(MetadataSelectDTO metadataSelectDTO)throws JSONException;
    //获取存在的元数据类型
    JSONObject getMetadataTypeService()throws JSONException;

}
