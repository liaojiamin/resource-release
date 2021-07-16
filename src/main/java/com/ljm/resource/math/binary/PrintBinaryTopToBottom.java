package com.ljm.resource.math.binary;

import java.util.LinkedList;
import java.util.Random;

/**
 *  从上到下，按层打印树
 * @author liaojiamin
 * @Date:Created in 16:29 2021/4/2
 */
public class PrintBinaryTopToBottom {
    public static void printBinaryTopToEnd(BinaryNode binaryNode){
        if(binaryNode == null){
            return;
        }
        LinkedList<BinaryNode> myQueue = new LinkedList<BinaryNode>();
        myQueue.addFirst(binaryNode);
        while (myQueue.size() > 0){
            BinaryNode temp = myQueue.removeFirst();
            if(temp != null){
                System.out.print(temp.getElement());
                System.out.print(",");
                myQueue.addLast(temp.getLeft());
                myQueue.addLast(temp.getRight());
            }
        }
    }

    public static void main(String[] args) {
        BinaryNode node = new BinaryNode(null, null, null);
        BinarySearchTree searchTree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int num = random.nextInt(100);
            node = searchTree.insert(num, node);
            System.out.print(num+",");
        }
        System.out.println();
        printBinaryTopToEnd(node);
    }
}
