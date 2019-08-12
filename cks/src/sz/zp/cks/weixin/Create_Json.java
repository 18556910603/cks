package sz.zp.cks.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import sz.zp.cks.entity.AccessToken;
import sz.zp.cks.utils.WeiXinUtil;
import sz.zp.cks.weixin.template.AlarmNoticeTemplate;
import sz.zp.cks.weixin.template.MaintainNoticeTemplate;
import net.sf.json.JSONObject;

/**
 * @author Lenovo
 *
 *发送模板消息类
 */
public class Create_Json {
	static String Template_id_Alarm="CDJZL6U2quVyOfSLbZRWmIAxwNJC2D3lNXP1myL8vSg";//告警模板
	static String Template_id_Maintain="6Qem7M5sSOrgaWahWAYmzH3XdpfL0hBz7snhW1kOcoc";//保养模板

	//获取告警提醒json;

	/**
	 *  构建模板消息所需json，加入跳转url，还有touser
	 * @param touser （跳转的下一目标 的 用户open_id）
	 * @param user	（用户名）
	 * @param ep_id	（设备编号或名称）
	 * @param remark_id	 (消息提醒编号）
	 * @return
	 */
	public static JSONObject create_Alarm_Json(String touser,String user,String ep_id,String ep_name,String remark_id){

		JSONObject jsonObject=null;

		//模板id


		//点击模板后的链接地址

		String url="http://cefc.nat100.top/";
		

		//模板的主题颜色

		String topcolor="#008000";
		
		//构造json包
		AlarmNoticeTemplate ant = new AlarmNoticeTemplate(touser,Template_id_Alarm,url,topcolor,user,ep_id,ep_name,remark_id);

		jsonObject=JSONObject.fromObject(ant.getMap());

		return jsonObject;

	}
	/**
	 * @param wx_open_id
	 * @param username
	 * @param ep_id
	 * @param ep_type
	 * @param maintainDate
	 * @return
	 */
	public static JSONObject create_Maintain_Json(String wx_open_id,String username,String ep_id,String ep_type,String maintainDate){

		JSONObject jsonObject=null;

		//模板id


		//点击模板后的链接地址

		String url="http://cefc.nat100.top/";

		//构造json包
		MaintainNoticeTemplate ant = new MaintainNoticeTemplate(wx_open_id, Template_id_Maintain, username, ep_id, ep_type, maintainDate, url);

		jsonObject=JSONObject.fromObject(ant.getMap());

		return jsonObject;

	}
	
	
	/**
	 * 发送模板消息json
	 * @param params
	 * @param accessToken 调用微信接口需要accesstoken
	 */
	public static void send_Json(String params,String accessToken){

		StringBuffer bufferRes =new StringBuffer();

		try {

			URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" +accessToken);

			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();

			// 请求方式

			conn.setDoOutput(true);

			conn.setDoInput(true);

			conn.setRequestMethod("POST");

			conn.setUseCaches(false);

			conn.setInstanceFollowRedirects(true);

			conn.setRequestProperty("Content-Type","application/json");

			conn.connect();

			// 获取URLConnection对象对应的输出流

			OutputStreamWriter out =new OutputStreamWriter(conn.getOutputStream(),"UTF-8");

			// 发送请求参数

			//out.write(URLEncoder.encode(params,"UTF-8"));

			//发送json包

			out.write(params);

			out.flush();

			out.close();

			InputStream in =conn.getInputStream();

			BufferedReader read =new BufferedReader(new InputStreamReader(in,"UTF-8"));

			String valueString =null;

			while ((valueString=read.readLine())!=null){

				bufferRes.append(valueString);

			}

			//输出返回的json

			System.out.println("bufferRes:"+bufferRes);

			in.close();

			if (conn !=null){

				// 关闭连接

				conn.disconnect();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}


	//获取用户基本信息(UnionID机制);

	/**
	 * 获取用户基本信息
	 * @param openid
	 * @return
	 */
	public static String getUserData(String openid){

		StringBuffer bufferRes = new StringBuffer();

		String result = null;

		try {
			AccessToken token= WeiXinUtil.getAccessToken();

			URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token.getToken() +"&openid=" + openid+"&lang=zh_CN");

			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();

			// 请求方式

			conn.setDoOutput(true);

			conn.setDoInput(true);

			conn.setRequestMethod("GET");

			conn.setUseCaches(false);

			conn.setInstanceFollowRedirects(true);

			conn.setRequestProperty("Content-Type","application/json");

			conn.connect();

			// 获取URLConnection对象对应的输入流

			InputStream in =conn.getInputStream();

			BufferedReader read =new BufferedReader(new InputStreamReader(in,"UTF-8"));

			String valueString =null;

			while ((valueString=read.readLine())!=null){

				bufferRes.append(valueString);

			}

			System.out.println(bufferRes);

			in.close();

			if (conn != null){

				// 关闭连接

				conn.disconnect();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		//将返回的字符串转换成json

		JSONObject jsonObject = JSONObject.fromObject(bufferRes.toString());

		//解析json中的数据

		String subscribe = jsonObject.get("subscribe").toString();

		//等于1表示有关注者，0表示没有关注者

		if("1".equals(subscribe.toString())){

			//解析出关注者的昵称

			result = (String)jsonObject.get("nickname");

		}

		return result;

	}
	
	/**
	 * 向指定用户发送指定类型的告警api
	 * @param open_id 用户open_id
	 * @param ep_id	设备id
	 * @param remark_id 标记（1为主管，2为维修员，3为维修确认）
	 * @param ep_name 家庭设备名称
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void sendAlarmNotice(String open_id,String ep_id,String ep_name,String remark_id){
		JSONObject jsonObject = null;
		String userName=null;
		userName=getUserData(open_id);
		if(userName!=null){
			//创建告警信息提醒json包
			jsonObject = Create_Json.create_Alarm_Json(open_id,userName,ep_id,ep_name, remark_id);

			//发送告警提醒模板消息
			AccessToken accesstoken;
			try {
				accesstoken = WeiXinUtil.getAccessToken();
				send_Json(jsonObject.toString(),accesstoken.getToken());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void sendMaintainNotice(String open_id,String ep_id,String ep_type,String maintainDate) throws ClientProtocolException, IOException{
		JSONObject jsonObject = null;
		String userName=null;
		userName=getUserData(open_id);
		if(userName!=null){
			jsonObject = Create_Json.create_Maintain_Json(open_id, userName, ep_id, ep_type, maintainDate);
			
			//发送告警提醒模板消息
			AccessToken accesstoken = WeiXinUtil.getAccessToken();
			send_Json(jsonObject.toString(),accesstoken.getToken());
		}
	}
	/**
	 * main方法
	 * @param args
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
      //  JSONObject jsonObject1 = null;

		String open_id = "omUw56Cm2pOS_w0bf9dMVwEo4YUI";

		String ep_id=null;
		
		String remark_id="1";
//		
//		userName = getUserData(open_id);
//		if(userName!=null){
//
//            //创建告警提醒json包;
//
//            jsonObject1 = Create_Json.create_TN_Json(open_id,userName,ep_id,remark_id);
//
//            //发送告警提醒模板消息;
//            
//            AccessToken accesstoken = WeiXinUtil.getAccessToken();
//
//            send_Json(jsonObject1.toString(),accesstoken.getToken());
//
//        }
		//sendAlarmNotice(open_id, ep_id, "冰箱",remark_id);
		sendMaintainNotice(open_id, "001", "002", "2014-01-01");

	}

}
