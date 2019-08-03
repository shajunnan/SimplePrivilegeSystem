package org.taru.lanqiao.entity;

/**
 * 角色权限表
 * 对应数据库role_priv表
 */
public class RolePriv {
    private String rpRoleId;    // 角色编号
    private String rpPrivId;    // 权限编号

    // get set 方法

    public String getRpRoleId() {
        return rpRoleId;
    }

    public void setRpRoleId(String rpRoleId) {
        this.rpRoleId = rpRoleId;
    }

    public String getRpPrivId() {
        return rpPrivId;
    }

    public void setRpPrivId(String rpPrivId) {
        this.rpPrivId = rpPrivId;
    }
}
