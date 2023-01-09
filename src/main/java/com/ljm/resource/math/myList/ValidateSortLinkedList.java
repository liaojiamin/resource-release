package com.ljm.resource.math.myList;

import java.util.LinkedList;

/**
 * Created by jiamin5 on 2022/12/13.
 */
public class ValidateSortLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> sortList = buildSortLinkedList(new int[]{92,33,45,66,7,7,8,3,432,42,0,534,6,2});
        while (!sortList.isEmpty()){
            System.out.print(sortList.removeFirst()+",");
        }

    }

    public static LinkedList<Integer> buildSortLinkedList(int[] elementArray) {
        if (elementArray == null || elementArray.length <= 0) {
            return new LinkedList<>();
        }
        LinkedList<Integer> sortList = new LinkedList<>();
        for (int element : elementArray) {
            insertIntoLinkedList(sortList, element);
        }
        return sortList;
    }

    public static void  insertIntoLinkedList(LinkedList<Integer> sortList, Integer element) {
        if (sortList.size() <= 0) {
            sortList.add(element);
        } else {
            for (int i = 0; i < sortList.size(); i++) {
                if (sortList.get(i) >= element) {
                    sortList.add(i, element);
                    break;
                }
            }
        }

    }
}
