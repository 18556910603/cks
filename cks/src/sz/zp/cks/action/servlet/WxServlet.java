package sz.zp.cks.action.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sz.zp.cks.service.WxService;

/**
 * Servlet implementation class WxServlet
 */
@WebServlet("/wx")
public class WxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public WxServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
//		timestamp	时间戳
//		nonce	随机数
//		echostr	随机字符串
		 String signature = request.getParameter("signature");
		 String timestamp = request.getParameter("timestamp");
		 String nonce = request.getParameter("nonce");
		 String echostr = request.getParameter("echostr");
		 System.out.println(signature);
		 System.out.println(timestamp);
		 System.out.println(nonce);
		 System.out.println(echostr);
		 //校验请求
		 if(WxService.check(signature,timestamp,nonce)){
			 System.out.println("接入成功"); 
			 PrintWriter out =response.getWriter();
			 //原样返回echostr参数
			 out.print(echostr);
			 out.flush();
			 out.close();
			
		 }else{
			 System.out.println("接入失败"); 
		 }
		 
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		System.out.println("post");
//		ServletInputStream  is=request.getInputStream();
//		byte[]  b=new byte[1024];
//		int len = -1;
//		StringBuilder sb= new StringBuilder();
//		while((len=is.read(b))!=-1){
//			sb.append(new String(b,0,len));
//		}
//		System.out.println(sb.toString());
		//处理消息和事件推送
		Map<String,String>requestMap=WxService.parseRequest(request.getInputStream());
		System.out.println("请求数据的map："+requestMap);
		//准备要回复的数据包
//		String respXml="<xml><ToUserName><![CDATA["+requestMap.get("FromUserName")+"]]></ToUserName><FromUserName><![CDATA["+requestMap.get("ToUserName")+"]]></FromUserName> <CreateTime>"+System.currentTimeMillis()/1000+"</CreateTime> <MsgType><![CDATA[text]]></MsgType> <Content><![CDATA[why?]]></Content> </xml>";
		String respXml=WxService.getResponse(requestMap);
		System.out.println(respXml);
		PrintWriter out =response.getWriter();
		out.print(respXml);
		out.flush();	
		out.close();
		
		
		
		
		
	}

}
