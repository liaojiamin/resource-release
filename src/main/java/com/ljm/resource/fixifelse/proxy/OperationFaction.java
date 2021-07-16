package com.ljm.resource.fixifelse.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author liaojiamin
 * @Date:Created in 11:06 2020/6/13
 */
public class OperationFaction  {
    private static final Map<String, Operator> operaMap = new HashMap<>();
    static {
        operaMap.put("add", new Addition());
        operaMap.put("modulo", new Modulo());
    }

    public static Optional<Operator> getOperation(String operation){
        return Optional.ofNullable(operaMap.get(operation));
    }


    public int calculateUsingFactory(int a, int b, String operator) {
        Operator targetOperation = OperationFaction
                .getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a, b);
    }

}
