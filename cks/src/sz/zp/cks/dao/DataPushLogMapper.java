package sz.zp.cks.dao;



import java.util.List;

import sz.zp.cks.entity.DataPushLog;


public interface DataPushLogMapper  extends BaseMapper<DataPushLog> {
	
	public List<DataPushLog> queryByNodeName(String  nodeName);
	
	public List<DataPushLog> list();
	
}