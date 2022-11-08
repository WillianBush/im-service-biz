package net.chenlin.dp.common.xss;


import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "securityXssFilter")
@Configuration
public class XSSFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }



    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("utf-8");
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);
        chain.doFilter(xssRequest, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}