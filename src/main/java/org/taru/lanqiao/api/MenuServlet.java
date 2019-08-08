package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.dao.MenuDaoImpl;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 菜单相关
 */
@RestController
public class MenuServlet {

    /**
     * 根据id查询菜单详情
     *
     * @param menuId
     * @return
     * @author zhangqiang
     */
    @RequestMapping("/api/menu/find")
    public JsonResult findById(String menuId) {
        JsonResult result = null;
        try {
            MenuDaoImpl m = new MenuDaoImpl();
            List<Map<String, Object>> list = m.findById(menuId);
            if (list != null && list.size() > 0) {
                result = new JsonResult("200", "查询成功", list);
            } else {
                result = new JsonResult("404", "查询错误", null);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            result = new JsonResult("500", "系统异常", exp.getMessage());
        }
        return result;
    }

    /**
     * 添加菜单
     *
     * @param menuName
     * @param menuLevel
     * @param menuSequence
     * @param menuFatherId
     * @param menuDescribe
     * @param menuUrl
     * @param menuStatus
     * @return
     * @author zhangqiang
     */
    @RequestMapping(value = "/api/menu/add")
    public JsonResult add(String menuName,
                          String menuLevel,
                          String menuSequence,
                          String menuFatherId,
                          String menuDescribe,
                          String menuUrl,
                          String menuStatus) {
        JsonResult result = null;
        try {
            MenuDaoImpl m = new MenuDaoImpl();
            int row = m.add(menuName, menuLevel, menuSequence, menuFatherId, menuDescribe, menuUrl, menuStatus);
            if (row > 0) {
                result = new JsonResult("200", "添加成功", menuName);
            } else {
                result = new JsonResult("404", "添加失败", null);
            }
        } catch (Exception exp) {
            result = new JsonResult("500", "系统错误", exp.getMessage());
        }
        return result;
    }

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     * @author zhangqiang
     */
    @RequestMapping("/api/menu/delete")
    public JsonResult delete(String menuId) {
        JsonResult result = null;
        try {
            MenuDaoImpl m = new MenuDaoImpl();
            int row = m.delete(menuId);
            if (row > 0) {
                result = new JsonResult("200", "删除成功", null);
            } else {
                result = new JsonResult("404", "删除失败", null);
            }
        } catch (Exception exp) {
            result = new JsonResult("500", "系统错误", exp.getMessage());
        }
        return result;
    }

    /**
     * 修改菜单
     *
     * @param menuName
     * @param menuLevel
     * @param menuSequence
     * @param menuFatherId
     * @param menuDescribe
     * @param menuUrl
     * @param menuStatus
     * @param menuId
     * @return
     * @author zhangqiang
     */
    @RequestMapping("/api/menu/update")
    public JsonResult update(String menuName,
                             String menuLevel,
                             String menuSequence,
                             String menuFatherId,
                             String menuDescribe,
                             String menuUrl,
                             String menuStatus,
                             String menuId) {
        JsonResult result = null;
        try {
            MenuDaoImpl m = new MenuDaoImpl();
            int row = m.update(menuName, menuLevel, menuSequence, menuFatherId, menuDescribe, menuUrl, menuStatus, menuId);
            if (row > 0) {
                result = new JsonResult("200", "修改成功", menuName);
            } else {
                result = new JsonResult("404", "修改失败", null);
            }
        } catch (Exception exp) {
            result = new JsonResult("500", "系统错误", exp.getMessage());
        }
        return result;
    }


    /**
     * 查询菜单列表
     * @author ShaJunnans
     * @return
     */
    @RequestMapping("/api/menu/list")
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
            MenuDaoImpl impl = new MenuDaoImpl();
            PageResult pageResult = impl.queryList(pageNum,pageSize);
            if(pageResult != null){
                result = new JsonResult("200", "查询菜单列表成功", pageResult);
            }else{
                result = new JsonResult("404", "查询菜单列表失败", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500", "查询菜单列表异常", null);
        }

        return result;
    }

}
