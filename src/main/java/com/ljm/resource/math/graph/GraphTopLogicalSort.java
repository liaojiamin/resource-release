package com.ljm.resource.math.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图论 求拓扑排序算法
 * @author liaojiamin
 * @Date:Created in 14:56 2021/1/6
 */
public class GraphTopLogicalSort {
    //节点定义：Vertex
    //图节点相邻节点组
    //    private List<Vertex> vertexList = new ArrayList<>();  存储节点出度节点
    //    private List<Vertex> insertVertexList = new ArrayList<>(); 存储节点入度节点
    //    private Integer dist; 存储节点拓扑编号

    //假设图基本数据结构已经被读入邻接表中
    private static final List<Vertex> vertices = new ArrayList<>();

    /**
     * 寻找入度为0 的图节点
     * */
    public Vertex findNewVertexOfIndegreeZero(){
        Vertex insetZeroVertex = null;
        for (Vertex vertex : vertices) {
            if(vertex.getInsertVertexList().size() <= 0){
                insetZeroVertex =  vertex;
            }
        }
        //节点入度，出度都为0 ，删除节点信息
        if(insetZeroVertex != null && insetZeroVertex.getVertexList().size() <= 0){
            vertices.remove(insetZeroVertex);
        }
        return insetZeroVertex;
    }

    /**
     * 拓扑排序算法
     * */
    public void topSort(){
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v = findNewVertexOfIndegreeZero();
            if(v == null){
                //每个节点都有入度，说明是有圈
                return;
            }
            v.setDist(i);
            for (Vertex vertex : v.getVertexList()) {
                //更新V节点相邻节点中所有信息
                vertex.getInsertVertexList().remove(v);
            }
        }
    }

    /**
     * 优化拓扑算法
     * */
    public void topSort_v1(){
        List<Vertex> insertZeroVertex = new ArrayList<>();
        insertZeroVertex.add(findNewVertexOfIndegreeZero());
        int i = 0;
        while (insertZeroVertex.size() > 0){
            Vertex v = insertZeroVertex.remove(0);
            v.setDist(i++);
            for (Vertex vertex : v.getVertexList()) {
                //减少入度
                vertex.getInsertVertexList().remove(v);
                if(vertex.getInsertVertexList().size() <= 0){
                    //将入度为0 的节点加入队列
                    insertZeroVertex.add(vertex);
                }
            }
        }
    }
}
