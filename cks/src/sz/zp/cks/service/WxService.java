package sz.zp.cks.service;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.aspectj.weaver.NewMethodTypeMunger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import sz.zp.cks.model.AccessToken;
import sz.zp.cks.model.Article;
import sz.zp.cks.model.BaseMessage;
import sz.zp.cks.model.ImageMessage;
import sz.zp.cks.model.MusicMessage;
import sz.zp.cks.model.NewsMessage;
import sz.zp.cks.model.TextMessage;
import sz.zp.cks.model.VideoMessage;
import sz.zp.cks.model.VoiceMessage;
import sz.zp.cks.utils.Util;

import com.thoughtworks.xstream.XStream;

//验证签名
public class WxService {
	private static  final  String  TOKEN="cks123456";
	
	private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//	private static final String APPID="wxe8127e43a45eecc1";
//	private static final String APPSECRET="a9dd4731fde15a531eb24c2dbb64fc4e";
	private static final String APPID="wxe6b3a1b7cfa760a6";
	private static final String APPSECRET="e2917d68bda1b9001c1464d3c2dad33a";		
	//用于存储token
	private static AccessToken at;
	
	
	
	
	//获取token 有两个小时的有效期 (不需要外界调用 private)
	private static void getToken(){
		String url=GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String tokenStr=Util.get(url);
		//解析封装成对象
		JSONObject jSONObject=JSONObject.fromObject(tokenStr);
		//token
		String token= jSONObject.getString("access_token");
		//过期时间
		String expiresIn=jSONObject.getString("expires_in");
		//创建token对象并存储
		at=new AccessToken(token, expiresIn);
		
	}
	//向外暴露的获取token的方法****
	public static String getAccessToken(){
		if(at==null||at.isExpired()){
			getToken();
		}
		return at.getAccessToken();
	}
	
	
	
	
	
	
	
	
	public static boolean check( String signature,String timestamp, String nonce ){
		String[] strs=new String[]{TOKEN,timestamp,nonce};
		//字典排序
		Arrays.sort(strs);
		//拼接成一个字符串
		String str=strs[0]+strs[1]+strs[2];
		//sha1加密
		String mysig=sha1(str);
		System.out.println(mysig);
		System.out.println(signature);
		//开发者获得加密后的字符串与signature对比，标识该请求来源于微信
		return mysig.equalsIgnoreCase(signature);
	}
	/**
	 * 进行sha1加密
	 * @param str
	 * @return
	 */
	private static String sha1(String str) {
		
			//获取一个加密的对象
		try {
			MessageDigest md=MessageDigest.getInstance("sha1");
			//加密
			byte[] digest = md.digest(str.getBytes());
			char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			StringBuilder sb=new StringBuilder();
			//处理加密结果
			 for(byte b:digest){
				sb.append(chars[(b>>4)&15]);
				sb.append(chars[b&15]);
			 }
			 return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解析xml数据包
	 * @param is
	 * @return
	 */
	public static Map<String, String> parseRequest(InputStream is) {
		Map<String,String >map =new HashMap<String, String>();
		SAXReader reader=new SAXReader();
		try {
			//读取输入流获取文档对象
			Document document = reader.read(is);
			//根据文档获取根节点
			Element root  = document.getRootElement();
			//获取根节点的所有子节点
			List<Element> elements = root.elements();
			for(Element e:elements){
				map.put(e.getName(), e.getStringValue());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 用于处理所有的事件和消息的回复，返回的是xml数据包
	 * @param requestMap
	 * @return
	 */
	public static String getResponse(Map<String, String> requestMap) {
		String msgType = requestMap.get("MsgType");
		BaseMessage msg=null;
		switch (msgType) {
		case "text":
			//处理文本消息
			msg=delTextMessage(requestMap);
			break;
		case "image":
			msg=delTextImage(requestMap);
			break;
		case "voice":
			
			break;	
		case "video":
			
			break;	
		case "shortvideo":
			
			break;				
		case "location":
			
			break;
		case "link":
			
			break;	
		case "event":
			msg=dealEvent(requestMap); 
			break;				
		default:
			break;
		}
		
		//把消息对象处理为xml数据包
		if(msg!=null){
			return beanToXml(msg);
		}
		return null;
	}
	//进行图片识别
	private static BaseMessage delTextImage(Map<String, String> requestMap) {
		
		
		
		// TODO Auto-generated method stub
		return null;
	}
	//专门用来处理事件推送
	private static BaseMessage dealEvent(Map<String, String> requestMap) {
		String event = requestMap.get("Event");
		switch (event) {
		case "CLICK":
			return dealClick(requestMap);
		case "VIEW":
			return dealView(requestMap);			
		default:
			break;
		}

		return null;
	}
	
	
	//处理view类型的按钮的菜单
	private static BaseMessage dealView(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		return null;
	}
	//处理click菜单
	private static BaseMessage dealClick(Map<String, String> requestMap) {
		String key = requestMap.get("EventKey");
		switch (key) {
		//点击一菜单
		case "1":
			//处理点击了第一个一级菜单
			return new TextMessage(requestMap, "你点了一下第一个一级菜单");
		case "32":
			//处理点击了第三个一级菜单的第二个子菜单
			break;
		default:
			break;
		}
		
		
		
		
		return null;
	}
	//把消息对象处理为xml数据包
	private static String beanToXml(BaseMessage msg) {
		XStream stream=new XStream();
		//加入注解
		//设置需要处理@XStreamAlias注释的类
		stream.processAnnotations(TextMessage.class);
		stream.processAnnotations(ImageMessage.class);
		stream.processAnnotations(MusicMessage.class);
		stream.processAnnotations(NewsMessage.class);
		stream.processAnnotations(VideoMessage.class);
		stream.processAnnotations(VoiceMessage.class);
		String xml=stream.toXML(msg);
		return xml;
	}
	/***
	 * 处理文本类型
	 * 
	 * */
	private static BaseMessage delTextMessage(Map<String, String> requestMap) {
		String msg=requestMap.get("Content");
		if(msg.equals("图文")){
			List<Article> articles=new ArrayList<Article>();
			articles.add(new Article("这是图文消息的标题", "这是图文消息的详细介绍", "http://mmbiz.qpic.cn/mmbiz_jpg/LNJKAKqotXZaibWHeeaIrDHm8MphJcQOObWMlI7dtsic4cUlRSG3RzX0NlPQgwuxlQjtsOdoMM4qZutSI5BXATuA/0", "http://www.baidu.com"));
			NewsMessage ne=new NewsMessage(requestMap,  articles);
			
			
			return ne;
		}
		
		TextMessage tm=new TextMessage(requestMap, "处理成功");
				
		return tm;
	}
	//获取带参数二维码Ticket
	public static String getQrCodeTicket(){
		String at=getAccessToken();
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+at;
		//生成临时字符串二维码
		String data="{\"expire_seconds\": 600, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"XYY\"}}}";
		String result=Util.post(url, data);
		String ticket=JSONObject.fromObject(result).getString("ticket")	;
		return ticket;
	}
}
