package com.jixianit.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 本类实现使用适配器模式与代理类二者之间的合作 使原来的类的继承接口转换为新的接口
 */
interface A{
    String greet(String name,String gender)throws Exception;
}
interface B{
    String greet(String username);
}
class Adapter implements InvocationHandler {
    private A a;
    public Adapter(A a) {
        this.a = a;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if ("greet".equals(methodName)) {
            String username = (String) args[0];
            String name = findName(username);
            String gender = findGender(username);
            try {
                Method greetMethod1 = A.class.getMethod(methodName, new Class[]{String.class, String.class});
                return greetMethod1.invoke(a, new Object[]{name, gender});
            } catch (Exception e) {
                if (e != null) {
                    throw e;
                }
            }
        } else {
            return method.invoke(a, args);
        }
        return null;
    }

    private String findGender(String username) {
        return Math.random()>0.5?username:null;
    }

    private String findName(String username) {
        return username;
    }
}

/**
 * 实现将A接口转换为B接口的适配器
 */
class AdapterFactory{
    public static B adaptGreet(A a){
        Adapter adapter=new Adapter(a);
        return (B) Proxy.newProxyInstance(a.getClass().getClassLoader(),new Class[]{B.class},adapter);
    }
}
class Core implements A{
    @Override
    public String greet(String name, String gender) throws Exception {
        return "name:"+name+",gender:"+gender;
    }
}
public class TestProxy {
    public static void main(String  args[]){
        B b = AdapterFactory.adaptGreet(new Core());
        System.out.println(b.greet("admin"));
    }
}

