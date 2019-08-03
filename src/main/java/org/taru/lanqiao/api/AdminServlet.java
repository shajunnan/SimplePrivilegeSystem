package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.vo.JsonResult;

import java.util.List;

/**
 * 管理员相关
 */
@RestController
public class AdminServlet {

    /**
     * 管理员登录
     * @author
     * @return
     */
    @RequestMapping("/admin/login")
    public JsonResult login(){
        JsonResult result = null;

        return result;
    }

    /**
     * 管理员列表
     * @author
     * @return
     */
    @RequestMapping("/admin/list")
    public JsonResult queryList(){
        JsonResult result = null;

        return result;
    }


    /**
     * 管理员详情
     * @author
     * @return
     */
    @RequestMapping("/admin/find")
    public JsonResult findById(){
        JsonResult result = null;

        return result;
    }

}
