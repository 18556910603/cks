package sz.zp.cks.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.HsequipmentRepair;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.EquipmentRepairT;
import sz.zp.cks.model.HsequipmentRepairT;
import sz.zp.cks.service.HsequipmentRepairService;
import sz.zp.cks.service.UserService;

@Controller
@RequestMapping("/hsequipmentRepair")
public class HsequipmentRepairController extends BaseAction {
	@Resource
	private HsequipmentRepairService hsequipmentRepairService;
	@Resource
	private UserService userService;	
	//巡检人员的已完成任务列表查询功能
	@RequestMapping(value = "/view")
	public String list(  HttpServletRequest request, HttpServletResponse response,Model model) {
		String url="forward:/WEB-INF/views/repair/completedTasks.jsp";
		HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();
		List list=new ArrayList();
		//获取当前登录者信息
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer != null &&sessionContainer.getUser() != null) {
			User tUser = sessionContainer.getUser();	
			tHsequipmentRepair.setEpAcNowuser(tUser.getUserId());
		}
		tHsequipmentRepair.setEpType("1");
		list=hsequipmentRepairService.findStatusTList(tHsequipmentRepair);
		model.addAttribute("list", list);
		return url;
	}
	
	//待办任务单个点击跳转
	@RequestMapping(value = "/displayOneById")
	public String displayOneById(HsequipmentRepairT tHsequipmentRepairT,HttpServletRequest request,Model model) {
		String returnUrl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + "/code/upload/imgs/"  ;// 存储路径
		model.addAttribute("returnUrl", returnUrl);
			
		HsequipmentRepairT mHsequipmentRepairT=new HsequipmentRepairT();
		mHsequipmentRepairT=hsequipmentRepairService.qryById(tHsequipmentRepairT.getHsEquipmentrepairId());
		model.addAttribute("mHsequipmentRepairT", mHsequipmentRepairT);	
		//加入维修责任人字段下拉框数据源
		//查询维修部门repair人员信息
		
		List<User> repairUserList =new ArrayList<User>();
		repairUserList = userService.findListByDeptType("repair");
		model.addAttribute("repairUserList", repairUserList);			
		return "forward:/WEB-INF/views/repair/editCompletedTask.jsp";
	}

	//所有维修完成的工单
	@RequestMapping(value="/searchList")
	public String searchList(  HttpServletRequest request, HttpServletResponse response,Model model) {
		String url="forward:/WEB-INF/views/print/print.jsp";
        List list=new ArrayList();
//		HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();

		//获取当前登录者信息
//		SessionContainer sessionContainer = (SessionContainer) request
//				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
//		if (sessionContainer != null &&sessionContainer.getUser() != null) {
//			User tUser = sessionContainer.getUser();
//			tHsequipmentRepair.setEpAcNowuser(tUser.getUserId());
//		}
//		tHsequipmentRepair.setEpType("1");
		list=hsequipmentRepairService.qryHsAll();
        System.out.println("校验一下"+list.toString());
		model.addAttribute("list", list);
		return url;
	}
	
	
	
	
	
}
