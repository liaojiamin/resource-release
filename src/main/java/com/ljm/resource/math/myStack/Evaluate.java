package com.ljm.resource.math.myStack;

/**
 * 计算器，中序表达式转后序表达式
 * @author liaojiamin
 * @Date:Created in 15:13 2020/12/3
 */
public class Evaluate {

    public static MyStack<String> middlePreTOAfterPre(String mathStr) {
        MyStack<String> number = new MyStack<>();
        MyStack<String> action = new MyStack<>();
        String[] chars = mathStr.split(" ");
        for (int i = 0; i < chars.length; i++) {
            String s = String.valueOf(chars[i]);
            switch (flag(s, action)) {
                case 1:
                    number.push(s);
                    break;
                case 2:
                    action.push(s);
                    break;
                case 3:
                    action.push(s);
                    break;
                case 4:
                    number.push(action.pop());
                    action.push(s);
                    break;
                case 5:
                    action.push(s);
                    break;
                case 6:
                    while (!action.isEmpty()) {
                        String temp = action.pop();
                        if (temp.matches("\\(")) {
                            break;
                        } else {
                            number.push(temp);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        while (!action.isEmpty()) {
            number.push(action.pop());
        }
        MyStack<String> temp = new MyStack<>();
        while (!number.isEmpty()) {
            temp.push(number.pop());
        }
        return temp;
    }


    /**
     * 中序转后序表达式的各种逻辑判断
     */
    public static int flag(String s, MyStack<String> action) {
        if (s.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")) {
            //数字
            return 1;
        }
        if (s.matches("(\\*)|(\\/)|(\\+)|(\\-)")) {
            if (action.isEmpty()) {
                //栈为空
                return 2;
            } else if (prior(s, action.getTop())) {
                //不为空，优先级高于栈顶元素
                return 3;
            } else {
                //不为空，优先级不高于于栈顶元素
                return 4;
            }
        }
        if (s.matches("\\(")) {
            //左括号
            return 5;
        }
        if (s.matches("\\)")) {
            //右括号
            return 6;
        }
        return 0;
    }

    /**
     * @return 优先级
     * @author: liaojiamin
     * @description:s 操作符， top 栈顶操作符
     */
    public static boolean prior(String s, String top) {
        if (top.matches("\\(")) {
            return true;
        }
        if (s.matches("(\\*)|(\\/)") && top.matches("(\\+)|(\\-)")) {
            return true;
        }
        return false;
    }

    public static Double evalutePostfix(MyStack<String> myStack){
        MyStack<String> newStack = new MyStack<>();
        while (!myStack.isEmpty()){
            String temp = myStack.pop();
            if(temp.matches("\\d+")){
                newStack.push(temp);
            }else if(temp.matches("(\\*)|(\\/)|(\\+)|(\\-)")){
                if(newStack.size() < 2){
                   break;
                }
                double a = Double.valueOf(newStack.pop());
                double b = Double.valueOf(newStack.pop());
                switch (temp){
                    case "+":
                        newStack.push(String.valueOf(b+a));
                        break;
                    case "-":
                        newStack.push(String.valueOf(b-a));
                        break;
                    case "*":
                        newStack.push(String.valueOf(b*a));
                        break;
                    case "/":
                        newStack.push(String.valueOf(b/a));
                        break;
                    default:
                        break;
                }

            }
        }
        return Double.valueOf(newStack.pop());
    }

    public static void main(String[] args) {
        MyStack<String> middlerStack = middlePreTOAfterPre("1 + 2 * 3 - 4 + 5");
        middlerStack.printMyStack();
        System.out.println();
        System.out.println(evalutePostfix(middlerStack));
    }
}
