package com.llt.gkrpc.client;

import com.llt.gkrpc.Peer;
import com.llt.gkrpc.transport.TransportClient;

import java.util.List;

/**
 * 类作用：todo
 * @Author llt
 * @Date 2023-04-15-10:39
 **/
public interface TransportSelector {
    /**
     * 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client对于server建立多少个连接
     * @param clazz client实现的class
     */
    void init(List<Peer> peers,int count,Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    void close();
}
