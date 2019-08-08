package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Menu;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.PageResult;

import java.util.ArrayList;
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

    /**
     * 查询菜单列表
     * @author ShaJunnan
     * @return
     */
    public PageResult queryList(int pageNum, int pageSize){
        // limit 偏移，查询条数
        String sql = "select * from menus limit ?,?";
        List<Map<String,Object>> dataList = DbUtil.query(sql,(pageNum-1)*pageSize,pageSize);

        // 1、设置列表数据
        List<Object> objList = new ArrayList<Object>();
        if(dataList != null && dataList.size() > 0){
            for(Map<String,Object> map:dataList){
                Menu menu = new Menu();
                menu.setMenuId(StringUtil.valueOf(map.get("menu_id")));
                menu.setMenuName(StringUtil.valueOf(map.get("menu_name")));
                menu.setMenuLevel(StringUtil.valueOf(map.get("menu_level")));
                menu.setMenuSequence(StringUtil.valueOf(map.get("menu_sequence")));
                menu.setMenuFatherId(StringUtil.valueOf(map.get("menu_father_id")));
                menu.setMenuDescribe(StringUtil.valueOf(map.get("menu_describe")));
                menu.setMenuUrl(StringUtil.valueOf(map.get("menu_url")));
                menu.setMenuStatus(StringUtil.valueOf(map.get("menu_status")));

                objList.add(menu);
            }
        }

        // 2、查询总商品数据条数
        String sql2 = "select count(*) as row_count from menus";
        List<Map<String, Object>> countList = DbUtil.query(sql2);
        int total = Integer.parseInt(StringUtil.valueOf(countList.get(0).get("row_count")));
        int pages = (int) Math.ceil(total * 1.0 / pageSize); // 向上取整为总页数,pageSize为0时的情况需特殊处理


        // 3、装入PageResult对象
        PageResult pageResult = new PageResult();
        pageResult.setList(objList);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotal(total);
        pageResult.setPages(pages);

        // 关闭数据库连接
        DbUtil.close();
        return pageResult;
    }
}
