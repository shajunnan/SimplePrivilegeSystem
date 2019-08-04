package org.taru.lanqiao.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.taru.lanqiao.dao.MenuDaoImpl;
import org.taru.lanqiao.vo.JsonResult;

import java.util.List;
import java.util.Map;

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
    public JsonResult findById(String userId){
        JsonResult result = null;
        MenuDaoImpl m=new MenuDaoImpl();
        System.out.println(userId);
        List<Map<String,Object>> list= m.findById(userId);
        try{
            if(list!=null&&list.size()>0){
                result=new JsonResult("200","查询成功",list);
            }else{
                result=new JsonResult("404","用户名或密码错误",null);
            }
        }catch(Exception exp){
            result=new JsonResult("500","系统异常",exp.getMessage());
        }
        return result;
    }

    /**
     * 添加菜单
     * @author
     * @return
     */
    @RequestMapping(value = "/api/menu/add")
    public JsonResult add(
                          String menuName,
                          String menuLevel,
                          String menuSequence,
                          String menuFatherId,
                          String menuDescribe,
                          String menuUrl,
                          String menuStatus){
        JsonResult result = null;
        MenuDaoImpl m=new MenuDaoImpl();
        int row = m.add(menuName,menuLevel,menuSequence,menuFatherId,menuDescribe,menuUrl,menuStatus);
        try{
            if(row>0){
                result=new JsonResult("200","菜单添加成功",menuName);
            }else{
                result=new JsonResult("404","菜单添加失败",null);
            }
        }catch (Exception exp){
            result=new JsonResult("500","系统错误",exp.getMessage());
        }
        return result;
    }

    /**
     * 删除菜单
     * @author
     * @return
     */
    @RequestMapping("/api/menu/delete")
    public JsonResult delete(String menuId){
        JsonResult result = null;
        MenuDaoImpl m=new MenuDaoImpl();
        int row=m.delete(menuId);
        try{
            if(row>0){
                result=new JsonResult("200","删除成功",null);
            }else {
                result=new JsonResult("404","删除失败",null);
            }
        }catch (Exception exp){
            result=new JsonResult("500","系统错误",exp.getMessage());
        }
        return result;
    }

    /**
     *修改菜单
     * @author
     * @return
     */
    @RequestMapping("/api/menu/update")
    public JsonResult update(String menuName,
                             String menuLevel,
                             String menuSequence,
                             String menuFatherId,
                             String menuDescribe,
                             String menuUrl,
                             String menuStatus,
                             String menuId){
        JsonResult result = null;
        MenuDaoImpl m=new MenuDaoImpl();
        int row=m.update(menuName,menuLevel,menuSequence,menuFatherId,menuDescribe,menuUrl,menuStatus,menuId);
        try{
            if(row>0){
                result=new JsonResult("200","修改成功",menuName);
            }else{
                result=new JsonResult("404","修改失败",null);
            }
        }catch(Exception exp){
            result=new JsonResult("500","系统错误",exp.getMessage());
        }
        return result;
    }

}
