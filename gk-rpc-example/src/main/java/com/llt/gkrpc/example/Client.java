package com.llt.gkrpc.example;

import com.llt.gkrpc.client.RpcClient;

/**
 * @Author llt
 * @Date 2023-04-15-20:31
 **/
public class Client {
    public static void main(String[] args) {
        RpcClient client=new RpcClient();
        CalcService service=client.getProxy(CalcService.class);
        int add=service.add(1,2);
        int minus=service.minus(1,2);
        System.out.println(add);
        System.out.println(minus);
    }
}
