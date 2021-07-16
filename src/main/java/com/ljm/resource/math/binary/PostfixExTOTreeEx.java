package com.ljm.resource.math.binary;

import com.ljm.resource.math.myStack.MyStack;

/**
 * 后缀表达式 转表达式树
 *
 * @author liaojiamin
 * @Date:Created in 15:25 2020/12/11
 */
public class PostfixExTOTreeEx {
    public static BinaryNode toTreeEx(String postfixEx) {
        MyStack<Object> number = new MyStack<>();
        String[] chars = postfixEx.split(" ");
        for (String s : chars) {
            if (s.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")) {
                //数字
                number.push(s);
            }else if (s.matches("(\\*)|(\\/)|(\\+)|(\\-)")) {
                if (number.size() < 2) {
                    return null;
                }

                BinaryNode binaryRight = null;
                Object right = number.pop();
                if(right instanceof BinaryNode){
                    binaryRight = (BinaryNode) right;
                }else {
                    binaryRight = new BinaryNode(right, null, null);
                }

                BinaryNode binaryLeft = null;
                Object left = number.pop();
                if(left instanceof BinaryNode){
                    binaryLeft = (BinaryNode) left;
                }else {
                    binaryLeft = new BinaryNode(left, null, null);
                }
                number.push(new BinaryNode(s, binaryLeft, binaryRight));
            }
        }
        return (BinaryNode) number.pop();
    }

    /**
     * 中序遍历
     * @author: liaojiamin
     * @date: 18:15 2020/12/11
     */
    public static void printMiddleFirstTree(BinaryNode binaryNode){
        if(binaryNode == null || binaryNode.getElement() == null){
            return;
        }
        printMiddleFirstTree(binaryNode.getLeft());
        System.out.print(binaryNode.getElement());
        printMiddleFirstTree(binaryNode.getRight());
    }

    /**
     * 前序遍历
     * @author: liaojiamin
     * @date: 18:15 2020/12/11
     */
    public static void printLeftFirstTree(BinaryNode binaryNode){
        if(binaryNode == null || binaryNode.getElement() == null){
            return;
        }
        System.out.print(binaryNode.getElement());
        printLeftFirstTree(binaryNode.getLeft());
        printLeftFirstTree(binaryNode.getRight());
    }

    /**
     * 后序遍历
     * @author: liaojiamin
     * @date: 18:15 2020/12/11
     */
    public static void printRightFirstTree(BinaryNode binaryNode){
        if(binaryNode == null || binaryNode.getElement() == null){
            return;
        }
        printRightFirstTree(binaryNode.getLeft());
        printRightFirstTree(binaryNode.getRight());
        System.out.print(binaryNode.getElement());
    }

    public static void main(String[] args) {
        BinaryNode binaryNode = toTreeEx("1 2 3 * 4 - 5 + +");
        printMiddleFirstTree(binaryNode);
        System.out.println();
        printLeftFirstTree(binaryNode);
        System.out.println();
        printRightFirstTree(binaryNode);
    }

}
