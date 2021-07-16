package com.ljm.resource.kilim;

import kilim.Mailbox;
import kilim.Pausable;
import kilim.Task;

import java.math.RoundingMode;

/**
 * @author liaojiamin
 * @Date:Created in 17:16 2020/4/24
 */
public class Calculator extends Task {
    private Mailbox<Calculation> mailbox;

    public Calculator(Mailbox<Calculation> mailbox){
        super();
        this.mailbox = mailbox;
    }

    @Override
    public void execute() throws Pausable {
        while(true){
            Calculation calc = mailbox.get();
            if(calc.getAnswer() == null){
                calc.setAnswer(calc.getDividend().divide(calc.getDivisor(), 8, RoundingMode.HALF_UP));
                System.out.println("Calculator determined answer");
                mailbox.putnb(calc);
            }
            Task.sleep(1000);
        }
    }
}
