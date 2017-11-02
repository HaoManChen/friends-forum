package com.zjicm.friend.service.impl;

import com.zjicm.friend.config.MsgConstant;
import com.zjicm.friend.domain.SaveImgTable;
import com.zjicm.friend.domain.SaveMetadataTable;
import com.zjicm.friend.dto.MetadataSaveDTO;
import com.zjicm.friend.dto.MetadataSelectDTO;
import com.zjicm.friend.persistence.SaveImgDao;
import com.zjicm.friend.persistence.SaveMetadataDao;
import com.zjicm.friend.service.SaveMetadataService;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author chenhao
 */
@Service(value = "saveMetadata")
public class SaveMetadataImpl implements SaveMetadataService {
    @Autowired
    SaveMetadataDao saveMetadataDao;
    @Autowired
    SaveImgDao saveImgDao;

    @Override
    public JSONObject saveService(MetadataSaveDTO metadataSaveDTO) throws JSONException {
        String[] data = metadataSaveDTO.getData();
        String path = metadataSaveDTO.getPath();
        try{
            String nul = data[0];
        }catch (Exception e){
            return MsgConstant.getJsonMsg(MsgConstant.MSG_ERROR_INSIDE);
        }
        if (data.length==0){
            return MsgConstant.getJsonMsg(MsgConstant.MSG_NO_DATA);
        }
        SaveMetadataTable saveMetadataTable = new SaveMetadataTable();
        for (int i = 0;i<data.length;i++){
            //生成uuid
            String uuid =  UUID.randomUUID().toString().replaceAll("-", "");
            saveMetadataTable.setMetadataType(data[i]);
            saveMetadataTable.setMetadataName(data[++i]);
            saveMetadataTable.setImgUrl(path);
            saveMetadataTable.setId(uuid);
            saveMetadataDao.save(saveMetadataTable);
        }

        return MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
    }

    @Override
    public JSONObject saveImg(String path, String location) throws JSONException {
        JSONObject json = new JSONObject();
        // 将数据存入saveImg实体类
        SaveImgTable saveImgTable = new SaveImgTable();
        saveImgTable.setImgPath(path);
        saveImgTable.setLocation(location);
        //将数据存入数据库
        saveImgDao.save(saveImgTable);
        json = MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
        return json;
    }

    @Override
    public JSONObject saveZoomImg(String path, String location) throws JSONException {
        return null;
    }

    @Override
    public JSONObject getImgPathService() throws JSONException {
        JSONObject json = new JSONObject();
        List<SaveImgTable> saveImgTables = new ArrayList<>();
        saveImgTables = saveImgDao.findAll();
        json = MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
        json.put("data",saveImgTables);
        return json;
    }

    @Override
    public JSONObject selectByMetadataService(MetadataSelectDTO metadataSelectDTO) throws JSONException {
        JSONObject json = new JSONObject();
        if ("".equals(metadataSelectDTO.getName())){
            metadataSelectDTO.setName("qwertyjvcxzsdfghj");
        }
        String type = metadataSelectDTO.getType();
        String name = "%"+metadataSelectDTO.getName()+"%";
        List<SaveMetadataTable> saveMetadataTables = new ArrayList<>();
        saveMetadataTables = saveMetadataDao.findLikeTypeOrName(type,name);
        try{
            saveMetadataTables.get(0).getId();
        }catch (Exception e){
            return MsgConstant.getJsonMsg(MsgConstant.MSG_NO_DATA);
        }
        json = MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
        json.put("data",saveMetadataTables);
        return json;
    }

    @Override
    public JSONObject getMetadataTypeService() throws JSONException {
        JSONObject json = new JSONObject();
        List<SaveMetadataTable> saveMetadataTables  = new ArrayList<>();
        saveMetadataTables = saveMetadataDao.findAllType();
        json = MsgConstant.getJsonMsg(MsgConstant.MSG_SUCCESS);
        json.put("data",saveMetadataTables);
        return json;
    }


}
