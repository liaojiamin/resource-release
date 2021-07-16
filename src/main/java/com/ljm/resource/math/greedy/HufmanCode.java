package com.ljm.resource.math.greedy;

import java.util.*;

/**
 * 哈夫曼算法  模拟 文件压缩问题
 * @author liaojiamin
 * @Date:Created in 11:46 2021/1/13
 */
public class HufmanCode {

    /**
     * 初始化文件字符信息
     * */
    public static String getRandomString(int length){
        String str="abcdefghabc";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(10);
            sb.append(str.charAt(number));
        }
        System.out.println(sb);
        return sb.toString();
    }

    /**
     * 哈夫曼编码问题实现
     * */
    public static HufmanNode hufmanCode(){
        String hufmanBase = getRandomString(20);
        if(hufmanBase == null || hufmanBase.length() <= 0){
            return null;
        }
        Map<String, Integer> nameToWeight = new HashMap<>();
        for (int i = 0; i < hufmanBase.length(); i++) {
            String key = String.valueOf(hufmanBase.charAt(i));
            Integer value = nameToWeight.get(key);
            nameToWeight.put(key, value == null ? 1 : value + 1);
        }
        LinkedList<HufmanNode> linkedList = buildHufmanNode(nameToWeight);
        quickSort(linkedList, 0, linkedList.size() -1);
        if(linkedList.size() == 1){
            return linkedList.get(0);
        }
        while (!linkedList.isEmpty()){
            if(linkedList.size() == 1){
                return linkedList.removeFirst();
            }
            HufmanNode hufmanNode =  buildNewNode(linkedList.removeFirst(), linkedList.removeFirst());
            insertLinkedList(linkedList, hufmanNode);
        }
        return linkedList.removeFirst();
    }

    /**
     * 按weight顺序插入哈夫曼节点
     * */
    public static void insertLinkedList(LinkedList<HufmanNode> linkedList, HufmanNode hufmanNode){
        if (linkedList.size() <= 0){
            linkedList.add(hufmanNode);
            return;
        }
        Integer temp = linkedList.size() - 1;
        for (int i = 0; i < linkedList.size(); i++) {
            if(linkedList.get(i).getWeight() > hufmanNode.getWeight()){
                temp = i;
            }
        }
        linkedList.add(temp, hufmanNode);
    }

    /**
     * 构造节点信息
     * */
    public static HufmanNode buildNewNode(HufmanNode left, HufmanNode right){
        HufmanNode hufmanNode = new HufmanNode();
        hufmanNode.setLeft(left);
        hufmanNode.setRight(right);
        hufmanNode.setNodeName("node" + System.currentTimeMillis());
        hufmanNode.setWeight(left.getWeight() + right.getWeight());
        return hufmanNode;
    }

    /**
     * 快排权重从小到大
     * */
    public static void quickSort(LinkedList<HufmanNode> linkedList, Integer left, Integer right){
        if(left <  right){
            Integer temp = swap(linkedList, left, right);
            quickSort(linkedList, left, temp -1);
            quickSort(linkedList, temp + 1, right);
        }
    }

    /**
     * 挖坑法实现快排
     * */
    public static Integer swap(LinkedList<HufmanNode> linkedList, Integer left, Integer right){
        if(left < right){
            HufmanNode position = linkedList.get(left);
            while (left < right){
                while (left < right && linkedList.get(right).compareTo(position) > 0){
                    right --;
                }
                if(left < right){
                    linkedList.set(left, linkedList.get(right));
                    left ++;
                }
                while (left < right && linkedList.get(left).compareTo(position) < 0){
                    left ++;
                }
                if(left < right){
                    linkedList.set(right, linkedList.get(left));
                    right--;
                }
            }
            linkedList.set(left, position);
        }
        return left;
    }

    /**
     * 构造树节点
     * */
    public static LinkedList<HufmanNode> buildHufmanNode(Map<String, Integer> nameToWeight){
        LinkedList<HufmanNode> linkedList = new LinkedList<>();
        for (String nodeName : nameToWeight.keySet()) {
            HufmanNode hufmanNode = new HufmanNode();
            hufmanNode.setNodeName(nodeName);
            hufmanNode.setWeight(nameToWeight.get(nodeName));
            linkedList.addFirst(hufmanNode);
        }
        return linkedList;
    }


    public static void main(String[] args) {
        HufmanNode hufmanNode = hufmanCode();
    }
}
