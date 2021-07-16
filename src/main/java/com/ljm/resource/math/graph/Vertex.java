package com.ljm.resource.math.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图节点元素
 * @author liaojiamin
 * @Date:Created in 16:28 2020/12/28
 */
public class Vertex implements Comparable<Vertex> {
    //图节点相邻节点组
    private List<Vertex> vertexList = new ArrayList<>();
    private List<Vertex> insertVertexList = new ArrayList<>();
    private Integer dist;
    private boolean know;
    //目标节点到本节点的上一个节点的 节点信息
    private Vertex path;
    private Integer weight;

    public List<Vertex> getInsertVertexList() {
        return insertVertexList;
    }

    public void setInsertVertexList(List<Vertex> insertVertexList) {
        this.insertVertexList = insertVertexList;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Vertex getPath() {
        return path;
    }

    public void setPath(Vertex path) {
        this.path = path;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public boolean isKnow() {
        return know;
    }

    public void setKnow(boolean know) {
        this.know = know;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.dist - o.dist;
    }
}
