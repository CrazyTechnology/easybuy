package com.ming.patterns.mediator.code;

/**
 * Created by ming on 2017/7/1.
 * 中介者具体实现
 */
public class ConcreteMediator implements Mediator {

    /**
     * 持有并维护同事A
     */
    private Colleague_A colleague_a;

    /**
     * 持有并维护同事B
     */
    private Colleague_B colleague_b;



    public void changed(Colleague colleague) {
        if (colleague instanceof Colleague_A){
            //同事a处理完
            colleague_b.someOpreation();
        }else{
            System.out.print("完成所有步骤");
        }
    }

    /**
     * 第一步
     * @param
     */
    public void stepOne() {
       colleague_a.someOpreation();
    }

    public Colleague_A getColleague_a() {
        return colleague_a;
    }

    public void setColleague_a(Colleague_A colleague_a) {
        this.colleague_a = colleague_a;
    }

    public Colleague_B getColleague_b() {
        return colleague_b;
    }

    public void setColleague_b(Colleague_B colleague_b) {
        this.colleague_b = colleague_b;
    }
}
