package com.ljm.resource.design.conponent;

/**
 * @author liaojiamin
 * @Date:Created in 15:13 2022/8/4
 */
public class PlaneFlyComponent extends FlyDecorator {
    private String tool;
    public PlaneFlyComponent(FlyConponent flyConponent, String tool) {
        super(flyConponent);
        this.tool = tool;
    }

    public void planFlay() {
        System.out.print(" use "+ tool);
        super.flay();
    }

}
