package com.ljm.resource.kilim;

import java.math.BigDecimal;

/**
 * @author liaojiamin
 * @Date:Created in 15:36 2020/4/24
 */
public class Calculation {
    private BigDecimal dividend;
    private BigDecimal divisor;
    private BigDecimal answer;
    public Calculation(BigDecimal dividend, BigDecimal divisor){
        this.dividend = dividend;
        this.divisor = divisor;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public BigDecimal getDivisor() {
        return divisor;
    }

    public void setDivisor(BigDecimal divisor) {
        this.divisor = divisor;
    }

    public BigDecimal getAnswer() {
        return answer;
    }

    public void setAnswer(BigDecimal answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Calculation{" +
                "dividend=" + dividend +
                ", divisor=" + divisor +
                ", answer=" + answer +
                '}';
    }
}
