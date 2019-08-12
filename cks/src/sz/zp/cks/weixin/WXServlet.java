package sz.zp.cks.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import sz.zp.cks.entity.TextMessage;
import sz.zp.cks.utils.Message;
import sz.zp.cks.utils.MessageUtil;

/**
 * Servlet implementation class WXServlet
 */
@WebServlet("/WXServlet")
public class WXServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WXServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String signature = request.getParameter("signature");

		String timestamp = request.getParameter("timestamp");

		String nonce = request.getParameter("nonce");

		String echostr = request.getParameter("echostr");



		PrintWriter out = response.getWriter();

		if(CheckUtil.checkSignature(signature, timestamp, nonce)){

			//如果校验成功，将得到的随机字符串原路返回

			out.print(echostr);}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {request.setCharacterEncoding("UTF-8");

	response.setCharacterEncoding("UTF-8");

	PrintWriter out = response.getWriter();



	try {

		Map<String , String> map = MessageUtil.xmlToMap(request);

		String ToUserName = map.get("ToUserName");

		String FromUserName = map.get("FromUserName");

		String CreateTime = map.get("CreateTime");

		String MsgType = map.get("MsgType");

		String Content = map.get("Content");

		String MsgId  = map.get("MsgId ");



		String message = null;

		if (MsgType.equals(MessageUtil.MESSAGE_TEXT)) {//判断是否为文本消息类型

			if (Content.equals("id")) {

				message = MessageUtil.initText(ToUserName, FromUserName,

						"你的openid为："+FromUserName);

			} else if(Content.equals("2")){

				message = MessageUtil.initText(ToUserName, FromUserName,

						"B success");

			}else if(Content.equals("新手帮助")){
				message = MessageUtil.initNewsMessage(ToUserName, FromUserName);
			}
			else if(Content.equals("?") || Content.equals("？")){

				message = MessageUtil.initText(ToUserName, FromUserName,

						MessageUtil.menuText());

			} else {

				message = MessageUtil.initText(ToUserName, FromUserName,

						"开发中的选项");

			}



		}else if(MsgType.equals(MessageUtil.MESSAGE_EVENT)){//判断是否为事件类型

			//从集合中，或许是哪一种事件传入

			String eventType = map.get("Event");

			//关注事件

			if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {

				message = MessageUtil.initText(ToUserName, FromUserName,

						"谢谢您的关注！");

			}
			else if(eventType.equals(MessageUtil.MESSAGE_CLICK)){
			      // 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                String eventKey = map.get("EventKey");  
                
                if (eventKey.equals("12")) {  
                	message = MessageUtil.initText(ToUserName, FromUserName,

    						"您的微信唯一识别码为："+FromUserName);  
                }
                else if (eventKey.equals("11")) {  
                	message = MessageUtil.initText(ToUserName, FromUserName,

    						"回复“id”，我们将告诉您唯一用户识别码");  
                } else if (eventKey.equals("13")) {  
                	message = MessageUtil.initText(ToUserName, FromUserName,
    						"您好，这里是志品巡检平台公众号，请点击下方菜单栏中的[OMMS巡检]，进入我们的平台");  
                }
			}

		}



		System.out.println(message);

		out.print(message);



	} catch (DocumentException e) {

		e.printStackTrace();

	}finally{

		out.close();

	}}

}
