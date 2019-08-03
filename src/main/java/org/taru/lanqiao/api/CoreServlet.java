package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.vo.JsonResult;

/**
 * 核心-交互 部分
 * 此处代码考虑移动分散至其他类中
 */
@RestController
public class CoreServlet {
    /**
     * 查询用户菜单
     * @author
     * @return
     */
    @RequestMapping("/api/user/query/menu")
    public JsonResult queryUserMenu(){
        JsonResult result = null;
        return result;
    }

    /**
     * 查询用户权限
     * @author
     * @return
     */
    @RequestMapping("/api/user/query/priv")
    public JsonResult queryUserPriv(){
        JsonResult result = null;

        return result;
    }

    /**
     * 上传图片
     * @author
     * @return
     */
    @RequestMapping("/api/upload")
    public JsonResult uploadImageFile(){
        JsonResult result = null;

        return result;
    }
}
