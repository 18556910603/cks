package sz.zp.cks.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * 微信工具类
 *
 */
public class WeixinUtil {
	
	
	private static final String APPID="wxe6b3a1b7cfa760a6";
	private static final String APPSECRET="e2917d68bda1b9001c1464d3c2dad33a";	
//	private static final String APPID = "wxe8127e43a45eecc1";//改为你自己公众号的APPID
//	private static final String APPSECRET = "a9dd4731fde15a531eb24c2dbb64fc4e";//改为你自己公众号的APPSECRET
	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	public static JSONObject doGetStr(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}
	
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}
	
	public static String httpGet(String url){
		String strResult = null;  
        try {  
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);  
            HttpResponse response = client.execute(request);  
   
            /**请求发送成功，并得到响应**/  
            if (response.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {  
                /**读取服务器返回过来的json字符串数据**/  
                  strResult = EntityUtils.toString(response.getEntity());  
            } else {
            	System.out.println("get请求提交失败");
            }  
        } catch (IOException e) {
        	System.out.println("get请求提交失败:" + e.getMessage());
        }  
        return strResult;
    }
	
	public static String httpPost(String url){
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        HttpPost method = new HttpPost(url);  
        String str = "";  
        try {  
            HttpResponse result = httpClient.execute(method);  
            url = URLDecoder.decode(url, "UTF-8");  
            /**请求发送成功，并得到响应**/  
            if (result.getStatusLine().getStatusCode() == 200) {  
                try {  
                    /**读取服务器返回过来的json字符串数据**/  
                    str = EntityUtils.toString(result.getEntity());  
                } catch (Exception e) {  
                    System.out.println("post请求提交失败:" + e.getMessage());
                }  
            }  
        } catch (IOException e) {
        	System.out.println("post请求提交失败:" + e.getMessage());
        }  
        return str;
    }
	
	public static AccessToken getAccessToken() {
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = null;
		try {
			jsonObject = doGetStr(url);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(jsonObject!=null){
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return token;
	}
	
	public static String getJsApiTicket(String accessToken) {
		String jsapi_ticket = null;
        try {
        	String urlticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
            String responseText = httpGet(urlticket);
            jsapi_ticket = null;
            JSONObject object = JSONObject.fromObject(responseText);
            if (object.containsKey("ticket")) {
                jsapi_ticket = object.getString("ticket");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return jsapi_ticket;
		
	}
    /**
     * 生成随机字符串
     */ 
    public static String getNonceStr() {
        String currTime = getCurrTime();  
        String strTime = currTime.substring(8, currTime.length());  
        String strRandom = buildRandom(4) + "";  
        return strTime + strRandom;
    }
    /** 
     * 获取当前时间 yyyyMMddHHmmss 
     */  
    public static String getCurrTime() {  
        Date now = new Date();  
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        String s = outFormat.format(now);  
        return s;  
    }  

    /** 
     * 取出一个指定长度大小的随机正整数. 
     * @param length 
     *            int 设定所取出随机数的长度。length小于11 
     * @return int 返回生成的随机数。 
     */  
    public static int buildRandom(int length) {  
        int num = 1;  
        double random = Math.random();  
        if (random < 0.1) {  
            random = random + 0.1;  
        }  
        for (int i = 0; i < length; i++) {  
            num = num * 10;  
        }  
        return (int) ((random * num));  
    }  

    
    
    public static void main(String[] args) throws ParseException, IOException {
    	String url="http://192.168.0.104/equipmentRepair/toWspassServlet.action";
    	doGetStr(url);
    } 
    
    
}
