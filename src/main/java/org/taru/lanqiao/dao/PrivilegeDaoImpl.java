package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Privilege;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.PageResult;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权限dao层
 */
public class PrivilegeDaoImpl {
    /**
     * 根据id查询权限详情
     * @return
     * @author XieHeng
     */
    public Privilege findById(String id) {
        Privilege priv = new Privilege();
        String sql = "select * from privs where priv_id=?";
        List<Map<String, Object>> list = DbUtil.query(sql, id);
        if (list.size() > 0) {
            priv.setPrivDescribe(StringUtil.valueOf(list.get(0).get("priv_describe")));
            priv.setPrivFatherId(StringUtil.valueOf(list.get(0).get("priv_father_id")));
            priv.setPrivId(StringUtil.valueOf(list.get(0).get("priv_describe")));
            priv.setPrivName(StringUtil.valueOf(list.get(0).get("priv_name")));
            priv.setPrivStatus(StringUtil.valueOf(list.get(0).get("priv_status")));
            priv.setPrivUrl(StringUtil.valueOf(list.get(0).get("priv_url")));
        }
        return priv;
    }

    /**
     * 添加权限
     * @return
     * @author XieHeng
     */
    public int add(Privilege priv) {
        String privName = priv.getPrivName();
        String privFid = priv.getPrivFatherId();
        String privDescribe = priv.getPrivDescribe();
        String privUrl = priv.getPrivUrl();
        String privStatus = priv.getPrivStatus();

        // 处理MySQL非char插入空值的问题
        if("".equals(privFid)){
            privFid = null;
        }

        String sql = "insert into privs  (priv_name ,priv_father_id,priv_describe,priv_url,priv_status)  VALUES(?,?,?,?,?) ";
        int num = DbUtil.update(sql, privName, privFid, privDescribe, privUrl, privStatus);
        return num;
    }

    /**
     * 删除权
     * @param id
     * @return
     * @author XieHeng
     */
    public int delete(String id) {
        String sql = "delete  from privs where priv_id=?";
        int num = DbUtil.update(sql, id);
        return num;
    }

    /**
     * 修改权限
     * @return
     * @author XieHeng
     */
    public int update(Privilege priv) {
        String privName = priv.getPrivName();
        String privFid = priv.getPrivFatherId();
        String privDescribe = priv.getPrivDescribe();
        String privUrl = priv.getPrivUrl();
        String privStatus = priv.getPrivStatus();
        String privid = priv.getPrivId();

        // 处理MySQL非char插入空值的问题
        if("".equals(privFid)){
            privFid = null;
        }

        String sql = "update privs set priv_name=?,priv_father_id=?,priv_describe=?,priv_url=?,priv_status= ? where priv_id=?";
        int num  = DbUtil.update(sql, privName, privFid, privDescribe, privUrl, privStatus, privid);
        return num;
    }


    /**
     * 查询权限列表
     * @author ShaJunnan
     * @return
     */
    public PageResult queryList(int pageNum, int pageSize){
        // limit 偏移，查询条数
        String sql = "select * from privs limit ?,?";
        List<Map<String,Object>> dataList = DbUtil.query(sql,(pageNum-1)*pageSize,pageSize);

        // 1、设置列表数据
        List<Object> objList = new ArrayList<Object>();
        if(dataList != null && dataList.size() > 0){
            for(Map<String,Object> map:dataList){
                Privilege priv = new Privilege();
                priv.setPrivId(StringUtil.valueOf(map.get("priv_id")));
                priv.setPrivName(StringUtil.valueOf(map.get("priv_name")));
                priv.setPrivUrl(StringUtil.valueOf(map.get("priv_url")));
                priv.setPrivDescribe(StringUtil.valueOf(map.get("priv_describe")));
                priv.setPrivFatherId(StringUtil.valueOf(map.get("priv_father_id")));
                priv.setPrivStatus(StringUtil.valueOf(map.get("priv_status")));

                objList.add(priv);
            }
        }

        // 2、查询总商品数据条数
        String sql2 = "select count(*) as row_count from privs";
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
