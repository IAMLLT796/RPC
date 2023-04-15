package com.llt.gkrpc.example;

/**
 * @Author llt
 * @Date 2023-04-15-20:30
 **/
public class CalcServiceImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}
