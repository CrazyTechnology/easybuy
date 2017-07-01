package com.ming.patterns.mediator.code;

/**
 * Created by ming on 2017/7/1.
 */
public class Client {

    public static void main(String [] args){
        //1.创建中介者对象
        ConcreteMediator mediator=new ConcreteMediator();
        //2.创建同事类,并告诉同事类中介者对象
        Colleague_A colleague_a=new Colleague_A(mediator);
        Colleague_B colleague_b=new Colleague_B(mediator);
        //告知中介者对象，同事类
        mediator.setColleague_a(colleague_a);
        mediator.setColleague_b(colleague_b);

        mediator.stepOne();

    }
}
