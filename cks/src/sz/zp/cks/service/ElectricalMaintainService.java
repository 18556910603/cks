package sz.zp.cks.service;	

import java.util.List;

import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.ElectricalMaintain;
import sz.zp.cks.model.ElectricalCheckT;
import sz.zp.cks.model.ElectricalMaintainT;

public interface ElectricalMaintainService extends BaseService<ElectricalMaintain> {
	
	public int  insertByCK(ElectricalCheckT telectricalCheck,ElectricalCheck electricalCheck);
	
	//根据主键查询ElectricalCheckT对象
	public ElectricalMaintainT qryById(String maintainId);
	public List<ElectricalMaintain> findElectricalMaintainList(String userId,String completeOrNot);
	
	public boolean updateTask(ElectricalMaintain mElectricalMaintain)throws Exception;
	//已完成任务
	public ElectricalMaintainT qryForComplete(String maintainId);
	
	//根据类型查全部
	public List<ElectricalMaintain> findAll(String reType);
	//查询所有
	public List<ElectricalMaintain> findAllList();
}
