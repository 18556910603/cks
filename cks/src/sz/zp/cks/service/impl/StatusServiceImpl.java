package sz.zp.cks.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.StatusMapper;
import sz.zp.cks.entity.Account;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.Status;
import sz.zp.cks.model.StatusT;
import sz.zp.cks.service.StatusService;

@Service("statusService")
public class StatusServiceImpl implements StatusService {
	@Autowired
	private StatusMapper statusMapper;
	@Override
	public int insert(Status entity) throws Exception {
		return statusMapper.insert(entity);
	}

	@Override
	public int update(Status entity) {
		// TODO Auto-generated method stub
		return statusMapper.update(entity);
	}

	@Override
	public int delete(Status entity) throws Exception {
		// TODO Auto-generated method stub
		return statusMapper.delete(entity);
	}

	@Override
	public Status select(Status entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusT> findStatusTList(StatusT entity) {
		return statusMapper.findStatusTList(entity);
	}

	@Override
	public StatusT selectById(String statusId) {
		// TODO Auto-generated method stub
		return statusMapper.selectById(statusId);
	}


	@Override
	public List<Equipment> getEquipList() {
		List<Equipment> list=new ArrayList<Equipment>();
		list=statusMapper.getEquipList();
		return list;
	}

	@Override
	public Status queryById(String statusId) {
		// TODO Auto-generated method stub
		return statusMapper.queryById(statusId);
	}





}
