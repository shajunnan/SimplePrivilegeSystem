package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public JsonResult login(){
        JsonResult result = null;

        return result;
    }

    /**
     * 用户注册
     * @author
     * @return
     */
    @RequestMapping("/api/user/regist")
    public JsonResult regist(){
        JsonResult result = null;

        return result;
    }

    /**
     * 用户删除
     * @author
     * @return
     */
    @RequestMapping("/api/user/delete")
    public JsonResult delete(){
        JsonResult result = null;

        return result;
    }

    /**
     * 用户修改
     * @author
     * @return
     */
    @RequestMapping("/api/user/update")
    public JsonResult update(){
        JsonResult result = null;

        return result;
    }

    /**
     * 根据id查询用户详情
     * @author
     * @return
     */
    @RequestMapping("/api/user/find")
    public JsonResult findById(){
        JsonResult result = null;

        return result;
    }

}
