package com.llt.gkrpc.example;

import com.llt.gkrpc.server.RpcServer;
import com.llt.gkrpc.server.RpcServerConfig;

/**
 * @Author llt
 * @Date 2023-04-15-20:33
 **/
public class Server {
    public static void main(String[] args) {
        RpcServer server=new RpcServer(new RpcServerConfig());
        server.register(CalcService.class,new CalcServiceImpl());
        server.start();
    }
}
