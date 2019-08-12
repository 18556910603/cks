package sz.zp.cks.service;

import java.util.List;
import java.util.Map;

import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.PageBean;

public interface IEpuserBiz {
	
   
	  public Epuser queryById(String  epUserId) throws Exception;
	  public String getUserID(String  mobile) throws Exception;
	  public int insert(Epuser epUser) throws Exception;	
	  public int remove(String  epUserId) throws Exception;
	  public int modify(Epuser epUser) throws Exception;
	  public List<Epuser> getList() throws Exception;
	  public String getEpUserId(String  userId) throws Exception;
}
