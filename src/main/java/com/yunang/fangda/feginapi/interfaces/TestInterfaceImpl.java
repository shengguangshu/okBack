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
        testInterface.getOwner("20180101010101","","","");
    }

    public static void main(String[] args) {
        new TestInterfaceImpl().test();
    }
}
