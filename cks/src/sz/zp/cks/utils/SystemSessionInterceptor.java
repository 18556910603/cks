package sz.zp.cks.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.User;

public class SystemSessionInterceptor implements HandlerInterceptor { 
    @Override
    public void postHandle(HttpServletRequest request, 
        HttpServletResponse response, Object handler, 
        ModelAndView modelAndView) throws Exception { 
    } 
      
    @Override
    public void afterCompletion(HttpServletRequest request, 
        HttpServletResponse response, Object handler, Exception ex) 
        throws Exception { 
    } 
      
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
        Object handler) throws Exception { 
//        Principal principal = UserUtils.getPrincipal();
    	User tUser=null;
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if(sessionContainer!=null){
			 tUser = sessionContainer.getUser();
		}
        if (sessionContainer == null||tUser==null) {  
        	//String url="http://" + request.getServerName()+ ":" + request.getServerPort()+ request.getRequestURI();
        	String url=request.getRequestURI();
        	
        	if(url.equals("/getEquip.action")){  
        		  return true;
        		}       	
            response.setContentType("text/html");  
            response.setCharacterEncoding("utf-8");  
            PrintWriter out = response.getWriter();    
            StringBuilder builder = new StringBuilder();            
            builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");    
            builder.append("alert(\"登录已过期，请重新登录！\");");    
            builder.append("parent.window.location.href='"+request.getContextPath()+"/';");     
            builder.append("</script>");    
            out.print(builder.toString());    
            out.close();    
            return false;  
        }  
        return true;
    }
}