package com.ljm.resource.design.conponent;

/**
 * @author liaojiamin
 * @Date:Created in 14:57 2022/8/4
 */
public class FlyDecorator implements FlyConponent {
    public FlyConponent flyConponent;

    public FlyDecorator(FlyConponent flyConponent) {
        this.flyConponent = flyConponent;
    }

    @Override
    public void flay() {
        flyConponent.flay();
    }
}
