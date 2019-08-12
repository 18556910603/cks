package sz.zp.cks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.DataPushLogMapper;
import sz.zp.cks.dao.EquipmentRepairMapper;
import sz.zp.cks.entity.DataPushLog;
import sz.zp.cks.service.ApmApiService;
import sz.zp.cks.service.DataPushLogService;

@Service("dataPushLogService")
public class DataPushLogServiceImpl implements DataPushLogService{
	
	@Autowired
	private DataPushLogMapper dataPushLogMapper;

	@Override
	public int insert(DataPushLog entity) throws Exception {
		// TODO Auto-generated method stub
		return dataPushLogMapper.insert(entity);
	}

	@Override
	public int update(DataPushLog entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(DataPushLog entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataPushLog select(DataPushLog entity) {
		// TODO Auto-generated method stub
		return dataPushLogMapper.select(entity);
	}

	@Override
	public List<DataPushLog> queryByNodeName(String nodeName) {
		// TODO Auto-generated method stub
		return dataPushLogMapper.queryByNodeName(nodeName);
	}

	@Override
	public List<DataPushLog> list() {
		// TODO Auto-generated method stub
		return dataPushLogMapper.list();
	}
     
}
