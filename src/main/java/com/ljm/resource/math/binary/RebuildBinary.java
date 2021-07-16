package com.ljm.resource.math.binary;

/**
 *  已知前序遍历：1,2,4,7,3,5,6,8 中序遍历：4,7,2,1,5,3,8,6 重建二叉树
 * @author liaojiamin
 * @Date:Created in 17:22 2021/3/8
 */
public class RebuildBinary {
    public static BinaryNode construct(int[] preOrder, int[] inOrder){
        if(preOrder == null || inOrder == null || preOrder.length != inOrder.length || preOrder.length <= 0){
            System.out.println("Invalid input");
            return null;
        }
        return constructCore(preOrder, inOrder, 0, preOrder.length -1, 0, inOrder.length -1);
    }

    public static BinaryNode constructCore(int[] preOrder, int[] inOrder,
                                    int preStart, int preEnd,
                                    int inStart, int inEnd){
        int rootValue = preOrder[preStart];
        BinaryNode rootNode = new BinaryNode(rootValue, null, null);
        //整棵树只有一个root节点的清空
        if(preStart == preEnd){
            if(inStart == inEnd && preOrder[preStart] == inOrder[inStart]){
                return rootNode;
            }else {
                System.out.println("Invalid input");
                return null;
            }
        }
        //找到root节点在 中序遍历中位置
        int rootInOrderIndex = inStart;
        while (rootInOrderIndex <= inEnd && inOrder[rootInOrderIndex] != rootValue){
            rootInOrderIndex ++;
        }
        //中序遍历中没有找到root节点
        if(rootInOrderIndex == inEnd && inOrder[rootInOrderIndex] != rootValue){
            System.out.println("Invalid input");
            return null;
        }
        int leftLength = rootInOrderIndex - inStart;

        //没有左子树情况start = end
        if(leftLength > 0){
            int leftInOrderStart = inStart;
            int leftInOrderEnd =  inStart + leftLength - 1;

            int leftPreOrderStart = preStart + 1;
            int leftPreOrderEnd = preStart + leftLength;
            rootNode.setLeft(constructCore(preOrder, inOrder, leftPreOrderStart, leftPreOrderEnd, leftInOrderStart, leftInOrderEnd));
        }
        //存在右节点
        if(leftLength < preEnd - preStart){
            int rightInOrderStart = rootInOrderIndex + 1;
            int rightInOrderEnd = inEnd;

            int rightPreOrderStart = preStart + 1 + leftLength;
            int rightPreOrderEnd = preEnd;
            rootNode.setRight(constructCore(preOrder, inOrder, rightPreOrderStart, rightPreOrderEnd, rightInOrderStart, rightInOrderEnd));
        }
        return rootNode;
    }

    public static void main(String[] args) {
        int preOrder[] = {1,2,4,7,3,5,6,8};
        int inOrder[] = {4,7,2,1,5,3,8,6};
        BinaryNode binaryNode = construct(preOrder, inOrder);
        PostfixExTOTreeEx.printMiddleFirstTree(binaryNode);
    }
}
