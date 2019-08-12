package sz.zp.cks.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.Supplier;
import sz.zp.cks.entity.User;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.service.MenuService;
import sz.zp.cks.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {
	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	@Autowired
	private EquipmentRepairService equipmentRepairService;
	protected final Logger log =LoggerFactory.getLogger(this.getClass()); 	
	
	@RequestMapping(value = "/login")
	public String login( User user,HttpServletRequest request,HttpSession session,Model model){
		//登录前校验是否已经登录  登陆对象--SessionContainer容器--session
		log.info(">>>>登录了>>>>");
		boolean flag = false;
		String resultURL = "";
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		if (null == sessionContainer) {
			flag = true;
		} else {
			User tUser = sessionContainer.getUser();
			if (null == tUser) {
				flag = true;
			} else {
				//初始化首页数据
				model=menuService.viewMain(model);
				if(tUser.getType().equals("2")){
				 resultURL = "forward:/main2.jsp";	
				}else{
				 resultURL = "forward:/main.jsp";		
				}
				
			}
		}	
		//未登陆
		if (flag) {
			try {
				if ((user.getLoginName()!=null&&!("").equals(user.getLoginName()))&& (user.getPassword()!=null&&!("").equals(user.getPassword()))){
					if (null == sessionContainer) {
					sessionContainer = new SessionContainer();
					}
					User u= userService.login(user);
					if(u!=null){
						//存session
						sessionContainer.setUser(u);
						JSONObject json=menuService.findListMsg(u);
						//查出菜单
						sessionContainer.setMenutree(json);
						session.setAttribute(SSIConstants.SESSIONCONTAINER,
								sessionContainer);

 					 Map<String,String> tequipmentRepair=new HashMap<>();
						tequipmentRepair.put("epAcNextuser", u.getUserId());
						tequipmentRepair.put("epType", "2");
						int count=equipmentRepairService.findOwnerCount(tequipmentRepair);
						session.setAttribute("count", count);
						resultURL = "forward:/main.jsp";
						//初始化首页数据
						model=menuService.viewMain(model);
						if(u.getType().equals("2")){
							 resultURL = "forward:/main2.jsp";	
							}else{
							 resultURL = "forward:/main.jsp";		
							}


					}else{
						request.setAttribute("msg", "用户名或密码错误！");
						request.setAttribute("rememberMe", true);
						resultURL = "forward:/index.jsp";
					}				
				 }else{
					 model.addAttribute("msg", "账号或密码不能为空!");
					 resultURL = "forward:/index.jsp";
					}	
			} catch (Exception e) {
				e.printStackTrace();
			}					
		}		
		return resultURL;
	}

	
	/**
	 * 前台用户退出
	 * @param request
	 * @return
	 */
	@RequestMapping("quit")
	public String loginout(HttpServletRequest request){
		String resultURL = "";
		SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if(null==sessionContainer){
			resultURL = "redirect:/index.jsp";
		}else{
//			User acc = sessionContainer.getUser();
//			if(null==acc){
//				sessionContainer.setMenutree(null);
//				resultURL = "redirect:/index.jsp";
//			}else{
//				sessionContainer.setUser(null);
//				sessionContainer.setMenutree(null);
//				resultURL = "redirect:/index.jsp";
//			}
			request.getSession().removeAttribute(SSIConstants.SESSIONCONTAINER);
			resultURL = "redirect:/index.jsp";
		}
		return resultURL;
	}	
	
	
	@RequestMapping("/doAjax")
	@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
	public Object doAjax(Supplier supplier){
		System.out.println("---doAjax.supplier:"+supplier);
		supplier.setSupName("supName1");
		return supplier;
	}
	@RequestMapping(value = {"list", ""})
	public String list( User user, HttpServletRequest request, HttpServletResponse response,Model model) {
		List list=new ArrayList();
		
		list=userService.findList(user);
		model.addAttribute("list", list);
//		return "device/device";
		return "forward:/WEB-INF/views/device/device.jsp";
	}

public static void main(String[] args) {
	System.out.println(UUID.randomUUID().toString());
}
 

		
 
}
