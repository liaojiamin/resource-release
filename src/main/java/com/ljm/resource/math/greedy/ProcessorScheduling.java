package com.ljm.resource.math.greedy;

import java.util.Random;

/**
 * 贪心算法，处理器调度问题
 *
 * @author liaojiamin
 * @Date:Created in 16:26 2021/1/12
 */
public class ProcessorScheduling {
    class Task implements Comparable<Task> {
        private String taskName;
        private Integer taskTime;

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public Integer getTaskTime() {
            return taskTime;
        }

        public void setTaskTime(Integer taskTime) {
            this.taskTime = taskTime;
        }

        @Override
        public int compareTo(Task o) {
            return this.taskTime - o.taskTime;
        }
    }

    /**
     * 获取需要处理的业务数据
     */
    public Task[] getTask(Integer num) {
        Task[] tasks = new Task[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            Task task = new Task();
            task.setTaskName("t" + i);
            task.setTaskTime(random.nextInt(10));
            tasks[i] = task;
        }
        return tasks;
    }

    /**
     * 对数据进行从小到大排序
     */
    public Task[] sortTask(Task[] tasks) {
        if (tasks == null) {
            return null;
        }
        quickSort(tasks, 0, tasks.length - 1);
        return tasks;
    }

    public Task[] quickSort(Task[] tasks, Integer left, Integer right) {
        if (left < right) {
            Integer temp = swap(tasks, left, right);
            quickSort(tasks, left, temp - 1);
            quickSort(tasks, temp + 1, right);
        }
        return tasks;
    }

    /**
     * 挖坑法快排
     */
    public Integer swap(Task[] tasks, Integer left, Integer right) {
        if (left < right) {
            Task position = tasks[left];
            while (left < right) {
                while (left < right && tasks[right].compareTo(position) > 0) {
                    right--;
                }
                if (left < right) {
                    tasks[left] = tasks[right];
                    left++;
                }
                while (left < right && tasks[left].compareTo(position) < 0) {
                    left++;
                }
                if (left < right) {
                    tasks[right] = tasks[left];
                    right--;
                }
            }
            tasks[left] = position;
        }
        return left;
    }

    /**
     * 贪心算法
     * 给P个处理器分配任务
     */
    public void finishTask(Integer p) {
        Task[] tasks = sortTask(getTask(8));
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(tasks[i].getTaskName() + ":" + tasks[i].getTaskTime());
        }
        //存储每个列表总时长
        int[] countP = new int[p];
        //存储每个cpu的任务存储位置
        int[] posicition = new int[p];
        //存储p个cpu的任务列表
        Task[][] taskResult = new Task[p][tasks.length - 1];
        for (int i = 0; i < tasks.length; i++) {
            Integer min = getMinId(countP);
            countP[min] += tasks[i].getTaskTime();
            taskResult[min][posicition[min]] = tasks[i];
            posicition[min] += 1;
        }
        for (Task[] tasks1 : taskResult) {
            for (Task task : tasks1) {
                if (task != null) {
                    System.out.print(task.getTaskName() + ":" + task.getTaskTime());
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }


    /**
     * 获取最小值id
     */
    public int getMinId(int[] countP) {
        if (countP.length <= 0) {
            return 0;
        }
        int min = countP[0];
        int minNum = 0;
        for (int i = 0; i < countP.length; i++) {
            if (min > countP[i]) {
                min = countP[i];
                minNum = i;
            }
        }
        return minNum;
    }

    public static void main(String[] args) {
        ProcessorScheduling fi = new ProcessorScheduling();
        fi.finishTask(3);
    }
}




