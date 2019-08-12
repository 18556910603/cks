package sz.zp.cks.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sz.zp.cks.common.Json;
import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.AjaxResponseMsg;
import sz.zp.cks.entity.Dict;
import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.HsequipmentRepair;
import sz.zp.cks.entity.Owner;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.EquipmentRepairO;
import sz.zp.cks.model.EquipmentRepairT;
import sz.zp.cks.model.HsequipmentRepairO;
import sz.zp.cks.model.HsequipmentRepairT;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.service.HsequipmentRepairService;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IOwnerBiz;
import sz.zp.cks.service.UserService;
import sz.zp.cks.utils.DictUtils;

@Controller
public class OwnerController {
	
	@Autowired
	private IOwnerBiz ownerBiz;
	
	@Autowired
	IEpuserBiz epuserBiz;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HsequipmentRepairService hsequipmentRepairService;
	@Autowired
	private EquipmentRepairService equipmentRepairService;
	
	//跳转到报修页面
	 @RequestMapping("/ownerRepair")      
     public String ownerRepair(HttpServletRequest request,ModelMap map) throws Exception{
		 SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		
		 if(sessionContainer!=null){
		 User user = sessionContainer.getUser();
		 if(user!=null){
		 System.out.println("-------userId:"+user.getUserId());
		 Owner owner = ownerBiz.queryById(user.getUserId());
		 System.out.println("-------user:"+owner);
	     map.put("owner", owner);}
		 }
	  return "forward:/ownerMaintaining.jsp";
     }
	 
	//业主跳转到历史报修单列表
	 @RequestMapping("/hownerRepair")
     public String hownerRepair(HttpServletRequest request,ModelMap map) throws Exception{
		 
		 SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		
		 if(sessionContainer!=null){
		 User user = sessionContainer.getUser();
		 if(user!=null){
		 System.out.println("-------userId:"+user.getUserId());
		 Owner owner = ownerBiz.queryById(user.getUserId());
		 List<EquipmentRepair> equipmentRepairs = equipmentRepairService.load(user.getUserId());
		 
		 System.out.println("-------equipmentRepair:"+equipmentRepairs);
	     map.put("owner", owner);
	     map.put("equipmentRepairs", equipmentRepairs);
		 }
		 }
	  return "forward:/HOwnerMaintaining.jsp";
     }
	 
	//是否维修完成筛选
	 @RequestMapping("/hRepairEnd")
     public String hRepairEnd(HttpServletRequest request,String epAcStatus,ModelMap map) throws Exception{
		 System.out.println("---------hRepairEnd:"+epAcStatus);
		 SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		
		 if(sessionContainer!=null){
		 User user = sessionContainer.getUser();
		 if(user!=null){
	     String userId=user.getUserId();	     
		 List<EquipmentRepair> equipmentRepairs = equipmentRepairService.selectByStatus(userId, epAcStatus);		 
		 System.out.println("-------equipmentRepair:"+equipmentRepairs);
	     map.put("equipmentRepairs", equipmentRepairs);
		 }
		 }
	  return "forward:/HOwnerMaintaining.jsp";
     }
	 
	//跳转到已完成报修单详情页
	 @RequestMapping("/hownerSpecific")
     public String hownerSpecific(HttpServletRequest request,Model model,String equipmentrepairId) throws Exception{		 
		  System.out.println("-----------equipmentrepairId:"+equipmentrepairId);
	      String returnUrl = request.getScheme() + "://" + request.getServerName()
	     		  + ":" + request.getServerPort() + "/upload/"  ;// 存储路径
	    	model.addAttribute("returnUrl", returnUrl);
		  
		  EquipmentRepair tEquipmentRepair=new EquipmentRepair();
		  List equipmentRepairs=new ArrayList();
	 		//获取当前登录者信息
	 		SessionContainer sessionContainer = (SessionContainer) request
	 				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
	 		if (sessionContainer != null &&sessionContainer.getUser() != null) {
	 			User tUser = sessionContainer.getUser();	
	 		
	 		tEquipmentRepair.setEpType("2");
	 		equipmentRepairs=equipmentRepairService.load(tUser.getUserId());
	 		model.addAttribute("equipmentrepairs", equipmentRepairs);}

		  EquipmentRepairO mEquipmentRepairO = equipmentRepairService.qryOwnerById(equipmentrepairId);		  
		  model.addAttribute("mEquipmentRepairO", mEquipmentRepairO);		 
	  return "forward:/SpecificOwnerRepair.jsp";
     }
	 
		//已完成任务单个点击跳转
		@RequestMapping(value = "/displayOById")
		public String displayOneById(HsequipmentRepairO tHsequipmentRepairO,HttpServletRequest request,Model model) {
		      String returnUrl = request.getScheme() + "://" + request.getServerName()
		    		  + ":" + request.getServerPort() + "/upload/"  ;// 存储路径
			model.addAttribute("returnUrl", returnUrl);
			System.out.println("------------displayOneById:"+returnUrl);	
			HsequipmentRepairO mHsequipmentRepairO=new HsequipmentRepairO();
			mHsequipmentRepairO=hsequipmentRepairService.qryOById(tHsequipmentRepairO.getHsEquipmentrepairId());
			model.addAttribute("mHsequipmentRepairO", mHsequipmentRepairO);	
			//加入维修责任人字段下拉框数据源
			//查询维修部门repair人员信息
			
			List<User> repairUserList =new ArrayList<User>();
			repairUserList = userService.findListByDeptType("ownerRepair");
			model.addAttribute("repairUserList", repairUserList);			
			return "forward:/OwnerCompletedTask.jsp";
		}
	 
	 
	//跳转到业主信息页
	 @RequestMapping("/ownerInformation")      
     public String ownerInformation(HttpServletRequest request,ModelMap map) throws Exception{
		 
		 SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		 if(sessionContainer!=null){
		 User user = sessionContainer.getUser();
		 if(user!=null){
		 System.out.println("-------userId:"+user.getUserId());
		 Owner owner = ownerBiz.queryById(user.getUserId());

	     map.put("owner", owner);	     
		 }
		 }
	  return "forward:/ownerInformation.jsp";
     }
	
	 //业主信息更新完善
	  @RequestMapping(value="/ownerAddInformation",method=RequestMethod.POST)
	  @ResponseBody
	  public Json save(Owner owner,User user,HttpServletRequest request,Model model) throws Exception, IOException{
		  	    
		   Json json = new Json(true, "提交成功！", null);
		   //获取登录者信息
		   SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
			if(sessionContainer==null||sessionContainer.getUser()==null){
				userService.insert(user);
			}
			User tUser = sessionContainer.getUser();
            String userId=tUser.getUserId();
            //首次插入
            if(ownerBiz.queryById(userId)==null){
			//主键
			String ownerId =String.valueOf(ownerBiz.max()+1);
        	tUser.setUserName(user.getUserName());
			tUser.setMobile(user.getMobile());
			owner.setOwnerId(ownerId);
			userService.update(tUser);
			owner.setUserId(userId);	
			if(ownerBiz.regist(owner)==0){
				json.setSuccess(false);
				json.setMsg("提交失败！");	
			}
            }
            //更新
            else{
            	tUser.setUserName(user.getUserName());
    			tUser.setMobile(user.getMobile());
    			userService.update(tUser);
    			System.out.println("----------:"+owner.getOwnLoc());
    			owner.setUserId(userId);
    			if(ownerBiz.modify(owner)==0){
    				json.setSuccess(false);
    				json.setMsg("提交失败！");	
    			}
            }
			return json;
            
		}
	  
	  //字典类设备名称的二级联动
	  @RequestMapping(value="/getPostTemplateJson",method=RequestMethod.POST)
	  @ResponseBody
	  public List getPostTemplateJson(String epHomeEqutype) throws Exception{
		System.out.println("----------epHomeEqutype:"+epHomeEqutype);
		 String type=null;
		 List<Dict> dictList=null; 
		 if(epHomeEqutype.equals("private_epName")){	
			 type="private_epName";
			 dictList = DictUtils.getDictList(type);
			 System.out.println("-----getPostTemplateJson:"+DictUtils.getDictList(type));
			
		 }
		 if(epHomeEqutype.equals("public_epName")){	
			 type="public_epName";
			 dictList = DictUtils.getDictList(type);
			 System.out.println("-----getPostTemplateJson:"+dictList);
			
		 }
		return dictList;
	  }	
	  
	  //业主报修提交
	  @RequestMapping(value="/ownerAddRepair",method=RequestMethod.POST)
	  @ResponseBody
	  public Json submit(EquipmentRepair equipmentRepair,HttpServletRequest request, HttpSession session) throws Exception, IOException{
		 
		 System.out.println("-----------------equipmentRepair:"+equipmentRepair);
		  Json json = new Json(true, "提交成功！", null);
			//主键
			String equipmentrepairId ="OW"+UUID.randomUUID().toString().replace("-", "");
			equipmentRepair.setEquipmentrepairId(equipmentrepairId);			
			
			//获取登录者信息
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
			if(sessionContainer==null||sessionContainer.getUser()==null){
				return  new Json(false, "提交失败,当前登录信息不存在!", null);
			}
			User tUser = sessionContainer.getUser();

			equipmentRepair.setUserId(tUser.getUserId());
			
			String imagesList = (String) session.getAttribute("imagesList");
			System.out.println("--------------imagesList:"+imagesList);
			equipmentRepair.setEprCkPhoto(imagesList);
			
			if(!equipmentRepairService.ownerSubmit(equipmentRepair,tUser)){
				json.setSuccess(false);
				json.setMsg("提交失败！");
			}
			session.setAttribute("imagesList","");
			return json;
		}
	 
	  //照片下载
	     @RequestMapping(value = "upload", method = RequestMethod.POST)
	     @ResponseBody
	     public String upload(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,HttpSession session) throws Exception {
		    String uploadPath = "D:/photo";
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
			User tUser = sessionContainer.getUser();
            String userId=tUser.getUserId();
		    Date date=new Date();
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
            String now=sdf.format(date);
		    String fileName ="OW"+userId+"-"+now+".jpg";
		    File dir = new File(uploadPath,fileName);
		    if (request instanceof MultipartRequest) {		    	
		        MultiValueMap < String,
		        MultipartFile > map = ((MultipartRequest) request).getMultiFileMap();
		        LinkedList < CommonsMultipartFile > imageList = (LinkedList) map.get("imagefile");
		        System.out.println("---------imageList:"+imageList);
		        if (imageList != null && imageList.size() > 0) {
		            CommonsMultipartFile file = imageList.get(0);
		            if (!file.isEmpty()) {
		                try {
		                    BASE64Encoder encoder = new BASE64Encoder();
		                    BASE64Decoder decoder = new BASE64Decoder(); // 通过base64来转化图片                    
		                    String data = encoder.encode(file.getBytes());
		                    byte[] bytes = decoder.decodeBuffer(data);
		                    for (int i = 0; i < bytes.length; ++i) {
		                        if (bytes[i] < 0) { // 调整异常数据                            
		                            bytes[i] += 256;
		                        }
		                    } // 生成jpeg图片                  
		                    OutputStream out = new FileOutputStream(dir);
		                    out.write(bytes);
		                    out.flush();
		                    out.close();
		                } catch(Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }
		    String imagesList=(String) session.getAttribute("imagesList");
		    if(imagesList==null||imagesList.equals("")){
		    session.setAttribute("imagesList", fileName);
		    }else{
		    	imagesList+=","+fileName;
		    	 session.setAttribute("imagesList", imagesList);
		    }
		    System.out.println("-------------upload:"+imagesList);
		    return null;
	  }
	   //Session清空
	     @RequestMapping(value = "clearSession", method = RequestMethod.POST)
	     @ResponseBody
	     public Json clearSession(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,HttpSession session) throws Exception {
	    	Json json = new Json(true, "提交成功！", null);
		    session.removeAttribute("imagesList");
		    String imagesList=(String) session.getAttribute("imagesList");
		    System.out.println("-------------clearSession:"+imagesList);
		    return json;
	  }
	   
	   //待办任务查询展示列表
	 	@RequestMapping( "/toTask")
	 	public String toTask(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
	 		EquipmentRepair tEquipmentRepair=new EquipmentRepair();
	 		List equipmentRepairs=new ArrayList();
	 		//获取当前登录者信息
	 		SessionContainer sessionContainer = (SessionContainer) request
	 				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
	 		if (sessionContainer != null &&sessionContainer.getUser() != null) {
	 			User tUser = sessionContainer.getUser();	
	 			tEquipmentRepair.setEpAcNextuser(tUser.getUserId());
	 		}
	 		tEquipmentRepair.setEpType("2");
	 		equipmentRepairs=equipmentRepairService.findOwnerStatusTList(tEquipmentRepair);
	 		model.addAttribute("equipmentRepairs", equipmentRepairs);
		 	List<User> repairUserList =new ArrayList<User>();
		 	repairUserList = userService.findListByDeptType("ownerRepair");
		 	model.addAttribute("repairUserList", repairUserList);	

	 		return "forward:/maintainingTotask.jsp";
	 		
	 	}
	 	 //待办任务单个点击跳转
		  @RequestMapping("/toEidtTask")  
	      public String toEidtTask(String equipmentrepairId,HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
		  System.out.println("-----------equipmentrepairId:"+equipmentrepairId);
	      String returnUrl = request.getScheme() + "://" + request.getServerName()
	    		  + ":" + request.getServerPort() + "/upload/"  ;// 存储路径
		  model.addAttribute("returnUrl", returnUrl);
		  EquipmentRepair tEquipmentRepair=new EquipmentRepair();
		  List equipmentRepairs=new ArrayList();
	 		//获取当前登录者信息
	 		SessionContainer sessionContainer = (SessionContainer) request
	 				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
	 		if (sessionContainer != null &&sessionContainer.getUser() != null) {
	 			User tUser = sessionContainer.getUser();	
	 			tEquipmentRepair.setEpAcNextuser(tUser.getUserId());
	 		}
	 		tEquipmentRepair.setEpType("2");
	 		equipmentRepairs=equipmentRepairService.findOwnerStatusTList(tEquipmentRepair);
	 		model.addAttribute("equipmentrepairs", equipmentRepairs);

		  EquipmentRepairO mEquipmentRepairO = equipmentRepairService.qryOwnerById(equipmentrepairId);		  
		  model.addAttribute("mEquipmentRepairO", mEquipmentRepairO);
		  System.out.println("-----------toEidtTask:"+mEquipmentRepairO.getEpReDescribe());
		  //加入维修责任人字段下拉框数据源
	 	  //查询维修部门repair人员信息
	 		
	 	  List<User> repairUserList =new ArrayList<User>();
	 	  repairUserList = userService.findListByDeptType("ownerRepair");
	 	  model.addAttribute("repairUserList", repairUserList);	

		  return "forward:/maintainingEdittask.jsp";
	      }
	 	
	 	
	 	
			//待办任务流程流转提交按钮
			@RequestMapping(value = "/submitOwnerToDo", method = RequestMethod.POST)
			@ResponseBody
			public Json submitOwnerToDo(EquipmentRepair equipmentRepair,HttpServletRequest request, Model model){
				Json json = new Json(true, "报修表单提交成功！", null);
				//获取登录者信息
				SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
				if(sessionContainer==null||sessionContainer.getUser()==null){
					return  new Json(false, "提交失败,当前登录信息不存在!", null);
				}
				User tUser = sessionContainer.getUser();
	            System.out.println("----------submitOwnerToDo:"+equipmentRepair.getEpReId());
				if(!equipmentRepairService.submitOwnerToDo(equipmentRepair,tUser)){
					json.setSuccess(false);
					json.setMsg("报修表单提交失败！");	
				}
				return json;
			}	
			
			//待办任务流程流转新增维修记录
			@RequestMapping(value = "/ownerRpAdd", method = RequestMethod.POST)
			@ResponseBody
			public Json ownerRpAdd(EquipmentRepair equipmentRepair,HttpServletRequest request, Model model){
				Json json = new Json(true, "报修表单提交成功！", null);
				//获取登录者信息
				SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
				if(sessionContainer==null||sessionContainer.getUser()==null){
					return  new Json(false, "提交失败,当前登录信息不存在!", null);
				}
				User tUser = sessionContainer.getUser();
				
				if(!equipmentRepairService.ownerRpAdd(equipmentRepair,tUser)){
					json.setSuccess(false);
					json.setMsg("报修表单提交失败！");	
				}
				return json;
			}	

			
			//通知数的更新
			@RequestMapping("getsum")
			public void getsum(HttpServletRequest request,HttpServletResponse response) throws Exception{
			    HttpSession session = request.getSession();

				//获取登录者信息
				SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
				if(sessionContainer==null||sessionContainer.getUser()==null){
				    
				}else{
				User tUser = sessionContainer.getUser();
			    try {
			        //获取报修中待办总数
					 Map<String,String> tequipmentRepair=new HashMap<>();
					tequipmentRepair.put("epAcNextuser", tUser.getUserId());
					tequipmentRepair.put("epType", "2");
					int count=equipmentRepairService.findOwnerCount(tequipmentRepair);
					session.setAttribute("count", count);
			        StringBuffer json =new StringBuffer("{");
			        json.append("'msg':'"+count+"'");
			        json.append("}");// 构造json数据格式
			        PrintWriter out = response.getWriter();
			        out.write(json.toString());
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
				}
			
			}
			
			 
			//工作人员跳转到已完成报修单列表
			 @RequestMapping("/hsRepair")
		     public String hsRepair(HttpServletRequest request,Model model) throws Exception{
					HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();
					List equipmentRepairs=new ArrayList();
					//获取当前登录者信息
					SessionContainer sessionContainer = (SessionContainer) request
							.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
					if (sessionContainer != null &&sessionContainer.getUser() != null) {
						User tUser = sessionContainer.getUser();	
						tHsequipmentRepair.setEpAcNowuser(tUser.getUserId());
					}
					tHsequipmentRepair.setEpType("2");
					equipmentRepairs=hsequipmentRepairService.findStatusOList(tHsequipmentRepair);
					model.addAttribute("equipmentRepairs", equipmentRepairs);
			  return "forward:/hsownerMaintaining.jsp";
			 }
			 
			//跳转到业主首页页面
			 @RequestMapping("/ownerIndex")      
		     public String ownerIndex(HttpServletRequest request,ModelMap map) throws Exception{
				 
			  
				 return "forward:/ownerIndex.jsp";
		     }
			 

}
