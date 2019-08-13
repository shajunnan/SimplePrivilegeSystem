package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.User;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户dao层
 */
public class UserDaoImpl {
    /**
     * 用户登录
     *
     * @param name
     * @param password
     * @return
     * @author XK
     */
    public User login(String name, String password) {
        User user = null;
        String sql = "select user_id,user_name,user_password,user_telephone,user_photo,user_comment,user_status from users where user_name=?";
        try {
            user = new User();
            List<Map<String, Object>> list = DbUtil.query(sql, name);
            if (list.size() == 0 || !password.equals(list.get(0).get("user_password"))) {
                user = null;
            } else {
                user.setUserId(StringUtil.valueOf(list.get(0).get("user_id")));
                user.setUserName(StringUtil.valueOf(list.get(0).get("user_name")));
                user.setUserTelephone(StringUtil.valueOf(list.get(0).get("user_telephone")));
                user.setUserTelephone(StringUtil.valueOf(list.get(0).get("user_address")));
                user.setUserPhoto(StringUtil.valueOf(list.get(0).get("user_photo")));
                user.setUserComment(StringUtil.valueOf(list.get(0).get("user_comment")));
                user.setUserStatus(StringUtil.valueOf(list.get(0).get("user_status")));
            }
        } catch (Exception ex) {
            user = null;
        }
        return user;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     * @author XK
     */
    public int regist(User user) {
        int num = 0;
        String sql = "INSERT INTO users (user_id,user_name,user_password,user_telephone,user_photo,user_address,user_comment,user_status) VALUES (?,?,?,?,?,?,?,?)";
        try {
            num = DbUtil.update(sql, user.getUserId(), user.getUserName(),
                    user.getUserPassword(), user.getUserTelephone(), user.getUserPhoto(),
                    user.getUserAddress(), user.getUserComment(),user.getUserStatus());
        } catch (Exception ex) {
            num = -1;
        }
        return num;
    }

    /**
     * 用户删除
     *
     * @param userId
     * @return
     * @author XK
     */
    public int delete(String userId) {
        int num = 0;
        String sql = "update users set user_status=0 where user_id=?";
        try {
            num = DbUtil.update(sql, userId);
        } catch (Exception ex) {
            num = 0;
        }
        return num;
    }

    /**
     * 用户修改
     *
     * @param user
     * @return
     * @author XK
     */
    public int update(User user) {
        int num = 0;
        String sql = "update users set user_name=?,user_password=?,user_telephone=?,user_photo=?,user_address=?,user_comment=?,user_status=? where user_id=?";
        try {
            num = DbUtil.update(sql, user.getUserName(), user.getUserPassword(), user.getUserTelephone(), user.getUserPhoto(), user.getUserAddress(), user.getUserComment(),user.getUserStatus(),user.getUserId());
        } catch (Exception ex) {
            num = 0;
        }
        return num;
    }

    /**
     * 根据id查询用户详情
     * @author XueKe
     * @param userId
     * @return
     */
    public User findById(String userId) {
        User user = null;
        String sql = "select user_id,user_name,user_telephone,user_photo,user_comment,user_status from users where user_id=?";
        List<Map<String, Object>> list = DbUtil.query(sql, userId);
        try {
            if (list.size() == 0) {
            } else {
                user = new User();
                user.setUserId(StringUtil.valueOf(list.get(0).get("user_id")));
                user.setUserName(StringUtil.valueOf(list.get(0).get("user_name")));
                user.setUserPassword(StringUtil.valueOf(list.get(0).get("user_password")));
                user.setUserTelephone(StringUtil.valueOf(list.get(0).get("user_telephone")));
                user.setUserTelephone(StringUtil.valueOf(list.get(0).get("user_address")));
                user.setUserPhoto(StringUtil.valueOf(list.get(0).get("user_photo")));
                user.setUserComment(StringUtil.valueOf(list.get(0).get("user_comment")));
                user.setUserStatus(StringUtil.valueOf(list.get(0).get("user_status")));
            }
        } catch (Exception ex) {
            user = null;
        }
        return user;
    }


    /**
     * 查询用户列表
     * @author ShaJunnan
     * @return
     */
    public PageResult queryList(int pageNum,int pageSize){
        // limit 偏移，查询条数
        String sql = "select * from users limit ?,?";
        List<Map<String,Object>> dataList = DbUtil.query(sql,(pageNum-1)*pageSize,pageSize);

        // 1、设置列表数据
        List<Object> objList = new ArrayList<Object>();
        if(dataList != null && dataList.size() > 0){
            for(Map<String,Object> map:dataList){
                User user = new User();
                user.setUserId(StringUtil.valueOf(map.get("user_id")));
                user.setUserName(StringUtil.valueOf(map.get("user_name")));
                user.setUserPassword(StringUtil.valueOf(map.get("user_password")));
                user.setUserTelephone(StringUtil.valueOf(map.get("user_telephone")));
                user.setUserTelephone(StringUtil.valueOf(map.get("user_address")));
                user.setUserPhoto(StringUtil.valueOf(map.get("user_photo")));
                user.setUserComment(StringUtil.valueOf(map.get("user_comment")));
                user.setUserStatus(StringUtil.valueOf(map.get("user_status")));

                objList.add(user);
            }
        }

        // 2、查询总商品数据条数
        String sql2 = "select count(*) as row_count from users";
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
