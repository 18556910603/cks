package sz.zp.cks.service;

import java.util.List;

import sz.zp.cks.entity.Hstatus;
import sz.zp.cks.model.StatusT;

public interface HstatusService extends BaseService<Hstatus> {

	 public List<StatusT> findStatusTList(StatusT entity);
}
