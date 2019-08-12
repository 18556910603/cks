package sz.zp.cks.dao;

import java.util.List;
import java.util.Map;

import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.model.CksDayReCounts;
import sz.zp.cks.model.DaanCount;
import sz.zp.cks.model.EquipmentRepairO;
import sz.zp.cks.model.EquipmentRepairT;
import sz.zp.cks.model.MainJspValue;
import sz.zp.cks.model.MonthReCounts;
import sz.zp.cks.model.OwnerStaticJspValue;
import sz.zp.cks.model.OwnerTypeCounts;
import sz.zp.cks.model.PerscentCounts;
import sz.zp.cks.model.RepairCounts;
import sz.zp.cks.model.StaticJspValue;
import sz.zp.cks.model.UserEpTypeCounts;


public interface EquipmentRepairMapper extends BaseMapper<EquipmentRepair> {
	
	public  List <EquipmentRepair> queryByUserId(String userId);
	
	public  List <EquipmentRepair> queryByStatus(Map<String,String> map);

	public EquipmentRepair queryByStatusAndEpid(Map<String,String> map);

	public  EquipmentRepair queryById(String equipmentrepairId);
	
	public  EquipmentRepairT qryById(String equipmentrepairId);
	
	public  List<EquipmentRepair> findOwnerList(EquipmentRepair entity);

	public  EquipmentRepairO qryOwnerById(String equipmentrepairId);


	public int findOwnerCount(Map equipmentRepair);

	//查询设备是否在维修
	public  List <EquipmentRepair> queryByEpId(String epId);
	
	
	public List<MonthReCounts> qryCountsByMonth();
	
	public MainJspValue selectForMainJspValue();
	//各巡检人员设备分布
	public List<UserEpTypeCounts> qryUserEpTypeCount();
	
	
	
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
	
	//根据设备编号查询其维修记录
	public  List <EquipmentRepair> findByEpId(String epId);
	
	
	//接口所需的数据--zdy
	
	public List<EquipmentRepair> getAllEquipmentRepairs();

	//更新故障情况说明
	public int updateDescribe(Map<String,String> map);
	
	
	public List<EquipmentRepairT> findAllList();	
	public PerscentCounts getNums();
	public  DaanCount  getDaanCount();
}