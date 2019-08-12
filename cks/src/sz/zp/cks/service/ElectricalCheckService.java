package sz.zp.cks.service;	

import java.util.List;

import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.ElectricalCheckT;
import sz.zp.cks.model.EquipmentT;

public interface ElectricalCheckService extends BaseService<ElectricalCheck> {
	
	public EquipmentT queryById(String epId);
	
	public boolean updateStatus(ElectricalCheck electricalCheck,User tUser);
	
	//历史巡检单查询
	public List<ElectricalCheck> findElectricalCheckList(String userId);
	//根据主键查询ElectricalCheckT对象
	public ElectricalCheckT qryById(String checksId);
	
	public List <EquipmentRepair> isRepair(String epId);

	//zdy
	public List<ElectricalCheck> findList();

	
	//历史巡检单查询
	public List<ElectricalCheck> findALLElectricalCheckList();	

}
