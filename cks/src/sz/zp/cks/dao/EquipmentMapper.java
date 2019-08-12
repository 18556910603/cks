package sz.zp.cks.dao;

import java.util.List;
import java.util.Map;

import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.User;

public interface EquipmentMapper {


		public int create(Equipment equipment) throws Exception;
	    
	    public Equipment findEpUser(String epId) throws Exception;
	    
	    public int delete(String epId) throws Exception;
	    
	    public Equipment load(String epId) throws Exception;
	    
	    public int update(Equipment equipment) throws Exception;
	    
	    public int updateQRCode(Equipment equipment) throws Exception;
	    
		public List <Equipment> list() throws Exception;
		
		public int findAllCount(Map<String,Object> map) throws Exception;
		
		public int findUpCount() throws Exception;
			
		public List<Equipment> findByPage(Map<String,Object> map) throws Exception;
		
		public List<Equipment> findAllEquipment() throws Exception;
		
	    public Equipment findEpInfoByEpId(String epId) throws Exception;
	    //查询设备的巡检人信息
	    public User qryEpUser(String epId) ;
}
