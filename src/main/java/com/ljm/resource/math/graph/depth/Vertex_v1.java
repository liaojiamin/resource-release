package com.ljm.resource.math.graph.depth;

import java.util.ArrayList;
import java.util.List;

/**
 * 图节点元素
 * @author liaojiamin
 * @Date:Created in 16:28 2020/12/28
 */
public class Vertex_v1 implements Comparable<Vertex_v1>{
    //图节点相邻节点组
    private List<Vertex_v1> vertexList = new ArrayList<>();
    private boolean know;
    //目标节点到本节点的上一个节点的 节点信息
    private Vertex_v1 path;
    private Integer num;
    private Integer low;

    public List<Vertex_v1> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex_v1> vertexList) {
        this.vertexList = vertexList;
    }

    public boolean isKnow() {
        return know;
    }

    public void setKnow(boolean know) {
        this.know = know;
    }

    public Vertex_v1 getPath() {
        return path;
    }

    public void setPath(Vertex_v1 path) {
        this.path = path;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    @Override
    public int compareTo(Vertex_v1 o) {
        return this.num - o.num;
    }
}
