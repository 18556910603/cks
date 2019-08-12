package sz.zp.cks.utils;

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
    public static Map<String,String> httpToApm(String urlPath, String Json,String RequestMethod) {
        //返回map
    	Map<String,String> resultMap = new HashMap<String,String>();
    	String result="";
    	int responseCode=888;//自定义code
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(RequestMethod);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "GBK");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=GBK");
            //String tokenAccess= "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjbi13aXNlcGFhcyIsImlhdCI6MTU1NzQ2MTc1NCwiZXhwIjoxNTU3NDY1MzU0LCJ1c2VySWQiOiI2ZGE1OGQ3Zi1mZDMyLTRhNmMtYTA0Zi01YmQ5MTdhMjk2MGQiLCJ1YWFJZCI6ImY2MDc1YWRlLTM0MjUtNDJjMC05Njc5LWRjNjRiMzc2ZWViNSIsImNyZWF0aW9uVGltZSI6MTU1MTgzNTAwNzAwMCwibGFzdE1vZGlmaWVkVGltZSI6MTU1NzQ1NDQxOTAwMCwidXNlcm5hbWUiOiJyZW5wQGNlZmMuY29tLmNuIiwiZmlyc3ROYW1lIjoiUCIsImxhc3ROYW1lIjoiUiIsInJvbGUiOiJ0ZW5hbnQiLCJncm91cHMiOlsiMWE3MjNiMzctN2E5OC00ZDBmLWJkNGItYzY1ZTc4ZWIwYmIwIiwicmVucEBjZWZjLmNvbS5jbiJdLCJjZlNjb3BlcyI6W3siZ3VpZCI6IjFhNzIzYjM3LTdhOTgtNGQwZi1iZDRiLWM2NWU3OGViMGJiMCIsInNzb19yb2xlIjoidGVuYW50Iiwic3BhY2VzIjpbIioiXX1dLCJzY29wZXMiOlsiQVBNLTE1NTMyMzY2Nzg0NzAuYWRtaW4iXSwic3RhdHVzIjoiYWN0aXZlIiwib3JpZ2luIjoiU1NPIiwib3ZlclBhZGRpbmciOmZhbHNlLCJzeXN0ZW0iOmZhbHNlLCJyZWZyZXNoVG9rZW4iOiJjNWVhYmEzOS1jZTg5LTQzMTUtYTlmOS0yZDFmNzAzNGI4ZDgifQ.ce5yeTUmff3Z2Bm0duOolUBXk29kyjQUWDvNZE7jq65vcsv3mOIo-GlqFi-MzkGb4IHZQfmdthTKSdeDi0wPUg";
            //conn.setRequestProperty("Authorization", "Bearer " + tokenAccess);

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
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                result = reader.readLine();
            }else{
                reader = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream(), "utf-8"));
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
    
    public static Map<String,String> doJsonTokenPost(String urlPath, String Json,String RequestMethod,String token) {
        //返回map
    	Map<String,String> resultMap = new HashMap<String,String>();
    	String result="";
    	int responseCode=888;//自定义code
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(RequestMethod);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "GBK");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=GBK");
            String tokenAccess=token;
            conn.setRequestProperty("Authorization", "Bearer " + tokenAccess);

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
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                result = reader.readLine();
            }else{
                reader = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream(), "utf-8"));
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
            conn.setRequestProperty("Charset", "utf-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=utf-8");
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
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                result = reader.readLine();
            }else{
                reader = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream(), "utf-8"));
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
    	String rawBody1="";
    	String rawBody2="";
//    {"request":{"parameters":{"customerName":"888","idType":"身份证","birthday":1501485108463,"sex":"1","idNumber":"13123912468712"},"user":"user1"}}
//    	rawBody ="{\"Request\":{\"Parameters\":{\"customerName\":\"888\",\"idType\":\"身份证\",\"birthday\":1501485108463,\"sex\":\"1\",\"idNumber\":\"13123912468712\"},\"User\":\"user1\",\"resource\":\"imap\"}}";
    	
//    	rawBody="{ \"userName\": \"renp@cefc.com.cn\", \"password\": \"Rp123945@\" }";
//    	rawBody="{ \"userName\": \"wugd@cefc.com.cn\", \"password\": \"88440786/Wgd\" }";
//    	System.out.println(rawBody);
    	
//    	Map<String,String> resultMap =doJsonPost(url, rawBody);
//    	String response=post(url, rawBody);
//    	System.out.println(resultMap);
    	
    //	url="http://192.168.0.112:8083/toApmApi/getFromApm.action";
//    	String url2="http://api-apm-fuzhou-default-space.wise-paas.com.cn/hist/event";
//    	rawBody1="{\"type\":\"CEFC\", \"category\":\"OMMS\",\"documents\":[{\"topoId\": \"21702\",\"nodeName\": \"Device 1\","+
//    			"topoName: \"yjdevgroupgroup\",nodeId: \"17341\",scadaId: \"b75bef07-68db-4b23-b526-6191ea0faea8\","+
//    			"deviceId: \"Device1\",eventName: \"atag1\",eventTime: \"2019-04-03 09:45:23.063\","+
//    			"subject: \"atag1-yjdevgroupgroup-Device 1-2019-04-03 09:45:23.063\","+
//    			"content: \"Group:yjdevgroupgroup\\nMachine:Device 1\\nEvent Name:atag1\\nDescription:\\nTime:2019-04-03 09:45:23.063\\nDetail:\\nAtag1>50\\n\","+
//    			"actionTime: \"2019-04-03 09:46:41.823\",actionTypes: \"log\",level: \"Critical\","+
//    			"ruleTagValueMap: { r0: { Atag1: {tagName: \"b75bef07-68db-4b23-b526-6191ea0faea8@Device1@ATag1\","+
//    			"value: Double(68.0)},level: \"Critical\",status: \"H\",comparison: \"value\","+
//    			"threshold: \"50\",symbols: \">\"}},"+
//    			"path: \"yjdevgroupgroup/Device 1\",recoverTime: \"2019-04-03 09:45:25.060\"}]}";
//    	
//    	rawBody2="{ \"userName\": \"renp@cefc.com.cn\", \"password\": \"Rp123945@\" }";
//    	//rawBody="{\"source\": \"dorm2_612_db_kwh\",\"timestamp\": \"17-Apr-19 5:01:09 PM CST\",\"sourceState\": \"Fault\",\"ackState\": \"Unacked\",\"priority\": \"1\",\"alarmClass\": \"HighPriorityAlarms\",\"text\": \"\",\"lowLimit\": \"0.0\",\"highLimit\": \"0.0\",\"hyperlinkOrd\": \"../file:^FEI/alarmInfo/alarmInfo.html\"}";
//    	
//    	String url1="http://api-apm-fuzhou-default-space.wise-paas.com.cn/auth/login";
//      //  url2="http://api-apm-fuzhou-default-space.wise-paas.com.cn/hist/event?startTs=2018-11-19T00:46:54Z&endTs=2019-05-09T17:16:54Z&type=advantech&category=apm";
//    	//rawBody="";		
//        System.out.println(rawBody1);
//
//    	Map<String,String> resultMap =httpToApm(url2, rawBody1,"POST");
//    	System.out.println(resultMap);
//    	
    	url="http://192.168.0.113:8083/toApmApi/getFromApm.action";

    	String loginInUrl="https://api-apm-fuzhou-default-space.wise-paas.cn/auth/login";

    	String userName="{ \"userName\": \"renp@cefc.com.cn\", \"password\": \"Rp123945@\" }";
    	 Map<String,String> resultMap = HttpUtil.httpToApm(loginInUrl, userName,"POST");
 	    System.out.println("login:"+resultMap);
	}
	
}

