package com.ljm.resource.design.conponent;

/**
 * @author liaojiamin
 * @Date:Created in 14:58 2022/8/4
 */
public class EagleFlyComponent extends FlyDecorator {
    private String tool;

    public EagleFlyComponent(FlyConponent flyConponent, String tool) {
        super(flyConponent);
        this.tool = tool;
    }

    public void birdFlay() {
        System.out.print(" use " + tool);
        flyConponent.flay();
    }
}
