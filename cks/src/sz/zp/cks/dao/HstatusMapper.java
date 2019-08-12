package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.Hstatus;
import sz.zp.cks.model.StatusT;


public interface HstatusMapper extends BaseMapper<Hstatus> {
	public List<StatusT> findStatusTList(StatusT entity);
}