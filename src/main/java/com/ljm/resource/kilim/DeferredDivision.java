package com.ljm.resource.kilim;

import kilim.Mailbox;
import kilim.Pausable;
import kilim.Task;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.Random;

/**
 * @author liaojiamin
 * @Date:Created in 15:37 2020/4/24
 */
public class DeferredDivision extends Task {
    private Mailbox<Calculation> mailbox;
    public DeferredDivision(Mailbox<Calculation> mailbox){
        super();
        this.mailbox = mailbox;
    }

    @Override
    public void execute() throws Pausable {
        Random numberGenerator = new Random(System.currentTimeMillis());
        MathContext context = new MathContext(8);
        while (true){
            System.out.println("i need to know the answer of something");
            mailbox.putnb(new Calculation(
                    new BigDecimal(numberGenerator.nextDouble(), context),
                    new BigDecimal(numberGenerator.nextDouble(), context)
            ));
            Task.sleep(1000);
            Calculation answer = mailbox.getnb();
            if(answer != null && answer.getAnswer() != null){
                System.out.println("Answer is :" + answer.toString());
            }
        }

    }
}
