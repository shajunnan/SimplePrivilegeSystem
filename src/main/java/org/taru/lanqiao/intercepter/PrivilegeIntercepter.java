package org.taru.lanqiao.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);
        // uri = uri.substring(uri.indexOf("/",1));
        // 1.获取用户
        // 2.查询用户权限
        // 3.判断当前用户是否有当前权限
        if(true){
            return true;
        }else{
            JsonResult result = new JsonResult("500","未授权，无访问权限",null);
            JsonWriter.writer(response,result);
            return false;
        }
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
