package com.yunang.fangda.feginapi.interfaces;

import com.yunang.fangda.feginapi.utils.FeginUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class TestInterfaceImpl {

    @Autowired
    private TestInterface testInterface = new FeginUtils().object.target(TestInterface.class, FeginUtils.URL);

    public void test() {
        Object object = testInterface.getOwner("20180101010101", "132");
        System.out.println(object.toString());
    }

    public void test2(){
        Object weihuzu = testInterface.tst("weihuzu", "123456");
        System.out.println(weihuzu.toString());
    }

    public static void main(String[] args) {
        new TestInterfaceImpl().test();
    }
}
