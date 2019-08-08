package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Role;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.PageResult;

import java.util.ArrayList;
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

    /**
     * 查询角色列表
     * @author ShaJunnan
     * @return
     */
    public PageResult queryList(int pageNum, int pageSize){
        // limit 偏移，查询条数
        String sql = "select * from roles limit ?,?";
        List<Map<String,Object>> dataList = DbUtil.query(sql,(pageNum-1)*pageSize,pageSize);

        // 1、设置列表数据
        List<Object> objList = new ArrayList<Object>();
        if(dataList != null && dataList.size() > 0){
            for(Map<String,Object> map:dataList){
                Role role = new Role();
                role.setRoleId(StringUtil.valueOf(map.get("role_id")));
                role.setRoleName(StringUtil.valueOf(map.get("role_name")));
                role.setRoleDescribe(StringUtil.valueOf(map.get("role_describe")));
                role.setRoleStatus(StringUtil.valueOf(map.get("role_status")));

                objList.add(role);
            }
        }

        // 2、查询总商品数据条数
        String sql2 = "select count(*) as row_count from roles";
        List<Map<String, Object>> countList = DbUtil.query(sql2);
        int total = Integer.parseInt(StringUtil.valueOf(countList.get(0).get("row_count")));
        int pages = (int) Math.ceil(total * 1.0 / pageSize); // 向上取整为总页数,pageSize为0时的情况需特殊处理


        // 3、装入PageResult对象
        PageResult pageResult = new PageResult();
        pageResult.setList(objList);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotal(total);
        pageResult.setPages(pages);

        // 关闭数据库连接
        DbUtil.close();
        return pageResult;
    }
}
