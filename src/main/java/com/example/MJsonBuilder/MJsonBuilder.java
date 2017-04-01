package com.example.MJsonBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * @Package: com.example.MJsonBuilder
 * @Description: 动态构建json
 * @author: liuxin
 * @date: 17/3/30 下午4:56
 */
public class MJsonBuilder {
    private final static Map<Object, Object> map = new HashedMap();
    private Gson GSON = new Gson();

    /**
     * 生成指定字段类型的json对象
     *
     * @param key
     * @param value
     * @param type
     * @return
     */
    public MJsonBuilder set(String key, Object value, Class<?> type) {
        map.put(key, type.cast(value));
        return this;
    }


    /**
     * 格式化
     * @return
     */
    public MJsonBuilder setPrettyPrint() {
        GSON = new GsonBuilder().setPrettyPrinting().create();
        return this;
    }

    public MJsonBuilder create() {
        return this;
    }

    public String toJson() {
        return GSON.toJson(map);
    }

    public String toJson(Object date) {
        return GSON.toJson(date);
    }
}
