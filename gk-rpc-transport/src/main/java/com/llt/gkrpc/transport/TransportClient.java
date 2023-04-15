package com.llt.gkrpc.transport;

import com.llt.gkrpc.Peer;

import java.io.InputStream;

/**
 * 1.创建连接
 * 2.发送数据，并且等待响应
 * 3.关闭连接
 */
public interface TransportClient {
    //连接Server服务
    void connect(Peer peer);
    //订阅Server服务，并返回response
    InputStream write(InputStream data);
    //关闭服务
    void close();
}
