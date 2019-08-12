package sz.zp.cks.utils;


import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import sz.zp.cks.entity.AccessToken;
import sz.zp.cks.entity.WXButton;
import sz.zp.cks.entity.WXClickButton;
import sz.zp.cks.entity.WXMenu;
import sz.zp.cks.entity.WXViewButton;


/**
 * @author Lenovo
 *
 */
public class WeiXinUtil {
	private static final String APPID="wxe6b3a1b7cfa760a6";
	private static final String APPSECRET="e2917d68bda1b9001c1464d3c2dad33a";
	private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	private static final String DELETE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/**
     * 编写Get请求的方法。没有参数传递的时候，可以使用Get请求
     * 
     * @param url 需要请求的URL
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();//获取DefaultHttpClient请求
        HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        HttpResponse response = client.execute(httpGet);//使用HttpResponse接收client执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");//HttpEntity转为字符串类型
            jsonObject = JSONObject.fromObject(result);//字符串类型转为JSON类型
        }
        return jsonObject;
    }
    
    /**
     * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
     * 
     * @param url 需要请求的URL
     * @param outStr  需要传递的参数
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doPostStr(String url,String outStr) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();//获取DefaultHttpClient请求
        HttpPost httpost = new HttpPost(url);//HttpPost将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        httpost.setEntity(new StringEntity(outStr,"UTF-8"));//使用setEntity方法，将我们传进来的参数放入请求中
        HttpResponse response = client.execute(httpost);//使用HttpResponse接收client执行httpost的结果
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");//HttpEntity转为字符串类型
        jsonObject = JSONObject.fromObject(result);//字符串类型转为JSON类型
        return jsonObject;
    }
    
    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public static AccessToken getAccessToken() throws ClientProtocolException, IOException{
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);//将URL中的两个参数替换掉
        JSONObject jsonObject = doGetStr(url);//使用刚刚写的doGet方法接收结果
        if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
       	System.out.println("jsonObiect为：----------------------"+jsonObject);
            try {
				token.setToken(jsonObject.getString("access_token"));//取出access_token
				token.setExpiresIn(jsonObject.getInt("expires_in"));//取出access_token的有效期
			} catch (Exception e) {
				// TODO Auto-generated catch block
		       	System.out.println("weixinUtil类中ip错误 jsonObiect为：----------------------"+jsonObject);
				e.printStackTrace();
			}
        }
        return token;
    }
    public static WXMenu initMenu(){
    	WXMenu wxmenu = new WXMenu();
    	
    	WXClickButton wxbutton11= new WXClickButton();
    	wxbutton11.setName("微信工具");
    	wxbutton11.setType("click");
    	wxbutton11.setKey("11");
    	
    	WXClickButton wxbutton12= new WXClickButton();
    	wxbutton12.setName("我的ID");
    	wxbutton12.setType("click");
    	wxbutton12.setKey("12");
    	
    	WXClickButton wxbutton13= new WXClickButton();
    	wxbutton13.setName("系统简介");
    	wxbutton13.setType("click");
    	wxbutton13.setKey("13");
    	
    	WXViewButton wxbutton21= new WXViewButton();
    	wxbutton21.setName("进入官网");
    	wxbutton21.setType("view");
    	wxbutton21.setUrl("http://www.cefc.com.cn/");
    	
    	WXViewButton wxbutton22= new WXViewButton();
    	wxbutton22.setName("业主登陆");
    	wxbutton22.setType("view");
    	wxbutton22.setUrl("http://cefc.nat100.top/");
    	
    	WXViewButton wxbutton31= new WXViewButton();
    	wxbutton31.setName("OMMS-巡检");
    	wxbutton31.setType("view");
    	wxbutton31.setUrl("http://cefc.nat100.top/");
    	
    	WXButton wxbutton1 = new WXButton();
    	wxbutton1.setName("工具"); //将11/12两个button作为二级菜单封装第一个一级菜单
    	wxbutton1.setSub_button(new WXButton[]{wxbutton11,wxbutton12,wxbutton13}); 

    	WXButton wxbutton2 = new WXButton();
    	wxbutton2.setName("更多功能"); //将21/22两个button作为二级菜单封装第二个二级菜单
    	wxbutton2.setSub_button(new WXButton[]{wxbutton21,wxbutton22});

   
    	wxmenu.setButton(new WXButton[]{wxbutton1,wxbutton31,wxbutton2});// 将31Button直接作为一级菜单
    	//wxmenu.setWxbutton(new WXButton[]{wxbutton1,wxbutton2,wxbutton31});// 将31Button直接作为一级菜单

    	return wxmenu;
    }
  
    /**
     * 创建公众号自定义菜单
     * @param token
     * @param menu
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static int createMenu(String token,String menu) throws ClientProtocolException,IOException{
    	int result = 0;
    	String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
    	JSONObject jsonObject = doPostStr(url, menu);
    	if(jsonObject!=null){
    		result = jsonObject.getInt("errcode");
    	}
    	return result;
    }
    /**
     * 删除公众号自定义菜单
     * @param token
     * @param menu
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static int deleteMenu(String token) throws ClientProtocolException, IOException{
    	int result = 0;
    	String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
    	JSONObject jsonObject = doGetStr(url);
    	if(jsonObject!=null){
    		result = jsonObject.getInt("errcode");
    	}
		return result;
    	
    }
}
