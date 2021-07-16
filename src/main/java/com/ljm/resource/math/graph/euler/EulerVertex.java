package com.ljm.resource.math.graph.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * 图论：欧拉回路 节点定义
 *
 * @author liaojiamin
 * @Date:Created in 15:58 2021/1/11
 */
public class EulerVertex {
    private List<EulerVertex> vertexList = new ArrayList<>();
    private List<Integer> weight = new ArrayList<>();
    private String name;
    private Boolean known;
    private EulerVertex path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EulerVertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<EulerVertex> vertexList) {
        this.vertexList = vertexList;
    }

    public List<Integer> getWeight() {
        return weight;
    }

    public void setWeight(List<Integer> weight) {
        this.weight = weight;
    }

    public Boolean getKnown() {
        return known;
    }

    public void setKnown(Boolean known) {
        this.known = known;
    }

    public EulerVertex getPath() {
        return path;
    }

    public void setPath(EulerVertex path) {
        this.path = path;
    }
}
