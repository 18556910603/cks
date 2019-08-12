package sz.zp.cks.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;

import sz.zp.cks.entity.AccessToken;
import sz.zp.cks.utils.WeiXinUtil;

public class WeiXinTest {
	public static void main(String[] args) throws ClientProtocolException,IOException,KeyManagementException,NoSuchAlgorithmException,NoSuchProviderException{
		AccessToken token= WeiXinUtil.getAccessToken();
		System.out.println("AccessToken为:"+token.getToken());
		System.out.println("有效时间为："+token.getExpiresIn());
		
		
		String menu = JSONObject.fromObject(WeiXinUtil.initMenu()).toString();
		int result = WeiXinUtil.createMenu(token.getToken(),menu);
		
//		int result = WeiXinUtil.deleteMenu(token.getToken());
		
		if(result==0){
			System.out.println("创建菜单成功");
		}else{
			System.out.println("创建菜单失败");
		}
	}
}
