package sz.zp.cks.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import sz.zp.cks.common.Json;
import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.EquipmentRepairT;
import sz.zp.cks.model.EquipmentT;
import sz.zp.cks.service.ElectricalCheckService;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IEquipmentBiz;
import sz.zp.cks.service.UserService;

@Controller
@RequestMapping("/equipmentRepair")
public class EquipmentRepairController extends BaseAction {
	@Resource
	private EquipmentRepairService equipmentRepairService;
	
	@Resource
	private  IEquipmentBiz equipmentBiz;
	@Resource
	private ElectricalCheckService electricalCheckService;	
	@Resource
	private IEpuserBiz epuserBiz;
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/toRepair")
	public String toRepair(Model model,String epId) throws Exception {
        System.out.println("toRepair:--------------");
        List<Equipment> equipments=equipmentBiz.getList();
        model.addAttribute("equipments", equipments);
        if(epId!=null)
        {
        Equipment equipment= equipmentBiz.queryById(epId);
        model.addAttribute("equipment", equipment);        
        Epuser epUser= epuserBiz.queryById(equipment.getEpUserId());
        model.addAttribute("epUser", epUser);
        }
		return "forward:/maintaining.jsp";		
	}
	
	@RequestMapping(value="/getEquip",method=RequestMethod.POST)
    @ResponseBody      
    public Map<String,Object> getEquip(String epId) throws Exception{
	  Map<String,Object> map = new HashMap<String,Object>();
  	  System.out.println("-----------getEquip:"+epId);
  	  Equipment equipment= equipmentBiz.queryById(epId);
  	  Epuser epUser= epuserBiz.queryById(equipment.getEpUserId());
  	  System.out.println("-----------getEquip2:"+equipment);
  	  map.put("equipment", equipment);
  	  map.put("epUser", epUser);
  	  return map;
    }
	
	//跳转报修首页
	@RequestMapping(value = "view")
	public String list(  HttpServletRequest request, HttpServletResponse response,Model model) {
		String url = request.getRequestURL().toString();
		Map<String, String> ret = new HashMap<String, String>();// 从后台获取信息，用于js验证
		ret = JsSignUtil.sign(url);
		model.addAttribute("ret", ret);
		
		String returnUrl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + "/code/upload/imgs/"  ;// 存储路径
		
		model.addAttribute("returnUrl", returnUrl);	
		return "forward:/WEB-INF/views/repair/equipmentRepair1.jsp";
	}
	//报修首页提交功能
	@RequestMapping(value = "/submit")
	@ResponseBody
	public Json submit(@RequestParam("files[]") MultipartFile[] files,EquipmentRepair equipmentRepair, HttpServletRequest request, Model model){
		Json json = new Json(true, "报修表单提交成功！", null);
		//主键
		String equipmentrepairId ="ER"+UUID.randomUUID().toString().replace("-", "");
		equipmentRepair.setEquipmentrepairId(equipmentrepairId);
		//获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		if(sessionContainer==null||sessionContainer.getUser()==null){
			return  new Json(false, "提交失败,当前登录信息不存在!", null);
		}
		User tUser = sessionContainer.getUser();
 
		
		if(!equipmentRepairService.insertSubmit(equipmentRepair,tUser)){
			json.setSuccess(false);
			json.setMsg("报修表单提交失败！");	
		}
		return json;
	}
	
	//待办任务查询展示列表
	@RequestMapping(value = "/toDoView")
	public String toDoViewlist(HttpServletRequest request, HttpServletResponse response,Model model) {
		String url="forward:/WEB-INF/views/repair/toDoTasks.jsp";
		EquipmentRepair tEquipmentRepair=new EquipmentRepair();
		List list=new ArrayList();
		//获取当前登录者信息
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer != null &&sessionContainer.getUser() != null) {
			User tUser = sessionContainer.getUser();	
			tEquipmentRepair.setEpAcNextuser(tUser.getUserId());
		}
		tEquipmentRepair.setEpType("1");
		list=equipmentRepairService.findRepairTList(tEquipmentRepair);
		model.addAttribute("list", list);
		return url;
	}
	//待办任务单个点击跳转
	@RequestMapping(value = "/displayOneById")
	public String delete(EquipmentRepair tEquipmentRepair,HttpServletRequest request,Model model) {
		String returnUrl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + "/code/upload/imgs/"  ;// 存储路径
		model.addAttribute("returnUrl", returnUrl);
		
		EquipmentRepairT mEquipmentRepairT=new EquipmentRepairT();
		mEquipmentRepairT=equipmentRepairService.qryById(tEquipmentRepair.getEquipmentrepairId());
		model.addAttribute("mEquipmentRepairT", mEquipmentRepairT);	
		//加入维修责任人字段下拉框数据源
		//查询维修部门repair人员信息
		
		List<User> repairUserList =new ArrayList<User>();
		repairUserList = userService.findListByDeptType("repair");
		model.addAttribute("repairUserList", repairUserList);	
		
		
		return "forward:/WEB-INF/views/repair/editToDoTask.jsp";
	}
	
	//待办任务流程流转提交按钮
	@RequestMapping(value = "/submitToDo")
	@ResponseBody
	public Json submitToDo(EquipmentRepairT equipmentRepairT,HttpServletRequest request, Model model){
		Json json = new Json(true, "报修表单提交成功！", null);
		//获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		if(sessionContainer==null||sessionContainer.getUser()==null){
			return  new Json(false, "提交失败,当前登录信息不存在!", null);
		}
		User tUser = sessionContainer.getUser();
		try {
			if(!equipmentRepairService.insertSubmitToDo(equipmentRepairT,tUser)){
				json.setSuccess(false);
				json.setMsg("报修表单提交失败！");	
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("报修表单提交失败！");	
			e.printStackTrace();
		}
		return json;
	}
	//待办任务流程流转提交按钮
	@RequestMapping(value = "/rpAdd")
	@ResponseBody
	public Json rpAdd(EquipmentRepair equipmentRepair,HttpServletRequest request, Model model){
		Json json = new Json(true, "报修表单提交成功！", null);
		//获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		if(sessionContainer==null||sessionContainer.getUser()==null){
			return  new Json(false, "提交失败,当前登录信息不存在!", null);
		}
		User tUser = sessionContainer.getUser();

		
		if(!equipmentRepairService.rpAdd(equipmentRepair,tUser)){
			json.setSuccess(false);
			json.setMsg("报修表单提交失败！");	
		}
		return json;
	}	
	@RequestMapping(value = "/equipOne")
	@ResponseBody
	public Json editOne(String epId, Model model) {
		EquipmentT tEquipmentT = new EquipmentT();
		Json json =new Json();
		try {
			tEquipmentT = electricalCheckService.queryById(epId);
			//加入判断是否能够提交
			//isCanSubmit
		    json = equipmentRepairService.isCanSubmit(epId);
			
			json.setObj(tEquipmentT);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
