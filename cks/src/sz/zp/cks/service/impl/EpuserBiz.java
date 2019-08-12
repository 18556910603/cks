package sz.zp.cks.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sz.zp.cks.dao.EpuserMapper;
import sz.zp.cks.dao.EquipmentMapper;
import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.PageBean;
import sz.zp.cks.service.IEpuserBiz;
import sz.zp.cks.service.IEquipmentBiz;

@Service
@Transactional(readOnly=true)
public class EpuserBiz implements IEpuserBiz{


	@Autowired
	EpuserMapper epuserMapper;
	

	@Override
	public Epuser queryById(String epUserId) throws Exception {
		// TODO Auto-generated method stub
		return epuserMapper.load(epUserId);
	}
	
	@Override
	public int insert(Epuser epUser) throws Exception {

		return epuserMapper.create(epUser);
		
	}

	@Override
	public int remove(String epUserId) throws Exception {

		return epuserMapper.delete(epUserId);
	}

	
	@Override
	public int  modify(Epuser epUser) throws Exception {

		return epuserMapper.update(epUser);
	}

	@Override
	public List<Epuser> getList() throws Exception {
		// TODO Auto-generated method stub
		return epuserMapper.list() ;
	}

	@Override
	public String getUserID(String mobile) throws Exception {
		// TODO Auto-generated method stub
		return epuserMapper.getUserID(mobile);
	}

	@Override
	public String getEpUserId(String userId) throws Exception {
		
		return epuserMapper.getEpUserId(userId);
	}

	

}
