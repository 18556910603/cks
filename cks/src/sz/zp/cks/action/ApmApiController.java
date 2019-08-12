package sz.zp.cks.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import sz.zp.cks.dao.DataPushLogMapper;
import sz.zp.cks.entity.Alarm;
import sz.zp.cks.dao.EquipmentRepairMapper;
import sz.zp.cks.entity.DataPushLog;
import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.ElectricalMaintain;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.DaanCount;
import sz.zp.cks.model.MainJspValue;
import sz.zp.cks.model.NewsCounts;
import sz.zp.cks.model.PerscentCounts;
import sz.zp.cks.model.apm.DocumentsN;
import sz.zp.cks.model.apm.GetParameter;
import sz.zp.cks.service.AlarmService;
import sz.zp.cks.service.ApmApiService;
import sz.zp.cks.service.ElectricalCheckService;
import sz.zp.cks.service.ElectricalMaintainService;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.utils.PubFun;

@Controller
@RequestMapping("/toApmApi")
public class ApmApiController extends BaseAction {
	@Resource
	private ApmApiService  apmApiService;
	@Autowired
	private DataPushLogMapper dataPushLogMapper;
	@Autowired
	private AlarmService alarmService;
	@Resource
	private ElectricalCheckService electricalCheckService;
	@Resource
	private ElectricalMaintainService electricalMaintainService;	
	@Resource
	private EquipmentRepairService equipmentRepairService;
	@Resource
	private EquipmentRepairMapper equipmentRepairMapper; 
	@RequestMapping(value = {"preForApm"},method=RequestMethod.POST)
	@ResponseBody 
	public String preForApm(@RequestBody String message) {
		String map=apmApiService.insertByPreForApm(message);
		return message;
	}

	@RequestMapping(value = {"getFromApm"},method=RequestMethod.POST)
	@ResponseBody 
	public String getFromApm(@RequestBody String requestJson,HttpServletRequest request, HttpServletResponse response) {
		//存日志
		String result="";
		DataPushLog tDataPushLog =new DataPushLog();
		try {
			result = apmApiService.insertByGetFromApm(requestJson, response);
		} catch (Exception e) {
			result=e.getMessage();
			e.printStackTrace();
		}finally{
			tDataPushLog.setDataPushLogId(UUID.randomUUID().toString().replace("-", ""));
			tDataPushLog.setData(requestJson);
			tDataPushLog.setPushTime(PubFun.getCurrentDate());
			tDataPushLog.setResponseMessage(result);
			tDataPushLog.setType("apmToomms");
			dataPushLogMapper.insert(tDataPushLog);
		}
		return result;
	}
	
	
	@RequestMapping(value = {"getAlarmFromApm"},method=RequestMethod.POST)
	@ResponseBody 
	public String getAlarmFromApm(@RequestBody String requestJson,HttpServletRequest request, HttpServletResponse response) {
		//存日志
		String result="";
		DataPushLog tDataPushLog =new DataPushLog();
		try {
			result = apmApiService.insertByGetFromApm(requestJson, response);
		} catch (Exception e) {
			result=e.getMessage();
			e.printStackTrace();
		}finally{
			tDataPushLog.setDataPushLogId(UUID.randomUUID().toString().replace("-", ""));
			tDataPushLog.setData(requestJson);
			tDataPushLog.setPushTime(PubFun.getCurrentDate());
			tDataPushLog.setResponseMessage(result);
			dataPushLogMapper.insert(tDataPushLog);
		}
		return result;
	}
	
	@RequestMapping(value = "/search")
	public String search(ModelMap model) throws Exception {
		System.out.println("--------------告警页面--------");
	
	    GetParameter getParameter=new GetParameter();
	    Date date=new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    String end=sdf.format(date);
		String currentDate = PubFun.getCurrentDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();       
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7); 
        Date d = c.getTime();
        String startTs = format.format(d);
        String start = sdf.format(d);
        System.out.println("--------------startTs--------:"+startTs+"---------endTs:"+currentDate);
        getParameter.setStartTs(startTs);
        getParameter.setEndTs(currentDate);
		List<DocumentsN> documents=apmApiService.getByPreForApm(getParameter);		
		model.addAttribute("documents", documents);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "forward:/WEB-INF/views/alarm/alarmFromApm.jsp";		
	  }
	
	//按照自定义时间筛选告警信息
	 @RequestMapping(value = "/timeSearch")
    public String hRepairEnd(HttpServletRequest request,String startTs,String endTs,ModelMap model) throws Exception{		 
		 System.out.println("---------startTs:"+startTs);
		 System.out.println("---------end:"+endTs);
		 if(startTs!=null&&!startTs.equals("")&&endTs!=null&&!endTs.equals("")){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		 Date startDate=sdf.parse(startTs);
		 Date endDate=sdf.parse(endTs);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String start=format.format(startDate);
		 String end=format.format(endDate);
		 GetParameter getParameter=new GetParameter();
		 System.out.println("---------start:"+start);
		 System.out.println("---------end:"+end);
		 getParameter.setStartTs(start);
		 getParameter.setEndTs(end);
		 List<DocumentsN> documents=apmApiService.getByPreForApm(getParameter);		
		 model.addAttribute("documents", documents);
		 model.addAttribute("start", startTs);
		 model.addAttribute("end", endTs);
		 }

	  return "forward:/WEB-INF/views/alarm/alarmFromApm.jsp";		
    }
	
	/**
	 * SZWS 系统总览
	 * 告警数量统计 异常 告警 正常
	 * 工单处理统计 等待  处理 完成
	 */
	@RequestMapping(value="alarmCounts",method=RequestMethod.GET)
    @ResponseBody
    public void alarmCounts(HttpServletRequest request,HttpServletResponse response){
		//允许跨域处理
        response.setHeader("P3P", "CP=CAO PSA OUR");
        response.addHeader("Access-Control-Allow-Origin", "*");
        String data = request.getParameter("data");
        //1：业务逻辑
        JSONObject returnMap = new JSONObject();
        //2:输入json数据到前台页面
        NewsCounts alarmCounts = alarmService.getAlarmCounts();
        returnMap.put("abnormal", alarmCounts.getAbnormal());
        returnMap.put("alarm", alarmCounts.getAlarm());
        returnMap.put("normal", alarmCounts.getNormal());
        returnMap.put("waitFor", alarmCounts.getWaitFor());
        returnMap.put("handle", alarmCounts.getHandle());
        returnMap.put("complete", alarmCounts.getComplete());
        PrintWriter pWriter = null;
        try {
            pWriter = response.getWriter();
            pWriter.write("callback('" + returnMap.toString() + "')");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            if(pWriter!=null){
                pWriter.flush();
                pWriter.close();
            }           
        }
 
    }
	/**
	 * DAAN 运维接数据
	 */
	@RequestMapping(value="getdaanMap",method = RequestMethod.POST)
    @ResponseBody
    public Map daanMap(@RequestBody Map<String,Object> jsonData,HttpServletRequest request,HttpServletResponse response){
		//允许跨域处理
//        String page= jsonData.get("page").toString();
        JSONObject returnMap = new JSONObject();
		//1.巡检工单
		List<ElectricalCheck> findALLElectricalCheckList = electricalCheckService.findALLElectricalCheckList();
		returnMap.put("allcklist", findALLElectricalCheckList);
		//2.报修工单
		List findALLRepairTList = equipmentRepairService.findALLRepairTList();
		returnMap.put("allrplist", findALLRepairTList);
		//3.保养工单
		List<ElectricalMaintain>  maintainlist= electricalMaintainService.findAllList();
		returnMap.put("maintainlist", maintainlist);
		//4.查询工單完成率統計 保养，报修已经完成，巡检单数量
		PerscentCounts nums = equipmentRepairService.getNums();
		List list =new ArrayList();
		List Ckslist =new ArrayList();
		Ckslist.add("巡檢工單");
		Ckslist.add(Integer.parseInt(nums.getCksNum()));
		list.add(Ckslist);
		
		List Replist =new ArrayList();
		Replist.add("報修工單");
		Replist.add(Integer.parseInt(nums.getRepNum()));
		list.add(Replist);
		
		List matlist =new ArrayList();
		matlist.add("保養工單");
		matlist.add(Integer.parseInt(nums.getMatNum()));
		list.add(matlist);
		returnMap.put("PerscentCounts", list);
		//5.數據對比分析
		DaanCount daanCount = equipmentRepairService.getDaanCount();
		List today =new ArrayList();
		today.add(Integer.parseInt(daanCount.getTodaycks()));
		today.add(Integer.parseInt(daanCount.getTodayrep()));
		today.add(Integer.parseInt(daanCount.getTodaymat()));
		List yesday =new ArrayList();
		yesday.add(Integer.parseInt(daanCount.getYescks()));
		yesday.add(Integer.parseInt(daanCount.getYesrep()));
		yesday.add(Integer.parseInt(daanCount.getYesmat()));		
		List lastday =new ArrayList();
		lastday.add(Integer.parseInt(daanCount.getLastcks()));
		lastday.add(Integer.parseInt(daanCount.getLastrep()));
		lastday.add(Integer.parseInt(daanCount.getLastmat()));			
		
		List allmap=new ArrayList();
		Map tmap=new HashMap();
		tmap.put("name", "今天("+daanCount.getToday()+")");
		tmap.put("data", today);
		allmap.add(tmap);
		Map ymap=new HashMap();
		ymap.put("name", "昨天("+daanCount.getYes()+")");
		ymap.put("data", yesday);		
		allmap.add(ymap);
		Map lmap=new HashMap();
		lmap.put("name", "去年今天("+daanCount.getLastday()+")");
		lmap.put("data", lastday);				
		allmap.add(lmap);
		returnMap.put("dataComp", allmap);
		//6.设备状态数据统计
		MainJspValue tMainJspValue = new MainJspValue();
		tMainJspValue=equipmentRepairMapper.selectForMainJspValue();
		List allstatus=new ArrayList();
		
		Map zhmap=new HashMap();
		zhmap.put("name", "正常运行");
		zhmap.put("y", Integer.parseInt(tMainJspValue.getNormalOperation()));
		zhmap.put("sliced", true);
		zhmap.put("selected", true);
		allstatus.add(zhmap);
		Map yimap=new HashMap();
		yimap.put("name", "异常状态");
		yimap.put("y", Integer.parseInt(tMainJspValue.getAbnormalOperation()));
		allstatus.add(yimap);
		Map wemap=new HashMap();
		wemap.put("name", "维修中");
		wemap.put("y", Integer.parseInt(tMainJspValue.getMaintenanceState()));
		allstatus.add(wemap);
		returnMap.put("overdue", allstatus);
		//7.
		returnMap.put("nor", Integer.parseInt(tMainJspValue.getNormalOperation()));
		returnMap.put("abnor", Integer.parseInt(tMainJspValue.getAbnormalOperation()));
		returnMap.put("wei", Integer.parseInt(tMainJspValue.getMaintenanceState()));
		
		return returnMap;
    }    
	
	@RequestMapping(value="getElecMap",method = RequestMethod.POST)
    @ResponseBody
    public Map getElecMap(@RequestBody Map<String,Object> jsonData,HttpServletRequest request,HttpServletResponse response){
		//允许跨域处理
//        String page= jsonData.get("page").toString();
        JSONObject returnMap = new JSONObject();
		//1.巡检工单
		List<ElectricalCheck> findALLElectricalCheckList = electricalCheckService.findALLElectricalCheckList();
		returnMap.put("allcklist", findALLElectricalCheckList);
		System.out.println(findALLElectricalCheckList);
		//2.报修工单
		List findALLRepairTList = equipmentRepairService.findALLRepairTList();
		returnMap.put("allrplist", findALLRepairTList);
		//3.保养工单
		List<ElectricalMaintain>  maintainlist= electricalMaintainService.findAllList();
		returnMap.put("maintainlist", maintainlist);
		//4.查询工單完成率統計 保养，报修已经完成，巡检单数量
		PerscentCounts nums = equipmentRepairService.getNums();
		List list =new ArrayList();
		List Ckslist =new ArrayList();
		Ckslist.add("巡檢工單");
		Ckslist.add(Integer.parseInt(nums.getCksNum()));
		list.add(Ckslist);
		
		List Replist =new ArrayList();
		Replist.add("報修工單");
		Replist.add(Integer.parseInt(nums.getRepNum()));
		list.add(Replist);
		
		List matlist =new ArrayList();
		matlist.add("保養工單");
		matlist.add(Integer.parseInt(nums.getMatNum()));
		list.add(matlist);
		returnMap.put("PerscentCounts", list);
		//5.告警列表
		List<Alarm> allAlarmlist = alarmService.getAllAlarms();
		returnMap.put("alarmlist", allAlarmlist);
		return returnMap;
    }    
}
