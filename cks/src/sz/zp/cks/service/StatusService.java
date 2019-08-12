package sz.zp.cks.service;

import java.util.List;

import sz.zp.cks.entity.Account;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.Status;
import sz.zp.cks.model.StatusT;

public interface StatusService extends BaseService<Status> {
	 public List<StatusT> findStatusTList(StatusT entity);
	 public StatusT selectById(String statusId);
	 public Status queryById(String statusId);
	 public int update(Status tStatus);
	 public List<Equipment> getEquipList();
	 
}
