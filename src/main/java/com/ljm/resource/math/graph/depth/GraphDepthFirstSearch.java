package com.ljm.resource.math.graph.depth;

import com.ljm.resource.math.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 16:29 2021/1/8
 */
public class GraphDepthFirstSearch {
    //假设图基本数据结构已经被读入邻接表中
    private static final List<Vertex_v1> vertices = new ArrayList<>();
    private Integer counter = 0;

    /**
     * 深度优先递归模板
     * */
    public void depthFirst(Vertex_v1 v){
        v.setKnow(true);
        for (Vertex_v1 vertex : v.getVertexList()) {
            if(!vertex.isKnow()){
                depthFirst(vertex);
            }
        }
    }

    /**
     * 深度优先递归赋值nnum
     * */
    public void assignNum(Vertex_v1 v){
        v.setNum(counter++);
        v.setKnow(true);
        for (Vertex_v1 vertex_v1 : v.getVertexList()) {
            if(!vertex_v1.isKnow()){
                vertex_v1.setPath(v);
                assignNum(vertex_v1);
            }
        }
    }

    /**
     * 同样的算法，深度优先对Low赋值
     * */
    public void assignLow(Vertex_v1 v){
        v.setLow(v.getNum());
        for (Vertex_v1 w : v.getVertexList()) {
            //w > v 标识w是v的子节点
            if(w.getNum() > v.getNum()){
                assignLow(w);
                //割点规则
                if(w.getLow() > v.getNum()){
                    System.out.println(v.getNum() + " is an articulation point");
                }
                v.setLow(Math.min(v.getLow(), w.getLow()));
            }else if(v.getPath().compareTo(w) != 0){
                //此处不能写成： v.setLow(Math.min(v.getLow(), w.getLow()));因为此种情况是在最后二次访问边路径的时候得到，可能w的Low已经被修改成更小的值
                v.setLow(Math.min(v.getLow(), w.getNum()));
            }
        }
    }

    /**
     * 还是用深度优先搜索，结合以上两种规则，只进行一次遍历得到
     * */
    public void findArt(Vertex_v1 v){
        v.setKnow(true);
        v.setNum(counter++);
        v.setLow(v.getNum());
        for (Vertex_v1 w : v.getVertexList()) {
            if(!w.isKnow()){
                w.setPath(v);
                findArt(w);
                if(w.getLow() >= v.getNum()){
                    System.out.println(v.getNum() + " is an articulation point");
                }
                v.setLow(Math.min(v.getLow(), w.getLow()));
            }else if(v.getPath().compareTo(w) != 0){
                //此处不能写成： v.setLow(Math.min(v.getLow(), w.getLow()));因为此种情况是在最后二次范文边路径的时候得到，可能w的Low已经被修改成更小的值
                v.setLow(Math.min(v.getLow(), w.getNum()));
            }
        }
    }

}
