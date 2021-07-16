package com.ljm.resource.math.dynamic;

/**
 * 矩阵对象定义
 * @author liaojiamin
 * @Date:Created in 15:21 2021/3/16
 */
public class Matrix2By2 {
    private long m_00;
    private long m_01;
    private long m_10;
    private long m_11;

    public Matrix2By2(){
        this.m_00 = 0;
        this.m_01 = 0;
        this.m_10 = 0;
        this.m_11 = 0;
    }

    public Matrix2By2(long m00, long m01, long m10, long m11){
        this.m_00 = m00;
        this.m_01 = m01;
        this.m_10 = m10;
        this.m_11 = m11;
    }

    public long getM_00() {
        return m_00;
    }

    public void setM_00(long m_00) {
        this.m_00 = m_00;
    }

    public long getM_01() {
        return m_01;
    }

    public void setM_01(long m_01) {
        this.m_01 = m_01;
    }

    public long getM_10() {
        return m_10;
    }

    public void setM_10(long m_10) {
        this.m_10 = m_10;
    }

    public long getM_11() {
        return m_11;
    }

    public void setM_11(long m_11) {
        this.m_11 = m_11;
    }
}
