package com.ljm.resource.opt.myobserver;

import java.util.ArrayList;

/**
 * @author liaojiamin
 * @Date:Created in 16:21 2020/3/24
 */
public class VideoSite implements Subject {
    private ArrayList<Observer> userList;
    private ArrayList<String> videos;

    public VideoSite() {
        userList = new ArrayList<>();
        videos = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        userList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        userList.remove(o);
    }

    @Override
    public void notivfyAllObserver() {
        for (Observer observer : userList) {
            observer.update(this);
        }
    }

    public void addVideos(String video) {
        this.videos.add(video);
        //通知所有用户
        notivfyAllObserver();
    }

    public ArrayList<String> getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return videos.toString();
    }
}
