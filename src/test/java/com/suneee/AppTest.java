package com.suneee;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        Method[] methods = Hello.class.getMethods();
        String arg = "haowuliao";
        for (Method method:methods
             ) {
            try {
                //System.out.println(method.getName());
                if(method.getName().equals("say")){
                    Object object = Hello.class.newInstance();

                    final Object invoke = method.invoke(object, arg);
                    System.out.println(invoke);
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
