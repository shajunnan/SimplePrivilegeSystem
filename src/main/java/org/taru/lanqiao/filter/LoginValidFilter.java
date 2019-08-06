package org.taru.lanqiao.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import org.taru.lanqiao.entity.User;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.util.SecurityUtil;
import org.taru.lanqiao.vo.JsonResult;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginValidFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 如果是登录，就放行
        String path = request.getRequestURI();
        if("/api/user/login".equals(path)) {
            chain.doFilter(request, response);
            return;
        }

        // 获取请求的cookie对比令牌
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for(Cookie ck : cookies) {
                if(ck.getName().equals("token")) {
                    if(ck.getValue().equals(SecurityUtil.encryptionMd5("lanqiao"))) {
                        chain.doFilter(request, response);	// 放行
                        return;
                    }
                }
            }
        }

        JsonResult result = new JsonResult("500","未登录，无访问权限",null);
        JsonWriter.writer(response,result);

        // 当前登录用户,使用session对象，判断是否携带userId
        /*User user = (User)request.getSession().getAttribute("loginUserKey");
        // 如果已登录则放行，否则返回未登录
        if(user != null){
            chain.doFilter(request,response);   // 放行
        }else{
            JsonResult result = new JsonResult("500","未登录，无访问权限",null);
            JsonWriter.writer(response,result);
        }*/
    }

    /**
     * Is Ajax request
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if(header != null && "XMLHttpRequest".equals(header)){
            // ajax request
            return true;
        }else{
            // traditional sync http request
            return false;
        }
    }
}
