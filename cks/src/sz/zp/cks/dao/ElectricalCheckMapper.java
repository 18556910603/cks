package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.model.ElectricalCheckT;
import sz.zp.cks.model.EquipmentT;

public interface ElectricalCheckMapper extends BaseMapper<ElectricalCheck> {
	
	
	public EquipmentT queryById(String epId);
	public List<ElectricalCheck> findListByUserId(String userId);
	public List<ElectricalCheck> findAllList();
	public  ElectricalCheckT qryById(String checksId);
	
}