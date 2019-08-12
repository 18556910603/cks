package sz.zp.cks.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.ElectricalMaintainMapper;
import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.ElectricalMaintain;
import sz.zp.cks.model.ElectricalCheckT;
import sz.zp.cks.model.ElectricalMaintainT;
import sz.zp.cks.service.ElectricalMaintainService;
import sz.zp.cks.service.IEquipmentBiz;

@Service("electricalMaintainService")
public class ElectricalMaintainServiceImpl implements ElectricalMaintainService {
	@Autowired
	private ElectricalMaintainMapper electricalMaintainMapper;
	@Autowired
	IEquipmentBiz equipmentBiz;
	@Override
	public int insert(ElectricalMaintain entity) throws Exception {
		// TODO Auto-generated method stub
		return electricalMaintainMapper.insert(entity);
	}

	@Override
	public int update(ElectricalMaintain entity) throws Exception {
		return electricalMaintainMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int delete(ElectricalMaintain entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ElectricalMaintain select(ElectricalMaintain entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertByCK(ElectricalCheckT telectricalCheck,ElectricalCheck electricalCheck) {
		String maintainId = "MA" + UUID.randomUUID().toString().replace("-", "");
		ElectricalMaintain tElectricalMaintain=new ElectricalMaintain();
		tElectricalMaintain.setMaintainId(maintainId);
		//巡检记录id
		tElectricalMaintain.setChecksId(electricalCheck.getChecksId());
		//保养人员id
		tElectricalMaintain.setUserId(electricalCheck.getUserId());
		//保养情况描述
		tElectricalMaintain.setMaintainDescribe(telectricalCheck.getMaintainDescribe());
		//保养完成时间
		tElectricalMaintain.setMaintainTime(telectricalCheck.getMaintainTime());
		//设备清洁、无油垢、灰尘
		tElectricalMaintain.setClear(telectricalCheck.getClear());
		//电气线路有无损坏
		tElectricalMaintain.setLine(telectricalCheck.getLine());
		//加机油
		tElectricalMaintain.setOil(telectricalCheck.getOil());
		//创建人
		tElectricalMaintain.setCreatedBy(electricalCheck.getCreatedBy());
		//创建时间
		tElectricalMaintain.setCreationDate(electricalCheck.getCreationDate());
		//设备id
		tElectricalMaintain.setEpId(telectricalCheck.getEpId());
		//数据来源类型
		tElectricalMaintain.setReType("1");//1.巡检单   2保修单发起人确认
		//是否已经完成
		tElectricalMaintain.setCompleteOrNot("Y");//已经完成  0：未完成

		
		
		electricalCheck.setMaintainId(maintainId);
		return electricalMaintainMapper.insert(tElectricalMaintain);
	}

	@Override
	public List<ElectricalMaintain> findElectricalMaintainList(String userId,String completeOrNot) {
		Map<String, String> map=new HashMap<String, String>();	
		map.put("userId", userId);
		map.put("completeOrNot", completeOrNot);
		return electricalMaintainMapper.findElectricalMaintainList(map);
	}

	@Override
	public ElectricalMaintainT qryById(String maintainId) {
		// TODO Auto-generated method stub
		return electricalMaintainMapper.qryById(maintainId);
	}

	@Override
	public boolean updateTask(ElectricalMaintain mElectricalMaintain) throws Exception {
		//更新保养表
		if (electricalMaintainMapper.updateByPrimaryKey(mElectricalMaintain)<1){
			return false;
		};
		//更新设备
		if (equipmentBiz.updateMaintain(mElectricalMaintain.getEpId(), mElectricalMaintain.getMaintainTime(), mElectricalMaintain.getLastUpdatedBy(), mElectricalMaintain.getLastUpdatedDate())<1) {
			return false;
		}
		return true;
	}

	@Override
	public ElectricalMaintainT qryForComplete(String maintainId) {
		return electricalMaintainMapper.qryForComplete(maintainId);
	}

	@Override
	public List<ElectricalMaintain> findAll(String reType) {
		// TODO Auto-generated method stub
		return electricalMaintainMapper.findAll(reType);
	}

	@Override
	public List<ElectricalMaintain> findAllList() {
		return electricalMaintainMapper.findAllList();
	}

}
