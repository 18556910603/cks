package sz.zp.cks.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.AjaxResponseMsg;
import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.Hstatus;
import sz.zp.cks.entity.Status;
import sz.zp.cks.entity.User;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.service.HstatusService;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IEquipmentBiz;
import sz.zp.cks.service.StatusService;
import sz.zp.cks.service.UserService;
import sz.zp.cks.utils.CreateQRCode;
import sz.zp.cks.utils.EpIdUtils;
import sz.zp.cks.utils.PubFun;


@Controller
public class EquipmentController {
	@Autowired
	IEquipmentBiz equipmentBiz;
	
	@Autowired
	IEpuserBiz epuserBiz;
	
	@Resource
    UserService userService;
	
	@Resource
	private StatusService statusService;
	@Resource
	private HstatusService hstatusService;
	
	@Resource
	private EquipmentRepairService equipmentRepairService;	

	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody 
	public AjaxResponseMsg add(Equipment equipment,User user, HttpServletRequest request) throws Exception  {
		System.out.println("---add:"+equipment.getEpType());		
		String epTpye=equipment.getEpType();
		String epId=EpIdUtils.getEpId(epTpye);
		System.out.println("---add:"+user.getUserId());
//		String epUserId=epuserBiz.getEpUserId(user.getUserId());
		equipment.setEpId(epId);
//		equipment.setEpUserId(epUserId);
		
		//状态表记录一条信息与历史状态表一致
		
		String statusId ="ST"+UUID.randomUUID().toString().replace("-", "");
		Status status=new Status();
		status.setStatusId(statusId);
		status.setEpId(epId);
		status.setStatusVal("1");
		status.setReType("1");//设备新增
		status.setReTypeid("");
		status.setRemarks("来自设备状态新增页面");
		//获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		User tUser = sessionContainer.getUser();
		status.setCreatedBy(tUser.getUserId());
		status.setCreationDate(PubFun.getCurrentDate());
		
		//历史状态表进行新增
		String hstatusId ="HST"+UUID.randomUUID().toString().replace("-", "");
		Hstatus hstatus=new Hstatus();
		BeanUtils.copyProperties(status, hstatus);
		hstatus.setHstatusId(hstatusId);
		
		statusService.insert(status);
		hstatusService.insert(hstatus);
		
		AjaxResponseMsg ajaxMsg=new AjaxResponseMsg();
		if(equipmentBiz.insert(equipment)==1){
			 ajaxMsg.setSuccess(true);
		  	 ajaxMsg.setMsg("操作成功！");
		}
		return ajaxMsg;

	}

	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody      
    public int delete(String epId) throws Exception{
  	  System.out.println("-----------delete:"+epId);
  	  int num = equipmentBiz.remove(epId);  	  
  	  System.out.println("-----------delete:"+num);
  	  return num;
    }



	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
    public AjaxResponseMsg update(Equipment equipment,User user) throws Exception{
  	  System.out.println("-----------update:"+equipment);
  	  Equipment tEquipment=equipmentBiz.queryById(equipment.getEpId());
//  	  String epUserId=epuserBiz.getEpUserId(user.getUserId());
//  	  equipment.setEpUserId(epUserId);
  	  //xuyaya修改数据保存不完整bug
  	  
  	  tEquipment.setEpName(equipment.getEpName());
  	  tEquipment.setEpModelNum(equipment.getEpModelNum());
  	  tEquipment.setEpLoc(equipment.getEpLoc());
  	  tEquipment.setEpLocation(equipment.getEpLocation());
  	  tEquipment.setEpType(equipment.getEpType());
  	  tEquipment.setEpProducer(equipment.getEpProducer());
  	  tEquipment.setEpProvider(equipment.getEpProvider());
  	  tEquipment.setEpBuyingTime(equipment.getEpBuyingTime());
  	  tEquipment.setEpUpTime(equipment.getEpUpTime());
  	  tEquipment.setMaintenanceDate(equipment.getMaintenanceDate());
  	  tEquipment.setEpUserId(equipment.getEpUserId());
  	  
  	  AjaxResponseMsg ajaxMsg=new AjaxResponseMsg();
  	  if(equipmentBiz.modify(equipment)==1){
  	  ajaxMsg.setSuccess(true);
  	  ajaxMsg.setMsg("操作成功！");}
  	  return ajaxMsg;
    }
	
	@RequestMapping(value="/qrcode",method=RequestMethod.POST)
	@ResponseBody
	public String qrcode(String epId,HttpServletRequest request) throws Exception{
		  String url = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() ;
          String qrData=url+"/getEquip.action?epId="+epId;
          System.out.println("-----------qrcode:"+qrData);
          String qrcode = CreateQRCode.create(qrData, epId);
	  	  Equipment equipment = equipmentBiz.queryById(epId);
	  	  equipment.setQrCode(qrcode);
	      equipmentBiz.modifyQRCode(equipment);
	  	  String path="/code/"+qrcode;
	  	  
	  	  return path;
	}
		
	
	  @RequestMapping(value="/editEquip",method=RequestMethod.POST)
      @ResponseBody      
      public Map<String,Object> getEquip(String epId) throws Exception{
	  Map<String,Object> map = new HashMap<String,Object>();
	  System.out.println("-----------getEquip:"+epId);
	  Equipment equipment= equipmentBiz.queryById(epId);
	  System.out.println("-----------getEquip2:"+equipment);
	  Epuser epUser= epuserBiz.queryById(equipment.getEpUserId());
	  System.out.println("-----------getEquip2:"+epUser.getUser().getUserName());
	  map.put("equipment", equipment);
  	  map.put("epUser", epUser);
	  return map;
      }
	  
	  @RequestMapping("/getEquip")      
      public String getSingleEquip(HttpServletRequest request,String epId,ModelMap map) throws Exception{
		  String returnUrl = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + "/code/upload/imgs/"  ;// 存储路径
	  map.put("returnUrl", returnUrl);
	  Equipment equipment= equipmentBiz.queryById(epId);
	  List<EquipmentRepair> equipmentRepairs = equipmentRepairService.findByEpId(epId);
	  Epuser epUser= epuserBiz.queryById(equipment.getEpUserId());
	  map.put("equipment", equipment);
	  map.put("equipmentRepairs", equipmentRepairs);
  	  map.put("epUser", epUser);
	  return "forward:/SingleEp.jsp";
	  
      }

	@RequestMapping("/search")
	public ModelAndView search(ModelMap model) throws Exception {
        System.out.println("search:--------------");
		List<Equipment> equipments=equipmentBiz.getList();
	    System.out.println("search:--------------"+equipments.toString());
	    List<Epuser> epUsers = epuserBiz.getList();
	    model.put("equipments", equipments);
	    model.put("epUsers", epUsers);
	    System.out.println("search:epUsers:--------------"+epUsers);
		return new ModelAndView("equipment.jsp",model);
		
	}


}
