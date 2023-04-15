package com.llt.gkrpc.server;

import com.llt.gkrpc.Request;
import com.llt.gkrpc.Response;
import com.llt.gkrpc.ServiceDescriptor;
import com.llt.gkrpc.codec.Decoder;
import com.llt.gkrpc.codec.Encoder;
import com.llt.gkrpc.common.utils.ReflectionUtils;
import com.llt.gkrpc.transport.RequestHandler;
import com.llt.gkrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 类作用：todo
 * @Author llt
 * @Date 2023-04-15-10:15
 **/
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(){}

    public RpcServer(RpcServerConfig config){
        this.config=config;
        //net
        this.net=ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);
        this.encoder=ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder=ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager=new ServiceManager();
        this.serviceInvoker=new ServiceInvoker();
    }

    public <T> void register(Class<T> interefaceClass,T bean)
    {
        serviceManager.register(interefaceClass,bean);
    }

    public void start(){
        this.net.start();
    }
    public void end()
    {
        this.net.end();
    }
    private RequestHandler handler=new RequestHandler(){
        @Override
        public void onRequest(InputStream recive, OutputStream toResp){
            Response response=new Response();

            try {
                byte[] inBytes= IOUtils.readFully(recive,recive.available());
                Request request=decoder.decode(inBytes,Request.class);
                log.info("get request: {}",request);

                ServiceInstance instance=serviceManager.lookup(request);
                Object invoke=serviceInvoker.invoke(instance,request);
                response.setData(invoke);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                response.setCode(1);
                response.setMessage("RpcServer got error: "+e.getClass().getName()+" : "+e.getMessage());
            }finally{
                try {
                    byte[] outBytes= encoder.encode(response);
                    toResp.write(outBytes);

                    log.info("response client");
                } catch (IOException e) {
                   log.warn(e.getMessage(),e);
                }
            }

        }
    };
}
