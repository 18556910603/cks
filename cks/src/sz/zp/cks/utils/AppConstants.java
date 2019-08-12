package sz.zp.cks.utils;

import com.digitalchina.base.util.DataUtil;


/**
 * 类描述：常量定义
 * 
 * @author CHENYB
 */
public class AppConstants {
 
	/**
	 * 成功
	 */
	public static final String RTN_CODE_SUCCESS = "000000";
	/** 返回码：无数据 */
	public static final String RTN_CODE_NODATA = "100002";
	public static final String RTN_CODE = "rtnCode";
	public static final String RTN_MSG = "rtnMsg";
	//MQTT订阅地址(IAQ网关地址).
	public static final String mqttSubsUrl = DataUtil.getProperty("mqttSubsUrl");
	//MQTT发布地址(服务器安装MQTT地址).
	public static final String mqttPubUrl = DataUtil.getProperty("mqttPubUrl");
	//统计宿舍数-当日最少用电数设置.
	public static final Double countElectLimit = Double.parseDouble(DataUtil.getProperty("countElectLimit"));
	//统计宿舍数-当日最少用水数设置.
	public static final Double countWaterLimit = Double.parseDouble(DataUtil.getProperty("countWaterLimit"));
	//附件上传根目录（服务器根目录地址）。
	public static final String uploadFilePath = DataUtil.getProperty("uploadFilePath");
	//下载附件地址
	public static final String downFilePath = DataUtil.getProperty("downFilePath");
	//obix服务地址
	public static final String obix_url = DataUtil.getProperty("obix_url");
	//obix账号
	public static final String obix_user = DataUtil.getProperty("obix_user");
	//obix账号密码
	public static final String obix_pwd = DataUtil.getProperty("obix_pwd");
	//ivs_serversocket_port视频推送ServerSocket端口.
	public static final Integer ivs_serversocket_port = Integer.parseInt(DataUtil.getProperty("ivs_serversocket_port"));
	//IVS推送视频是否保存到本服务器(默认是-1：根据服务器部署实际情况配置).
	public static final String ivs_video_copy2local = DataUtil.getProperty("ivs_video_copy2local");
	//IVS推送视频下载延迟时间(PS:因为IVS告警视频未录制完毕就推送,需要延迟下载到本地,具体根据录制视频时间长短配置)
	public static final Long ivs_video_copydelay = Long.parseLong(DataUtil.getProperty("ivs_video_copydelay"));
	//IVS视频保存地址。
	public static final String ivsFileSavePath = DataUtil.getProperty("ivsFileSavePath");
	//IVS视频下载地址。
	public static final String downIvsFilePath = DataUtil.getProperty("downIvsFilePath");
//	@SuppressWarnings("serial")
//	public static Map<String, String> findTaskMap() {
//		return new HashMap<String, String>() {
//			{
//				put("1", "未上报");
//				put("2", "处理中");
//				put("3", "已处理");
//				put("4", "已处理");
//				put("9", "不受理");
//			}
//		};
//	}
	//一卡通系统APPKEY
	public static final String APPKEY = DataUtil.getProperty("door_appkey");
	
	//一卡通系统密钥
	public static final String SECRET = DataUtil.getProperty("door_secret");
	
	
	//一卡通系统请求http地址
	public static final String OPENAPI_IP_PORT_HTTP = DataUtil.getProperty("door_ip_port_http");
	
	//一卡通系统请求https地址
	public static final String OPENAPI_IP_PORT_HTTPS =DataUtil.getProperty("door_ip_port_https");   
}
