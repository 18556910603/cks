package sz.zp.cks.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;


public class JSONUtil {
	
	    public static Map<String,String> acceptJSON(HttpServletRequest request){
	        String acceptjson = "";
	        Map<String,String> map = new HashMap();
	        try {
	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
	            StringBuffer sb = new StringBuffer("");
	            String temp;
	            while ((temp = br.readLine()) != null) {
	                sb.append(temp);
	            }
	            br.close();
	            acceptjson = sb.toString();
	            map = (Map)JSON.parseObject(acceptjson);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return map;
	    }
}

