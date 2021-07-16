package com.ljm.resource.redis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 12:41 2020/11/14
 */
public class ArrayMap<K, V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    public V put(K k, V v){
        for (int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals(k)){
                V oldv = values.get(i);
                values.set(i, v);
                return oldv;
            }
        }
        keys.add(k);
        values.add(v);
        return null;
    }

    public V get(K k){
        for (int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals(k)){
                return values.get(i);
            }
        }
        return null;
    }

    public V delete(K k){
        for (int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals(k)){
                keys.remove(k);
                return values.remove(i);
            }
        }
        return null;
    }
}
