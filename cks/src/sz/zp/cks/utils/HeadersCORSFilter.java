package sz.zp.cks.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeadersCORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        httpResp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");
        httpResp.setHeader("Access-Control-Allow-Origin", "*");
        httpResp.setHeader("Access-Control-Allow-Credentials","true");
        httpResp.setHeader("Access-Control-Max-Age", "3600");
        if (httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            httpResp.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin," +
                    "Access-Control-Request-Method,Access-Control-Request-Headers,userID,Authentication");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
