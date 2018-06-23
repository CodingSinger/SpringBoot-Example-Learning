package com.zzc.test.springboot_mybatis.springbootmybatis.filter;

import com.zzc.test.springboot_mybatis.springbootmybatis.controller.TestCom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zhengzechao
 * @date 2018/6/14
 * Email ooczzoo@gmail.com
 */
@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    @Autowired
    private TestCom com;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        com.run();
        System.out.println("start");

        chain.doFilter(request,response);

        System.out.println("end");

    }

    @Override
    public void destroy() {

    }
}
