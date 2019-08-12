package sz.zp.cks.service;

import java.util.List;
import java.util.Map;

import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.PageBean;
import sz.zp.cks.model.ElectricalCheckT;

public interface IEquipmentBiz {
	
    public int insert(Equipment ep) throws Exception;
    
    public Equipment queryEpUser(String  epId) throws Exception;
	
	public int remove(String  epId) throws Exception;
	
	public Equipment queryById(String  epId) throws Exception;
		
	public int modify(Equipment ep) throws Exception;
	
	public int modifyQRCode(Equipment ep) throws Exception;
	
	public List<Equipment> getList() throws Exception;
	
	public int getAllCount(Map<String,Object> conditions) throws Exception;
	
	public int findUpCount() throws Exception;
	
	public PageBean queryByPageLike(int pageSize,int pageCode,Map<String, Object> conditions)throws Exception;
	//巡检页面保养计划的更新，根据设备id更新下一次规定的保养时间
	public int updateByMaintain(ElectricalCheckT telectricalCheck,ElectricalCheck electricalCheck) throws Exception;
	public  int updateMaintain(String epId,String maintainTime,String lastUpdatedBy,String lastUpdatedDate) throws Exception ;
	
}
