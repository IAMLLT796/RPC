package com.llt.gkrpc.server;

import com.llt.gkrpc.codec.JSONDecoder;
import com.llt.gkrpc.transport.HTTPTransportServer;
import com.llt.gkrpc.transport.TransportServer;
import com.llt.gkrpc.codec.Encoder;
import com.llt.gkrpc.codec.JSONEncoder;
import com.llt.gkrpc.codec.Decoder;
import lombok.Data;

/**
 * server 配置
 */
@Data
public class RpcServerConfig  {
    private Class<? extends TransportServer> transportClass= HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass=JSONEncoder.class;
    private Class<? extends Decoder> decoderClass= JSONDecoder.class;
    private int port=3000;
}
