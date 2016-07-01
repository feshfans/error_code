package com.sense.cloud.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

/**
 * Created by kang on 16-5-31.
 */
public class SessionFilter implements Filter {

    private String sessionKey;
    private Pattern exceptUrlPattern;
    private String redirectUrl;

    public void init(FilterConfig filterConfig) throws ServletException {

        sessionKey=filterConfig.getInitParameter("sessionKey");
        String exceptUrlRegex=filterConfig.getInitParameter("exceptUrlRegex");
        if(!StringUtils.isBlank(exceptUrlRegex)){
            exceptUrlPattern=Pattern.compile(exceptUrlRegex);
        }

        redirectUrl=filterConfig.getInitParameter("redirectUrl");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //未设置sessionKey,存放行
        if(StringUtils.isBlank(sessionKey)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        String servletPath=request.getServletPath();

        System.out.println("servletPath:"+servletPath);
        System.out.println();
        //如果请求的路径与redirectUrl相同，或请求的路径是排除的URL时，则直接放行
        if(redirectUrl.equals(servletPath)|| exceptUrlPattern.matcher(servletPath).matches()){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        Object sessionObj=request.getSession().getAttribute(sessionKey);
        if(sessionObj==null){
            String contextPath=request.getContextPath();

             /*
             * login.jsp 的 <form> 表单中新增一个隐藏表单域：
             * <input type="hidden" name="redirect" value="${param.redirect }">
             *
             *  LoginServlet.java 的 service 的方法中新增如下代码：
             *  String redirect = request.getParamter("redirect");
             *  if(loginSuccess){
             *      if(redirect == null || redirect.length() == 0){
             *          // 跳转到项目主页（home.jsp）
             *      }else{
             *          // 跳转到登录前访问的页面（java.net.URLDecoder.decode(s, "UTF-8")）
             *      }
             *  }
             */
            String redirect=servletPath+"?"+StringUtils.defaultString(request.getQueryString());
            response.sendRedirect(contextPath+StringUtils.defaultString(redirectUrl,"/")+"?redirect="+ URLEncoder.encode(redirect,"UTF-8"));
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    public void destroy() {

    }
}
