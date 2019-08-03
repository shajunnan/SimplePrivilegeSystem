package org.taru.lanqiao.entity;

/**
 * 菜单
 * 对应数据库menus表
 */
public class Menu {
    private String menuId;          // 编号
    private String menuName;        // 名称
    private String menuLevel;       // 级别
    private String menuSequence;    // 序号
    private String menuFatherId;    // 父菜单编号
    private String menuDescribe;    // 描述
    private String menuUrl;         // 菜单Url
    private String menuStatus;      // 状态

    // get set 方法
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuSequence() {
        return menuSequence;
    }

    public void setMenuSequence(String menuSequence) {
        this.menuSequence = menuSequence;
    }

    public String getMenuFatherId() {
        return menuFatherId;
    }

    public void setMenuFatherId(String menuFatherId) {
        this.menuFatherId = menuFatherId;
    }

    public String getMenuDescribe() {
        return menuDescribe;
    }

    public void setMenuDescribe(String menuDescribe) {
        this.menuDescribe = menuDescribe;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }
}
