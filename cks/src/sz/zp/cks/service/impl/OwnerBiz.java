package sz.zp.cks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






import sz.zp.cks.dao.OwnerMapper;
import sz.zp.cks.entity.Owner;
import sz.zp.cks.service.IOwnerBiz;

@Service
@Transactional(readOnly=true)
public class OwnerBiz implements IOwnerBiz{


	@Autowired
	OwnerMapper ownerMapper;
	

	@Override
	public Owner queryById(String userId) throws Exception {

		return ownerMapper.load(userId);
	}

	//@Transactional(readOnly=false)
	@Override
	public int regist(Owner owner) throws Exception {

		return ownerMapper.insert(owner);
	}

	//@Transactional(readOnly=false)
	@Override
	public int modify(Owner  owner) throws Exception {
	
		return ownerMapper.update(owner);
	}

	@Override
	public int max() throws Exception {
		// TODO Auto-generated method stub
		return ownerMapper.max();
	}

	@Override
	public Owner qryById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return ownerMapper.load(userId);
	}

	
	
	
	


	

}
