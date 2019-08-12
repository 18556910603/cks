package sz.zp.cks.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sz.zp.cks.dao.AlarmMapper;
import sz.zp.cks.dao.DataPushLogMapper;
import sz.zp.cks.entity.Alarm;
import sz.zp.cks.entity.DataPushLog;
import sz.zp.cks.entity.JackJsonUtils;
import sz.zp.cks.model.apm.ApmRule;
import sz.zp.cks.model.apm.Documents;
import sz.zp.cks.model.apm.ApmUser;
import sz.zp.cks.model.apm.DocumentsN;
import sz.zp.cks.model.apm.FailResponse;
import sz.zp.cks.model.apm.GetParameter;
import sz.zp.cks.model.apm.NiagaraJson;
import sz.zp.cks.model.apm.RequestBody;
import sz.zp.cks.model.apm.SuccessResponse;
import sz.zp.cks.model.apm.Tag;
import sz.zp.cks.service.ApmApiService;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.utils.DocuToNiag;
import sz.zp.cks.utils.FormatTime;
import sz.zp.cks.utils.HttpUtil;
import sz.zp.cks.utils.PubFun;



@Transactional(readOnly=true)
@Service("apmApiService")
public class ApmApiServiceImpl implements ApmApiService{
	@Autowired
	private DataPushLogMapper dataPushLogMapper;
	//登录账号renp@cefc.com.cn
	//Rp123945@
	private static String userName="{ \"userName\": \"renp@cefc.com.cn\", \"password\": \"Rp123945@\" }";
	//登陆接口地址
	private static String loginInUrl="http://api-apm-fuzhou-default-space.wise-paas.cn/auth/login";
	//type为公司名常量
	private static String type="CEFC";
	//category为对接系统名常量
	private static String category="OMMS";
	//推数接口地址
	private static String dataUrl="http://api-apm-fuzhou-default-space.wise-paas.cn/hist/event";
	//查询数据接口地址
	private static String getUrl="http://api-apm-fuzhou-default-space.wise-paas.cn/hist/event?type=CEFC&category=OMMS";
	
	private static String GET="GET";
	
	private static String POST="POST";
	
	@Autowired
	private AlarmMapper alarmMapper;
	@Autowired
	EquipmentRepairService equipmentRepairService;
	
	
	
	
	/** client接口
	 *  推送南瓜数据给apm 获取apm响应 
	 * @param    request请求参数  
	 *  zdy        
	 * @return  
	 */	
	@Override
	public String insertByPreForApm(String message) {
		//1.准备南瓜的数据调用本接口
		//2.模拟登录接口获取token>>
	   Map<String,String> resultMap = HttpUtil.httpToApm(loginInUrl, userName,POST);
	   System.out.println("login:"+resultMap);
		String code=resultMap.get("code");
		String result=resultMap.get("result");
		String token="";
		ApmUser tApmUser=new ApmUser();
		JSONObject resBodyObj = JSONObject.fromObject(result);
		if(("200").equals(code)){
			//正常返回
			tApmUser.setExpiresIn(resBodyObj.getString("expiresIn"));
			tApmUser.setDashboardUrl(resBodyObj.getString("dashboardUrl"));
			tApmUser.setTokenType(resBodyObj.getString("tokenType"));
			token=resBodyObj.getString("accessToken");
			System.out.println(token);
			tApmUser.setAccessToken(token);
			tApmUser.setRefreshToken(resBodyObj.getString("refreshToken"));
			
		}else{
			//异常返回
			System.out.println("登陸接口失败");
		}
		//登录接口结束>>
	
		//3.用tok认证--调apm接口推数过去
		//开始报文转换
		String niagaraJson=new String();
		niagaraJson=message;
    	System.out.println("------------niagaraJson:"+niagaraJson);
    	NiagaraJson alarmJson = JackJsonUtils.fromJson(niagaraJson, NiagaraJson.class);
    	String source = alarmJson.getSource();
    	String text = alarmJson.getText();
    	String value= alarmJson.getValue();
    	if(value==null){
    		value="";
    	}
    	String sourceState = alarmJson.getSourceState();
    	String hyperlinkOrd = alarmJson.getHyperlinkOrd();
    	String highLimit = alarmJson.getHighLimit();
    	String lowLimit = alarmJson.getLowLimit();
    	String content=source+"@"+text+"@"+hyperlinkOrd;
    	String time=alarmJson.getTimestamp();
    	String UTCtime="";
    	if(time!=null){
    	//time=FormatTime.getTime(time);
    	UTCtime = FormatTime.localToUTC(time);
    	}
    	String recoverTime=alarmJson.getNormalTime();
    	if(recoverTime!=null){
    		//recoverTime=FormatTime.getTime(recoverTime);
    		recoverTime = FormatTime.localToUTC(time);
    	}
    	String[] sourceArr = source.split("_");
    	for (int i = 0; i < sourceArr.length; i++) {
			//System.out.println(sourceArr[i]);
		}
    	String epIdt=sourceArr[2];
    	String area=sourceArr[0];
    	String factory=sourceArr[1];
    	String point=sourceArr[3];
    	RequestBody requestBody=new RequestBody();
    	requestBody.setType(type);
    	requestBody.setCategory(category);
    	Documents documents=new Documents();
    	documents.setTopoId("1");
    	documents.setNodeName(epIdt);
    	documents.setTopoName(area);
    	documents.setNodeId("1"); 
    	documents.setScadaId("");
    	documents.setDeviceId(""); 
    	documents.setEventName(point);
    	documents.setEventTime(UTCtime);
    	documents.setSubject("");
    	documents.setContent(content);
    	documents.setActionTime("");
    	documents.setActionTypes("");
    	documents.setLevel(sourceState);
    	Map ruleTagValueMap=new HashMap<String, Map>();
    	Integer ruleCount=0;
    	Map r0=new HashMap<String, Object>();
    	Map r1=new HashMap<String, Object>();
    	Tag tag=new Tag();
    	Map atag=new HashMap<String, Tag>();
    	tag.setTagName(point);
    	tag.setValue(value);
    	atag.put(point, tag);
    	r0.put("level", sourceState);
    	r0.put("status", "");
    	r0.put("comparison", "value");
    	r1.put("level", sourceState);
    	r1.put("status", "");
    	r1.put("comparison", "value");
    	
    	if(highLimit.indexOf("err")==-1){ 
    		ruleCount++;	
        	}
    	if(lowLimit.indexOf("err")==-1){ 
    		ruleCount++;	
        	}
    	if(ruleCount==1){
    	if(highLimit.indexOf("err")!=-1){ 
    	System.out.println("highLimit未设置值");    	
    	}else{
        	r0.put("symbols", ">");
        	r0.put("threshold", highLimit);
        	r0.put(point, tag);
    	}
    	if(lowLimit.indexOf("err")!=-1){ 
        	System.out.println("lowLimit未设置值");    	
        }else{
        	r0.put("symbols", "<");
        	r0.put("threshold",lowLimit);
        	r0.put(point, tag);
        }
    	ruleTagValueMap.put("r0", r0);
    	}
    	if(ruleCount==2){
        	r0.put("symbols", ">");
        	r0.put("threshold", highLimit);
    		r0.put(point, tag);        	
        	r1.put("symbols", "<");
        	r1.put("threshold",lowLimit);
    		r1.put(point, tag);
    		ruleTagValueMap.put("r0", r0);
    		ruleTagValueMap.put("r1", r1);
        }    	
    	documents.setRuleTagValueMap(ruleTagValueMap);    	
    	documents.setPath(area+"/"+factory+"/"+epIdt);
    	documents.setRecoverTime(recoverTime);
    	List document=new ArrayList();
    	document.add(documents);
    	requestBody.setDocuments(document);    	
    	String requestJson = JackJsonUtils.toJson(requestBody);
    	//报文转换结束
    	 System.out.println("------------推数:"+documents.getNodeName());	
        System.out.println("------------requestJson:"+requestJson);	
		//调用推数据接口
		Map<String,String> resultTokenMap = HttpUtil.doJsonTokenPost(dataUrl,requestJson,POST,token);
		//4.获取推数接口返回，存表存日志
		String tCode=resultTokenMap.get("code");
		String tResult=resultTokenMap.get("result");
		JSONObject tResBodyObj = JSONObject.fromObject(tResult);
		String responseMessage=tResBodyObj.getString("message");
		if(("200").equals(tCode)){
			//正常返回
		System.out.println("-----------对接成功:"+responseMessage);
		
		}else{
			//异常返回
		System.out.println("-----------对接失败:"+responseMessage);		
		
		}		
		DataPushLog dataPushLog=new DataPushLog();
		String dataPushLogId = UUID.randomUUID().toString().replace("-", "");
		dataPushLog.setDataPushLogId(dataPushLogId);
		dataPushLog.setOriginalData(message);
		dataPushLog.setCode(code);
		dataPushLog.setData(requestJson);
		dataPushLog.setNodeName(epIdt);
		String currentDate = PubFun.getCurrentDate();
		dataPushLog.setPushTime(currentDate);
		dataPushLog.setResponseMessage(responseMessage);
		dataPushLog.setType("OMMS-TO-APM");
		dataPushLogMapper.insert(dataPushLog);
		//5.结束
		return result;
	}


	/**
	 * server接口 接受apm推送的数据 返回响应
	 * xuyaya 20190510
	 * @param request请求参数
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String insertByGetFromApm(String requestJson,
			HttpServletResponse response) throws Exception {
		JSONObject bodyObj = JSONObject.fromObject(requestJson);
		JSONObject responseObj = new JSONObject();
		// 1.校验
		try {
		String check = checkRule(bodyObj);
		if (!check.isEmpty()) {
//			response.setStatus(400100);
			FailResponse tFailResponse = new FailResponse("400100", check);
			responseObj = JSONObject.fromObject(tFailResponse);
			return responseObj.toString();
		  }		
		} catch (Exception e) {
			e.printStackTrace();
			responseObj = JSONObject.fromObject(new FailResponse("200", "successful,but checkRule exception."));
			throw new Exception(responseObj.toString());
		}		
		// 3.准备接受apm推来的数据解析		 
		List<NiagaraJson> list=new  ArrayList<NiagaraJson>();
		try {
			resolveJson(list, bodyObj);
		} catch (Exception e) {
			e.printStackTrace();
			responseObj = JSONObject.fromObject(new FailResponse("200", "successful,but resolveJson exception."));
			throw new Exception(responseObj.toString());
		}
		// 4.alarm表
		Alarm alarm = new Alarm();
		insertLog(list, alarm);
		
		// 5.业务表处理
		boolean flag=true;
		try {
			for(int i=0;i<list.size();i++){
				if(!equipmentRepairService.insertSubmitByAPM(list.get(i))){
					flag=false;
				};
			}
			if(!flag){
				responseObj = JSONObject.fromObject(new FailResponse("200", "successful,but insertSubmitByAPM return false."));
				return responseObj.toString();
			};
		} catch (Exception e) {
			e.printStackTrace();
			responseObj = JSONObject.fromObject(new FailResponse("200", "successful,but insertSubmitByAPM exception."));
			throw new Exception(responseObj.toString());
		}			
		// 6.正确返回响应
		FailResponse tResponseToApm = new FailResponse("200", "successful");
		responseObj = JSONObject.fromObject(tResponseToApm);
		// 7.结束
		return responseObj.toString();
	}

	/**
	 * 检查apm的数据是否完整，不完整返回false
	 * 
	 * @return
	 */
	public String checkRule(JSONObject obj) {
		String msg = "";
		String type = obj.getString("type");
		String category = obj.getString("category");
		if (type == null || ("").equals(type)) {
			msg += "error document format, type";
		}
		if (category == null || ("").equals(category)) {
			msg += "/category";
		}
		if (msg.isEmpty()) {
			return "";
		} else {
			return msg+" is missing";
		}
	}

	/**
	 * 解析apm推送过来的数据
	 * @param tNiagaraJson 组装后的数据
	 * @param bodyObj
	 * @return
	 */
		public String resolveJson(List<NiagaraJson> list,JSONObject bodyObj) {
			String type = bodyObj.getString("type");
			String category = bodyObj.getString("category");
			//Documents
			JSONArray documentsListObj=bodyObj.getJSONArray("documents");	
	    	if(documentsListObj!=null&&documentsListObj.size()>0){
			for(int i=0;i<documentsListObj.size();i++){
				NiagaraJson tNiagaraJson=new NiagaraJson();
				JSONObject json =  (JSONObject) documentsListObj.get(i);
				String topoId = json.getString("topoId");
				String nodeName = json.getString("nodeName");
				String topoName = json.getString("topoName");
				String nodeId = json.getString("nodeId");
				String scadaId = json.getString("scadaId");
				String deviceId = json.getString("deviceId");
				String eventName = json.getString("eventName");
				String eventTime = json.getString("eventTime");
				String subject = json.getString("subject");
				String content = json.getString("content");
				String actionTime = json.getString("actionTime");
				String actionTypes = json.getString("actionTypes");
				String level = json.getString("level");			
				String path = json.getString("path");	
				String recoverTime = json.getString("recoverTime");	
				//utc时间转本地时间
				if(eventTime!=null&&!("").equals(eventTime)){
					eventTime=FormatTime.UTCToCST(eventTime, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd HH:mm:ss"); 
				}
				if(actionTime!=null&&!("").equals(actionTime)){
					actionTime=FormatTime.UTCToCST(actionTime, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd HH:mm:ss"); 
				}			
				if(recoverTime!=null&&!("").equals(recoverTime)){
					recoverTime=FormatTime.UTCToCST(recoverTime, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd HH:mm:ss"); 
				}			
				//ruleTagValueMap
				JSONObject tjson = json.getJSONObject("ruleTagValueMap");//ruleTagValueMap
				Map<String, ApmRule> ruleTagValueMap =new HashMap<String, ApmRule>();
		        Iterator<String> iterator = tjson.keys();
		        String mKey = "";
		        JSONObject mValue = null;
		        while (iterator.hasNext()) {
		        	mKey = iterator.next();//r0 r1
		        	mValue = tjson.getJSONObject(mKey);
		            if(mValue!=null){
		            
		            Iterator<String> niterator = mValue.keys();
				        String nKey = "";
				        JSONObject nValue = null;
				        String level2 ="";
				        String status ="";
				        String comparison ="";
				        String threshold ="";
				        String symbols ="";
				        String tagName ="";
				        String value ="";
				        while (niterator.hasNext()) {
				        	nKey = niterator.next();
				        	if(("level").equals(nKey)){
				        		level2 = mValue.getString("level");	
				        	}else if(("status").equals(nKey)){
				        		status = mValue.getString("status");	
				        	}else if(("comparison").equals(nKey)){
				        		comparison = mValue.getString("comparison");	
				        	}else if(("threshold").equals(nKey)){
				        		threshold =mValue.getString("threshold");	
				        	}else if(("symbols").equals(nKey)){
				        		symbols =mValue.getString("symbols");	
				        	}else{
				        		nValue = mValue.getJSONObject(nKey);//Atag1
				        		if(nValue!=null){
					        		tagName=nValue.getString("tagName");			
					        		value=nValue.getString("value");	
				        		}
				        	}
				        }
				        
				        if((">").equals(symbols)||(">=").equals(symbols)){
				        	tNiagaraJson.setHighLimit(threshold);
				        }else if(("<").equals(symbols)||("<=").equals(symbols)){
				        	tNiagaraJson.setLowLimit(threshold);
				        }
				        tNiagaraJson.setValue(value);
		            } 
		        }
				
		        tNiagaraJson.setSource(path.replace("/", "_")+"_"+eventName);
		        tNiagaraJson.setTimestamp(eventTime);
		        
		        if(recoverTime!=null&&!("").equals(recoverTime)){
		        	 tNiagaraJson.setSourceState("Normal");//告警类型
		        }else{
		        	 tNiagaraJson.setSourceState("Offnormal");//告警类型
		        }
		        tNiagaraJson.setAckState("Unacked");
		        tNiagaraJson.setPriority("");//告警等级：	1~188,1最大
		        tNiagaraJson.setAlarmClass("");//选择指定的告警组件(名称可自定义)
//		        tNiagaraJson.setText(content.substring(PubFun.getCharacterPosition(content, "@", 1)+1, PubFun.getCharacterPosition(content, "@", 2)));
		        tNiagaraJson.setText(content);
		        tNiagaraJson.setNormalTime(recoverTime);
//		        tNiagaraJson.setHyperlinkOrd(content.substring(PubFun.getCharacterPosition(content, "@", 2)+1, content.length()));
		        tNiagaraJson.setHyperlinkOrd(content);
		        list.add(tNiagaraJson);
			}	
		  }
			return "";
		}

		/**
		 *  存日志
		 * 
		 * @return
		 */
		public Boolean insertLog(List<NiagaraJson> list,Alarm alarm){
			for(int i=0;i<list.size();i++){
				NiagaraJson tNiagaraJson=list.get(i);
//				if(tNiagaraJson.getNormalTime()!=null&&!("").equals(tNiagaraJson.getNormalTime())){
					String alarmId = "APM"
							+ UUID.randomUUID().toString().replace("-", "");
					alarm.setAlarmId(alarmId); // 告警id
					alarm.setSource(tNiagaraJson.getSource()); // 设备id
					alarm.setTimestamp(tNiagaraJson.getTimestamp()); // 告警时间
					alarm.setSourceState(tNiagaraJson.getSourceState()); // 设备状态（Normal正常/Offnormal异常/fault断开状态）
					alarm.setAckState(tNiagaraJson.getAckState()); // 告警确认 acked/Unacked
					alarm.setAlarmClass(tNiagaraJson.getAlarmClass()); // 告警类别：如HighPriorityAlarms（上限告警）
					alarm.setAlarmText(tNiagaraJson.getText()); // 故障情况描述
					alarm.setPriority(tNiagaraJson.getPriority()); // 告警等级
					alarm.setHighLimit(tNiagaraJson.getHighLimit()); // 上限
					alarm.setLowLimit(tNiagaraJson.getLowLimit()); // 下限
					alarm.setValue(tNiagaraJson.getValue());
					alarm.setType("APM");
					alarm.setCreationDate(PubFun.getCurrentDate());
					alarm.setNormalTime(tNiagaraJson.getNormalTime());
					if(alarmMapper.insert(alarm)<=0){
						return false;	
					};
//				}
			}
			return true;
		}
	

	
	//{"expiresIn":1555926604,"dashboardUrl":"https://dashboard-fuzhou-default-space.wise-paas.com.cn","tokenType":"Bearer","accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjbi13aXNlcGFhcyIsImlhdCI6MTU1NTkyMzAwNCwiZXhwIjoxNTU1OTI2NjA0LCJ1c2VySWQiOiI5ZGU0NjQ4NC01OTdlLTQwMWUtOWFkYi0zZDU0NzVhM2IzZWUiLCJ1YWFJZCI6ImQ1MzQ3MGIzLTI4OGMtNDMxZi04MWJlLWRiNDdhOTAzYmQxOCIsImNyZWF0aW9uVGltZSI6MTU1MzE1MDQ1MDAwMCwibGFzdE1vZGlmaWVkVGltZSI6MTU1NTAyODUxMzAwMCwidXNlcm5hbWUiOiJ3dWdkQGNlZmMuY29tLmNuIiwiZmlyc3ROYW1lIjoi5qGC5LicIiwibGFzdE5hbWUiOiLlkLQiLCJjb3VudHJ5IjoiVFciLCJyb2xlIjoidGVuYW50IiwiZ3JvdXBzIjpbIjFhNzIzYjM3LTdhOTgtNGQwZi1iZDRiLWM2NWU3OGViMGJiMCIsInd1Z2RAY2VmYy5jb20uY24iXSwiY2ZTY29wZXMiOlt7Imd1aWQiOiIxYTcyM2IzNy03YTk4LTRkMGYtYmQ0Yi1jNjVlNzhlYjBiYjAiLCJzc29fcm9sZSI6InRlbmFudCIsInNwYWNlcyI6WyIqIl19XSwic2NvcGVzIjpbImRhc2hib2FyZC1ncmFmYW5hLWVuc2Fhcy1iai0xNTM2MTMwMDMzMTA1LlZpZXdlciJdLCJzdGF0dXMiOiJhY3RpdmUiLCJvcmlnaW4iOiJTU08iLCJvdmVyUGFkZGluZyI6ZmFsc2UsInN5c3RlbSI6ZmFsc2UsInJlZnJlc2hUb2tlbiI6IjRlNzMzYWJlLWFmZmItNDAyMy1iZTQ0LWZiMjg0NDViN2Q2OSJ9.A3eNxrv7QmvY-HG83gJ6-kesR1XWOpx20e2nmRmp-MaBCLcER1ITlogclupDnyjXHnJwwwfK4Shr9NkhQhJ7Hg","refreshToken":"4e733abe-affb-4023-be44-fb28445b7d69"}
	//返回异常	{"code":400025,"message":"The userName or password is incorrect"}

// String date = "Thu Aug 27 18:05:49 CST 2015";
		// date = "2019-四月-24 13:49:28";
		//
		// SimpleDateFormat sdf = new SimpleDateFormat(
		// "EEE-MMM-dd HH:mm:ss zzz yyyy", Locale.US);
		// sdf = new SimpleDateFormat("EEEE-MMM-dd HH:mm:ss aa zzz",
		// Locale.ENGLISH);
		//
		// sdf = new SimpleDateFormat("yyyy-MM'月'-dd HH:mm:ss", Locale.CHINA);
		// Date d = sdf.parse(date);
		// String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		// .format(d);
		//
		// System.out.println(formatDate);
		// 转对象>
		// ApmRequestJson tApmRequestJson = new ApmRequestJson();
		// Map<String, ApmRule> map = new HashMap<String, ApmRule>();
		// ApmRule tApmRule = new ApmRule();
		// tApmRule.setComparison("2222");
		// map.put("r0", tApmRule);
		// tApmRequestJson.setRuleTagValueMap(map);
		//
		// JSONObject resBodyObj = JSONObject.fromObject(tApmRequestJson);
		// System.out.println(resBodyObj.toString());
		// 读取报文
		// 模拟
		//String requestJson = PubFun.readJson("D:\\Code\\Request\\Request.txt");
		// 存日志
//		JSONObject bodyObj = JSONObject.fromObject(requestJson);
//		ApmApiServiceImpl tApmApiServiceImpl = new ApmApiServiceImpl();
//		tApmApiServiceImpl.insertByGetFromApm(bodyObj, null);

	/**
	 * client接口 查询OMMS推送的数据 返回响应
	 * 
	 * @param request请求参数
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List getByPreForApm(GetParameter getParameter) {
		//1.准备南瓜的数据调用本接口
		//2.模拟登录接口获取token>>
	   Map<String,String> resultMap = HttpUtil.httpToApm(loginInUrl, userName,POST);
	   System.out.println("login:"+resultMap);
		String code=resultMap.get("code");
		String result=resultMap.get("result");
		String token="";
		String url="";
		ApmUser tApmUser=new ApmUser();
		JSONObject resBodyObj = JSONObject.fromObject(result);
		if(("200").equals(code)){
			//正常返回
			tApmUser.setExpiresIn(resBodyObj.getString("expiresIn"));
			tApmUser.setDashboardUrl(resBodyObj.getString("dashboardUrl"));
			tApmUser.setTokenType(resBodyObj.getString("tokenType"));
			token=resBodyObj.getString("accessToken");
			System.out.println(token);
			tApmUser.setAccessToken(token);
			tApmUser.setRefreshToken(resBodyObj.getString("refreshToken"));
			
		}else{
			//异常返回
			System.out.println("登陸接口失败");
		}
		//登录接口结束>>
		String startTs=getParameter.getStartTs();
		startTs= FormatTime.localToUTC(startTs);
		String endTs=getParameter.getEndTs();
		endTs= FormatTime.localToUTC(endTs);
		System.out.println(startTs+":"+endTs);
		if(!startTs.equals("")||startTs!=null){
			url=getUrl+"&startTs="+startTs;
		}
		if(!endTs.equals("")||endTs!=null){
			url=url+"&endTs="+endTs;
		}
		//4.获取推数接口返回，存表存日志
		String requestJson="";
		System.out.println("---------------url:"+url);
		Map<String,String> resultTokenMap = HttpUtil.doJsonTokenPost(url,requestJson,GET,token);
		String tCode=resultTokenMap.get("code");
		String tResult=resultTokenMap.get("result");		
		System.out.println("---------tResult:"+tResult);
		List<DocumentsN> list = new ArrayList<>();
		JSONObject tResBodyObj = JSONObject.fromObject(tResult);
		String totalCount=tResBodyObj.getString("totalCount");
		JSONArray jsonArray = (JSONArray) tResBodyObj.get("documents");
		   for (int i=0;i<jsonArray.size();i++){
               String string = jsonArray.getString(i);
               //转换为documents
               Documents documents = JackJsonUtils.fromJson(string, Documents.class);
               DocumentsN documentsN = DocuToNiag.getNiagJson(documents);
               String eventTime = documents.getEventTime();
               System.out.println("--------------eventTime:"+eventTime);
               //添加到集合里面去
               list.add(documentsN);
           }
		System.out.println("--------------list的大小:"+list.size());
		if(("200").equals(tCode)){
			//正常返回
		System.out.println("-----------成功查询:"+totalCount);
		
		}else{
			//异常返回
		System.out.println("-----------查询失败--------------");		
		
		}		
		return list;
	}
	

	public static void main(String[] args) throws ParseException {
		// 1、使用JSONObject
		// ApmUser tApmUser=new ApmUser();
		// tApmUser.setAccessToken("jjj");
		// JSONObject json = JSONObject.fromObject(tApmUser);
		// System.out.println(json.toString());
		//


/*String date = "Thu Aug 27 18:05:49 CST 2015";
date="2019-四月-24 13:49:28";
		// String name="2019-四月-24 13:49:28 PM CST";
		// uct
 sdf = new SimpleDateFormat("yyyy-MM'月'-dd HH:mm:ss", Locale.CHINA);
Date d = sdf.parse(date);
String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
 
System.out.println(formatDate);
	String niagaraJson=new String();
	niagaraJson=PubFun.readJson("D:\\Request\\Request.txt");
	niagaraJson="{\"source\": \"dorm2_612_db_kwh\",\"timestamp\": \"17-Apr-19 5:01:09 PM CST\",\"sourceState\": \"Fault\",\"ackState\": \"Unacked\",\"priority\": \"1\",\"alarmClass\": \"HighPriorityAlarms\",\"text\": \"\",\"lowLimit\": \"0.0\",\"highLimit\": \"0.0\",\"hyperlinkOrd\": \"../file:^FEI/alarmInfo/alarmInfo.html\"}";
	System.out.println(niagaraJson);
	NiagaraJson alarmJson = JackJsonUtils.fromJson(niagaraJson, NiagaraJson.class);
	
	System.out.println(alarmJson.getTimestamp());
	System.out.println(alarmJson.getValue());*/	
//		GetParameter getParameter=new GetParameter();
//		String startTs="2019-05-16 00:00:00";
//		String endTs="2019-05-16 24:00:00";
//		getParameter.setStartTs(startTs);
//		getParameter.setEndTs(endTs);	
		ApmApiServiceImpl apmApiServiceImpl=new ApmApiServiceImpl();
//		apmApiServiceImpl.getByPreForApm(getParameter);
		
		
		
		String message="{\"source\":\"cf2_6_612_rsb_ys\",\"normalTime\":\"\",\"timestamp\":\"2019-05-16 09:57:35\",\"sourceState\":\"Offnormal\",\"ackState\":\"Unacked\",\"priority\":\"1\",\"alarmClass\":\"HighPriorityAlarms\",\"text\":\"qq悲伤那么大qq\",\"lowLimit\":\"%err:baja:Facets:lowLimit%\",\"highLimit\":\"50.0\",\"hyperlinkOrd\":\"../file:^FEI/alarmInfo/alarmInfo.html\",\"value\":\"91231239.0\",\"uuid\":\"449efbc0-214f-4ab8e-8df7-475040d0e22b\"}";
		apmApiServiceImpl.insertByPreForApm(message);	
		

   }
	
	}


