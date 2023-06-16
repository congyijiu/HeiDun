package com.congyijiu.auth.filter;

/**
 * @author congyijiu
 * @create 2023-05-31-16:19
 */
import com.alibaba.fastjson.JSON;
import com.congyijiu.common.result.Result;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */
@WebFilter("/*")
public class LoginCheckFilter implements Filter{
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();// /backend/index.html

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/index/login",
                "/index/logout",
                "/doc.html/*",
                "/swagger-resources/*",
                "/v2/*",
                "/*",
                "/captcha/*",
        };

        //2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        check =true;
        //3、如果不需要处理，则直接放行
        if(check){
            filterChain.doFilter(request,response);
            return;
        }

        //4-1、判断登录状态，如果已登录，则直接放行
        String token = request.getHeader("token");
        if(token != null){
            filterChain.doFilter(request,response);
            return;
        }

        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(Result.fail("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String url : urls) {
            boolean match = antPathMatcher.matchStart(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}