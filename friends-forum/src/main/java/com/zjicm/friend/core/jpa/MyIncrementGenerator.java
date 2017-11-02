package com.zjicm.friend.core.jpa;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.IncrementGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;

public class MyIncrementGenerator extends IncrementGenerator implements IdentifierGenerator,Configurable {
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        Serializable id = null ;
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
        if (id == null){

            id = super.generate(session,object);
        }

        return id;
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {

    }
}
