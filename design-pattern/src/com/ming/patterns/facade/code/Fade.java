package com.ming.patterns.facade.code;

/**
 * Created by ming on 2017/6/30.
 * facade  外观
 */
public class Fade {
       public void test(){
        new AMoudle().test();
        new BMoudle().test();
        new CMoudle().test();
    }

}
