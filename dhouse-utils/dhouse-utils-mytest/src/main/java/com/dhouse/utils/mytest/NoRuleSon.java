package com.dhouse.utils.mytest;
import com.dhouse.utils.transition.example.NoRule;
import com.dhouse.utils.transition.rule.Rule;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
/**
 * 没什么用
 * 测试一下反射时继承类是否能获得父类的接口及方法
 * 结论：继承类直接读取不到父类实现的接口
 * getMethods可以得到父类接口的方法
 * 继承类可以以此接口实现jdk代理
 */
public class NoRuleSon extends NoRule {
    public static class InnerClass {
    }

    public static void main(String[] args) {
        Rule ruleP = new NoRule();
        Rule ruleS = new NoRuleSon();
        Map<Integer, String> map = new HashMap<Integer, String>();
        //showClassName(InnerClass.class);
        //showInterfaces(ruleP);
        //showMethods(ruleP);
        //showInterfaces(ruleS);
        //showMethods(ruleS);
        //showInterfaces(map);
        JDKProxy proxy = new JDKProxy();
        proxy.setTargetObject(ruleS);
        //Rule ruleProxy=(Rule) Proxy.newProxyInstance(ruleS.getClass().getClassLoader(), ruleP.getClass().getInterfaces(), proxy);
        Rule ruleProxy = (Rule) Proxy.newProxyInstance(ruleS.getClass().getClassLoader(), new Class[]{Rule.class}, proxy);
        System.out.println("match:");
        System.out.println(ruleProxy.match("No.11"));
        int cap = 1;
        System.out.println((cap + (cap >> 1) + 1));
        for (int i = 1; i <= 10; i++) {
            System.out.println("\""+i+"\"="+"\""+Integer.toBinaryString(i)+"\"右移后为："+(i+(i>>1)+1));
        }

    }

    private static void showMethods(Object o) {
        System.out.println("showMethods-begin>>>param:" + "o = [" + o + "]");
        System.out.println("========getDeclaredMethods:");
        Method[] methods = o.getClass().getDeclaredMethods();
        if (methods != null && methods.length != 0) {
            for (int i = 0; i < methods.length; i++) {
                System.out.println(methods[i].getName());
            }
        }
        System.out.println("========getMethods:");
        methods = o.getClass().getMethods();
        if (methods != null && methods.length != 0) {
            for (int i = 0; i < methods.length; i++) {
                System.out.println(methods[i].getName());
            }
        }
        System.out.println("showMethods-end<<<return:");
    }

    private static void showInterfaces(Object o) {
        System.out.println("showInterfaces-begin>>>param:" + "o = [" + o + "]");
        System.out.println("========getInterfaces:");
        Class<?>[] classes = o.getClass().getInterfaces();
        if (classes != null && classes.length != 0) {
            for (int i = 0; i < classes.length; i++) {
                showClassName(classes[i]);

            }
        }
        System.out.println("========getGenericInterfaces:");
        Type[] types = o.getClass().getGenericInterfaces();
        if (types != null && types.length != 0) {
            for (int i = 0; i < types.length; i++) {
                System.out.println(types[i].toString());

            }
        }
        System.out.println("showInterfaces-end<<<return:");
    }

    private static void showClassName(Class<?> clazz) {
        //System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getName());
        //System.out.println(clazz.getSimpleName());
    }

    static public class JDKProxy implements InvocationHandler {

        private Object targetObject;//需要代理的目标对象

        public void setTargetObject(Object targetObject) {
            this.targetObject = targetObject;
        }

        public Object newProxy(Object targetObject) {//将目标对象传入进行代理
            this.targetObject = targetObject;
            return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                    targetObject.getClass().getInterfaces(), this);//返回代理对象
        }

        public Object invoke(Object proxy, Method method, Object[] args)//invoke方法
                throws Throwable {
            before();
            Object ret = null;      // 设置方法的返回值
            ret = method.invoke(targetObject, args);       //invoke调用需要代理的方法
            after();
            return ret;
        }

        private void before() {//方法执行前
            System.out.println("方法执行前 !");
        }

        private void after() {//方法执行后
            System.out.println("方法执行后");
        }
    }
}
