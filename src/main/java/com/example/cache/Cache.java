package com.example.cache;
import org.springframework.scheduling.annotation.Scheduled;
/**
 * @Package: com.example.cache
 * @Description: 对缓存的监听控制
 * @author: liuxin
 * @date: 17/3/13 下午3:59
 */
public class Cache extends MemoryCache {

    private CacheConfiguration configure;
    private CacheListener listener;

    /**
     * 根据配置信息，去调用监听器
     *
     * @param configure
     */
    public Cache(CacheConfiguration configure) {
        super(configure);
        this.configure = configure;
        if (!configure.getEternal() && configure.getIsNeedCacheCheckListener()) {
            listener = new CacheListener(this);
            listener.start();
        }
    }

    public CacheConfiguration getConfigure() {
        return configure;
    }

    // 销毁
    public void destory() {
        try {
            super.clear();
            if (listener != null) {
                listener.interrupt();
                listener.stop();
                listener = null;
            }
        } catch (Exception e) {
        }

    }
}