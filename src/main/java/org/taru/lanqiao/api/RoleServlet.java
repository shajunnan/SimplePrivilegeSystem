package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.dao.RoleDaoImpl;
import org.taru.lanqiao.entity.Role;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色相关
 */
@RestController
public class RoleServlet {
    /**
     * 根据id查询角色详情
     * @author ShaJunnan
     * @param roleId
     * @return
     */
    @RequestMapping("/api/role/find")
    public JsonResult findById(@RequestParam("roleId") String roleId){
        JsonResult result = null;

        try{
            RoleDaoImpl impl = new RoleDaoImpl();
            Role role = impl.findById(roleId);
            if(role != null){
                result = new JsonResult("200","查询角色详情成功",role);
            }else{
                result = new JsonResult("404","查询角色详情失败",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500","查询角色详情异常",null);
        }

        return result;
    }


    /**
     * 添加角色
     * @author ShaJunnan
     * @param roleName
     * @param roleDescribe
     * @param roleStatus
     * @return
     */
    @RequestMapping("/api/role/add")
    public JsonResult add(@RequestParam("roleName") String roleName,
                          @RequestParam("roleDescribe") String roleDescribe,
                          @RequestParam("roleStatus") String roleStatus){
        JsonResult result = null;
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleDescribe(roleDescribe);
        role.setRoleStatus(roleStatus);

        try {
            RoleDaoImpl impl = new RoleDaoImpl();
            int num = impl.add(role);
            if (num > 0) {
                result = new JsonResult("200","添加角色成功",null);
            } else {
                result = new JsonResult("404","添加角色失败",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500","添加角色异常",null);
        }

        return result;
    }


    /**
     * 删除角色
     * @author ShaJunnan
     * @param roleId
     * @return
     */
    @RequestMapping("/api/role/delete")
    public JsonResult delete(@RequestParam("roleId") String roleId){
        JsonResult result = null;

        try{
            RoleDaoImpl impl = new RoleDaoImpl();
            int num = impl.delete(roleId);
            if(num > 0){
                result = new JsonResult("200","删除角色成功",null);
            }else {
                result = new JsonResult("404","删除角色失败",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500","删除角色异常",null);
        }

        return result;
    }


    /**
     * 修改角色
     * @author ShaJunnan
     * @param roleId
     * @param roleName
     * @param roleDescribe
     * @param roleStatus
     * @return
     */
    @RequestMapping("/api/role/update")
    public JsonResult update(@RequestParam("roleId") String roleId,
                             @RequestParam("roleName") String roleName,
                             @RequestParam("roleDescribe") String roleDescribe,
                             @RequestParam("roleStatus") String roleStatus){
        JsonResult result = null;
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        role.setRoleDescribe(roleDescribe);
        role.setRoleStatus(roleStatus);
        try {
            RoleDaoImpl impl = new RoleDaoImpl();
            int num = impl.update(role);
            if(num > 0){
                result = new JsonResult("200","修改角色成功",null);
            }else {
                result = new JsonResult("404","修改角色失败",null);
            }
        }catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult("500","修改角色异常",null);
        }

        return result;
    }


    /**
     * 查询角色列表
     * @author ShaJunnans
     * @return
     */
    @RequestMapping("/api/role/list")
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
            RoleDaoImpl impl = new RoleDaoImpl();
            PageResult pageResult = impl.queryList(pageNum,pageSize);
            if(pageResult != null){
                result = new JsonResult("200", "查询角色列表成功", pageResult);
            }else{
                result = new JsonResult("404", "查询角色列表失败", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500", "查询角色列表异常", null);
        }

        return result;
    }
}
