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
     * 根据菜单id查询菜单详情
     *
     * @param menuId
     * @return
     * @author zhangqiang
     */
    public List<Map<String, Object>> findById(String menuId) {
        String sql = "select * from menus where menu_id=?";
        List<Map<String, Object>> list = DbUtil.query(sql, menuId);
        return list;
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
    public int add(String menuName,
                   String menuLevel,
                   String menuSequence,
                   String menuFatherId,
                   String menuDescribe,
                   String menuUrl,
                   String menuStatus) {
        int num = 0;
        String sql = "insert into menus(menu_name,menu_level,menu_sequence,menu_father_id,menu_describe,menu_url,menu_status) values(?,?,?,?,?,?,?)";
        num = DbUtil.update(sql, menuName, menuLevel, menuSequence, menuFatherId, menuDescribe, menuUrl, menuStatus);
        return num;
    }

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     * @author zhangqiang
     */
    public int delete(String menuId) {
        int row = 0;
        String sql = "delete from menus where menu_id=?";
        row = DbUtil.update(sql, menuId);
        return row;
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
    public int update(String menuName,
                      String menuLevel,
                      String menuSequence,
                      String menuFatherId,
                      String menuDescribe,
                      String menuUrl,
                      String menuStatus,
                      String menuId) {
        int row = 0;
        String sql = "update menus set menu_name=?,menu_level=?,menu_sequence=?,menu_father_id=?,menu_describe=?,menu_url=?,menu_status=? where menu_id=?";
        row = DbUtil.update(sql, menuName, menuLevel, menuSequence, menuFatherId, menuDescribe, menuUrl, menuStatus, menuId);
        return row;
    }
}
