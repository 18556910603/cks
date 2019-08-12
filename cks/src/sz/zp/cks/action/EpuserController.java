package sz.zp.cks.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sz.zp.cks.entity.AjaxResponseMsg;
import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.PageBean;
import sz.zp.cks.entity.User;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IEquipmentBiz;
import sz.zp.cks.service.UserService;
import sz.zp.cks.service.UserService;
import sz.zp.cks.utils.EpIdUtils;

@Controller
public class EpuserController {
	@Autowired
	IEpuserBiz epuserBiz;
	
	@Autowired
	UserService userService;
	
	

	@RequestMapping(value="/addEpUser",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponseMsg add(Epuser epUser,User user) throws Exception  {
		System.out.println("---addEpUser:"+epUser);
		System.out.println("---addEpUser:"+user.getMobile());
		String epUserId=EpIdUtils.getEpUserId();
		epUser.setEpUserId(epUserId);
		System.out.println("---addEpUser:"+epUser);
		String userId = epuserBiz.getUserID(user.getMobile());
		AjaxResponseMsg ajaxMsg=new AjaxResponseMsg();	
		if(userId==null){
			ajaxMsg.setSuccess(false);
		  	ajaxMsg.setMsg("该用户还未注册！");
		  	return ajaxMsg;
		}
		epUser.setUserId(userId);
		if(epuserBiz.insert(epUser)==1){
			 ajaxMsg.setSuccess(true);
		  	 ajaxMsg.setMsg("操作成功！");
		}else{
			ajaxMsg.setMsg("操作失败！");
		}
		
		return ajaxMsg;

	}
	
	@RequestMapping(value="/deleteEpUser",method=RequestMethod.POST)
    @ResponseBody      
    public int delete(String epUserId) throws Exception{
  	  System.out.println("-----------delete:"+epUserId);
  	  int num = epuserBiz.remove(epUserId);
  	  return num;
    }
	
	@RequestMapping(value="/updateEpUser",method=RequestMethod.POST)
	@ResponseBody  
    public AjaxResponseMsg update(Epuser epUser,User user) throws Exception{
  	  System.out.println("-----------update:"+epUser);
  	  System.out.println("-----------update:"+user);
  	  String userName = user.getUserName();
  	  String mobile =user.getMobile();
  	  System.out.println("-----------updateEpUser:"+userName);
  	  System.out.println("-----------updateEpUser:"+mobile);
  	  user.setUserName(userName);
      user.setMobile(mobile);
  	  userService.update(user);
  	  AjaxResponseMsg ajaxMsg=new AjaxResponseMsg();
  	  if( epuserBiz.modify(epUser)==1){
  	  ajaxMsg.setSuccess(true);
  	  ajaxMsg.setMsg("操作成功！");}
  	  return ajaxMsg;
    }
	
	@RequestMapping(value="/editepUser",method=RequestMethod.POST)
    @ResponseBody      
    public Epuser getEpUser(String epUserId) throws Exception{
  	  System.out.println("-----------getEpUser:"+epUserId);
  	  Epuser epUser= epuserBiz.queryById(epUserId);
  	  System.out.println("-----------getEquip2:"+epUser);
  	  return epUser;
    }
	
	@RequestMapping("/searchEpUser")
	public ModelAndView search(ModelMap model) throws Exception {
        System.out.println("searchEpUser:--------------");
		List<Epuser> epUsers=epuserBiz.getList();
	    System.out.println("searchEpUser:--------------"+epUsers);
	    model.put("epUsers", epUsers);
	    
		return new ModelAndView("epUser.jsp",model);
		
	}
}
