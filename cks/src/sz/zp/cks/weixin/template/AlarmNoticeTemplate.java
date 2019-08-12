package sz.zp.cks.weixin.template;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * @author Lenovo
 *
 *定制模板消息
 */
public class AlarmNoticeTemplate {
	private Map<String, Object> map;

	private Map<String, Object> data;

	/**
	 * @param touser （跳转的下一目标 的 用户open_id）
	 * @param template_id 模板消息id（公众号设置中）
	 * @param url	（跳转地址）	
	 * @param topcolor （颜色）
	 * @param user	（用户名）
	 * @param ep_id		（设备编号）
	 * @param remark_id  (消息提醒编号）
	 */
	public AlarmNoticeTemplate (String touser, String template_id, String url,
			String topcolor, String user,String ep_id,String ep_name,String remark_id) {

		map = new LinkedHashMap<String, Object>();
		data = new LinkedHashMap<String, Object>();

		LinkedHashMap<String, String> first = new LinkedHashMap<String, String>();
		first.put("value", "尊敬的" + user + "(测试)");
		first.put("color", "#743A3A");
		data.put("first", first);

		if(ep_id == null||ep_id.equals("")){
			LinkedHashMap<String, String> device = new LinkedHashMap<String, String>();
			device.put("value", ep_name);
			device.put("color", "#008000");
			data.put("device", device);
		}
		else{	
			LinkedHashMap<String, String> device = new LinkedHashMap<String, String>();
			device.put("value", ep_id);
			device.put("color", "#008000");
			data.put("device", device);
		}

		LinkedHashMap<String, String> time = new LinkedHashMap<String, String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datevalue=df.format(new Date());
		time.put("value", datevalue);
		time.put("color", "#008000");
		data.put("time", time);
		
		System.out.println("!@#$%^&*(!@#$%^&*(#$%^&*%^&*%^&*%现在流程到达步骤："+remark_id);
		String RemarkMessage =null;
		if(StringUtils.equals("1", remark_id)){
			RemarkMessage="提醒类型:主管派单提醒。请及时派单";
		}else if(StringUtils.equals("2", remark_id)){
			RemarkMessage="提醒类型:维修员维修提醒。请及时维修";
		}else if(StringUtils.equals("3", remark_id)){
			RemarkMessage="提醒类型:发起人确认提醒。请及时确认维修情况";
		}else{
			RemarkMessage="您有新的设备告警流程待办，点击查看详情";
		}
		LinkedHashMap<String,String> remark = new LinkedHashMap<String, String>();
		remark.put("value", RemarkMessage);
		remark.put("color", "#008000");
		data.put("remark", remark);
		map.put("touser",touser);

		map.put("template_id",template_id);

		map.put("url",url);

		map.put("topcolor",topcolor);

		map.put("data",data);
	}
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
