package com.ljm.resource.jvm.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * FM表Result
 */
public class FmResult implements Serializable {
    public FmResult() {
    }

    public FmResult(Integer sortNo, String platform) {
        this.sortNo = sortNo;
        this.platform = platform;
    }

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 7327233719573948963L;


    /**
     * 主键ID
     */
    private Long id;

    /**
     * 排序值
     */
    private Integer sortNo;

    /**
     * FM名称
     */
    private String fmName;


    /**
     * 开放平台号，多个半角逗号隔开
     */
    private String platform;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getFmName() {
        return fmName;
    }

    public void setFmName(String fmName) {
        this.fmName = fmName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
