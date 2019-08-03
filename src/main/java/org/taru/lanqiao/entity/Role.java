package org.taru.lanqiao.entity;

/**
 * 角色
 * 对应数据库roles表
 */
public class Role {
    private String roleId;          // 编号
    private String roleName;        // 名称
    private String roleDescribe;    // 描述
    private String roleStatus;      // 状态

    // get set 方法
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }
}
