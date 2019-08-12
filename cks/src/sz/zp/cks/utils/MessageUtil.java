package sz.zp.cks.utils;

import sz.zp.cks.entity.News;
import sz.zp.cks.entity.NewsMessage;
import sz.zp.cks.entity.TextMessage;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @author Lenovo
 *
 */
public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";

	public static final String MESSAGE_IMAGE = "image";

	public static final String MESSAGE_VOICE = "voice";

	public static final String MESSAGE_VIDEO = "video";

	public static final String MESSAGE_SHORTVIDEO = "shortvideo";

	public static final String MESSAGE_LINK = "link";

	public static final String MESSAGE_LOCATION = "location";

	public static final String MESSAGE_EVENT = "event";

	public static final String MESSAGE_SUBSCRIBE = "subscribe";

	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";

	public static final String MESSAGE_CLICK = "CLICK";

	public static final String MESSAGE_VIEW = "VIEW";

	public static final String MESSAGE_SCAN = "SCAN";
	
	public static final String MESSAGE_NEWS= "news";
	/**

	 * 将XML转为MAP集合

	 * @param request

	 * @return

	 * @throws IOException

	 * @throws DocumentException

	 */

	public static Map<String , String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{

	Map<String , String> map = new HashMap<String, String>();

	SAXReader reader = new SAXReader();

	//从request对象中获取输入流

	InputStream ins = request.getInputStream();

	//使用reader对象读取输入流,解析为XML文档

	Document doc = reader.read(ins);

	//获取XML根元素

	Element root = doc.getRootElement();

	//将根元素的所有节点，放入列表中

	List<Element> list = root.elements();

	//遍历list对象，并保存到集合中

	for (Element element : list) {

	map.put(element.getName(), element.getText());

	}

	ins.close();

	return map;

	}

	/**

	 * 将文本消息对象转成XML

	 * @param text

	 * @return

	 */

	public static String testMessageToXml(TextMessage textMessage){

	XStream xstream = new XStream();

	//将xml的根节点替换成<xml>  默认为TextMessage的包名

	xstream.alias("xml", textMessage.getClass());

	return xstream.toXML(textMessage);

	}
	/**
     * 将图文消息对象转成XML
     * @param 
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xstream = new XStream();
        //将xml的根节点替换成<xml>  默认为NewsMessage的包名
        xstream.alias("xml", newsMessage.getClass());
        //同理，将每条图文消息News类的报名，替换为<item>标签
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    }

	/**

	 * 拼接关注主菜单

	 */

	public static String menuText(){

	StringBuffer sb = new StringBuffer();

	sb.append("欢迎关注志品研发公众号，请选择:\n\n");

	sb.append("1、测试A。\n");

	sb.append("2、测试B。\n\n");

	sb.append("回复？调出主菜单。\n\n");

	return sb.toString();

	}

	/**

	 * 初始化回复消息

	 */

	public static String initText(String toUSerName,String fromUserName,String content){

	TextMessage text = new TextMessage();

	text.setFromUserName(toUSerName);

	text.setToUserName(fromUserName);

	text.setMsgType(MESSAGE_TEXT);

	text.setCreateTime(new Date().getTime());

	text.setContent(content);

	return MessageUtil.testMessageToXml(text);

	}
	/**
	 * 初始化图文消息
	 */
	public static String initNewsMessage(String toUserName,String fromUserName){
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		//组建一条图文
		News newsItem1 = new News();
		newsItem1.setTitle("志品巡检新手帮助");
		newsItem1.setDescription("志品巡检，致力于高效便捷的巡检运维系统");
		newsItem1.setPicUrl("https://mmbiz.qpic.cn/mmbiz_jpg/UyuQWFtJWw58ZT5OzKpQSaK7t6ZNEapoHYlcDR2QPOOwic38oDDWZzKQ2y87T88zjnobaQh2GNh593gvPTxBrIg/0?wx_fmt=jpeg");
		newsItem1.setUrl("www.baidu.com");
		newsList.add(newsItem1);
		
		//组建第二条图文
		News newsItem2 = new News();
		newsItem2.setTitle("志品巡检介绍");
		newsItem2.setDescription("志品巡检开发日志");
		newsItem2.setPicUrl("https://mmbiz.qpic.cn/mmbiz_jpg/UyuQWFtJWw58ZT5OzKpQSaK7t6ZNEapoHYlcDR2QPOOwic38oDDWZzKQ2y87T88zjnobaQh2GNh593gvPTxBrIg/0?wx_fmt=jpeg");
		newsItem2.setUrl("www.youku.com");
		newsList.add(newsItem2);
		//组装图文消息相关信息
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticle(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		return MessageUtil.newsMessageToXml(newsMessage);
		
	}
	
}
