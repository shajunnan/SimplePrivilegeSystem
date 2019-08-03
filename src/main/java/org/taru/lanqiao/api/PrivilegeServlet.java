package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 权限相关
 */
@RestController
public class PrivilegeServlet {
    /**
     * 根据id查询权限详情
     * @author
     * @return
     */
    @RequestMapping("/api/priv/find")
    public JsonResult findById(){
        JsonResult result = null;

        return result;
    }

    /**
     * 添加权限
     * @author
     * @return
     */
    @RequestMapping("/api/priv/add")
    public JsonResult add(){
        JsonResult result = null;

        return result;
    }

    /**
     * 删除权限
     * @author
     * @return
     */
    @RequestMapping("/api/priv/delete")
    public JsonResult delete(){
        JsonResult result = null;

        return result;
    }

    /**
     * 修改权限
     * @author
     * @return
     */
    @RequestMapping("/api/priv/update")
    public JsonResult update(){
        JsonResult result = null;

        return result;
    }
}
