package com.ming.patterns.mediator.code;

/**
 * Created by ming on 2017/7/1.
 */
public class Colleague_A extends Colleague {

    public Colleague_A(Mediator mediator) {
        super(mediator);
    }

    public Colleague_A() {

    }

    /**
     * 一些操作方法
     */
    public void someOpreation(){
        System.out.print("第一步");
        mediator.changed(new Colleague_A());
    }
}
