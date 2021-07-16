package com.ljm.resource.math.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 最短路径算法
 * @author liaojiamin
 * @Date:Created in 16:29 2020/12/28
 */
public class GraphShotLine {
    //图节点数组
    private static final List<Vertex> vertexVraph = new ArrayList<>();
    private static final Integer MAX_DIST = Integer.MAX_VALUE;

    /**
     * 递归打印路径
     * */
    public void printPath(Vertex v){
        if(v.getPath() != null){
            printPath(v.getPath());
        }
        System.out.println(v);
    }

    /**
     * 有权重图最短路径算法
     * */
    public void dijkstra(Vertex s){
        for (Vertex vertex : vertexVraph) {
            vertex.setDist(MAX_DIST);
            vertex.setKnow(false);
        }
        s.setDist(0);
        LinkedList<Vertex> sunVertexList = new LinkedList<>();
        sunVertexList.addFirst(s);
        while (sunVertexList.size() > 0){
            //每次取第一个最小值
            Vertex smallVertex = sunVertexList.removeFirst();
            smallVertex.setKnow(true);

            for (Vertex vertex : smallVertex.getVertexList()) {
                if(!vertex.isKnow()){
                    Integer cvw = vertex.getWeight();
                    if(smallVertex.getDist() + cvw < vertex.getDist()){
                        vertex.setDist(smallVertex.getDist() + cvw);
                        vertex.setPath(smallVertex);
                    }
                }
            }
            //按从小到大顺序加入有序队列
            sunVertexList.addAll(getSortVertex(smallVertex.getVertexList()));
        }
    }

    /**
     * 对子节点列表进行从小到大排序
     * */
    public LinkedList<Vertex> getSortVertex(List<Vertex> vertexs){
        if(vertexs.size() <= 0){
            return null;
        }
        for (int i = 0; i < vertexs.size(); i++) {
            for(int j = i;j > 0;j--){
                if(vertexs.get(j-1).compareTo(vertexs.get(j)) > 0 ){
                    Vertex vertex = vertexs.get(j-1);
                    vertexs.set(j-1, vertexs.get(j));
                    vertexs.set(j, vertex);
                }
            }
        }
        return new LinkedList<>(vertexs);
    }

    /**
     * 无权重图最短路径算法 解法二
     * */
    public void unweighted_1(Vertex s){
        for (Vertex vertex : vertexVraph) {
            vertex.setDist(MAX_DIST);
            vertex.setKnow(false);
        }
        s.setDist(0);
        LinkedList<Vertex> sunVertexList = new LinkedList<>();
        sunVertexList.addFirst(s);
        while (!sunVertexList.isEmpty()){
            Vertex sunVertex = sunVertexList.getFirst();
            sunVertexList.removeFirst();
            for (Vertex vertex : sunVertex.getVertexList()) {
                if(vertex.getDist() == MAX_DIST){
                    vertex.setDist(sunVertex.getDist() +1);
                    vertex.setPath(sunVertex);
                    sunVertexList.addLast(vertex);
                }
            }
        }
    }

    /**
     * 无权重图最短路径算法 解法一
     * 时间复杂度O(V^2)
     * */
    public void unweighted(Vertex s){
        for (Vertex vertex : vertexVraph) {
            vertex.setDist(MAX_DIST);
            vertex.setKnow(false);
        }
        s.setDist(0);
        Integer NUM_VERTICES = vertexVraph.size();
        //遍历从 0 ~ MAX 距离的距离范围内所有节点到目标节点的距离，默认最差是链式，距离是NUM_VERTICES
        for (Integer i = 0; i < NUM_VERTICES; i++) {

            for (Vertex vertex : vertexVraph) {
                if(!vertex.isKnow() && vertex.getDist() == i){
                    vertex.setKnow(true);
                    for (Vertex sunVertex : vertex.getVertexList()) {
                        if(sunVertex.getDist() == MAX_DIST){
                            sunVertex.setDist(i + 1);
                            sunVertex.setPath(vertex);
                        }
                    }
                }
            }
        }
    }

}
