package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.Status;
import sz.zp.cks.model.StatusT;


public interface StatusMapper extends BaseMapper<Status> {
	public List<StatusT> findStatusTList(StatusT entity);
	public StatusT selectById(String statusId);
	public void submit(StatusT statusT);
	public List<Equipment> getEquipList();
	public Status queryById(String statusId);
	public Status getStatusByEqId(String epId);  //根据设备id获取状态信息
	public int updateFromMqtt(Status status);
}