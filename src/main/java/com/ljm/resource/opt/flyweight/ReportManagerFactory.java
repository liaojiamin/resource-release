package com.ljm.resource.opt.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaojiamin
 * @Date:Created in 10:35 2020/3/24
 */
public class ReportManagerFactory {
    Map<String, IReportManager> financialMap = new HashMap<>();
    Map<String, IReportManager> empolyeeMap = new HashMap<>();

    public IReportManager getFinancialReportManager(String tenantId){
        if(!financialMap.containsKey(tenantId)){
            IReportManager financialReportManager = new FinancialReportManager(tenantId);
            financialMap.put(tenantId, financialReportManager);
            return financialReportManager;
        }
        return financialMap.get(tenantId);
    }
    public IReportManager getEmployeeReportReportManager(String tenantId){
        if(!empolyeeMap.containsKey(tenantId)){
            IReportManager employeeReportManager= new EmployeeReportManager(tenantId);
            empolyeeMap.put(tenantId, employeeReportManager);
            return employeeReportManager;
        }
        return empolyeeMap.get(tenantId);
    }
}
