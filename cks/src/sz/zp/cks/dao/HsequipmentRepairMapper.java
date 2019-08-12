package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.HsequipmentRepair;
import sz.zp.cks.model.HsequipmentRepairO;
import sz.zp.cks.model.HsequipmentRepairT;

public interface HsequipmentRepairMapper extends BaseMapper<HsequipmentRepair> {

	public  HsequipmentRepairT qryById(String hsEquipmentrepairId);
	public List<HsequipmentRepairO> findOList(HsequipmentRepair hsequipmentRepair);
		 
	public  HsequipmentRepairO qryOById(String hsEquipmentrepairId);

	public List<HsequipmentRepair> qryHsAll();
	
	
}