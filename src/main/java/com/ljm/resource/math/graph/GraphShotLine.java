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
     * 有权最短路径算法- 面排序
     * */
    public void dijkstra_no_sort(Vertex s){
        for (Vertex vertex : vertexVraph) {
            vertex.setDist(MAX_DIST);
            vertex.setKnow(Boolean.FALSE);
        }
        s.setDist(0);
        LinkedList<Vertex> subVertexList = new LinkedList<>();
        subVertexList.add(s);
        while (!subVertexList.isEmpty()){
            Vertex smallVertex = subVertexList.removeFirst();
            smallVertex.setKnow(Boolean.TRUE);
            for (Vertex vertex : smallVertex.getVertexList()) {
                Integer c_vw = vertex.getWeight();
                if(vertex.getDist() > smallVertex.getDist() + c_vw){
                    vertex.setDist(smallVertex.getDist() + c_vw);
                    vertex.setPath(smallVertex);
                    insertIntoSortLinkedList(subVertexList, vertex);
                }
            }
        }
    }

    public static void  insertIntoSortLinkedList(LinkedList<Vertex> sortList, Vertex element) {
        if (sortList.size() <= 0) {
            sortList.add(element);
        } else {
            for (int i = 0; i < sortList.size(); i++) {
                if (sortList.get(i).getDist() >= element.getDist()) {
                    sortList.add(i, element);
                    break;
                }
            }
        }

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
            //保存需要添加的新节点
            LinkedList<Vertex> newVertexList = new LinkedList<>();
            for (Vertex vertex : smallVertex.getVertexList()) {
                if(!vertex.isKnow()){
                    Integer cvw = vertex.getWeight();
                    if(smallVertex.getDist() + cvw < vertex.getDist()){
                        vertex.setDist(smallVertex.getDist() + cvw);
                        vertex.setPath(smallVertex);
                        //处理过的节点才是之后可能的路径
                        newVertexList.add(vertex);
                    }
                }
            }
            //将可能的路径按从小到大顺序加入有序队列
            sunVertexList.addAll(getSortVertex(newVertexList));
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
            for(int j = i+1;j > 0;j--){
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
            Vertex sunVertex =  sunVertexList.removeFirst();;
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
