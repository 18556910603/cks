package sz.zp.cks.dao;

import java.util.List;






import sz.zp.cks.entity.Epuser;
import sz.zp.cks.entity.Equipment;

import org.apache.ibatis.annotations.Param;


public interface EpuserMapper {
		
	public  Epuser load(String epUserId) throws Exception;
	public int create(Epuser epUser) throws Exception;
	public int delete(String epUserId) throws Exception;
	public int update(Epuser epUser) throws Exception;
	public String getUserID(String mobile) throws Exception;
	public List <Epuser> list() throws Exception;
	public String getEpUserId(String userId) throws Exception;
	public String getUserIdByLocAndType(String loc,String type)throws Exception;
	public String getUserIdByEpId(String epId) throws Exception;
	
}