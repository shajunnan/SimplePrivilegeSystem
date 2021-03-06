package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.dao.AdminDaoImpl;
import org.taru.lanqiao.entity.Admin;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理员相关
 */
@RestController
public class AdminServlet {
    /**
     * 管理员登录
     * @author ShaJunnan
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/admin/login")
    public JsonResult login(@RequestParam("name")String name,@RequestParam("password")String password){
        JsonResult result = null;

        try {
            AdminDaoImpl impl = new AdminDaoImpl();
            Admin admin = impl.login(name,password);
            if(admin != null){
                result = new JsonResult("200","管理员登陆成功",admin);
            }else {
                result = new JsonResult("404","管理员登陆失败,账号或密码错误",null);
            }
        }catch (Exception e){
            result = new JsonResult("500","管理员登陆异常",null);
        }

        return result;
    }

    /**
     * 管理员列表
     * @author ShaJunnan
     * @return
     */
    @RequestMapping("/admin/list")
    public JsonResult queryList(HttpServletRequest request) {
        JsonResult result = null;
        int pageNum = 1;
        int pageSize = 10;
        if(request.getParameter("pageNum") != null){
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        if(request.getParameter("pageSize") != null){
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }

        try {
            AdminDaoImpl impl = new AdminDaoImpl();
            PageResult pageResult = impl.queryList(pageNum,pageSize);
            if(pageResult != null){
                result = new JsonResult("200", "查询管理员列表成功", pageResult);
            }else{
                result = new JsonResult("404", "查询管理员列表失败", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500", "系统异常", null);
        }

        return result;
    }


    /**
     * 管理员详情
     * @author ShaJunnan
     * @param id
     * @return
     */
    @RequestMapping("/admin/find")
    public JsonResult findById(@RequestParam("id") String id){
        JsonResult result = null;

        try {
            AdminDaoImpl impl = new AdminDaoImpl();
            Admin admin = impl.findById(id);
            if(admin != null){
                result = new JsonResult("200","查询管理员详情成功",admin);
            }else{
                result = new JsonResult("404","查询管理员详情失败",null);
            }
        }catch (Exception e){
            result = new JsonResult("500","查询管理员详情异常",null);
        }

        return result;
    }

}
