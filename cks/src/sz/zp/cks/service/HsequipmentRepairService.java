package sz.zp.cks.service;

import java.util.List;

import sz.zp.cks.entity.HsequipmentRepair;
import sz.zp.cks.model.HsequipmentRepairO;
import sz.zp.cks.model.HsequipmentRepairT;

public interface HsequipmentRepairService extends BaseService<HsequipmentRepair> {
	
	
	
	public List findStatusTList(HsequipmentRepair hsequipmentRepair);
	public List findStatusOList(HsequipmentRepair hsequipmentRepair);
	
	//根据主键查询EquipmentRepairT对象
	public HsequipmentRepairT qryById(String hsEquipmentrepairId);
	public HsequipmentRepairO qryOById(String hsEquipmentrepairId);
	public List qryHsAll();
	
}
