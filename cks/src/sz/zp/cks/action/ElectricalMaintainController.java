package sz.zp.cks.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sz.zp.cks.common.Json;
import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.dao.EquipmentMapper;
import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.ElectricalMaintain;
import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.ElectricalCheckT;
import sz.zp.cks.model.ElectricalMaintainT;
import sz.zp.cks.model.EquipmentT;
import sz.zp.cks.service.ElectricalCheckService;
import sz.zp.cks.service.ElectricalMaintainService;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IEquipmentBiz;
import sz.zp.cks.service.UserService;
import sz.zp.cks.utils.PubFun;

@Controller
@RequestMapping("/electricalMaintain")
public class ElectricalMaintainController extends BaseAction {
	@Resource
	private ElectricalCheckService electricalCheckService;
	@Resource
	private ElectricalMaintainService electricalMaintainService;

	@Resource
	private IEpuserBiz epuserBiz;
	@Autowired
	EquipmentMapper equipmentMapper;
	
	@Resource
	private UserService userService;
	
	//跳转主管指派 
	@RequestMapping(value = "view")
	public String view(HttpServletRequest request,
			HttpServletResponse response, Model model,String epId) throws Exception {
		String url = request.getRequestURL().toString();
		Map<String, String> ret = new HashMap<String, String>();// 从后台获取信息，用于js验证
		ret = JsSignUtil.sign(url);
		model.addAttribute("ret", ret);		
		
		List<Epuser>list = new ArrayList<Epuser>();
		List<User>userList = userService.findListByDeptType("cks");
		model.addAttribute("userList", userList);
		return "forward:/WEB-INF/views/electricalMaintain/electricalMaintainTasks.jsp";
	}

	//主管指派巡检人员不定期保养
	@RequestMapping(value = "/save")
	@ResponseBody
	public Json save(ElectricalMaintain tElectricalMaintain,
			HttpServletRequest request, Model model) {
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg("保养任务指派提交成功！");
		
		// 获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer == null || sessionContainer.getUser() == null) {
			json.setSuccess(false);
			json.setMsg("提交失败,当前登录信息不存在!");
			return json;
		}
		// 主键
		String maintainId = "MA" + UUID.randomUUID().toString().replace("-", "");	
		User tUser = sessionContainer.getUser();
		tElectricalMaintain.setMaintainId(maintainId);
		tElectricalMaintain.setCreatedBy(tUser.getUserId());
		tElectricalMaintain.setCreationDate(PubFun.getCurrentDate());
		tElectricalMaintain.setReType("3");//不定期保养
		tElectricalMaintain.setCompleteOrNot("N");//0:未完成
		tElectricalMaintain.setLeaderId(tUser.getUserId());
			try {
				if (electricalMaintainService.insert(tElectricalMaintain) < 1) {
					json.setSuccess(false);
					json.setMsg("提交失败,保养任务指派异常!");
					return json;
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("提交失败,存入数据库异常!");
			}
		return json;

	}
	//指派保养人页面，规则：在报修流程中的设备无法进行保养
	@RequestMapping(value = "/equipOne")
	@ResponseBody
	public Json editOne(String epId, Model model) {
		EquipmentT tEquipmentT = new EquipmentT();
		Json tJson=new Json(true, "成功", tEquipmentT);
		try {
			tEquipmentT = electricalCheckService.queryById(epId);
			tJson.setObj(tEquipmentT);
			if(tEquipmentT!=null){
				//如果设备在维修中
				if(("Y").equals(tEquipmentT.getRepairIn())){
					tJson.setSuccess(false);
					tJson.setMsg("设备异常在报修流程中未结束，暂不支持保养工作！");
				}
			}else{
				tJson.setSuccess(false);
				tJson.setMsg("设备不存在！");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tJson;
	}

	@RequestMapping(value = "tips")
	public String tips() {
		return "forward:/WEB-INF/views/checks/tips.jsp";
	}



	//代办保养单列表
	@RequestMapping(value = "/toDoView")
	public String historyLists(HttpServletRequest request, HttpServletResponse response,Model model) {
		String url="forward:/WEB-INF/views/electricalMaintain/electricalMaintainTable.jsp";
		String userId="";
		//获取当前登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer != null &&sessionContainer.getUser() != null) {
			User tUser = sessionContainer.getUser();	
			userId=tUser.getUserId();	
		}
		List<ElectricalMaintain> list=new ArrayList<ElectricalMaintain>();
		list=electricalMaintainService.findElectricalMaintainList(userId,"N");
		model.addAttribute("list", list);
		return url;
	}	
	//已完成任务
	@RequestMapping(value = "/completeView")
	public String completeView(HttpServletRequest request, HttpServletResponse response,Model model) {
		String url="forward:/WEB-INF/views/electricalMaintain/electricalMaintaincompleteTable.jsp";
		String userId="";
		//获取当前登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer != null &&sessionContainer.getUser() != null) {
			User tUser = sessionContainer.getUser();	
			userId=tUser.getUserId();	
		}
		List<ElectricalMaintain> list=new ArrayList<ElectricalMaintain>();
		list=electricalMaintainService.findElectricalMaintainList(userId,"Y");
		model.addAttribute("list", list);
		return url;
	}		
	//主管总览列表
	@RequestMapping(value = "/AllView")
	public String allView(HttpServletRequest request, HttpServletResponse response,Model model) {
		String url="forward:/WEB-INF/views/electricalMaintain/electricalMaintainAllTable.jsp";
		String userId="";
		//获取当前登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer != null &&sessionContainer.getUser() != null) {
			User tUser = sessionContainer.getUser();	
			userId=tUser.getUserId();	
		}
		List<ElectricalMaintain> list=new ArrayList<ElectricalMaintain>();
		list=electricalMaintainService.findAll("3");
		model.addAttribute("list", list);
		return url;
	}	
	
	
	
	
	//待办任务单个点击跳转
	@RequestMapping(value="/displayOneById")
	public  String  displayOneById(ElectricalMaintainT tElectricalMaintainT,HttpServletRequest request,Model model) {
		ElectricalMaintainT mElectricalMaintainT=new ElectricalMaintainT();
		mElectricalMaintainT=electricalMaintainService.qryById(tElectricalMaintainT.getMaintainId());
		model.addAttribute("mElectricalMaintainT", mElectricalMaintainT);	
		return "forward:/WEB-INF/views/electricalMaintain/editElectricalMaintain.jsp";
	}

	//已完成任务单个点击跳转
	@RequestMapping(value="/displayOneByIdCom")
	public  String  displayOneByIdCom(ElectricalMaintainT tElectricalMaintainT,HttpServletRequest request,Model model) {
		ElectricalMaintainT mElectricalMaintainT=new ElectricalMaintainT();
		mElectricalMaintainT=electricalMaintainService.qryForComplete(tElectricalMaintainT.getMaintainId());
		model.addAttribute("mElectricalMaintainT", mElectricalMaintainT);	
		return "forward:/WEB-INF/views/electricalMaintain/editcompleteElectricalMaintain.jsp";
	}	
	
	//已完成任务单个点击跳转
	@RequestMapping(value="/displayOneByIdAll")
	public  String  displayOneByIdAll(ElectricalMaintainT tElectricalMaintainT,HttpServletRequest request,Model model) {
		ElectricalMaintainT mElectricalMaintainT=new ElectricalMaintainT();
		mElectricalMaintainT=electricalMaintainService.qryForComplete(tElectricalMaintainT.getMaintainId());
		model.addAttribute("mElectricalMaintainT", mElectricalMaintainT);	
		return "forward:/WEB-INF/views/electricalMaintain/editALLElectricalMaintain.jsp";
	}	
		
	
	
	//完成不定期保养任务提交功能
	@RequestMapping(value = "/update")
	@ResponseBody
	public Json update(ElectricalMaintain tElectricalMaintain,
			HttpServletRequest request, Model model) {
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg("保养工作提交成功！");
		
		// 获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer == null || sessionContainer.getUser() == null) {
			json.setSuccess(false);
			json.setMsg("提交失败,当前登录信息不存在!");
			return json;
		}
		User tUser = sessionContainer.getUser();
		ElectricalMaintainT tElectricalMaintainT = electricalMaintainService.qryById(tElectricalMaintain.getMaintainId());
		ElectricalMaintain mElectricalMaintain=new ElectricalMaintain();
		BeanUtils.copyProperties(tElectricalMaintainT, mElectricalMaintain);
		mElectricalMaintain.setMaintenanceDate(tElectricalMaintain.getMaintenanceDate());
		mElectricalMaintain.setDistanceDays(tElectricalMaintain.getDistanceDays());
		mElectricalMaintain.setClear(tElectricalMaintain.getClear());
		mElectricalMaintain.setLine(tElectricalMaintain.getLine());
		mElectricalMaintain.setOil(tElectricalMaintain.getOil());
		mElectricalMaintain.setMaintainTime(tElectricalMaintain.getMaintainTime());
		mElectricalMaintain.setMaintainDescribe(tElectricalMaintain.getMaintainDescribe());
		mElectricalMaintain.setCompleteOrNot("Y");
		mElectricalMaintain.setReType("3");
		mElectricalMaintain.setLastUpdatedBy(tUser.getUserId());
		mElectricalMaintain.setLastUpdatedDate(PubFun.getCurrentDate());
			try {
				if (!electricalMaintainService.updateTask(mElectricalMaintain)) {
					json.setSuccess(false);
					json.setMsg("失败,保养任务提交异常!");
					return json;
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("失败,存入数据库异常!");
			}
		return json;

	}	
	
	
}
