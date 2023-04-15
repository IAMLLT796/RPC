package com.llt.gkrpc.codec;

import static org.junit.Assert.*;

public class JSONEncoderTest {

    @org.junit.Test
    public void encode() {
        Encoder encoder=new JSONEncoder();
        TestBean bean=new TestBean();
        bean.setName("llt");
        bean.setAge(18);

        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}