package com.example.cache;
import java.util.Map;

/**
 * @Package: com.example.cache
 * @Description: 实现接口进行抽象实现
 * @author: liuxin
 * @date: 17/3/13 下午3:46
 */
public abstract class AbstractStore implements Store{

    protected Map<Object, Element> map;

    public AbstractStore(){}

    public AbstractStore(Map<Object, Element> map){
        this.map = map;
    }

    @Override
    public Element get(Object key) {
        Element e = map.get(key);
        return e;
    }

    public Map<Object, Element> getAll(){
        return map;
    }

    @Override
    public void clear() {
        map.clear();
    }


    public Element put(Element e) {
        return map.put(e.getKey(), e);
    }

    @Override
    public void remove(Object key) {
        map.remove(key);
    }

    @Override
    public Integer size() {
        return map.size();
    }

    @Override
    public void removeAll(Object[] keys) {
        for(int i =0;i<keys.length;i++){
            remove(keys[i]);
        }
    }
}
