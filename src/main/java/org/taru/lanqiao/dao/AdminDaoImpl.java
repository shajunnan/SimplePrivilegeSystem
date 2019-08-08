package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Admin;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.PageResult;

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
     * @author ShaJunnan
     * @return
     */
    public PageResult queryList(int pageNum, int pageSize){
        // limit 偏移，查询条数
        String sql = "select * from admins limit ?,?";
        List<Map<String,Object>> dataList = DbUtil.query(sql,(pageNum-1)*pageSize,pageSize);

        // 1、设置列表数据
        List<Object> objList = new ArrayList<Object>();
        if(dataList != null && dataList.size() > 0){
            for(Map<String,Object> map:dataList){
                Admin admin = new Admin();
                admin.setAdminId(StringUtil.valueOf(map.get("admin_id")));
                admin.setAdminName(StringUtil.valueOf(map.get("admin_name")));
                admin.setAdminPassword(StringUtil.valueOf(map.get("admin_password")));
                admin.setAdminComment(StringUtil.valueOf(map.get("admin_comment")));
                admin.setAdminPhoto(StringUtil.valueOf(map.get("admin_photo")));
                admin.setAdminTelephone(StringUtil.valueOf(map.get("admin_telephone")));
                admin.setAdminStatus(StringUtil.valueOf(map.get("admin_status")));

                objList.add(admin);
            }
        }

        // 2、查询总商品数据条数
        String sql2 = "select count(*) as row_count from admins";
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
