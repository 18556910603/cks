package sz.zp.cks.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sz.zp.cks.common.Json;
import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.Account;
import sz.zp.cks.entity.Menu;
import sz.zp.cks.entity.Supplier;
import sz.zp.cks.service.AccountService;
import sz.zp.cks.service.MenuService;

@Controller
@RequestMapping("/account")
public class AccountAction extends BaseAction {
	@Resource
	private AccountService accountService;
	@Resource
	private MenuService menuService;
	
	private Logger logger;

	
//	
//	@RequestMapping(value = "/login")
//	public String login( Account account,HttpServletRequest request,HttpSession session,ModelMap map){
//		//登录前校验是否已经登录  登陆对象--SessionContainer容器--session
//		boolean flag = false;
//		String resultURL = "";
//		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
//		if (null == sessionContainer) {
//			flag = true;
//		} else {
//			Account tAccount = sessionContainer.getAccount();
//			if (null == tAccount) {
//				flag = true;
//			} else {
//				resultURL = "redirect:/main.jsp";
//			}
//		}	
//		//未登陆
//		if (flag) {
//			try {
//				if ((account.getAccLogin()!=null&&!("").equals(account.getAccLogin()))&& (account.getAccPass()!=null&&!("").equals(account.getAccPass()))){
//					if (null == sessionContainer) {
//					sessionContainer = new SessionContainer();
//					}
//					Account acc= accountService.login(account);
//					if(acc!=null){
//						//存session
//						sessionContainer.setAccount(account);
//						JSONObject json=menuService.findListMsg(new Menu());
//						//查出菜单
//						sessionContainer.setMenutree(json);
//						session.setAttribute(SSIConstants.SESSIONCONTAINER,
//								sessionContainer);
//						resultURL = "redirect:/main.jsp";
//					}else{
//						request.setAttribute("msg", "用户名或密码错误！");
//						request.setAttribute("rememberMe", true);
//						resultURL = "forward:/ckslogin.jsp";
//					}				
//				 }else{
//						map.put("msg", "账号或密码不能为空!");
//						resultURL = "forward:/ckslogin.jsp";
//					}	
//			} catch (Exception e) {
//				logger.info("登陆时，查询用户出现异常！");
//				e.printStackTrace();
//			}					
//		}		
//		return resultURL;
//	}
//
//	
//	/**
//	 * 前台用户退出
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("quit")
//	public String loginout(HttpServletRequest request){
//		String resultURL = "";
//		SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
//		if(null==sessionContainer){
//			resultURL = "redirect:/ckslogin.jsp";
//		}else{
//			Account acc = sessionContainer.getAccount();
//			if(null==acc){
//				sessionContainer.setMenutree(null);
//				resultURL = "redirect:/ckslogin.jsp";
//			}else{
//				sessionContainer.setAccount(null);
//				sessionContainer.setMenutree(null);
//				resultURL = "redirect:/ckslogin.jsp";
//			}
//		}
//		return resultURL;
//	}	
	
	
	@RequestMapping("/doAjax")
	@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
	public Object doAjax(Supplier supplier){
		System.out.println("---doAjax.supplier:"+supplier);
		supplier.setSupName("supName1");
		return supplier;
	}
	@RequestMapping(value = {"list", ""})
	public String list( Account account, HttpServletRequest request, HttpServletResponse response,Model model) {
		List list=new ArrayList();
		
		list=accountService.findList(account);
		model.addAttribute("list", list);
//		return "device/device";
		return "forward:/WEB-INF/views/device/device.jsp";
	}

}
