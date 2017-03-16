package com.example.mycache;

import java.util.Map;

/**
 * @Package: com.example.mycache
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/14 上午10:18
 */
public abstract class AbstractStore implements IStore {
    protected Map<Object, Element> map;

    public AbstractStore() {
    }

    public AbstractStore(Map<Object, Element> map) {
        this.map = map;
    }

}
