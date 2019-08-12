package sz.zp.cks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.HstatusMapper;
import sz.zp.cks.entity.Hstatus;
import sz.zp.cks.model.StatusT;
import sz.zp.cks.service.HstatusService;

@Service("hstatusService")
public class HstatusServiceImpl implements HstatusService {
	@Autowired
	private HstatusMapper hstatusMapper;

	@Override
	public int insert(Hstatus entity) throws Exception {
		// TODO Auto-generated method stub
		return hstatusMapper.insert(entity);
	}

	@Override
	public int update(Hstatus entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Hstatus entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Hstatus select(Hstatus entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusT> findStatusTList(StatusT entity) {
		return hstatusMapper.findStatusTList(entity);
	}




}
