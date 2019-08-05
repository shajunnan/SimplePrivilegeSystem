package org.taru.lanqiao.dao;

import org.taru.lanqiao.entity.Privilege;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;

import java.awt.*;
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
        String sql = "update privs set priv_name=?,priv_father_id=?,priv_describe=?,priv_url=?,priv_status= ? where priv_id=?";
        int num  = DbUtil.update(sql, privName, privFid, privDescribe, privUrl, privStatus, privid);
        return num;
    }
}
