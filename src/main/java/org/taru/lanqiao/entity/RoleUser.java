package org.taru.lanqiao.entity;

/**
 * 角色用户表
 * 对应数据库role_user表
 */
public class RoleUser {
    private String ruRoleId;    // 角色编号
    private String ruUserId;    // 用户编号

    // get set 方法
    public String getRuRoleId() {
        return ruRoleId;
    }

    public void setRuRoleId(String ruRoleId) {
        this.ruRoleId = ruRoleId;
    }

    public String getRuUserId() {
        return ruUserId;
    }

    public void setRuUserId(String ruUserId) {
        this.ruUserId = ruUserId;
    }
}
