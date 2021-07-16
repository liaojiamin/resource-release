package com.ljm.resource.opt.myobserver;

/**
 * @author liaojiamin
 * @Date:Created in 16:26 2020/3/24
 */
public class MainTest {
    public static void main(String[] args) {
        VideoSite vs = new VideoSite();
        vs.registerObserver(new VideoFans("one"));
        vs.registerObserver(new VideoFans("two"));
        vs.registerObserver(new VideoFans("three"));
        vs.addVideos("生活大爆炸大更新!!!");
        vs.registerObserver(new VideoFans("one"));
        vs.addVideos("test");
    }
}
