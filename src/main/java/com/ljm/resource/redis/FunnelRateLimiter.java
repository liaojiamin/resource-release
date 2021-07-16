package com.ljm.resource.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * 有点像令牌桶的漏桶
 * @author liaojiamin
 * @Date:Created in 16:57 2020/5/29
 */
public class FunnelRateLimiter {
    private Map<String, Funnel> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate){
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if(funnel == null){
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1);
    }

    static class Funnel{
        //总量
        int capacity;
        //流速
        float leakingRate;
        //漏桶现有配额
        int leftQuota;
        //开始时间
        long leakingTs;

        public Funnel(int capacity, float leakingRate){
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }
        //获取空间（更新当前桶中的令牌数量，依据时间以及流速）
        void makeSpace(){
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            //计算这段时间的总流量 时间差* 流速
            int deltaQuota = (int) (deltaTs * leakingRate);
            //int类型越界情况 重新初始化
            if(deltaQuota < 0){
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            //腾出空间太小，最小单位是1
            if(deltaQuota < 1){
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs =nowTs;
            if(this.leftQuota > this.capacity){
                this.leftQuota = this.capacity;
            }
        }

        //消耗存储
        boolean watering(int quota){
            makeSpace();
            if(this.leftQuota >= quota){
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }
}
