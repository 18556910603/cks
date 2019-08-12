package sz.zp.cks.utils;

import net.sf.json.JSONObject;
import sz.zp.cks.model.Button;
import sz.zp.cks.model.ClickButton;
import sz.zp.cks.model.PhotoOrAlbumButton;
import sz.zp.cks.model.SubButton;
import sz.zp.cks.model.ViewButton;
import sz.zp.cks.service.WxService;

public class CreateMenu {

	
	
	public static void main(String[] args) {
		//菜单对象
		Button btn=new Button();
		//第一个一级菜单
		btn.getButton().add(new ViewButton("志品简介","http://2r324210b2.51mypc.cn/"));
		//第二个一级菜单
//		btn.getButton().add(new ViewButton("一级跳转","http://xuyaya.free.idcfengye.com/cks/ckslogin.jsp"));
		btn.getButton().add(new ViewButton("巡检运维系统","http://cefc.nat100.top"));
		//第三个一级菜单
		SubButton sb=new SubButton("工业系统");
		//为第三个一级菜单创建子菜单
		sb.getSub_button().add(new PhotoOrAlbumButton("污水处理厂", "31"));
		sb.getSub_button().add(new ClickButton("充电桩", "32"));
		sb.getSub_button().add(new ViewButton("变电站", "http://news.163.com"));
		//加入第三个一级菜单
		btn.getButton().add(sb);
		
//		btn.getButton().add(new AbstractButton("菜单一"));
//		btn.getButton().add(new AbstractButton("菜单二"));
//		转为json
		JSONObject jSONObject =JSONObject.fromObject(btn);
		//准备url
		String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		url=url.replace("ACCESS_TOKEN", WxService.getAccessToken());
		//发送请求
		String result=Util.post(url, jSONObject.toString());
		System.out.println(result);
		
		
		
		
	}
}
