package com.ljm.resource.math.greedy;

/**
 * @author liaojiamin
 * @Date:Created in 11:47 2021/1/13
 */
public class HufmanNode implements Comparable<HufmanNode>{
    //节点权重
    private Integer weight;
    //节点名称
    private String nodeName;
    private HufmanNode left;
    private HufmanNode right;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public HufmanNode getLeft() {
        return left;
    }

    public void setLeft(HufmanNode left) {
        this.left = left;
    }

    public HufmanNode getRight() {
        return right;
    }

    public void setRight(HufmanNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(HufmanNode o) {
        return this.weight - o.weight;
    }
}
