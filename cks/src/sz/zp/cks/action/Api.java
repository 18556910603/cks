package sz.zp.cks.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import sz.zp.cks.entity.Alarm;
import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.JackJsonUtils;
import sz.zp.cks.entity.ListObject;
import sz.zp.cks.entity.StatusHouse;
import sz.zp.cks.service.AlarmService;
import sz.zp.cks.service.ElectricalCheckService;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IEquipmentBiz;
import sz.zp.cks.service.UserService;
import sz.zp.cks.utils.JSONUtil;
import sz.zp.cks.utils.ResponseUtils;

@Controller
@RequestMapping("/Api")
public class Api {

	@Resource
	private EquipmentRepairService equipmentRepairService;

	@Resource
	private AlarmService alarmService;

	@Resource
	private IEquipmentBiz equipmentBiz;
	@Resource
	private ElectricalCheckService electricalCheckService;
	@Resource
	private IEpuserBiz epuserBiz;
	@Resource
	private UserService userService;

	@RequestMapping(value = "/toWspassServlet")
	public void WspassServlet(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Alarm> list = alarmService.getAllAlarms();

		ListObject listObject = new ListObject();

		listObject.setItems(list);

		listObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);

		String responseText = JackJsonUtils.toJson(listObject);
		
		ResponseUtils.renderJson(response, responseText);

	}
	
	@RequestMapping(value="getelectricalCheckList",method=RequestMethod.POST)
    @ResponseBody
	public void  getelectricalCheckList(Model model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		List<ElectricalCheck> list=new ArrayList<>();
		 
		list = electricalCheckService.findList();

		ListObject listObject = new ListObject();

		listObject.setItems(list);

		listObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		
		//String responseText = JackJsonUtils.toJson(listObject);
		String responseText = JackJsonUtils.toJson(list);
        System.out.println("getelectricalCheckList-------------"+responseText);
         
         

		ResponseUtils.renderJson(resp, responseText);
		
		 //JSONObject returnMap = new JSONObject();
        //Map requestMap = JSONUtil.acceptJSON(req);

       // String time = (String) requestMap.get("time");
       // System.out.println("--------:"+time);

        //PrintWriter out = resp.getWriter();
        //out.println(JSON.toJSONString(list));
        
        
        
	}
}
