package sz.zp.cks.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sz.zp.cks.common.Json;
import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.Hstatus;
import sz.zp.cks.entity.Status;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.StatusT;
import sz.zp.cks.service.HstatusService;
import sz.zp.cks.service.StatusService;
import sz.zp.cks.utils.PubFun;

@Controller
@RequestMapping("/status")
public class StatusController extends BaseAction {
	@Resource
	private StatusService statusService;
	@Resource
	private HstatusService hstatusService;

	@RequestMapping(value = {"list"})
	public String list(  HttpServletRequest request, HttpServletResponse response,Model model) {
		List list=statusService.findStatusTList(new StatusT());
		model.addAttribute("list", list);
		List EquipList=statusService.getEquipList();
		model.addAttribute("EquipList", EquipList);
		return "forward:/WEB-INF/views/status/status.jsp";
	}

	@RequestMapping(value = "/main")
	public String list(  HttpServletRequest request, HttpServletResponse response,ModelMap map) {
		return "forward:/main.jsp";
	}
	
	@RequestMapping(value = "/editOne")
	@ResponseBody
	public StatusT editOne(String statusId,Model model) {
		StatusT  tStatus=new StatusT();
		tStatus=statusService.selectById(statusId);
		
		model.addAttribute("tStatus", tStatus);
		System.out.println(tStatus);
		return tStatus;
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Json update(StatusT statusT,HttpServletRequest request){
		
		//获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		User tUser = sessionContainer.getUser();
		//获取状态
		Status tStatus = statusService.queryById(statusT.getStatusId())	;
		//页面的值
		tStatus.setStatusVal(statusT.getStatusVal());
		tStatus.setLastUpdatedBy(tUser.getUserId());
		tStatus.setLastUpdatedDate(PubFun.getCurrentDate());
		
		
		
		Hstatus hstatus=new Hstatus();
		BeanUtils.copyProperties(tStatus, hstatus);
		String hstatusId ="HST"+UUID.randomUUID().toString().replace("-", "");
		hstatus.setHstatusId(hstatusId);
		
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg("设备状态修改成功！");
		
		try {
			int a=statusService.update(tStatus);
			int b=hstatusService.insert(hstatus);
			System.out.println(a);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("设备状态修改失败！");
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Json add(StatusT statusT,HttpServletRequest request){
		String statusId ="ST"+UUID.randomUUID().toString().replace("-", "");
		Status status=new Status();
		status.setStatusId(statusId);
		status.setEpId(statusT.getEpId());
		status.setStatusVal(statusT.getStatusVal());
		status.setReType("1");//设备新增
		status.setReTypeid("");
		status.setRemarks("来自设备状态新增页面");
		//获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		User tUser = sessionContainer.getUser();
		status.setCreatedBy(tUser.getUserId());
		status.setCreationDate(PubFun.getCurrentDate());
		
		
		String hstatusId ="HST"+UUID.randomUUID().toString().replace("-", "");
		Hstatus hstatus=new Hstatus();
		BeanUtils.copyProperties(status, hstatus);
		hstatus.setHstatusId(hstatusId);	
		
		
		
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg("设备状态新增成功！");
		try {
			int a=statusService.insert(status);
			int b=hstatusService.insert(hstatus);
			System.out.println(a);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("设备状态新增失败！");
			e.printStackTrace();
		}
		return json;
	}	
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public int deletOne(String statusId) {
		int res=0;
		Status  tStatus=new Status();
		tStatus.setStatusId(statusId);
		try {
			res=statusService.delete(tStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}	
	
	
	
	
	
	
	public static void main(String[] args) {
		String boId ="bo"+UUID.randomUUID().toString().replace("-", "");
		System.out.println(boId);
	}
}
