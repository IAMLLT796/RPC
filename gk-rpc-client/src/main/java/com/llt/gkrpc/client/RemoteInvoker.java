package com.llt.gkrpc.client;

import com.llt.gkrpc.Request;
import com.llt.gkrpc.Response;
import com.llt.gkrpc.ServiceDescriptor;
import com.llt.gkrpc.codec.Decoder;
import com.llt.gkrpc.codec.Encoder;
import com.llt.gkrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;

/**
 * 类作用：调用远程服务代理类
 * @Author llt
 * @Date 2023-04-15-13:28
 **/
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz,Encoder encoder,Decoder decoder,TransportSelector selector)
    {
        this.clazz=clazz;
        this.encoder=encoder;
        this.decoder=decoder;
        this.selector=selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request=new Request();
        request.setService(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        Response resp=invokeRemote(request);
        if(resp==null||resp.getCode()!=0)
        {
            throw new IllegalStateException("fail to invoke remote: "+resp);
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request)
    {
        Response resp=null;
        TransportClient client=null;
        try {
            client= selector.select();
            byte[] outBytes=encoder.encode(request);
            InputStream recive=client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes= IOUtils.readFully(recive,recive.available());

            //response赋值
            resp=decoder.decode(inBytes,Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            resp.setCode(1);
            resp.setMessage("RpcClient got error ："+e.getMessage()+" : ");
        }finally{
            if(client!=null)
            {
                selector.release(client);
            }
        }
        return resp;
    }
}
