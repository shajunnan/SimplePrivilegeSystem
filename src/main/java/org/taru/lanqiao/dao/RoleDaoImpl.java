package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Role;
import org.taru.lanqiao.util.DbUtil;

import java.util.List;
import java.util.Map;

public class RoleDaoImpl {
    /**
     * 根据id查询角色详情
     * @author ShaJunnan
     * @param roleId
     * @return
     */
    public Role findById(String roleId){
        Role role = null;
        String sql = "SELECT * FROM roles WHERE role_id = ?";
        List<Map<String,Object>> dataList = DbUtil.query(sql,roleId);
        if(dataList != null && dataList.size() > 0){
            Map<String,Object> map = dataList.get(0);
            role = new Role();
            role.setRoleId(String.valueOf(map.get("role_id")));
            role.setRoleName(String.valueOf(map.get("role_name")));
            role.setRoleDescribe(String.valueOf(map.get("role_describe")));
            role.setRoleStatus(String.valueOf(map.get("role_status")));
        }

        return role;
    }


    /**
     * 添加角色
     * @author ShaJunnan
     * @param role
     * @return
     */
    public int add(Role role){
        String sql = "INSERT INTO roles(role_name,role_describe,role_status) VALUES(?,?,?)";
        int num = DbUtil.update(sql,role.getRoleName(),role.getRoleDescribe(),role.getRoleStatus());
        return num;
    }


    /**
     * 删除角色，物理删除
     * @author ShaJunnan
     * @param roleId
     * @return
     */
    public int delete(String roleId){
        String sql = "DELETE FROM roles WHERE role_id = ?";
        int num = DbUtil.update(sql,roleId);
        return num;
    }


    /**
     * 修改角色
     * @author ShaJunnan
     * @param role
     * @return
     */
    public int update(Role role){
        String sql = "update roles set role_name = ?,role_describe = ?,role_status = ? where role_id = ?";
        int num = DbUtil.update(sql,role.getRoleName(),role.getRoleDescribe(),role.getRoleStatus(),role.getRoleId());
        return num;
    }
}
