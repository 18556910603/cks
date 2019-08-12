package sz.zp.cks.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import sz.zp.cks.model.AbstractButton;
import sz.zp.cks.model.Article;
import sz.zp.cks.model.Button;
import sz.zp.cks.model.ClickButton;
import sz.zp.cks.model.ImageMessage;
import sz.zp.cks.model.Music;
import sz.zp.cks.model.MusicMessage;
import sz.zp.cks.model.NewsMessage;
import sz.zp.cks.model.PhotoOrAlbumButton;
import sz.zp.cks.model.SubButton;
import sz.zp.cks.model.TextMessage;
import sz.zp.cks.model.VideoMessage;
import sz.zp.cks.model.ViewButton;
import sz.zp.cks.model.VoiceMessage;
import sz.zp.cks.service.WxService;

import com.thoughtworks.xstream.XStream;

public class TestWx {
	
	
	@Test
	public void testQrCode(){
		String ticket=WxService.getQrCodeTicket();
		System.out.println(ticket);
	}
	
	
	@Test
	public void testButton(){
		//菜单对象
		Button btn=new Button();
		//第一个一级菜单
		btn.getButton().add(new ClickButton("一级点击","1"));
		//第二个一级菜单
		btn.getButton().add(new ViewButton("一级跳转","http://www.baidu.com"));
		//第三个一级菜单
		SubButton sb=new SubButton("有子菜单");
		//为第三个一级菜单创建子菜单
		sb.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
		sb.getSub_button().add(new ClickButton("点击", "32"));
		sb.getSub_button().add(new ViewButton("网易新闻", "http://news.163.com"));
		//加入第三个一级菜单
		btn.getButton().add(sb);
		
//		btn.getButton().add(new AbstractButton("菜单一"));
//		btn.getButton().add(new AbstractButton("菜单二"));
//		转为json
		JSONObject jSONObject =JSONObject.fromObject(btn);
		System.out.println(jSONObject.toString());
	}
	
	
	
	
	@Test
	public void testToken(){
		System.out.println(WxService.getAccessToken());
		System.out.println(WxService.getAccessToken());
		
		
		
		
	}
	
	
	
	
	
	
	@Test
	public void testMsg(){
		Map<String, String> requestMap=new HashMap<String, String>();
		requestMap.put("ToUserName", "to");
		requestMap.put("FromUserName", "from");
		requestMap.put("MsgType", "type");
		TextMessage  tm=new TextMessage(requestMap, "还好");
//		System.out.print(tm);
		
		XStream stream=new XStream();
		//加入注解
		//设置需要处理@XStreamAlias注释的类
		stream.processAnnotations(TextMessage.class);
		stream.processAnnotations(ImageMessage.class);
		stream.processAnnotations(MusicMessage.class);
		stream.processAnnotations(NewsMessage.class);
		stream.processAnnotations(VideoMessage.class);
		stream.processAnnotations(VoiceMessage.class);
		
		List<Article> articles=new ArrayList<Article>();
		Article a1=new Article("1", "1", "1", "1");
		Article a2=new Article("2", "2", "2", "2");
		articles.add(a1);
		articles.add(a2);
		NewsMessage ne=new NewsMessage(requestMap, articles);
		
		
		Music music=new Music("1", "1", "1", "1", "1");
		MusicMessage  mu=new MusicMessage(requestMap, music);
		
		
		String xml=stream.toXML(mu);
		System.out.println(xml);
	}
}
