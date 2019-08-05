package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Admin;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理员dao层
 */
public class AdminDaoImpl {
    /**
     * 管理员登录
     * @author
     * @return
     */
    public Admin login(String name,String password){
        String sql = "SELECT * FROM admins WHERE admin_name = ?  AND admin_password = ?";
        List<Map<String,Object>> dataList = DbUtil.query(sql,name,password);
        Admin admin = null;
        if(dataList != null && dataList.size() > 0){
            Map<String,Object> map = dataList.get(0);
            admin = new Admin();
            admin.setAdminId(StringUtil.valueOf(map.get("admin_id")));
            admin.setAdminName(StringUtil.valueOf(map.get("admin_name")));
            admin.setAdminTelephone(StringUtil.valueOf(map.get("admin_telephone")));
            admin.setAdminPhoto(StringUtil.valueOf(map.get("admin_photo")));
            admin.setAdminComment(StringUtil.valueOf(map.get("admin_comment")));
            admin.setAdminStatus(StringUtil.valueOf(map.get("admin_status")));
        }

        DbUtil.close();
        return admin;
    }

    /**
     * 管理员列表
     * @return ShaJunnan
     * @author
     */
    public List<Admin> queryList() {
        String sql = "SELECT * FROM admins";
        List<Map<String,Object>> dataList = DbUtil.query(sql);
        List<Admin> resultList = null;
        if(dataList != null && dataList.size() > 0){
            resultList = new ArrayList<Admin>();
            for(Map<String,Object> map:dataList){
                Admin admin = new Admin();
                admin.setAdminId(StringUtil.valueOf(map.get("admin_id")));
                admin.setAdminName(StringUtil.valueOf(map.get("admin_name")));
                admin.setAdminPassword(StringUtil.valueOf(map.get("admin_password")));
                admin.setAdminTelephone(StringUtil.valueOf(map.get("admin_telephone")));
                admin.setAdminPhoto(StringUtil.valueOf(map.get("admin_photo")));
                admin.setAdminComment(StringUtil.valueOf(map.get("admin_comment")));
                admin.setAdminStatus(StringUtil.valueOf(map.get("admin_status")));

                resultList.add(admin);
            }
        }

        DbUtil.close();
        return resultList;
    }


    /**
     * 管理员详情
     * @author ShaJunnan
     * @param id
     * @return
     */
    public Admin findById(String id){
        String sql = "SELECT * FROM admins WHERE admin_id = ?";
        List<Map<String,Object>> dataList = DbUtil.query(sql,id);
        Admin admin = null;
        if(dataList != null && dataList.size() > 0){
            Map<String,Object> map = dataList.get(0);
            admin = new Admin();
            admin.setAdminId(StringUtil.valueOf(map.get("admin_id")));
            admin.setAdminName(StringUtil.valueOf(map.get("admin_name")));
            admin.setAdminTelephone(StringUtil.valueOf(map.get("admin_telephone")));
            admin.setAdminPhoto(StringUtil.valueOf(map.get("admin_photo")));
            admin.setAdminComment(StringUtil.valueOf(map.get("admin_comment")));
            admin.setAdminStatus(StringUtil.valueOf(map.get("admin_status")));
        }

        DbUtil.close();
        return admin;
    }
}
