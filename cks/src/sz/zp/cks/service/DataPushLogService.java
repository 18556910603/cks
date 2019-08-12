package sz.zp.cks.service;

import java.util.List;

import sz.zp.cks.entity.DataPushLog;

public interface DataPushLogService extends BaseService<DataPushLog> {
	public List<DataPushLog> queryByNodeName(String nodeName);
	
	public List<DataPushLog> list();
}
