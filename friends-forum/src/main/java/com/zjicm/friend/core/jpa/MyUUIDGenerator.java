package com.zjicm.friend.core.jpa;

import com.zjicm.friend.util.MD5Util;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by ChenHao on 2017/08/19
 * UUID主键生成策略
 */
public class MyUUIDGenerator implements IdentifierGenerator , Configurable{

    public void configure(Type type, Properties params, Dialect d)
            throws MappingException {

    }
    /**
     * 判断实体主键是否已经有值，有值直接返回，无值自动生成
     */
    public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {
        String id =null;
        for (Field field : object.getClass().getDeclaredFields()) {
            if(field.getAnnotation(javax.persistence.Id.class)!=null){
                try {
                    field.setAccessible(true);
                    id =(String) field.get(object);
                    //id =(String) object.getClass().getMethod("getUserId").invoke(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        if(id==null){
            UUID uuid = UUID.randomUUID();
            id = Long.toHexString(uuid.getMostSignificantBits())
                    + Long.toHexString(uuid.getLeastSignificantBits());
        }
        return id;
    }

    public static String createUUID(){
        UUID uuid = UUID.randomUUID();
        String id = Long.toHexString(uuid.getMostSignificantBits())+ Long.toHexString(uuid.getLeastSignificantBits());
        return id;

    }

    public static String initUUID(String str){
        return MD5Util.EncoderByMd5(str);
    }

//	public Serializable generate(SessionImplementor session, Object object)
//			throws HibernateException {
//		String id = UUID.randomUUID().toString().toLowerCase();
//		return id;
//	}

    public static void main(String[] args){
        for (int i = 0; i < 200; i++) {
            System.out.println(createUUID());
        }
    }


    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {

    }
}
