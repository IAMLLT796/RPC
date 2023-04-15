package com.llt.gkrpc.transport;

/**
 * 1.启动、监听
 * 2。接受请求
 * 3.关闭监听
 */
public interface TransportServer {
    //初始化Server服务
    void init(int port,RequestHandler handler);
    //开启Server服务
    void start();
    //关闭Server服务
    void end();
}
