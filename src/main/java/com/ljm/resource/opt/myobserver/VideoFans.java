package com.ljm.resource.opt.myobserver;

/**
 * @author liaojiamin
 * @Date:Created in 16:25 2020/3/24
 */
public class VideoFans implements Observer {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VideoFans(String name){
        this.name = name;
    }

    @Override
    public void update(Subject s) {
        System.out.println(this.name + ", new Video are available!");
        System.out.println(s);
    }
}
