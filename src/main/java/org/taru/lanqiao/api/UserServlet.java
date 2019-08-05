package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.dao.UserDaoImpl;
import org.taru.lanqiao.entity.User;
import org.taru.lanqiao.util.IdUtil;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 用户相关
 */
@RestController
public class UserServlet {
    /**
     * 用户登录
     * @author
     * @return
     */
    @RequestMapping("/api/user/login")
    public JsonResult login(String name,String password){
        JsonResult result = null;
        UserDaoImpl impl=new UserDaoImpl();
        User user=impl.login(name, password);
        if(user==null){
            result=new JsonResult("404", "用户名或者密码错误", "");
        }else{
            result= new JsonResult("200", "查询用户成功", user);
        }
        return result;
    }

    /**
     * 用户注册
     * @author
     * @return
     */
    @RequestMapping("/api/user/regist")
    public JsonResult regist(String userName, String userPassword,
                             String userTelephone, String userPhoto,
                             String userAddress, String userComment){
        JsonResult result = null;
        User user=new User();
        user.setUserId(IdUtil.getUuid());
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserTelephone(userTelephone);
        user.setUserPhoto(userPhoto);
        user.setUserAddress(userAddress);
        user.setUserComment(userComment);
        UserDaoImpl impl=new UserDaoImpl();
        int i=impl.regist(user);
        if(i==0){
            result=new JsonResult("404","注册失败","");
        }else{
            result=new JsonResult("200","注册成功","");
        }
        return result;
    }

    /**
     * 用户删除
     * @author
     * @return
     */
    @RequestMapping("/api/user/delete")
    public JsonResult delete(String userId){
        JsonResult result = null;
        UserDaoImpl impl=new UserDaoImpl();
        int i=impl.delete(userId);
        if(i==0){
            result = new JsonResult("404", "删除失败", "");
        }else if(i==-1) {
            result=new JsonResult("500","删除异常","");
        }else{
            result=new JsonResult("200","删除成功","");
        }
        return result;
    }

    /**
     * 用户修改
     * @author
     * @return
     */
    @RequestMapping("/api/user/update")
        public JsonResult update(String userId,String userName,String userPassword,
                                 String userTelephone,String userPhoto,String userAddress,String userComment){
        JsonResult result = null;
        User user=new User();
        UserDaoImpl impl=new UserDaoImpl();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserTelephone(userTelephone);
        user.setUserPhoto(userPhoto);
        user.setUserAddress(userAddress);
        user.setUserComment(userComment);
        int i = impl.update(user);
        if(i==0){
            result=new JsonResult("404","修改失败","");
        }else if(i==-1) {
            result=new JsonResult("500","修改异常","");
        }else{
            result=new JsonResult("200","修改成功","");
        }
        return result;
    }

    /**
     * 根据id查询用户详情
     * @author
     * @return
     */
    @RequestMapping("/api/user/find")
    public JsonResult findById(String userId){
        JsonResult result = null;
        UserDaoImpl impl=new UserDaoImpl();
        User user=impl.findById(userId);
        if(user==null){
            result=new JsonResult("404", "查询用户为空", "");
        }else{
            result= new JsonResult("200", "查询用户成功", user);
        }
        return result;
    }

}
