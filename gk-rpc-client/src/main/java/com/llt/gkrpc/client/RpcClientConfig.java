package com.llt.gkrpc.client;


import com.llt.gkrpc.Peer;
import com.llt.gkrpc.codec.Decoder;
import com.llt.gkrpc.codec.Encoder;
import com.llt.gkrpc.codec.JSONDecoder;
import com.llt.gkrpc.codec.JSONEncoder;
import com.llt.gkrpc.transport.HTTPTransportClient;
import com.llt.gkrpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Author llt
 * @Date 2023-04-15-10:45
 **/
@Data
public class RpcClientConfig
{
    private Class<? extends TransportClient> transportClass= HTTPTransportClient.class;

    private Class<? extends Encoder> encoderClass= JSONEncoder.class;
    private Class<? extends Decoder> decoderClass= JSONDecoder.class;

    private Class<? extends TransportSelector> selectorClass= RandomTransportSelector.class;

    private int connectCount=1;

    private List<Peer> servers= Arrays.asList(new Peer("127.0.0.1",3000));
}
