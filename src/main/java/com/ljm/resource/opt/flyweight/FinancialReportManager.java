package com.ljm.resource.opt.flyweight;

/**
 * @author liaojiamin
 * @Date:Created in 10:35 2020/3/24
 */
public class FinancialReportManager implements IReportManager {
    private String tenantId;
    public FinancialReportManager(String tenantId){
        this.tenantId = tenantId;
    }
    @Override
    public String createReport() {
        return "is financial";
    }
}
