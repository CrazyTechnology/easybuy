package com.ming.patterns.mediator.code;

/**
 * Created by ming on 2017/7/1.
 * 中介者接口
 */
public interface Mediator {

    /**
     * 同事对象在改变的时候通知中介者对象的方法
     * 让中介者负责相应的与其他同事对象的交互
     * @param colleague
     */
    public void changed(Colleague colleague);
}
