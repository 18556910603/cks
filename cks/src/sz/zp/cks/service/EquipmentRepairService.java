package sz.zp.cks.service;

import java.util.List;
import java.util.Map;

import sz.zp.cks.common.Json;
import sz.zp.cks.entity.EquipmentRepair;
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

public interface EquipmentRepairService extends BaseService<EquipmentRepair> {
	 
	public boolean  insertSubmit(EquipmentRepair equipmentRepair,User tUser);
	public boolean  ownerSubmit(EquipmentRepair equipmentRepair,User tUser);
	public List<EquipmentRepair> load (String userId);
	public List<EquipmentRepair> selectByStatus (String userId,String epAcStatus);
	public EquipmentRepair selectById(String equipmentrepairId);
	//待办任务查询列表
	public List findRepairTList(EquipmentRepair equipmentRepair);
	//待办任务查询列表
	public List findALLRepairTList();	
	//根据主键查询EquipmentRepairT对象
	public EquipmentRepairT qryById(String equipmentrepairId);

	public boolean insertSubmitToDo(EquipmentRepairT equipmentRepairT, User tUser) throws Exception;
	
	//待办任务查询列表-业主块
	public List findOwnerStatusTList(EquipmentRepair equipmentRepair);
	
	//待办任务查询列总数-业主块
	public int findOwnerCount(Map equipmentRepair);
	
	//根据主键查询EquipmentRepairO对象-业主块
	public EquipmentRepairO qryOwnerById(String equipmentrepairId);
	

	//代办任务处理提交-业主块
	public boolean submitOwnerToDo(EquipmentRepair equipmentRepair, User tUser);
	//新增维修记录-业主块
	public boolean rpAdd(EquipmentRepair equipmentRepair, User tUser);

	public boolean ownerRpAdd(EquipmentRepair equipmentRepair, User tUser);
	
	//查看设备是否能够提交报修单    在维修中  异常的设备
	public Json  isCanSubmit(String epId);
	
	//查询每个月巡检和业主报修的台数
	
	public List<MonthReCounts> qryCountsByMonth();
	
	
	//查询每个设备类型今日维修的台数--zdy
	
	public List<StaticJspValue> qryEpTypeValue();
	
	
	//当日巡检数--zdy
	
	public List<CksDayReCounts> qryCountByDate();
	
	//本周维修统计--zdy
	
	public RepairCounts qryCountByWeek();
	
	//本周维修明细--zdy
	
	public List <EquipmentRepairT>  qryByWeek();
	
	//业主系统统计--zdy
	public OwnerStaticJspValue selectForOwnerStatic();
	
	//业主系统柱状图统计--zdy
	public List<OwnerTypeCounts> qryCountsByepName();
	
	//根据设备编号查询其维修记录--zdy
	public  List <EquipmentRepair> findByEpId(String epId);

	public boolean insertSubmitByMQTT(String  message);
	void sendNoticeByUserId(EquipmentRepair ep);

	//接口所需的数据--zdy
	
	public List<EquipmentRepair> getAllEquipmentRepairs();
	
	public boolean insertSubmitByAPM(NiagaraJson tNiagaraJson);
	
	
	public PerscentCounts getNums();
	
	
	public DaanCount getDaanCount();
	
}
