package com.llt.gkrpc.server;

import com.llt.gkrpc.Request;
import com.llt.gkrpc.common.utils.ReflectionUtils;

/**
 * 类作用： 调用具体服务
 * @Author llt
 * @Date 2023-04-15-10:11
 **/
public class ServiceInvoker {
    public Object invoke(ServiceInstance instance, Request request)
    {
        return ReflectionUtils.invoke(
                instance.getTarget(),
                instance.getMethod(),
                request.getParameters()
        );
    }

}
