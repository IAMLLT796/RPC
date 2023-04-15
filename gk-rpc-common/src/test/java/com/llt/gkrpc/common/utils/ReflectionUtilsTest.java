package com.llt.gkrpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;
import java.nio.file.ReadOnlyFileSystemException;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @org.junit.Test
    public void newInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    @org.junit.Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);
        String mname=methods[0].getName();
        assertEquals("b",mname);
    }

    @org.junit.Test
    public void invoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method b=methods[0];

        TestClass t=new TestClass();
        Object r = ReflectionUtils.invoke(t, b);

        assertEquals("b",r);
    }
}