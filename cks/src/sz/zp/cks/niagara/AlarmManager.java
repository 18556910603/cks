package sz.zp.cks.niagara;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sz.zp.cks.utils.AppConstants;
import sz.zp.cks.niagaraApi.IAlarm;
import sz.zp.cks.utils.HUtilTest;
import sz.zp.cks.utils.HttpClient;
import sz.zp.cks.utils.StringUtil;

public class AlarmManager implements IAlarm {
	static String urlBase = AppConstants.obix_url;
	static String user = AppConstants.obix_user;
	static String password = AppConstants.obix_pwd;
	static String listEndFm = "</list>";
	static String listBeginFm = "<list name=\"values\" of=\"obix:obj\">";

	@Override
	public void updateWorkAlarm() throws Exception {
		// --------批量更新告警信息——————————————开始
		String sWatch,makeVal,alarms;
		makeVal = Send("POST",urlBase+"/obix/watchService/make","");
		//截取的是href中的网址
		sWatch = HUtilTest.getInnerText(makeVal, "<obj href=\""+urlBase, "\"");
		alarms= Send("POST",urlBase+sWatch+"add/ ",
				"<obj is ='obix:WatchIn'><list names='hrefs'><uri val='/obix/config/Services/AlarmService/~alarmFeed/' /></list></obj> ");
		
		// -------------------过滤告警记录-------------------开始
		if(!StringUtil.isEmpty(alarms)){
			alarms = HUtilTest.getInnerText(alarms, listBeginFm, listEndFm);
			alarms = alarms.replace("</feed>", "");
			String[] alarmAy = alarms.split("</obj>");
			if(alarmAy !=null && alarmAy.length>0){
				for(String amKey : alarmAy){ //所有告警异常记录.
					if (amKey != null && amKey.indexOf("toState") > -1) {
						
					}
				}
			}
		}
	}
	
	private static String Send(String sMenthod,String sUrl,String sPara){
		String sRet="";
		try{
			URL url = new URL(sUrl);
			HttpClient client=new HttpClient(url);
			client.setAuthorization(user, password);
			sRet = client.run(sPara, sMenthod);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return sRet;
		
	}

}
