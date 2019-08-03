package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 角色相关
 */
@RestController
public class RoleServlet {
    /**
     * 根据id查询角色详情
     * @author
     * @return
     */
    @RequestMapping("/api/role/find")
    public JsonResult findById(){
        JsonResult result = null;

        return result;
    }

    /**
     * 添加角色
     * @author
     * @return
     */
    @RequestMapping("/api/role/add")
    public JsonResult add(){
        JsonResult result = null;

        return result;
    }

    /**
     * 删除角色
     * @author
     * @return
     */
    @RequestMapping("/api/role/delete")
    public JsonResult delete(){
        JsonResult result = null;

        return result;
    }

    /**
     * 修改角色
     * @author
     * @return
     */
    @RequestMapping("/api/role/update")
    public JsonResult update(){
        JsonResult result = null;

        return result;
    }
}
