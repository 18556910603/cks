package sz.zp.cks.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import sz.zp.cks.common.Json;
import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.dao.EquipmentMapper;
import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.ElectricalMaintain;
import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.ElectricalCheckT;
import sz.zp.cks.model.EquipmentT;
import sz.zp.cks.model.Gps;
import sz.zp.cks.model.HsequipmentRepairT;
import sz.zp.cks.service.ElectricalCheckService;
import sz.zp.cks.service.ElectricalMaintainService;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IEquipmentBiz;
import sz.zp.cks.utils.GetLocationMsg;
import sz.zp.cks.utils.PubFun;

@Controller
@RequestMapping("/electricalCheck")
public class ElectricalCheckController extends BaseAction {
	
	@Resource
	private ElectricalCheckService electricalCheckService;
	@Resource
	private ElectricalMaintainService electricalMaintainService;
	
	
	@Autowired
	IEquipmentBiz equipmentBiz;
	
	@Resource
	private IEpuserBiz epuserBiz;
	@Autowired
	EquipmentMapper equipmentMapper;
	
	//设备不定期保养
	@RequestMapping(value = "view")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model,String epId) throws Exception {
		String url = request.getRequestURL().toString();
		Map<String, String> ret = new HashMap<String, String>();// 从后台获取信息，用于js验证
		ret = JsSignUtil.sign(url);
		model.addAttribute("ret", ret);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String returnUrl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + "/code/upload/imgs/"  ;// 存储路径
		
		model.addAttribute("returnUrl", returnUrl);
		
		//zdy
		if(epId!=null&&!("").equals(epId))
        {
        Equipment equipment= equipmentBiz.queryById(epId);
        model.addAttribute("equipment", equipment);        
        Epuser epUser= epuserBiz.queryById(equipment.getEpUserId());
        model.addAttribute("epUser", epUser);
        }
		
		
		return "forward:/WEB-INF/views/checks/electricalCheck.jsp";
	}

	
	

	// 手机扫码二维码
	@RequestMapping(value = "form")
	public String form(ElectricalCheck electricalCheck,
			HttpServletRequest request, Model model) {
		String resultURL = "";
		// 获取登录者
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if ((null != sessionContainer)
				&& (null != (sessionContainer.getUser()))) {
			resultURL = "forward:/WEB-INF/views/checks/electricalCheck.jsp";
		} else {
			resultURL = "redirect:/index.jsp";
		}

		return resultURL;
	}

	// 巡检表单： 1.提交后在状态表中更新该设备状态（没有就新增）
	// 2.在状态备份表中新增一条记录

	@RequestMapping(value = "/save")
	@ResponseBody
	public Json save(ElectricalCheckT telectricalCheck,
			HttpServletRequest request, Model model) {
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg("巡检表单提交成功！");
		// 主键
		String checksId = "El" + UUID.randomUUID().toString().replace("-", "");
		// 获取登录者信息
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer == null || sessionContainer.getUser() == null) {
			json.setSuccess(false);
			json.setMsg("提交失败,当前登录信息不存在!");
			return json;
		}
		User tUser = sessionContainer.getUser();
		
		//复制值
		ElectricalCheck electricalCheck=new ElectricalCheck();
		BeanUtils.copyProperties(telectricalCheck, electricalCheck);
		
		// 页面无法获取的参数
		electricalCheck.setChecksId(checksId);
		electricalCheck.setCreatedBy(tUser.getUserId());
		electricalCheck.setCreationDate(PubFun.getCurrentDate());
		try {
			// 更新状态表
			if (!electricalCheckService.updateStatus(electricalCheck, tUser)) {
				json.setSuccess(false);
				json.setMsg("提交失败,更新状态表异常!");
				return json;
			}
			//设备状态正常 当前日期距离保养日期相差多少天存在且小于3
			if(("1").equals(telectricalCheck.getStatusNewVal())&&telectricalCheck.getDistanceDays()!=null&&!("").equals(telectricalCheck.getDistanceDays())&&(Integer.parseInt(telectricalCheck.getDistanceDays()))<=3){
				//保养单提交
				if(electricalMaintainService.insertByCK(telectricalCheck, electricalCheck)<1){
					json.setSuccess(false);
					json.setMsg("提交失败,保养单记录异常!");
					return json;					
				};
				//更新设备表的保养日期(根据实际保养日期是否有值)
				if(equipmentBiz.updateByMaintain(telectricalCheck, electricalCheck)<1){
					json.setSuccess(false);
					json.setMsg("提交失败,更新设备下一次保养日期异常!");
					return json;
				};
				
			}
			//巡检单提交
			int count = electricalCheckService.insert(electricalCheck);
			if (count < 1) {
				json.setSuccess(false);
				json.setMsg("提交失败,巡检单记录异常!");
				return json;
			}	

			

			
			
			
			
			
		} catch (Exception e) {
			// 提交失败
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("提交失败,存入数据库异常!");
		}
		return json;

	}

	@RequestMapping(value = "/equipOne")
	@ResponseBody
	public Json editOne(String epId, Model model) {
		EquipmentT tEquipmentT = new EquipmentT();
		Json tJson=new Json(true, "成功", tEquipmentT);
		try {
			//设备是否存在
			tEquipmentT = electricalCheckService.queryById(epId);
			if(tEquipmentT!=null){
				//设备是否在报修中
				List<EquipmentRepair> repair = electricalCheckService.isRepair(epId);
				//在报修中
				if(repair!=null&&repair.size()>0){
					tJson=new Json(false, "当前设备在报修中，请先完成报修流程!", tEquipmentT);
				}else{
					tJson=new Json(true, "成功", tEquipmentT);
				}
				
			}else{
				tJson=new Json(false, "当前设备不存在!", tEquipmentT);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tJson;
	}

	@RequestMapping(value = "tips")
	public String tips() {
		return "forward:/WEB-INF/views/checks/tips.jsp";
	}

	// 得到详情地址
	@ResponseBody
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public Json details(Map<String, Object> model, HttpSession session,
			HttpServletRequest request) throws JsonProcessingException,
			IOException {
		Json json = new Json();
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		if (latitude != null & longitude != null) {
			double lat = Double.valueOf(latitude).doubleValue();
			double lon = Double.valueOf(longitude).doubleValue();

			// 微信是GPS需要转化地图
			Gps gps = new Gps(lat, lon);
			String jsonStr = GetLocationMsg.GetLocationMs(gps.getWgLat(),
					gps.getWgLon());

			JSONObject bodyObj = JSONObject.fromObject(jsonStr);
			JSONObject detailObj = bodyObj.getJSONObject("result");
			String locationNode = detailObj.getString("formatted_address");
			String sematiclocationNode = detailObj
					.getString("sematic_description");
			json.setMsg(locationNode + sematiclocationNode);

			return json;
		} else {
			return null;
		}

	}
	@Transactional
	@RequestMapping(value = "/getPhotos", method = RequestMethod.POST)
	@ResponseBody
	public String getphotos(@RequestParam(value = "logos", required = false) MultipartFile logos){
		if (null != logos) {
			if (logos.getSize() > 0) {
				System.out.println("23232");
			}
		} else {
			System.out.println("39000");
		}
		return "success";
	}


	@RequestMapping(value = "/getPhoto", method = RequestMethod.POST)
	@ResponseBody
	public Json getPhoto(String media_id, HttpServletRequest request)
			throws NoSuchAlgorithmException {
		Json json = new Json();
		// 保存图片 路径 PathKit.getWebRootPath() + "/vehicleupload/" + filename;
		String filename = saveImageToDisk(media_id, request);
		json.setMsg(filename);

		return json;
	}

	/**
	 * 获取临时素材
	 */
	private InputStream getMedia(String mediaId) {
		String url = "https://api.weixin.qq.com/cgi-bin/media/get";
		String access_token = WeixinUtil.getAccessToken().getToken();
		String params = "access_token=" + access_token + "&media_id=" + mediaId;
		InputStream is = null;
		try {
			String urlNameString = url + "?" + params;
			URL urlGet = new URL(urlNameString);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			// 获取文件转化为byte流
			is = http.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 保存图片至服务器
	 * 
	 * @param mediaId
	 * @return 文件名
	 */
	public String saveImageToDisk(String mediaId, HttpServletRequest request) {
		String filename = "";
		InputStream inputStream = getMedia(mediaId);
		byte[] data = new byte[1024];
		int len = 0;
		String path = "";
		FileOutputStream fileOutputStream = null;
		String returnUrl = "";
		String fileAdd = "";
		try {

			// filename = System.currentTimeMillis() + WeixinUtil.getNonceStr()
			// + ".jpg";
			//
			//
			//
			// path =
			// request.getSession().getServletContext().getRealPath("/Images");
			// //文件存储位置
			//
			//
			// //先判断文件是否存在
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			// String fileAdd = sdf.format(new Date());
			// //获取文件夹路径
			// File file1 =new File(path+"/"+fileAdd);
			// //如果文件夹不存在则创建
			// if((!file1.exists()) && (!file1.isDirectory())){
			// file1.mkdir();
			// }

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			// returnUrl = request.getScheme() + "://" + request.getServerName()
			// + ":" + request.getServerPort() + request.getContextPath()
			// +"/code/upload/imgs/";//存储路径
			returnUrl = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + "/code/upload/imgs/";// 存储路径

			// path =
			// request.getSession().getServletContext().getRealPath("upload/imgs");

			path = "D:/code/upload/imgs";

			fileAdd = sdf.format(new Date());

			filename =System.currentTimeMillis() + WeixinUtil.getNonceStr()
					+ ".jpg";

			// 获取文件夹路径
			File file1 = new File(path + "/" + fileAdd);
			// 如果文件夹不存在则创建
			if ((!file1.exists()) && (!file1.isDirectory())) {
				file1.mkdirs();
			}

			fileOutputStream = new FileOutputStream(path + "/" + fileAdd + "/"
					+ filename);
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return  fileAdd+ "/" +filename;
	}

	//历史巡检单记录查询展示	
	@RequestMapping(value = "/historyLists")
	public String historyLists(HttpServletRequest request, HttpServletResponse response,Model model) {
		String url="forward:/WEB-INF/views/checks/electricalCheckTasks.jsp";
		String userId="";
		//获取当前登录者信息
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute(SSIConstants.SESSIONCONTAINER);
		if (sessionContainer != null &&sessionContainer.getUser() != null) {
			User tUser = sessionContainer.getUser();	
			userId=tUser.getUserId();
		}
		List<ElectricalCheck> list=new ArrayList<ElectricalCheck>();
		list=electricalCheckService.findElectricalCheckList(userId);
		model.addAttribute("list", list);
		return url;
	}	
	
	//待办任务单个点击跳转
	@RequestMapping(value = "/displayOneById")
	public String displayOneById(ElectricalCheckT tElectricalCheckT,HttpServletRequest request,Model model) {
		String returnUrl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + "/code/upload/imgs/"  ;// 存储路径
		model.addAttribute("returnUrl", returnUrl);
			
		ElectricalCheckT mElectricalCheckT=new ElectricalCheckT();
		mElectricalCheckT=electricalCheckService.qryById(tElectricalCheckT.getChecksId());
		model.addAttribute("mElectricalCheckT", mElectricalCheckT);	
		return "forward:/WEB-INF/views/checks/editElectricalCheckTask.jsp";
	}

}
