package sz.zp.cks.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;


/**
 * 通过http调用接口
 * @author xuyaya 2019/04/22
 * 
 */
public class HttpUtil {
	
	//发送JSON字符串 如果成功则返回成功标识。
    public static Map<String,String> doJsonPost(String urlPath, String Json) {
        //返回map
    	Map<String,String> resultMap = new HashMap<String,String>();
    	String result="";
    	int responseCode=888;//自定义code
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");
            // 往服务器里面发送数据
            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
//                System.out.println("doJsonPost:返回code"+conn.getResponseCode());
            }
            
//            Map<String, List<String>> map = conn.getHeaderFields();
//            System.out.println("显示响应Header信息...\n");
//          for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//              System.out.println("Key : " + entry.getKey() + 
//                      " ,Value : " + entry.getValue());
//          }    
            responseCode=conn.getResponseCode();
            //返回正常
            if (responseCode == 200) {
//            	{"expiresIn":1555926604,"dashboardUrl":"https://dashboard-fuzhou-default-space.wise-paas.com.cn","tokenType":"Bearer","accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjbi13aXNlcGFhcyIsImlhdCI6MTU1NTkyMzAwNCwiZXhwIjoxNTU1OTI2NjA0LCJ1c2VySWQiOiI5ZGU0NjQ4NC01OTdlLTQwMWUtOWFkYi0zZDU0NzVhM2IzZWUiLCJ1YWFJZCI6ImQ1MzQ3MGIzLTI4OGMtNDMxZi04MWJlLWRiNDdhOTAzYmQxOCIsImNyZWF0aW9uVGltZSI6MTU1MzE1MDQ1MDAwMCwibGFzdE1vZGlmaWVkVGltZSI6MTU1NTAyODUxMzAwMCwidXNlcm5hbWUiOiJ3dWdkQGNlZmMuY29tLmNuIiwiZmlyc3ROYW1lIjoi5qGC5LicIiwibGFzdE5hbWUiOiLlkLQiLCJjb3VudHJ5IjoiVFciLCJyb2xlIjoidGVuYW50IiwiZ3JvdXBzIjpbIjFhNzIzYjM3LTdhOTgtNGQwZi1iZDRiLWM2NWU3OGViMGJiMCIsInd1Z2RAY2VmYy5jb20uY24iXSwiY2ZTY29wZXMiOlt7Imd1aWQiOiIxYTcyM2IzNy03YTk4LTRkMGYtYmQ0Yi1jNjVlNzhlYjBiYjAiLCJzc29fcm9sZSI6InRlbmFudCIsInNwYWNlcyI6WyIqIl19XSwic2NvcGVzIjpbImRhc2hib2FyZC1ncmFmYW5hLWVuc2Fhcy1iai0xNTM2MTMwMDMzMTA1LlZpZXdlciJdLCJzdGF0dXMiOiJhY3RpdmUiLCJvcmlnaW4iOiJTU08iLCJvdmVyUGFkZGluZyI6ZmFsc2UsInN5c3RlbSI6ZmFsc2UsInJlZnJlc2hUb2tlbiI6IjRlNzMzYWJlLWFmZmItNDAyMy1iZTQ0LWZiMjg0NDViN2Q2OSJ9.A3eNxrv7QmvY-HG83gJ6-kesR1XWOpx20e2nmRmp-MaBCLcER1ITlogclupDnyjXHnJwwwfK4Shr9NkhQhJ7Hg","refreshToken":"4e733abe-affb-4023-be44-fb28445b7d69"}
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }else{
            //返回异常	{"code":400025,"message":"The userName or password is incorrect"}
                reader = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));
                result = reader.readLine();
            }
            resultMap.put("code", responseCode+"");
            resultMap.put("result", result);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "请求APM代码异常!");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }
	
	public static void main(String[] args) {
		
		
    	String url="https://api-apm-fuzhou-default-space.wise-paas.com.cn/auth/login";
    	String rawBody="";

//    {"request":{"parameters":{"customerName":"888","idType":"身份证","birthday":1501485108463,"sex":"1","idNumber":"13123912468712"},"user":"user1"}}
//    	rawBody ="{\"Request\":{\"Parameters\":{\"customerName\":\"888\",\"idType\":\"身份证\",\"birthday\":1501485108463,\"sex\":\"1\",\"idNumber\":\"13123912468712\"},\"User\":\"user1\",\"resource\":\"imap\"}}";
    	
//    	rawBody="{ \"userName\": \"renp@cefc.com.cn\", \"password\": \"Rp123945@\" }";
//    	rawBody="{ \"userName\": \"wugd@cefc.com.cn\", \"password\": \"88440786/Wgd\" }";
//    	System.out.println(rawBody);
    	
//    	Map<String,String> resultMap =doJsonPost(url, rawBody);
//    	String response=post(url, rawBody);
//    	System.out.println(resultMap);
    	
    	url="http://192.168.0.112:8083/hstatus/list2.action";
    	rawBody="{\"a\":{ \"userName\": \"renp@cefc.com.cn\", \"password\": \"Rp123945@\"}}";
    	Map<String,String> resultMap =doJsonPost(url, rawBody);
    	System.out.println(resultMap);
	}
	
}

