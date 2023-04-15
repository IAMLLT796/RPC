package com.llt.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示网络传输的一个端点
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;//远程主机的地址
    private int port;//端口
}
