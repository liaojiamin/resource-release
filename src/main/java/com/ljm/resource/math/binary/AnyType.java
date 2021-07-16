package com.ljm.resource.math.binary;

/**
 * @author liaojiamin
 * @Date:Created in 15:57 2020/12/24
 */
public class AnyType implements Comparable<AnyType> {
    private Object element;

    public AnyType(Object element){
        this.element = element;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    @Override
    public int compareTo(AnyType o) {
        if (o == null) {
            return -1;
        }
        int flag;
        if (o instanceof AnyType) {
            int myElement = Integer.valueOf(this.element.toString()) - Integer.valueOf(o.getElement().toString());
            flag = myElement > 0 ? 1 : myElement == 0 ? 0 : -1;
        } else {
            flag = this.element.toString().compareTo(o.toString());
        }

        if (flag == 0) {
            return 0;
        } else if (flag > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
