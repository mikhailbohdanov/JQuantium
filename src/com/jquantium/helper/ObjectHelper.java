package com.jquantium.helper;

import javax.servlet.ServletRequest;
import java.lang.reflect.Field;

/**
 * Created by Mykhailo_Bohdanov on 06/08/2015.
 */
public class ObjectHelper {


//
//    public static <T> T getFromRequest(Class<T> _class, ServletRequest request) {
//        Object object;
//
//        try {
//            object = _class.newInstance();
//        } catch (Exception e){
//            return null;
//        }
//
//        return getFromRequest((T) object, request);
//    }
//    public static <T> T getFromRequest(T obj, ServletRequest request) {
//        if (obj == null || request == null)
//            return null;
//
//        Class _class    = obj.getClass();
//        Field[] fields  = _class.getDeclaredFields();
//
//        Row row;
//        String value;
//        Class __class;
//        for (Field field : fields) {
//            row = field.getDeclaredAnnotation(Row.class);
//
//            if (row == null)
//                continue;
//
//            if ((value = request.getParameter(row.name())) != null) {
//                field.setAccessible(true);
//
//                try {
//                    __class = field.getType();
//
//                    if (classes.containsKey(__class)) {
//                        field.set(
//                                obj,
//                                classes.get(__class).getConstructor(String.class).newInstance(value)
//                        );
//                    } else
//                        field.set(obj, field.getType().cast(value));
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return obj;
//    }


}
