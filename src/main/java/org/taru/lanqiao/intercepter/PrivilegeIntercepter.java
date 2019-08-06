package org.taru.lanqiao.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.taru.lanqiao.dao.CoreDaoImpl;
import org.taru.lanqiao.dao.UserDaoImpl;
import org.taru.lanqiao.entity.Privilege;
import org.taru.lanqiao.entity.User;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PrivilegeIntercepter implements HandlerInterceptor {
    /**
     * (先前预备)拦截到之后就执行 请求没有到达controller
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取URI
        String uri = request.getRequestURI();
        // 1.获取用户
        String uid = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for(Cookie ck : cookies) {
                if(ck.getName().equals("uid")) {
                    uid = ck.getValue();
                }
            }
        }
        // 2.查询用户权限
        UserDaoImpl impl = new UserDaoImpl();
        User user = impl.findById(uid);

        // 3.判断当前用户是否有当前权限
        CoreDaoImpl implCore = new CoreDaoImpl();
        List<Privilege> privList = implCore.queryUserPriv(uid);
        if(privList != null && privList.size() > 0) {
            for (Privilege priv : privList) {
                if (priv.getPrivUrl().equals(uri)) {
                    return true;    // 放行
                }
            }
        }

        JsonResult result = new JsonResult("500","未授权，无访问权限",null);
        JsonWriter.writer(response,result);
        return false;
    }


    /**
     * 回来的时候，还没有进行视图渲染
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    /**
     * 整个请求结束之后，视图已渲染完毕  例如：视图"main"    /WEB-INFO/main.jsp
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
