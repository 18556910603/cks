package sz.zp.cks.weixin;

import java.io.IOException;

import net.sf.json.JSONObject;
import sz.zp.cks.entity.AccessToken;
import sz.zp.cks.utils.WeiXinUtil;

public class WXMenuAction {
	public static void createMenu() throws IOException{
		AccessToken token= WeiXinUtil.getAccessToken();

		String menu = JSONObject.fromObject(WeiXinUtil.initMenu()).toString();
		int result = WeiXinUtil.createMenu(token.getToken(),menu);
		
//		int result = WeiXinUtil.deleteMenu(token.getToken());
		
		if(result==0){
			System.out.println("创建菜单成功");
		}else{
			System.out.println("创建菜单失败");
		}
	}
	public static void deleteMenu() throws IOException{
		AccessToken token= WeiXinUtil.getAccessToken();

		String menu = JSONObject.fromObject(WeiXinUtil.initMenu()).toString();
		int result =  WeiXinUtil.deleteMenu(token.getToken());
		if(result==0){
			System.out.println("删除菜单成功");
		}else{
			System.out.println("删除菜单失败");
		}
	}
	public static void main(String[] args) throws IOException {
		WXMenuAction.createMenu();
		//WXMenuAction.deleteMenu();

	}

}
