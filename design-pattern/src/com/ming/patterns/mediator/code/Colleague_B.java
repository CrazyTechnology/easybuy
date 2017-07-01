package com.ming.patterns.mediator.code;

/**
 * Created by ming on 2017/7/1.
 */
public class Colleague_B extends Colleague {
    public Colleague_B(Mediator mediator) {
        super(mediator);
    }
    public Colleague_B() {

    }
    /**
     * 一些操作方法
     */
    public void someOpreation(){
        System.out.print("第二步");
        mediator.changed(new Colleague_B());
    }
}
