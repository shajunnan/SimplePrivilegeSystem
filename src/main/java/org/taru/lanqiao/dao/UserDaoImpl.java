package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.User;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * 用户dao层
 */
public class UserDaoImpl {
    /**
     * 用户登录
     * @author
     * @return
     */
    public User login(String name,String password){
        User user = null;
        String sql="select user_id,user_name,user_password,user_telephone,user_photo,user_comment,user_status from users where user_name=?";
            try {
                user = new User();
                List<Map<String, Object>> list = DbUtil.query(sql, name);
                if (list.size() == 0||!password.equals(list.get(0).get("user_password"))) {
                    user=null;
                } else {
                    user.setUserId(StringUtil.valueOf(list.get(0).get("user_id")));
                    user.setUserName(StringUtil.valueOf(list.get(0).get("user_name")));
                    user.setUserTelephone(StringUtil.valueOf(list.get(0).get("user_telephone")));
                    user.setUserPhoto(StringUtil.valueOf(list.get(0).get("user_photo")));
                    user.setUserComment(StringUtil.valueOf(list.get(0).get("user_comment")));
                    user.setUserStatus(StringUtil.valueOf( list.get(0).get("user_status")));
                }
            }catch (Exception ex){
                user=null;
            }
        return user;
    }

    /**
     * 用户注册
     * @author
     * @return
     */
    public int regist(User user){
        int num = 0;
        String sql="INSERT INTO users (user_id,user_name,user_password,user_telephone,user_photo,user_address,user_comment) VALUES (?,?,?,?,?,?,?)";
        try{
            num= DbUtil.update(sql,user.getUserId(),user.getUserName(),user.getUserPassword(),user.getUserTelephone(),user.getUserPhoto(),user.getUserAddress(),user.getUserComment());
            }catch (Exception ex){
            num=-1;
        }
        return num;
    }

    /**
     * 用户删除
     * @author
     * @return
     */
    public int delete(String userId){
        int num = 0;
        String sql="update users set user_status=0 where user_id=?";
        try {
            num=DbUtil.update(sql,userId);
        }catch (Exception ex){
            num=0;
        }
        return num;
    }

    /**
     * 用户修改
     * @author
     * @return
     */
    public int update(User user){
        int num = 0;
        String sql="update users set user_name=?,user_password=?,user_telephone=?,user_photo=?,user_address=?,user_comment=? where user_id=?";
        try {
            num= DbUtil.update(sql,user.getUserName(),user.getUserPassword(),user.getUserTelephone(),user.getUserPhoto(),user.getUserAddress(),user.getUserComment(),user.getUserId());
        }catch (Exception ex){
            num=0;
        }
        return num;
    }

    /**
     * 根据id查询用户详情
     * @author
     * @return
     */
    public User findById(String userId){
        User user = null;
        String sql="select user_id,user_name,user_telephone,user_photo,user_comment,user_status from users where user_id=?";
        List<Map<String, Object>> list = DbUtil.query(sql, userId);
        try{
            if (list.size() == 0) {
            } else {
                user = new User();
                user.setUserId(StringUtil.valueOf(list.get(0).get("user_id")));
                user.setUserName(StringUtil.valueOf(list.get(0).get("user_name")));
                user.setUserTelephone(StringUtil.valueOf(list.get(0).get("user_telephone")));
                user.setUserPhoto(StringUtil.valueOf(list.get(0).get("user_photo")));
                user.setUserComment(StringUtil.valueOf(list.get(0).get("user_comment")));
                user.setUserStatus(StringUtil.valueOf( list.get(0).get("user_status")));
            }
        }catch(Exception ex){
            user=null;
        }
        return user;
    }
}
