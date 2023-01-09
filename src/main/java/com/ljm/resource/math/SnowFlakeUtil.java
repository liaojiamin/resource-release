package com.ljm.resource.math;

import java.util.Date;

/**
 * Created by jiamin5 on 2022/12/20.
 */
public class SnowFlakeUtil {
    private static SnowFlakeUtil snowFlakeUtil;

    static {
        snowFlakeUtil = new SnowFlakeUtil();
    }

    //初始化时间，可以用算法上线时间
    private static final long INIT_EPOCH = 1671524811000L;
    //时间位取值
    private static final long TIME_BIT = 0b1111111111111111111111111111111111111111110000000000000000000000L;
    //记录最后一次使用的时间戳，单位毫秒，判断两次请求是否同一个毫秒，用于服务器时间判断（服务器时间异常时候用）
    private long lastTimeMillis = -1L;
    //dataCenterId占用的位数  机器id
    private static final long DATA_CENTER_ID_BITS = 5L;
    //dataCentrId占用5bit位，最大值2^5 -1 = 31
    //0000000000000000000000000000000000000000000000000000000000011111
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    //dataCenterId
    private long dataCenterId;
    //workId占用的位数
    private static final long WORKER_ID_BITS = 5L;
    //workId占用5个bit位，最大2^5-1 = 31
    //0000000000000000000000000000000000000000000000000000000000011111
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    //workId
    private long workerId;
    //最后12位，代表每毫秒可以产生的最大的序列号，2^12 -1 = 4095
    private static final long SEQUENCE_BITS = 12L;
    //掩码（最低12位为1，高位都为0），主要用于与自增之后的序列号进行按位与，如果值为0，则代表自增后的序列号超过了4095
    private static final long SEQUENCE_MASK = ~(-1 << SEQUENCE_BITS);
    //同一秒内的最新序列号，最大是 2^12 -1 = 4095
    private long sequence;
    //workId位需要左移位数 12
    private static final long WORK_ID_SHIFT = SEQUENCE_BITS;
    //dataCenterId位需要左移的位数12+5
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    //时间戳需要左移的位数12+5+5
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    public SnowFlakeUtil() {
        this(1, 1);
    }

    public SnowFlakeUtil(long dataCenterId, long workerId) {
        if (dataCenterId < 0 || dataCenterId > MAX_DATA_CENTER_ID) {
            throw new IllegalArgumentException(String.format("dataCenterId 值必须大于 0 并且小于 %d", MAX_DATA_CENTER_ID));
        }
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException(String.format("workId 值必须大于 0 并且小于 %d", MAX_WORKER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long nextId() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
        if (currentTimeMillis < lastTimeMillis) {
            throw new RuntimeException(
                    String.format("可能出现服务器时钟回拨问题，请检查服务器时间。当前服务器时间戳：%d，上一次使用时间戳：%d", currentTimeMillis,
                            lastTimeMillis));
        }
        //同一个毫秒时间段内，序号递增1，序列号最大是4095
        //序列号最大值4095，使用掩码（最低12位为1，高位都为0） 进行& 运算，如果值为0 ，则自增后的序列号超过了4095
        //同时更新时间戳
        if(currentTimeMillis == lastTimeMillis){
            sequence = (sequence +1) & SEQUENCE_MASK;
            if(sequence == 0){
                currentTimeMillis = getNextMillis(lastTimeMillis);
            }
        }else {
            sequence = 0;
        }
        // 记录最后一次使用的毫秒时间戳
        lastTimeMillis = currentTimeMillis;
        //将不同部分的数值移动到指定的位置，然后进行或运行
        // <<：左移运算符, 1 << 2 即将二进制的 1 扩大 2^2 倍
        // |：位或运算符, 是把某两个数中, 只要其中一个的某一位为1, 则结果的该位就为1
        // 优先级：<< > |
        return  //时间戳部分数据位处理
                ((currentTimeMillis - INIT_EPOCH) << TIMESTAMP_SHIFT)
                //数据中心部分数据位
                | (dataCenterId << DATA_CENTER_ID_SHIFT)
                //机器表示部分
                | (workerId << WORKER_ID_BITS)
                //序列号
                | sequence;

    }

    public long getNextMillis(long lastTimeMillis){
        long currentTimeMillis = System.currentTimeMillis();                                    bv vv bvj,hl
        while(currentTimeMillis <= lastTimeMillis){
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis;
    }

}
