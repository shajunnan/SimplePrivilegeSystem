package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Menu;
import org.taru.lanqiao.entity.Privilege;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoreDaoImpl {

    /**
     * 查询用户菜单
     * @author ShaJunnan
     * @param userId
     * @return
     */
    public List<Menu> queryUserMenu(String userId){
        List<Menu> menuList = null;
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("SELECT * FROM menus a ");
        sqlBuffer.append("LEFT JOIN role_menu b ON a.menu_id = b.rm_menu_id ");
        sqlBuffer.append("LEFT JOIN roles c ON b.rm_role_id = c.role_id ");
        sqlBuffer.append("LEFT JOIN role_user d ON c.role_id = d.ru_role_id ");
        sqlBuffer.append("LEFT JOIN users e ON d.ru_user_id = e.user_id ");
        sqlBuffer.append("WHERE e.user_id = ? and a.menu_status = 1");
        String sql = sqlBuffer.toString();

        List<Map<String,Object>> dataList = DbUtil.query(sql,userId);
        if(dataList != null && dataList.size() > 0){
            menuList = new ArrayList<Menu>();
            for(Map<String,Object> map:dataList){
                Menu menu = new Menu();
                menu.setMenuId(StringUtil.valueOf(map.get("menu_id")));
                menu.setMenuName(StringUtil.valueOf(map.get("menu_name")));
                menu.setMenuLevel(StringUtil.valueOf(map.get("menu_level")));
                menu.setMenuFatherId(StringUtil.valueOf(map.get("menu_father_id")));
                menu.setMenuSequence(StringUtil.valueOf(map.get("menu_sequence")));
                menu.setMenuUrl(StringUtil.valueOf(map.get("menu_url")));
                menu.setMenuDescribe(StringUtil.valueOf(map.get("menu_describe")));
                menu.setMenuStatus(StringUtil.valueOf(map.get("menu_status")));

                menuList.add(menu);
            }
        }

        return menuList;
    }


    /**
     * 查询用户权限
     * @author ShaJunnan
     * @param userId
     * @return
     */
    public List<Privilege> queryUserPriv(String userId){
        List<Privilege> privList = null;
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("SELECT * FROM privs a ");
        sqlBuffer.append("LEFT JOIN role_priv b ON a.priv_id = b.rp_priv_id ");
        sqlBuffer.append("LEFT JOIN roles c ON b.rp_role_id = c.role_id ");
        sqlBuffer.append("LEFT JOIN role_user d ON c.role_id = d.ru_role_id ");
        sqlBuffer.append("LEFT JOIN users e ON d.ru_user_id = e.user_id ");
        sqlBuffer.append("WHERE e.user_id = ?");
        String sql = sqlBuffer.toString();

        List<Map<String,Object>> dataList = DbUtil.query(sql,userId);
        if(dataList != null && dataList.size() > 0){
            privList = new ArrayList<Privilege>();
            for(Map<String,Object> map:dataList){
                Privilege priv = new Privilege();
                priv.setPrivId(StringUtil.valueOf(map.get("priv_id")));
                priv.setPrivName(StringUtil.valueOf(map.get("priv_name")));
                priv.setPrivUrl(StringUtil.valueOf(map.get("priv_url")));
                priv.setPrivFatherId(StringUtil.valueOf(map.get("priv_father_id")));
                priv.setPrivDescribe(StringUtil.valueOf(map.get("priv_describe")));
                priv.setPrivStatus(StringUtil.valueOf(map.get("priv_status")));

                privList.add(priv);
            }
        }

        return privList;
    }
}
