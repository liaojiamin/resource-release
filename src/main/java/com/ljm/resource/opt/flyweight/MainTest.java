package com.ljm.resource.opt.flyweight;

/**
 * @author liaojiamin
 * @Date:Created in 10:51 2020/3/24
 */
public class MainTest {
    public static void main(String[] args) {
        ReportManagerFactory reportManagerFactory = new ReportManagerFactory();
        IReportManager reportManager = reportManagerFactory.getFinancialReportManager("A");
        System.out.println(reportManager.createReport());
    }
}
