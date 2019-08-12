package sz.zp.cks.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.HsequipmentRepairMapper;
import sz.zp.cks.entity.HsequipmentRepair;
import sz.zp.cks.model.EquipmentRepairT;
import sz.zp.cks.model.HsequipmentRepairO;
import sz.zp.cks.model.HsequipmentRepairT;
import sz.zp.cks.service.HsequipmentRepairService;

@Service("hsequipmentRepairService")
public class HsequipmentRepairServiceImpl implements HsequipmentRepairService {
	@Autowired
	private HsequipmentRepairMapper hsequipmentRepairMapper;


	@Override
	public int insert(HsequipmentRepair entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(HsequipmentRepair entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(HsequipmentRepair entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HsequipmentRepair select(HsequipmentRepair entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findStatusTList(HsequipmentRepair hsequipmentRepair) {
		// TODO Auto-generated method stub
		return hsequipmentRepairMapper.findList(hsequipmentRepair);
	}

	@Override
	public HsequipmentRepairT qryById(String equipmentrepairId) {
		// TODO Auto-generated method stub
		return hsequipmentRepairMapper.qryById(equipmentrepairId);
	}

	@Override
	public List findStatusOList(HsequipmentRepair hsequipmentRepair) {
		// TODO Auto-generated method stub
		return hsequipmentRepairMapper.findOList(hsequipmentRepair);
	}

	@Override
	public HsequipmentRepairO qryOById(String hsEquipmentrepairId) {
		// TODO Auto-generated method stub
		return hsequipmentRepairMapper.qryOById(hsEquipmentrepairId);
	}
	@Override
	public List qryHsAll(){
	   List list=new ArrayList();
        list=hsequipmentRepairMapper.qryHsAll();


        System.out.println("impl校验一下"+list);
	    return list;
	}

}
