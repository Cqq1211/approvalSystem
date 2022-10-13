package com.student.cq.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.time.LocalDateTime;

/**
 * 视图工作类
 */
public class ViewUtils {

    /**
     * 返回JSON数据
     * @param message
     * @param value
     * @param state
     * @return
     */
    public static String view(String message, Object value, int state) {
        JSONObject object = new JSONObject();
        object.put("state", state);
        if (message != null) {
            object.put("message", message);
        }
        if (value != null) {
            object.put("value", JSON.toJSON(value));
        }
        object.put("timestamp", LocalDateTime.now());
        return object.toString(SerializerFeature.WriteMapNullValue);
    }

}
