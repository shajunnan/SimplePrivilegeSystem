package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 菜单相关
 */
@RestController
public class MenuServlet {

    /**
     * 根据id查询菜单详情
     * @author
     * @return
     */
    @RequestMapping("/api/menu/find")
    public JsonResult findById(){
        JsonResult result = null;

        return null;
    }

    /**
     * 添加菜单
     * @author
     * @return
     */
    @RequestMapping("/api/menu/add")
    public JsonResult add(){
        JsonResult result = null;

        return null;
    }

    /**
     * 删除菜单
     * @author
     * @return
     */
    @RequestMapping("/api/menu/delete")
    public JsonResult delete(){
        JsonResult result = null;

        return null;
    }

    /**
     *修改菜单
     * @author
     * @return
     */
    @RequestMapping("/api/menu/update")
    public JsonResult update(){
        JsonResult result = null;

        return null;
    }

}
