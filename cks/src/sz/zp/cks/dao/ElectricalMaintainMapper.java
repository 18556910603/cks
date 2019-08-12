package sz.zp.cks.dao;

import java.util.List;
import java.util.Map;

import sz.zp.cks.entity.ElectricalMaintain;
import sz.zp.cks.model.ElectricalMaintainT;


public interface ElectricalMaintainMapper extends BaseMapper<ElectricalMaintain> {
	public List<ElectricalMaintain> findElectricalMaintainList(Map map);
	public ElectricalMaintainT qryById(String maintainId);
	public int updateByPrimaryKey(ElectricalMaintain entity);
	public ElectricalMaintainT qryForComplete(String maintainId);
	public List<ElectricalMaintain> findAll(String reType);
	public List<ElectricalMaintain> findAllList();
}