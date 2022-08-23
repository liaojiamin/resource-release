package com.ljm.resource.design.conponent;

/**
 * @author liaojiamin
 * @Date:Created in 15:09 2022/8/4
 */
public class MainTest {

    public static void main(String[] args) {
        EagleFlyComponent eagleFlyComponent = new EagleFlyComponent(new FlyConcreteComponent(), "翅膀 ");
        eagleFlyComponent.birdFlay();

        PlaneFlyComponent planeFlyComponent = new PlaneFlyComponent(new FlyConcreteComponent(), "发动机 ");
        planeFlyComponent.planFlay();
    }
}
