package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.dao.PrivilegeDaoImpl;
import org.taru.lanqiao.entity.Privilege;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限相关
 */
@RestController
public class PrivilegeServlet {
    /**
     * 根据id查询权限详情
     * @return
     * @author XieHeng
     */
    @RequestMapping("/api/priv/find")
    public JsonResult findById(@RequestParam("privId") String id) {
        JsonResult result = null;
        try {
            PrivilegeDaoImpl privimpl = new PrivilegeDaoImpl();
            Privilege priv = privimpl.findById(id);
            if (priv != null) {
                result = new JsonResult("200", "查询成功", null);
            } else {
                result = new JsonResult("404", "查询失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("500", "查询异常", null);
        }


        return result;
    }

    /**
     * 添加权限
     * @return
     * @author XieHeng
     */
    @RequestMapping("/api/priv/add")
    public JsonResult add(@RequestParam("privName") String privName, @RequestParam("privFatherId") String privFid, @RequestParam("privDescribe") String privDescribe, @RequestParam("privUrl") String privUrl, @RequestParam("privStatus") String privStatus) {
        JsonResult result = null;
        try {
            Privilege priv = new Privilege();
            priv.setPrivName(privName);
            priv.setPrivUrl(privUrl);
            priv.setPrivStatus(privStatus);
            priv.setPrivFatherId(privFid);
            priv.setPrivDescribe(privDescribe);
            PrivilegeDaoImpl privimpl = new PrivilegeDaoImpl();
            int num = privimpl.add(priv);
            if (num > 0) {
                result = new JsonResult("200", "添加权限成功", null);
            } else {
                result = new JsonResult("404", "添加权限失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("500", "添加权限异常", null);
        }
        return result;
    }

    /**
     * 删除权限
     * @param id
     * @return
     * @author XieHeng
     */
    @RequestMapping("/api/priv/delete")
    public JsonResult delete(@RequestParam("privId") String id) {
        JsonResult result = null;

        try {
            PrivilegeDaoImpl privimpl = new PrivilegeDaoImpl();
            int num = privimpl.delete(id);
            if (num > 0) {
                result = new JsonResult("200", "删除成功", null);
            } else {
                result = new JsonResult("404", "删除失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("500", "删除异常", null);
        }

        return result;
    }

    /**
     * 修改权限
     * @return
     * @author XieHeng
     */
    @RequestMapping("/api/priv/update")
    public JsonResult update(@RequestParam("privName") String privName, @RequestParam("privFatherId") String privFid, @RequestParam("privDescribe") String privDescribe, @RequestParam("privUrl") String privUrl, @RequestParam("privStatus") String privStatus, @RequestParam("privId") String id) {
        JsonResult result = null;
        try {
            Privilege priv = new Privilege();
            priv.setPrivDescribe(privDescribe);
            priv.setPrivFatherId(privFid);
            priv.setPrivStatus(privStatus);
            priv.setPrivUrl(privUrl);
            priv.setPrivName(privName);
            priv.setPrivId(id);
            PrivilegeDaoImpl pri =new PrivilegeDaoImpl();
            int num = pri.update(priv);
            if (num > 0) {
                result = new JsonResult("200", "修改成功", null);
            } else {
                result = new JsonResult("404", "修改失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("500", "修改异常 ", null);
        }
        return result;
    }


    /**
     * 查询权限列表
     * @author ShaJunnans
     * @return
     */
    @RequestMapping("/api/priv/list")
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
            PrivilegeDaoImpl impl = new PrivilegeDaoImpl();
            PageResult pageResult = impl.queryList(pageNum,pageSize);
            if(pageResult != null){
                result = new JsonResult("200", "查询权限列表成功", pageResult);
            }else{
                result = new JsonResult("404", "查询权限列表失败", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500", "查询权限列表异常", null);
        }

        return result;
    }
}
