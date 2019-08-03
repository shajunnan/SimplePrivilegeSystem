package org.taru.lanqiao.entity;

/**
 * 权限
 * 对应数据库privs表
 */
public class Privilege {
    private String privId;          // 编号
    private String privName;        // 名称
    private String privFatherId;    // 父权限编号
    private String privDescribe;    // 描述
    private String privUrl;         // 权限Url(api接口)
    private String privStatus;      // 状态

    // get set 方法
    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId;
    }

    public String getPrivName() {
        return privName;
    }

    public void setPrivName(String privName) {
        this.privName = privName;
    }

    public String getPrivFatherId() {
        return privFatherId;
    }

    public void setPrivFatherId(String privFatherId) {
        this.privFatherId = privFatherId;
    }

    public String getPrivDescribe() {
        return privDescribe;
    }

    public void setPrivDescribe(String privDescribe) {
        this.privDescribe = privDescribe;
    }

    public String getPrivUrl() {
        return privUrl;
    }

    public void setPrivUrl(String privUrl) {
        this.privUrl = privUrl;
    }

    public String getPrivStatus() {
        return privStatus;
    }

    public void setPrivStatus(String privStatus) {
        this.privStatus = privStatus;
    }
}
