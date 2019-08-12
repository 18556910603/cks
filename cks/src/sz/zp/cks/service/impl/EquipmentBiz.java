package sz.zp.cks.service.impl;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sz.zp.cks.dao.EquipmentMapper;
import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.PageBean;
import sz.zp.cks.model.ElectricalCheckT;
import sz.zp.cks.service.IEquipmentBiz;
import sz.zp.cks.utils.PubFun;

@Service
@Transactional(readOnly=true)
public class EquipmentBiz implements IEquipmentBiz{


	@Autowired
	EquipmentMapper equipmentMapper;
	
	@Override
	public int insert(Equipment ep) throws Exception {
		// TODO Auto-generated method stub
		return equipmentMapper.create(ep);
		
	}

	@Override
	public int remove(String epId) throws Exception {
		// TODO Auto-generated method stub
		
		return equipmentMapper.delete(epId);
	}

	@Override
	public Equipment queryById(String epId) throws Exception {
		// TODO Auto-generated method stub
		return equipmentMapper.load(epId);
	}

	@Override
	public int  modify(Equipment ep) throws Exception {

		return equipmentMapper.update(ep);
	}

	@Override
	public List<Equipment> getList() throws Exception {
		// TODO Auto-generated method stub
		return equipmentMapper.list() ;
	}

	@Override
	public int getAllCount(Map<String, Object> conditions) throws Exception {
		// TODO Auto-generated method stub
		return equipmentMapper.findAllCount(conditions);
	}

	@Override
	public PageBean queryByPageLike(int pageSize, int pageCode,
			Map<String, Object> conditions) throws Exception {
		String epId=null;
		if(conditions!=null&conditions.size()>0){
			Set<String> set = conditions.keySet();
			Iterator<String> iter=set.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if("epId".equals(key)){
					epId=(String) conditions.get(key);
				
				}		
			}
		}
        
		PageBean pb=new PageBean();
		int allCount=equipmentMapper.findAllCount(conditions);
		pb.setAllCount(allCount);
		pb.setPageSize(pageSize);
		if(pageCode>pb.getAllPages()){
			pageCode=pb.getAllPages();
		}
		pb.setPageCode(pageCode);
		conditions.put("pageCode", pageCode);
		conditions.put("pageSize", pageSize);
		conditions.put("epId",epId);
		List<Equipment> datas = equipmentMapper.findByPage(conditions);
		pb.setDatas(datas);
		return pb;
		
	}


	@Override
	public Equipment queryEpUser(String epId) throws Exception {
		// TODO Auto-generated method stub
		return equipmentMapper.findEpUser(epId);
	}

	@Override
	public int modifyQRCode(Equipment ep) throws Exception {
		// TODO Auto-generated method stub
		return equipmentMapper.updateQRCode(ep);
	}

	@Override
	public int findUpCount() throws Exception {
		// TODO Auto-generated method stub
		return equipmentMapper.findUpCount();
	}

	@Override
	public int updateByMaintain(ElectricalCheckT telectricalCheck,ElectricalCheck electricalCheck) throws Exception {
		Equipment tEquipment=new Equipment();
		tEquipment= equipmentMapper.load(telectricalCheck.getEpId());
		
		tEquipment.setLastUpdatedBy(electricalCheck.getCreatedBy());
		tEquipment.setLastUpdatedDate(electricalCheck.getCreationDate());
		
		String maintainTime=telectricalCheck.getMaintainTime();
		tEquipment.setMaintenanceDate(PubFun.subMonth(maintainTime));
		
		return equipmentMapper.update(tEquipment);
	}

	@Override
	public int updateMaintain(String epId,String maintainTime,String lastUpdatedBy,String lastUpdatedDate) throws Exception {
		Equipment tEquipment=new Equipment();
		tEquipment= equipmentMapper.load(epId);
		
		tEquipment.setLastUpdatedBy(lastUpdatedBy);
		tEquipment.setLastUpdatedDate(lastUpdatedDate);
		
		tEquipment.setMaintenanceDate(PubFun.subMonth(maintainTime));
		
		return equipmentMapper.update(tEquipment);
	}
	
}
