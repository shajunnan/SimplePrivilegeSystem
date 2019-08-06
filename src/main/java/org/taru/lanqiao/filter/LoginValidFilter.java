package org.taru.lanqiao.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginValidFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        JsonResult result = null;
        /*result = new JsonResult("500","未登录，无访问权限",null);
        JsonWriter.writer(response,result);*/
        chain.doFilter(request,response);   // 放行
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
