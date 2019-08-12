package sz.zp.cks.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.common.Json;
import sz.zp.cks.dao.AlarmMapper;
import sz.zp.cks.dao.ElectricalMaintainMapper;
import sz.zp.cks.dao.EpuserMapper;
import sz.zp.cks.dao.EquipmentMapper;
import sz.zp.cks.dao.EquipmentRepairMapper;
import sz.zp.cks.dao.HsequipmentRepairMapper;
import sz.zp.cks.dao.HstatusMapper;
import sz.zp.cks.dao.StatusMapper;
import sz.zp.cks.dao.UserMapper;
import sz.zp.cks.entity.Alarm;
import sz.zp.cks.entity.ElectricalMaintain;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.HsequipmentRepair;
import sz.zp.cks.entity.Hstatus;
import sz.zp.cks.entity.Status;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.CksDayReCounts;
import sz.zp.cks.model.DaanCount;
import sz.zp.cks.model.EquipmentRepairO;
import sz.zp.cks.model.EquipmentRepairT;
import sz.zp.cks.model.MonthReCounts;
import sz.zp.cks.model.OwnerStaticJspValue;
import sz.zp.cks.model.OwnerTypeCounts;
import sz.zp.cks.model.PerscentCounts;
import sz.zp.cks.model.RepairCounts;
import sz.zp.cks.model.StaticJspValue;
import sz.zp.cks.model.apm.NiagaraJson;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.utils.DictUtils;
import sz.zp.cks.utils.PubFun;
import sz.zp.cks.utils.StringUtil;
import sz.zp.cks.weixin.Create_Json;

@Service("equipmentRepairService")
public class EquipmentRepairServiceImpl implements EquipmentRepairService {
	private static final Log log= LogFactory.getLog(EquipmentRepairServiceImpl.class);
	@Autowired
	private AlarmMapper alarmMapper;
	@Autowired
	private EquipmentRepairMapper equipmentRepairMapper;
	@Autowired
	private HsequipmentRepairMapper hsequipmentRepairMapper;

	@Autowired
	private EpuserMapper epUserMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private StatusMapper statusMapper;
	@Autowired
	private HstatusMapper hstatusMapper;
	@Autowired
	private ElectricalMaintainMapper electricalMaintainMapper;
	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public int insert(EquipmentRepair entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(EquipmentRepair entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(EquipmentRepair entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EquipmentRepair select(EquipmentRepair entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertSubmit(EquipmentRepair equipmentRepair,User tUser) {
		//保存报修单记录
		//页面无法获取的参数
		equipmentRepair.setEpType(tUser.getType());
		equipmentRepair.setEpReStatus("");
		equipmentRepair.setCreatedBy(tUser.getUserId());
		equipmentRepair.setCreationDate(PubFun.getCurrentDate());

		equipmentRepair.setEpAcStatus("1");//流转中 流程状态
		equipmentRepair.setEpAcNowid("1");//1.报修申请 当前节点
		equipmentRepair.setEpAcNowuser(tUser.getUserId());

		equipmentRepair.setEpAcNextid("2");//主管派单
		//下个节点负责人即维修部门的主管
		User LeaderUser=new User();
		LeaderUser=userMapper.selectForLeaderByType("repair");
		System.out.println("-------------LeaderUser:"+LeaderUser);
		equipmentRepair.setEpAcNextuser(LeaderUser.getUserId());

		//WL 2019-02-27   通知主管
		sendNoticeByUserId(equipmentRepair);


		//		int eqCount=equipmentRepairMapper.insert(equipmentRepair);
		//备份历史记录表中
		HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();
		BeanUtils.copyProperties(equipmentRepair, tHsequipmentRepair);
		String hsEquipmentrepairId =null;
		if(tUser.getType().equals("1")){
			hsEquipmentrepairId="HER"+UUID.randomUUID().toString().replace("-", "");
		}
		if(tUser.getType().equals("2")){
			hsEquipmentrepairId="HOW"+UUID.randomUUID().toString().replace("-", "");
		}
		tHsequipmentRepair.setHsEquipmentrepairId(hsEquipmentrepairId);
		//		int heqCount=hsequipmentRepairMapper.insert(tHsequipmentRepair);

		if((equipmentRepairMapper.insert(equipmentRepair))>0&&(hsequipmentRepairMapper.insert(tHsequipmentRepair))>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean insertSubmitToDo(EquipmentRepairT equipmentRepairT, User tUser) throws Exception  {
		EquipmentRepair equipmentRepair=new EquipmentRepair();
		BeanUtils.copyProperties(equipmentRepairT, equipmentRepair);
		Equipment tEquipment=new Equipment();
		int sCount=0;
		int hsCount=0;
		//历史数据
		EquipmentRepair tequipmentRepair = equipmentRepairMapper.queryById(equipmentRepair.getEquipmentrepairId());
		//获取历史下个节点
		String epAcNextid=tequipmentRepair.getEpAcNextid();
		//获取字典配置的流程枚举值
		//主管派单
		String leaderDispatch=DictUtils.getDictValue("主管派单 ", "acti_status_id", "");
		//维修人员确认
		String repair=DictUtils.getDictValue("维修中（维修确认）", "acti_status_id", "");
		//流程发起人确认
		String confirm=DictUtils.getDictValue("发起人确认", "acti_status_id", "");
		//节点结束
		String end=DictUtils.getDictValue("节点结束", "acti_status_id", "");

		if(!("").equals(epAcNextid)&&(epAcNextid).equals(leaderDispatch)){
			//2.主管派单
			//故障类别
			tequipmentRepair.setEpReKind(equipmentRepair.getEpReKind());
			//维修责任人
			tequipmentRepair.setEpReId(equipmentRepair.getEpReId());
			//更新人		
			tequipmentRepair.setLastUpdatedBy(tUser.getUserId());
			//更新时间		
			tequipmentRepair.setLastUpdatedDate(PubFun.getCurrentDate());
			//流程状态	
			tequipmentRepair.setEpAcStatus("1");
			//当前节点
			tequipmentRepair.setEpAcNowid(leaderDispatch);
			//当前负责人	
			tequipmentRepair.setEpAcNowuser(tUser.getUserId());
			//下个节点
			tequipmentRepair.setEpAcNextid(repair);
			//下个节点负责人	
			tequipmentRepair.setEpAcNextuser(equipmentRepair.getEpReId());

			//WL 2019-02-27  通知维修员
			sendNoticeByUserId(tequipmentRepair);


		}else if(!("").equals(epAcNextid)&&(epAcNextid).equals(repair)){
			//3.维修人员确认	
			//维修情况说明
			tequipmentRepair.setEpReDescribe(equipmentRepair.getEpReDescribe());
			//维修完成时间
			tequipmentRepair.setEpReTime(equipmentRepair.getEpReTime());

			//更新人		
			tequipmentRepair.setLastUpdatedBy(tUser.getUserId());
			//更新时间		
			tequipmentRepair.setLastUpdatedDate(PubFun.getCurrentDate());
			//流程状态	
			tequipmentRepair.setEpAcStatus("1");
			//当前节点
			tequipmentRepair.setEpAcNowid(repair);
			//当前负责人	
			tequipmentRepair.setEpAcNowuser(tUser.getUserId());
			//下个节点
			tequipmentRepair.setEpAcNextid(confirm);
			//下个节点负责人	(由流程发起人去确认)
			if(StringUtils.equals("00000",tequipmentRepair.getUserId())){//如果发起人是00000（niagara)则会把确认流程转给设备负责人
				tequipmentRepair.setEpAcNextuser(epUserMapper.getUserIdByEpId(tequipmentRepair.getEpId()));
			}else {
				tequipmentRepair.setEpAcNextuser(tequipmentRepair.getUserId());
			}
			//WL 2019-02-27    通知发起人
			sendNoticeByUserId(tequipmentRepair);

		}else if(!("").equals(epAcNextid)&&(epAcNextid).equals(confirm)){
			//4.流程发起人确认

			tequipmentRepair.setEpReStatus(equipmentRepair.getEpReStatus());
			//更新人
			tequipmentRepair.setLastUpdatedBy(tUser.getUserId());
			//更新时间
			tequipmentRepair.setLastUpdatedDate(PubFun.getCurrentDate());

			//规定保养日期
			tequipmentRepair.setMaintenanceDate((equipmentRepair.getMaintenanceDate()).substring(0,10));
			//当前日期距离保养日期相差多少天（未到显示n天/超时显示-n天）
			tequipmentRepair.setDistanceDays(equipmentRepair.getDistanceDays());

			//设备状态
			String status=equipmentRepair.getEpReStatus();
			if(status!=null&&!("").equals(status)){

				if(("1").equals(status)){
					//设备状态正常

					//流程状态	
					tequipmentRepair.setEpAcStatus("2");//2.流转完成
					//当前节点
					tequipmentRepair.setEpAcNowid(confirm);
					//当前负责人	
					tequipmentRepair.setEpAcNowuser(tUser.getUserId());
					//下个节点
					tequipmentRepair.setEpAcNextid(end);
					//下个节点负责人	(由流程发起人去确认)
					tequipmentRepair.setEpAcNextuser("000000");		//默认置6个0				

					//WL 2019-02-27   000000维修完成，无通知
					sendNoticeByUserId(tequipmentRepair);

					//设备状态正常  并且  距离保养日期3天内 或者超出保养日期  进行保养任务
					if(Integer.parseInt(equipmentRepairT.getDistanceDays())<=3){
						//保养单提交
						String maintainId = "MA" + UUID.randomUUID().toString().replace("-", "");
						ElectricalMaintain tElectricalMaintain=new ElectricalMaintain();
						tElectricalMaintain.setMaintainId(maintainId);
						//巡检记录id
						tElectricalMaintain.setChecksId(equipmentRepairT.getEquipmentrepairId());
						//保养人员id
						tElectricalMaintain.setUserId(equipmentRepairT.getUserId());
						//保养情况描述
						tElectricalMaintain.setMaintainDescribe(equipmentRepairT.getMaintainDescribe());
						//保养完成时间
						tElectricalMaintain.setMaintainTime(equipmentRepairT.getMaintainTime());
						//设备清洁、无油垢、灰尘
						tElectricalMaintain.setClear(equipmentRepairT.getClear());
						//电气线路有无损坏
						tElectricalMaintain.setLine(equipmentRepairT.getLine());
						//加机油
						tElectricalMaintain.setOil(equipmentRepairT.getOil());
						//创建人
						tElectricalMaintain.setCreatedBy(tUser.getUserId());
						//创建时间
						tElectricalMaintain.setCreationDate(PubFun.getCurrentDate());
						//设备id
						tElectricalMaintain.setEpId(equipmentRepairT.getEpId());
						//数据来源类型
						tElectricalMaintain.setReType("2");//1.巡检单   2保修单发起人确认
						//是否已经完成
						tElectricalMaintain.setCompleteOrNot("Y");//已经完成
						if(electricalMaintainMapper.insert(tElectricalMaintain)<1){
							return false;
						}	

						//更新设备保养日期

						tEquipment= equipmentMapper.load(equipmentRepairT.getEpId());

						tEquipment.setLastUpdatedBy(tUser.getUserId());
						tEquipment.setLastUpdatedDate(PubFun.getCurrentDate());

						String maintainTime=equipmentRepairT.getMaintainTime();
						tEquipment.setMaintenanceDate(PubFun.subMonth(maintainTime));
						if( equipmentMapper.update(tEquipment)<1){
							return false;
						}							
					}
				}else{
					//设备状态异常
					//流程状态	
					tequipmentRepair.setEpAcStatus("1");//流转中
					//当前节点
					tequipmentRepair.setEpAcNowid(confirm);
					//当前负责人	
					tequipmentRepair.setEpAcNowuser(tUser.getUserId());
					//下个节点
					tequipmentRepair.setEpAcNextid(leaderDispatch);
					//下个节点负责人即维修部门的主管
					User LeaderUser=new User();
					LeaderUser=userMapper.selectForLeaderByType("repair");
					System.out.println("-------------LeaderUser:"+LeaderUser);
					tequipmentRepair.setEpAcNextuser(LeaderUser.getUserId());	
					
					//WL 2019-02-27   维修未完成，通知主管
					sendNoticeByUserId(tequipmentRepair);
				}
			}else{
				return false;
			}
			//发起人确认需要更新设备状态和设备状态历史表 设备状态表记录一条设备状态
			//1.根据设备id查询状态表获取数据更新
			Status tStatus = statusMapper.getStatusByEqId(tequipmentRepair.getEpId());
			tStatus.setStatusVal(equipmentRepair.getEpReStatus());
			tStatus.setReType("4");//维修确认 数据类型
			tStatus.setReTypeid(tequipmentRepair.getEquipmentrepairId());//报修单id 数据来源
			tStatus.setLastUpdatedBy(tUser.getUserId());
			tStatus.setLastUpdatedDate(PubFun.getCurrentDate());
			sCount=statusMapper.update(tStatus);
			//2.在历史表中新增一条数据
			String hstatusId ="HST"+UUID.randomUUID().toString().replace("-", "");
			Hstatus hstatus=new Hstatus();
			BeanUtils.copyProperties(tStatus, hstatus);
			hstatus.setHstatusId(hstatusId);
			hstatus.setCreationDate(PubFun.getCurrentDate());
			hsCount=hstatusMapper.insert(hstatus);
			if(sCount==0||hsCount==0){
				return false;
			}	
		}else if(!("").equals(epAcNextid)&&(epAcNextid).equals(end)){
			//流程节点是5，维修结束
		}else{
			return false;
		}
		//更新设备报修单
		int eqCount=equipmentRepairMapper.update(tequipmentRepair);
		//备份历史记录表中
		HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();
		BeanUtils.copyProperties(tequipmentRepair, tHsequipmentRepair);
		String hsEquipmentrepairId = "HOW"+UUID.randomUUID().toString().replace("-", "");
		tHsequipmentRepair.setHsEquipmentrepairId(hsEquipmentrepairId);
		int heqCount=hsequipmentRepairMapper.insert(tHsequipmentRepair);
		if(eqCount>0&&heqCount>0){
			return true;
		}
		return false;
	}



	@Override
	public List<EquipmentRepair> load(String userId) {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.queryByUserId(userId);
	}

	@Override
	public List<EquipmentRepair> selectByStatus(String userId,String epAcStatus) {
		Map<String, String> map=new HashMap<String, String>();	
		map.put("userId", userId);
		map.put("epAcStatus", epAcStatus);
		return equipmentRepairMapper.queryByStatus(map);
	}

	@Override
	public EquipmentRepair selectById(String equipmentrepairId) {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.queryById(equipmentrepairId);
	}

	@Override
	public List findRepairTList(EquipmentRepair equipmentRepair) {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.findList(equipmentRepair);
	}

	@Override
	public EquipmentRepairT qryById(String equipmentrepairId) {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.qryById(equipmentrepairId);
	}

	@Override
	public List findOwnerStatusTList(EquipmentRepair equipmentRepair) {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.findOwnerList(equipmentRepair);
	}

	@Override
	public int findOwnerCount(Map equipmentRepair) {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.findOwnerCount(equipmentRepair);
	}
	@Override
	public EquipmentRepairO qryOwnerById(String equipmentrepairId) {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.qryOwnerById(equipmentrepairId);
	}

	@Override
	public boolean submitOwnerToDo(EquipmentRepair equipmentRepair, User tUser) {
		//历史数据
		System.out.println("-------------------equipmentRepair:"+equipmentRepair.getEquipmentrepairId());
		EquipmentRepair tequipmentRepair = equipmentRepairMapper.queryById(equipmentRepair.getEquipmentrepairId());
		//获取历史下个节点
		System.out.println("----------------submitOwnerToDo:"+tequipmentRepair.getEpHomeEquname());
		String epAcNextid=tequipmentRepair.getEpAcNextid();
		//获取字典配置的流程枚举值
		//主管派单
		String leaderDispatch=DictUtils.getDictValue("主管派单 ", "acti_status_id", "");
		//维修人员确认
		String repair=DictUtils.getDictValue("维修中（维修确认）", "acti_status_id", "");
		//流程发起人确认
		String confirm=DictUtils.getDictValue("发起人确认", "acti_status_id", "");
		//节点结束
		String end=DictUtils.getDictValue("节点结束", "acti_status_id", "");

		if(!("").equals(epAcNextid)&&(epAcNextid).equals(leaderDispatch)){
			//2.主管派单
			//故障类别
			tequipmentRepair.setEpReKind(equipmentRepair.getEpReKind());
			//维修责任人
			tequipmentRepair.setEpReId(equipmentRepair.getEpReId());
			//更新人		
			tequipmentRepair.setLastUpdatedBy(tUser.getUserId());
			//更新时间		
			tequipmentRepair.setLastUpdatedDate(PubFun.getCurrentDate());
			//流程状态	
			tequipmentRepair.setEpAcStatus("1");
			//当前节点
			tequipmentRepair.setEpAcNowid(leaderDispatch);
			//当前负责人	
			tequipmentRepair.setEpAcNowuser(tUser.getUserId());
			//下个节点
			tequipmentRepair.setEpAcNextid(repair);
			//下个节点负责人	
			tequipmentRepair.setEpAcNextuser(equipmentRepair.getEpReId());

			//WL 2019-02-27   通知维修员
			sendNoticeByUserId(tequipmentRepair);

		}else if(!("").equals(epAcNextid)&&(epAcNextid).equals(repair)){
			//3.维修人员确认	
			//维修情况说明
			tequipmentRepair.setEpReDescribe(equipmentRepair.getEpReDescribe());
			//维修完成时间
			tequipmentRepair.setEpReTime(equipmentRepair.getEpReTime());

			//更新人		
			tequipmentRepair.setLastUpdatedBy(tUser.getUserId());
			//更新时间		
			tequipmentRepair.setLastUpdatedDate(PubFun.getCurrentDate());
			//流程状态	
			tequipmentRepair.setEpAcStatus("1");
			//当前节点
			tequipmentRepair.setEpAcNowid(repair);
			//当前负责人	
			tequipmentRepair.setEpAcNowuser(tUser.getUserId());
			//下个节点
			tequipmentRepair.setEpAcNextid(confirm);
			//下个节点负责人	(由流程发起人去确认)
			tequipmentRepair.setEpAcNextuser(tequipmentRepair.getUserId());

			//WL 2019-02-27   通知业主
			sendNoticeByUserId(tequipmentRepair);
		}else if(!("").equals(epAcNextid)&&(epAcNextid).equals(confirm)){
			//流程发起人确认

			tequipmentRepair.setEpReStatus(equipmentRepair.getEpReStatus());
			//更新人		
			tequipmentRepair.setLastUpdatedBy(tUser.getUserId());
			//更新时间		
			tequipmentRepair.setLastUpdatedDate(PubFun.getCurrentDate());			

			//设备状态
			String status=equipmentRepair.getEpReStatus();
			if(status!=null&&!("").equals(status)){

				if(("1").equals(status)){
					//设备状态正常

					//流程状态	
					tequipmentRepair.setEpAcStatus("2");//2.流转完成
					System.out.println("----------sub:"+tequipmentRepair.getEpAcStatus());
					//当前节点
					tequipmentRepair.setEpAcNowid(confirm);
					//当前负责人	
					tequipmentRepair.setEpAcNowuser(tUser.getUserId());
					//下个节点
					tequipmentRepair.setEpAcNextid(end);
					//下个节点负责人	
					tequipmentRepair.setEpAcNextuser("000000");		//默认置6个0			

					//WL 2019-02-27   000000无通知
					sendNoticeByUserId(tequipmentRepair);
				}else{
					//设备状态异常
					//流程状态	
					tequipmentRepair.setEpAcStatus("1");//流转中
					//当前节点
					tequipmentRepair.setEpAcNowid(confirm);
					//当前负责人	
					tequipmentRepair.setEpAcNowuser(tUser.getUserId());
					//下个节点
					tequipmentRepair.setEpAcNextid(leaderDispatch);
					//下个节点负责人即维修部门的主管
					User LeaderUser=new User();
					LeaderUser=userMapper.selectOwnerForLeaderByType("ownerRepair");
					System.out.println("-------------LeaderUser:"+LeaderUser);
					tequipmentRepair.setEpAcNextuser(LeaderUser.getUserId());				

					//WL 2019-02-27      通知主管继续派单
					sendNoticeByUserId(tequipmentRepair);
				}
			}else{
				return false;
			}

		}else if(!("").equals(epAcNextid)&&(epAcNextid).equals(end)){
			//流程节点是5，维修结束
		}else{
			return false;
		}
		//更新设备报修单
		int eqCount=equipmentRepairMapper.update(tequipmentRepair);
		//备份历史记录表中
		HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();
		BeanUtils.copyProperties(tequipmentRepair, tHsequipmentRepair);
		String hsEquipmentrepairId = "HOW"+UUID.randomUUID().toString().replace("-", "");
		tHsequipmentRepair.setHsEquipmentrepairId(hsEquipmentrepairId);
		int heqCount=hsequipmentRepairMapper.insert(tHsequipmentRepair);
		if(eqCount>0&&heqCount>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean rpAdd(EquipmentRepair equipmentRepair, User tUser) {
		EquipmentRepair tequipmentRepair = equipmentRepairMapper.queryById(equipmentRepair.getEquipmentrepairId());
		//维修情况说明
		tequipmentRepair.setEpReDescribe(equipmentRepair.getEpReDescribe());
		//更新人		
		tequipmentRepair.setLastUpdatedBy(tUser.getUserId());
		//更新时间		
		tequipmentRepair.setLastUpdatedDate(PubFun.getCurrentDate());

		//更新设备报修单
		int eqCount=equipmentRepairMapper.update(tequipmentRepair);
		if(eqCount>0){
			return true;
		}
		return false;
	}


	@Override
	public Json isCanSubmit(String epId){

		Json tJson=new Json(true, "可以进行设备报修！", null);
		//根据设备id查询
		//1.设备状态正常的时候是不可以报修
		Status tStatus = statusMapper.getStatusByEqId(epId);
		if(tStatus!=null){
			//设备状态正常
			if(("1").equals(tStatus.getStatusVal())){
				tJson.setSuccess(false);
				tJson.setMsg("设备状态正常，不允许进行设备报修！");
			}
		}else{
			tJson.setSuccess(false);
			tJson.setMsg("设备状态数据不存在，不允许进行设备报修！");
		}

		//2.在维修中的设备是不可以再次提交报修
		List <EquipmentRepair> list=new ArrayList<EquipmentRepair>();
		list=equipmentRepairMapper.queryByEpId(epId);
		if(list.size()>0){
			tJson.setSuccess(false);
			tJson.setMsg("设备已经存在报修单，不可重复报修！");	
		}
		return tJson;
	}

	@Override
	public List<MonthReCounts> qryCountsByMonth() {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.qryCountsByMonth();
	}

	@Override
	public boolean ownerSubmit(EquipmentRepair equipmentRepair, User tUser) {
		//保存报修单记录
		//页面无法获取的参数
		equipmentRepair.setEpType(tUser.getType());
		equipmentRepair.setEpReStatus("");
		equipmentRepair.setCreatedBy(tUser.getUserId());
		equipmentRepair.setCreationDate(PubFun.getCurrentDate());

		equipmentRepair.setEpAcStatus("1");//流转中 流程状态
		equipmentRepair.setEpAcNowid("1");//1.报修申请 当前节点
		equipmentRepair.setEpAcNowuser(tUser.getUserId());

		equipmentRepair.setEpAcNextid("2");//主管派单
		//下个节点负责人即维修部门的主管
		User LeaderUser=new User();
		LeaderUser=userMapper.selectOwnerForLeaderByType("ownerRepair");
		System.out.println("-------------LeaderUser:"+LeaderUser);
		equipmentRepair.setEpAcNextuser(LeaderUser.getUserId());
		//				int eqCount=equipmentRepairMapper.insert(equipmentRepair);
		//备份历史记录表中
		HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();
		BeanUtils.copyProperties(equipmentRepair, tHsequipmentRepair);
		String hsEquipmentrepairId =null;
		if(tUser.getType().equals("1")){
			hsEquipmentrepairId="HER"+UUID.randomUUID().toString().replace("-", "");
		}
		if(tUser.getType().equals("2")){
			hsEquipmentrepairId="HOW"+UUID.randomUUID().toString().replace("-", "");
		}
		tHsequipmentRepair.setHsEquipmentrepairId(hsEquipmentrepairId);
		//				int heqCount=hsequipmentRepairMapper.insert(tHsequipmentRepair);

		if((equipmentRepairMapper.insert(equipmentRepair))>0&&(hsequipmentRepairMapper.insert(tHsequipmentRepair))>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean ownerRpAdd(EquipmentRepair equipmentRepair, User tUser) {

		EquipmentRepair tequipmentRepair = equipmentRepairMapper.queryById(equipmentRepair.getEquipmentrepairId());
		//维修情况说明
		tequipmentRepair.setEpReDescribe(equipmentRepair.getEpReDescribe());
		//更新人		
		tequipmentRepair.setLastUpdatedBy(tUser.getUserId());
		//更新时间		
		tequipmentRepair.setLastUpdatedDate(PubFun.getCurrentDate());

		//更新设备报修单
		int eqCount=equipmentRepairMapper.update(tequipmentRepair);
		if(eqCount>0){
			return true;
		}
		return false;

	}



	@Override
	public List<StaticJspValue> qryEpTypeValue() {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.qryEpTypeValue();
	}

	@Override
	public List<CksDayReCounts> qryCountByDate() {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.qryCountByDate();
	}


	@Override
	public RepairCounts qryCountByWeek() {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.qryCountByWeek();
	}
	@Override
	public void sendNoticeByUserId(EquipmentRepair ep){
		
		//判断00000 和00000
        System.out.println("++++++++++++++++++++++++++++++++++++开始执行微信推送+++++++++++++++++++++++++++++++");
		//AcNextuser=000000 表示流程结束，不通知
		if(StringUtils.equals("000000", ep.getEpAcNextuser())){
			//流程结束通知
			System.out.println("已处理完成，无微信通知");
		}else{
			User tuser;
			//当EpAcNextuser=00000，寻找设备负责人
			if(StringUtils.equals("00000", ep.getEpAcNextuser())){//
				//00000代表niagara系统告警发出的巡检
				//根据维修单的设备区域和设备类型，查找epuser表中的userid，获取user。
				String Nextuser = new String();
				String equipmentid=new String(ep.getEpId());
				Equipment equipment= new Equipment();
				try {
					//根据设备id获取设备
					equipment=equipmentMapper.load(equipmentid);
					//获取区域Loc
					String loc=equipment.getEpLoc();
					//获取设备类型
					String ep_type=equipment.getEpType();
					//查找该区域该类型设备负责人
					Nextuser = epUserMapper.getUserIdByLocAndType(loc, ep_type);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tuser = userMapper.load(Nextuser);
				
			}else{
				tuser = userMapper.load(ep.getEpAcNextuser());
			}
			
			String WX_wx_open_id=tuser.getWxOpenId();
			System.out.println(WX_wx_open_id);
			String ep_id = null;
			String ep_name = null;
			//使用org.apache.commons.lang包下的StringUtils.equals(String str1, String str2)方法避免null
			//或者使用("1").equals(ep.getEpType())，已知的值放前面
			if(StringUtils.equals("1", ep.getEpType())){ //设备类型为1，巡检报修
				ep_id = ep.getEpId();
			}else if(StringUtils.equals("2", ep.getEpType())){ //设备类型为2，业主报修
				ep_name = ep.getEpHomeEquname();
				if(ep.getEpHomeEqutype().equals("private_epName"))
				{
					ep_name=DictUtils.getDictLabel(ep_name,"private_epName", ep_name);
				}
				if(ep.getEpHomeEqutype().equals("public_epName"))
				{
					ep_name=DictUtils.getDictLabel(ep_name,"public_epName", ep_name);
				}
			}		

			if(StringUtils.equals(ep_id, null)||StringUtils.equals(ep_id, "")){ //ep_id为空，使用epname
				ep_id=ep_name;
			}
			System.out.println("------------sendNoticeByUserId:"+WX_wx_open_id);
			System.out.println("------------sendNoticeByUserId:"+ep_id);
			System.out.println("------------sendNoticeByUserId:"+ep_name);
			System.out.println("------------sendNoticeByUserId:"+ep.getEpAcNowid());

			Create_Json.sendAlarmNotice(WX_wx_open_id, ep_id,ep_name, ep.getEpAcNowid());
		}
	}

	@Override
	public List<EquipmentRepairT> qryByWeek() {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.qryByWeek();
	}

	@Override
	public OwnerStaticJspValue selectForOwnerStatic() {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.selectForOwnerStatic();
	}

	@Override
	public List<OwnerTypeCounts> qryCountsByepName() {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.qryCountsByepName();
	}

	@Override
	public List<EquipmentRepair> findByEpId(String epId) {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.findByEpId(epId);
	}
	@Override
	public boolean insertSubmitByAPM(NiagaraJson tNiagaraJson) {
		String epId="";
		String source=tNiagaraJson.getSource();//苏州市相城区变电厂_1号厂房第27间_053号电表_电度
		if(source!=null&&!("").equals(source)){
			epId=source.substring(PubFun.getCharacterPosition(source, "_", 2)+1, PubFun.getCharacterPosition(source, "_", 3));
			epId="DAAN-006";
			//如果是恢复正常消息，系统不做处理
			if(tNiagaraJson.getNormalTime()!=null&&!("").equals(tNiagaraJson.getNormalTime())){
				//恢复正常
				return true;
			}else{
				//查询该设备是否在报修中
				List <EquipmentRepair> list=new ArrayList<EquipmentRepair>();
				list=equipmentRepairMapper.queryByEpId(epId);
				if(list==null||list.isEmpty()){
					//2.设备不在报修中，生成报修单  保存报修单记录
					EquipmentRepair equipmentRepair=new EquipmentRepair();
					equipmentRepair.setEquipmentrepairId("HER"+UUID.randomUUID().toString().replace("-", ""));
					equipmentRepair.setEpId(epId);
					equipmentRepair.setEpType("1");
					//报修人
					User user=equipmentMapper.qryEpUser(epId);
					if(user!=null&&user.getUserId()!=null&&!(user.getUserId()).equals("")){
						equipmentRepair.setUserId(user.getUserId());
					}else{
						return false;
					}
					//1.更新设备状态为异常
					Status tStatus = statusMapper.getStatusByEqId(epId);
					tStatus.setStatusVal("2");
					tStatus.setLastUpdatedBy(user.getUserId());
					tStatus.setLastUpdatedDate(PubFun.getCurrentDate());
					Hstatus tHStatus=new Hstatus();
					BeanUtils.copyProperties(tStatus, tHStatus);
					tHStatus.setHstatusId(UUID.randomUUID().toString().replace("-", ""));
					statusMapper.update(tStatus);
					hstatusMapper.insert(tHStatus);						
					
					equipmentRepair.setEprCkTime(PubFun.getCurrentDate());
					equipmentRepair.setEprCkDescribe("异常来自APM:"+tNiagaraJson.getText());
					equipmentRepair.setCreatedBy(user.getUserId());
					equipmentRepair.setCreationDate(PubFun.getCurrentDate());			
					equipmentRepair.setEpAcStatus("1");
					equipmentRepair.setEpAcNowid("1");//1.报修申请 当前节点
					equipmentRepair.setEpAcNowuser(user.getUserId());
					equipmentRepair.setEpAcNextid("2");//主管派单
					//下个节点负责人即维修部门的主管
					User LeaderUser=new User();
					LeaderUser=userMapper.selectForLeaderByType("repair");
					System.out.println("-------------LeaderUser:"+LeaderUser);
					equipmentRepair.setEpAcNextuser(LeaderUser.getUserId());			
					
					//4.备份历史记录表中
					HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();
					BeanUtils.copyProperties(equipmentRepair, tHsequipmentRepair);
					String hsEquipmentrepairId ="HER"+UUID.randomUUID().toString().replace("-", "");
					tHsequipmentRepair.setHsEquipmentrepairId(hsEquipmentrepairId);
					if((equipmentRepairMapper.insert(equipmentRepair))>0&&(hsequipmentRepairMapper.insert(tHsequipmentRepair))>0){
						//5.通知主管任务
						sendNoticeByUserId(equipmentRepair);
						return true;
					}
				}else{
					//设备在报修中，不生成工单不处理
					//添加描述
					User user=equipmentMapper.qryEpUser(epId);
					EquipmentRepair equipmentRepair=list.get(0);
					String describe=equipmentRepair.getEprCkDescribe()+";异常来自APM:"+tNiagaraJson.getText();
					equipmentRepair.setEprCkDescribe(describe);
					equipmentRepair.setLastUpdatedBy(user.getUserId());
					equipmentRepair.setLastUpdatedDate(user.getUserId());
					if((equipmentRepairMapper.update(equipmentRepair))>0){
						//5.通知主管任务
						sendNoticeByUserId(equipmentRepair);
						return true;
					}					
					
					
					return true;
				}
	 }
	}else{
			return false;
	}
		return true;
}
	
	/*
	 * WL
	 * 系统告警信息  1、存入alarm表  2、传入报修流程
	 */
	@Override
	public boolean insertSubmitByMQTT(String messageStr) {
		JSONObject obj = JSONObject.fromObject(messageStr);
		String uuid=obj.getString("uuid").replace("-", "");
		//判断uuid是否已经存在    ------此段代码已废弃，放在MqttConnection中判断。
//		if(alarmMapper.determineAlarmId(uuid)!=0){
//			System.out.println("-----已经存在该alarm-----");
//			return false;
//		}
		String source=obj.getString("source");
		String[] sourceArr = source.split("_");
		String epId =sourceArr[2];		                              //设备编号
		String text = obj.getString("text");  				 //故障情况描述
		String sourceState = obj.getString("sourceState");   //设备状态（Normal正常/Offnormal异常/fault断开状态）
		String ackState = obj.getString("ackState"); 		 //告警确认 acked/Unacked
		String lowLimit = obj.getString("lowLimit");		 //下限
		String highLimit = obj.getString("highLimit");		 //上限
		String alarmClass = obj.getString("alarmClass");	 //告警类别：如HighPriorityAlarms（上限告警）
		String priority = obj.getString("priority");		 //告警等级（1-255） 1最大
		String value = obj.getString("value"); //设备实时读数															
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String normalTime =obj.getString("normalTime"); //恢复正常时间
		String timestamp =obj.getString("timestamp");   //异常告警出现时间（用在alarm表） --同一设备只要没修好、并持续发出同一类别告警时，这个时间是不变的（如：温度持续过高的告警，断连重发，但timestamp不变）
		String creationDate =df.format(System.currentTimeMillis());//告警传入OMMS的时间（用在EquipmentRepair表）
		//将数据放入equipmentRepair和Alarm类中
        if(!StringUtils.equals(normalTime,"")){//如果是恢复正常告警
            uuid="NM"+uuid;
        }

		//放入equipmentRepair

			EquipmentRepair equipmentRepair=new EquipmentRepair();
			equipmentRepair.setEquipmentrepairId(uuid);                 //报修单id
			equipmentRepair.setEpId(epId);                         //设备编号
			equipmentRepair.setEpType("1");                         //报修类别
			equipmentRepair.setEprCkTime(creationDate);             //报修时间
			equipmentRepair.setEprCkDescribe(text);                 //故障情况描述
			//下个节点负责人即维修部门的主管
			User LeaderUser = userMapper.selectForLeaderByType("repair");
			equipmentRepair.setEpAcNextuser(LeaderUser.getUserId());
			//无法获取的参数
			equipmentRepair.setEpType("1");                             //--设备类型
			equipmentRepair.setEpReStatus("");                         //设备状态
			equipmentRepair.setCreatedBy("00000");                     //--创建人  00000五个0代表用户为Niagara系统
			equipmentRepair.setUserId("00000");                         //--报修人
			equipmentRepair.setCreationDate(PubFun.getCurrentDate());//报修单创建时间
			equipmentRepair.setEpAcStatus("1");                         //流转中 流程状态
			equipmentRepair.setEpAcNowid("1");                         //当前节点id  1.报修申请
			equipmentRepair.setEpAcNowuser("00000");                 //当前节点用户id
			equipmentRepair.setEpAcNextid("2");                         // 下一节点id  2：主管派单
		//放入alarm类中
		//包装一个alarm类
		Alarm alarm=new Alarm();
		
		alarm.setAlarmId(uuid);				  //告警id
		alarm.setSource(source);			  //数据源id
		alarm.setTimestamp(timestamp);		  //告警时间
		alarm.setSourceState(sourceState);    //设备状态（Normal正常/Offnormal异常/fault断开状态）
		alarm.setAckState(ackState);		  //告警确认 acked/Unacked
		alarm.setAlarmClass(alarmClass);      //告警类别：如HighPriorityAlarms（上限告警）
		alarm.setAlarmText(text);		      //故障情况描述
		alarm.setPriority(priority);		  //告警等级
		alarm.setHighLimit(highLimit);		  //上限
		alarm.setLowLimit(lowLimit);		  //下限
		alarm.setValue(value); 		  		  //实时读数
		alarm.setNormalTime(normalTime);	  //恢复正常时间
		alarm.setNatureMessage(messageStr);   //原始数据
		alarm.setCreationDate(creationDate);  //创建时间
		alarm.setType("NI");				  //数据来源
		alarm.setNatureMessage(messageStr);   //原始数据
		
		//判断是不是正确的告警信息  （默认消息  当告警为255，是一条假数据：source":"AbstractMqttDriverNetwork AbstractMqttDriverDevice"
		if(StringUtils.equals(priority, "255")){
			return false;
		}else{
			alarmMapper.insert(alarm);
		}


		//WL 2019-04-08
		//Step2 判断是恢复正常提醒还是异常告警提醒
		if(sourceState.equals("Normal")){
			System.out.println("恢复正常提醒");
			return true;
		}else{
			Map<String, String> map=new HashMap<String, String>();
			map.put("epId", epId);
			map.put("epAcStatus", "1");
			EquipmentRepair equipmentRepairT=equipmentRepairMapper.queryByStatusAndEpid(map);
			//Step3 判断告警点所属设备是否正在维修中
			if(equipmentRepairT==null) { //无正在流转工单
				System.out.println("14141equipmentRepair = null 无正在流转工单");


				//更新设备状态表
				//开始获取状态信息
				Status tstatus = statusMapper.getStatusByEqId(epId);
				if(tstatus==null){
					if(log.isTraceEnabled()){
						log.trace("status表中无该设备信息");
					}
					System.out.println("status表中无该设备信息");
				}
				System.out.println("epid:"+epId);
//                System.out.println("epid:"+status.getEpId());
				tstatus.getStatusId();
                tstatus.setLastUpdatedBy("00000");
				tstatus.setStatusVal("2");      //设置状态为异常
				tstatus.setLastUpdatedDate(PubFun.getCurrentDate());


				//复制到状态历史表
				Hstatus hstatus=new Hstatus();
				String hstatusId ="HST"+UUID.randomUUID().toString().replace("-", "");
				hstatus.setHstatusId(hstatusId);
				BeanUtils.copyProperties(tstatus, hstatus);

				try {
					int a=statusMapper.update(tstatus);
					int b=hstatusMapper.insert(hstatus);
					System.out.println(a);
				} catch (Exception e) {
//				json.setSuccess(false);
//				json.setMsg("设备状态修改失败！");
					e.printStackTrace();
					return false;
				}
				//结束更新状态表

				//备份历史记录表中
				HsequipmentRepair tHsequipmentRepair=new HsequipmentRepair();
				BeanUtils.copyProperties(equipmentRepair, tHsequipmentRepair);
				String hsEquipmentrepairId =null;
				hsEquipmentrepairId="HOW"+UUID.randomUUID().toString().replace("-", "");
				tHsequipmentRepair.setHsEquipmentrepairId(hsEquipmentrepairId);

				if((equipmentRepairMapper.insert(equipmentRepair))>0&&(hsequipmentRepairMapper.insert(tHsequipmentRepair))>0){
					sendNoticeByUserId(equipmentRepair);
					System.out.println("niagara to wechat");

					return true;
				}

				return false;
			}else{  //有正在流转工单
				System.out.println("14141equipmentRepair != null 有正在流转工单");
				Map<String, String> mapDesc=new HashMap<String, String>();
				mapDesc.put("epId", epId);
				mapDesc.put("eprCkDescribe", "【Niagara Append:"+text+";Time:"+timestamp+"】");
				if(equipmentRepairMapper.updateDescribe(mapDesc)>0){
					System.out.println("niagara Append success");
				}
				return true;
			}

		}
	}
	//根据epid查询是否在维修进程中
	public EquipmentRepair queryByStatusAndEpid(String epId){
		Map<String, String> map=new HashMap<String, String>();
		map.put("epId", epId);
		map.put("epAcStatus", "1");
		EquipmentRepair equipmentRepair=equipmentRepairMapper.queryByStatusAndEpid(map);
		return equipmentRepair;
	}
	
	//接口所需的数据--zdy
	
	public List<EquipmentRepair> getAllEquipmentRepairs(){
		
		return equipmentRepairMapper.getAllEquipmentRepairs();
		
	}

	@Override
	public List findALLRepairTList() {
		return equipmentRepairMapper.findAllList();
	}

	@Override
	public PerscentCounts getNums() {
		return equipmentRepairMapper.getNums();
	}

	@Override
	public DaanCount getDaanCount() {
		// TODO Auto-generated method stub
		return equipmentRepairMapper.getDaanCount();
	}






}
