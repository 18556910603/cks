package sz.zp.cks.weixin.template;

import java.util.LinkedHashMap;
import java.util.Map;

public class MaintainNoticeTemplate {
	private Map<String, Object> map;

	private Map<String, Object> data;

	public MaintainNoticeTemplate(String wx_open_id,String template_id,String username,String ep_id,String ep_type,String maintainDate,String url){
		map = new LinkedHashMap<String, Object>();
		data = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, String> first = new LinkedHashMap<String,String>();
		first.put("value", "尊敬的"+username+"，您有设备临近保养");
		first.put("color", "#008000");
		data.put("first", first);
		
		LinkedHashMap<String, String> keyword1 = new LinkedHashMap<String,String>();
		keyword1.put("value", ep_type);
		keyword1.put("color", "#008000");
		data.put("keyword1", keyword1);
		

		LinkedHashMap<String, String> keyword2 = new LinkedHashMap<String,String>();
		keyword2.put("value", ep_id);
		keyword2.put("color", "#008000");
		data.put("keyword2", keyword2);

		LinkedHashMap<String,String> remark = new LinkedHashMap<String, String>();
		remark.put("value", "保养到期时间:"+maintainDate+",请您在此日期前尽快保养。");
		remark.put("color", "#008000");
		data.put("remark", remark);
		map.put("touser", wx_open_id);
		map.put("template_id",template_id);

		map.put("url",url);

		map.put("topcolor", "#008000");

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
