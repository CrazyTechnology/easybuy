package com.ming.patterns.mediator.code;

/**
 * Created by ming on 2017/7/1.
 * 同事对象的父类
 */
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague() {
    }

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
