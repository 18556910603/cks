package sz.zp.cks.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.Account;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.MonthReCounts;
import sz.zp.cks.service.AccountService;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuAction extends BaseAction {
	@Resource
	private MenuService menuService;
	@Resource
	private AccountService accountService;
	private Logger logger;

	

	@RequestMapping(value = {"list"})
	public String list(  HttpServletRequest request, HttpServletResponse response,Model model) {
		List list=accountService.findList(new Account());
		model.addAttribute("list", list);
		return "forward:/WEB-INF/views/device/device.jsp";
	}

	@RequestMapping(value = "/main")
	public String main(  HttpServletRequest request, HttpServletResponse response,Model model) {
		SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);		
		String resultURL="forward:/main.jsp";
		if (null == sessionContainer) {
			
		} else {
			User tUser = sessionContainer.getUser();
        
		model=menuService.viewMain(model);
		
		if(tUser.getType().equals("2")){
			
			resultURL = "forward:/main2.jsp";	
		}else{
			
			resultURL = "forward:/main.jsp";		
			}
		}
		return resultURL;
	}
	
	
	
}
