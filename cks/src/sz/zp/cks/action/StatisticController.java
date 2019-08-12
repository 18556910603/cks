package sz.zp.cks.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.Owner;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.CksDayReCounts;
import sz.zp.cks.model.EquipmentRepairT;
import sz.zp.cks.model.MonthReCounts;
import sz.zp.cks.model.OwnerStaticJspValue;
import sz.zp.cks.model.OwnerTypeCounts;
import sz.zp.cks.model.RepairCounts;
import sz.zp.cks.model.StaticJspValue;
import sz.zp.cks.service.ElectricalCheckService;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IEquipmentBiz;
import sz.zp.cks.service.MenuService;
import sz.zp.cks.utils.DictUtils;


@Controller
public class StatisticController {

	@Resource
	private MenuService menuService;
	
	@Resource
	private ElectricalCheckService electricalCheckService;
	@Resource
	private EquipmentRepairService equipmentRepairService;	
	@Autowired
	IEquipmentBiz equipmentBiz;
	
	@Resource
	private IEpuserBiz epuserBiz;
	//跳转到报修页面
	@RequestMapping("/statistics")      
    public String statistics(HttpServletRequest request,Model model) throws Exception{
	 model=menuService.viewMain(model);
	 int sum = equipmentBiz.findUpCount();
	 model.addAttribute("sum", sum);
	 List<StaticJspValue> StaticJspValues = equipmentRepairService.qryEpTypeValue();
	 int todayUncks=0;
	 for(StaticJspValue staticJspValue : StaticJspValues) {
		 todayUncks=todayUncks+Integer.parseInt(staticJspValue.getTodayUnChecked());
		}  
	 
	    List<CksDayReCounts> cksDayReCounts = equipmentRepairService.qryCountByDate();
		List dayList=new ArrayList<>();
		List dayCountList=new ArrayList<>();
		for(int i=0;i<cksDayReCounts.size();i++){
			dayList.add(cksDayReCounts.get(i).getDay());
			dayCountList.add(cksDayReCounts.get(i).getCkdayCounts());
		}
		JSONArray dayListJson = JSONArray.fromObject( dayList );//将java对象转换为json对象
		JSONArray dayCountListJson = JSONArray.fromObject( dayCountList );//将java对象转换为json对象
	 model.addAttribute("dayList", dayListJson);
	 model.addAttribute("dayCountList", dayCountListJson);
	 model.addAttribute("StaticJspValues", StaticJspValues);
	 model.addAttribute("todayUncks", String.valueOf(todayUncks));
	 model.addAttribute("cksDayReCounts", cksDayReCounts);
	 
	 
	 return "forward:/statistics.jsp";
    }
	
	@RequestMapping("/statisticsRepair")      
    public String statisticsRepair(HttpServletRequest request,Model model) throws Exception{
	  String returnUrl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + "/code/upload/imgs/"  ;// 存储路径
		model.addAttribute("returnUrl", returnUrl);

	 RepairCounts repairCounts = equipmentRepairService.qryCountByWeek();
	 List<EquipmentRepairT> equipmentRepairTs = equipmentRepairService.qryByWeek();
	 model.addAttribute("repairCounts", repairCounts);
	 model.addAttribute("equipmentRepairTs", equipmentRepairTs);
	 int size = equipmentRepairTs.size();
	 model.addAttribute("size", size);	 
	 System.out.println("-----------------statisticsRepair:"+equipmentRepairTs.size());
	 return "forward:/statisticsRepair.jsp";
    }
	
	  @RequestMapping(value="/editEquipRepair",method=RequestMethod.POST)
      @ResponseBody      
      public Map<String,Object> getEquipRepair(String equipmentrepairId) throws Exception{
	  Map<String,Object> map = new HashMap<String,Object>();
	  System.out.println("-----------getEquipRepair:"+equipmentrepairId);
	  EquipmentRepairT equipmentRepairT = equipmentRepairService.qryById(equipmentrepairId);
	  map.put("equipmentRepairT", equipmentRepairT);
	  String epReStatus=DictUtils.getDictLabel(equipmentRepairT.getEpReStatus(), "status_type", equipmentRepairT.getEpReStatus());
	  System.out.println("-----------getEquipRepair:"+epReStatus);
	  String epReKind=DictUtils.getDictLabel(equipmentRepairT.getEpReKind(), "epReKind", equipmentRepairT.getEpReKind());
	  map.put("epReStatus", epReStatus);
	  map.put("epReKind", epReKind);
	  return map;
      }

	
	
	@RequestMapping("/ownerStatistics")      
    public String owenerStatistics(HttpServletRequest request,Model model) throws Exception{
	 OwnerStaticJspValue ownerStaticJspValue = equipmentRepairService.selectForOwnerStatic();
	 model.addAttribute("ownerStaticJspValue", ownerStaticJspValue);
		//查询每个月巡检和业主报修的台数(首页柱状图)
		List<OwnerTypeCounts> countsList = equipmentRepairService.qryCountsByepName();
		List <String>epNames=new ArrayList<>();
		List counts=new ArrayList<>();
        for (OwnerTypeCounts ownerTypeCounts : countsList) {
    	   epNames.add(ownerTypeCounts.getEpName());
    	   counts.add(ownerTypeCounts.getCounts());
	    }

		JSONArray epNamesJosn = JSONArray.fromObject( epNames );//将java对象转换为json对象
		JSONArray countsJosn = JSONArray.fromObject( counts );
		JSONArray json2 = JSONArray.fromObject( countsList );
		model.addAttribute("countsList", json2);
		model.addAttribute("epNames", epNamesJosn);
		model.addAttribute("counts", countsJosn);
	 return "forward:/ownerStatistics.jsp";
    }

    @RequestMapping("/statisticsPrint")
	public String statisticsPrint(HttpServletRequest request, HttpServletResponse response, Model model){
		return "forward:/ownerStatistics.jsp";
	}

}
