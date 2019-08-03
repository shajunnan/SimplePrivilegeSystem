package org.taru.lanqiao.entity;

/**
 * 角色菜单
 * 对应数据库role_menu表
 */
public class RoleMenu {
    private String rmRoleId;    // 角色编号
    private String rmMenuId;    // 菜单编号

    // get set 方法
    public String getRmRoleId() {
        return rmRoleId;
    }

    public void setRmRoleId(String rmRoleId) {
        this.rmRoleId = rmRoleId;
    }

    public String getRmMenuId() {
        return rmMenuId;
    }

    public void setRmMenuId(String rmMenuId) {
        this.rmMenuId = rmMenuId;
    }
}
