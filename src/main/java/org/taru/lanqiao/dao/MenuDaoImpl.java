package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Menu;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * 菜单dao层
 */
public class MenuDaoImpl {
    /**
     * 根据id查询菜单详情
     * @author
     * @return
     */
    public  List<Map<String,Object>> findById(String userId){
        String sql="select menu_id,menu_name,menu_level,menu_sequence,menu_father_id,menu_describe,menu_url,menu_status from menus m left join role_menu rm on m.menu_id=rm.rm_menu_id left join roles r on \n" +
                "rm.rm_role_id=r.role_id left join role_user ru on r.role_id=ru.ru_role_id left join users u on u.user_id=ru.ru_user_id where u.user_id=?";
        List<Map<String,Object>> list= DbUtil.query(sql,userId);
        return list;
    }

    /**
     * 添加菜单
     * @author
     * @return
     */
    public int add(
                    String menuName,
                    String menuLevel,
                    String menuSequence,
                    String menuFatherId,
                    String menuDescribe,
                    String menuUrl,
                    String menuStatus){
        int num = 0;
        String sql="insert into menus(menu_name,menu_level,menu_sequence,menu_father_id,menu_describe,menu_url,menu_status) values(?,?,?,?,?,?,?)";
        num=DbUtil.update(sql,menuName,menuLevel,menuSequence,menuFatherId,menuDescribe,menuUrl,menuStatus);
        return num;
    }

    /**
     * 删除菜单
     * @author
     * @return
     */
    public int delete(String menuId){
        int row = 0;
        String sql="delete from menus where menu_id=?";
        row=DbUtil.update(sql,menuId);
        return row;
    }

    /**
     *修改菜单
     * @author
     * @return
     */
    public int update(String menuName,
                      String menuLevel,
                      String menuSequence,
                      String menuFatherId,
                      String menuDescribe,
                      String menuUrl,
                      String menuStatus,
                      String menuId){
        int row = 0;
        String sql="update menus set menu_name=?,menu_level=?,menu_sequence=?,menu_father_id=?,menu_describe=?,menu_url=?,menu_status=? where menu_id=?";
        row=DbUtil.update(sql,menuName,menuLevel,menuSequence,menuFatherId,menuDescribe,menuUrl,menuStatus,menuId);
        return row;
    }
}
