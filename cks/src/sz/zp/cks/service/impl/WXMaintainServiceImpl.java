package sz.zp.cks.service.impl;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.EquipmentMapper;
import sz.zp.cks.dao.UserMapper;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.User;
import sz.zp.cks.service.WXMaintainService;
import sz.zp.cks.utils.DictUtils;

@Service("wXMaintainService")
public class WXMaintainServiceImpl implements WXMaintainService {
	
	@Autowired
	private  EquipmentMapper equipmentMapper;
	
	@Autowired
	UserMapper userMapper;
	
	/* (non-Javadoc)
	 * @see sz.zp.cks.weixin.service.WXMaintainService#getNeedMaintainEpId()
	 */
	@Override
	public List<String> getNeedMaintainEpId() throws Exception {
		System.out.println("service------");
		// TODO Auto-generated method stub
		List<String> epIdList=new ArrayList<String>();
		List<Equipment> equipmentlist= new ArrayList<Equipment>();
		equipmentlist= equipmentMapper.findAllEquipment();
		for(Equipment epresult:equipmentlist){
			String endDate=epresult.getMaintenanceDate();
			if(getTwoDayDiff(endDate)<=3||getTwoDayDiff(endDate)==-2){
				epIdList.add(epresult.getEpId());
			}
		}
		return epIdList;
	}
	@Override
	public String getUserId(String ep_id){
		User user =userMapper.selectUserByEpId(ep_id);
		String userid=user.getUserId();
		return userid;
		
	}
	@Override
	public String getWXOpenId(String ep_id) {
		User user =userMapper.selectUserByEpId(ep_id);
		if(!(user==null)){
			System.out.println("getWXOpenI的ep_id"+ep_id);
			System.out.println("getWXOpenI的user"+user);
			String wxOpenId=user.getWxOpenId();
			if(!(wxOpenId==null)){
				System.out.println("getWXOpenId的open id"+wxOpenId);
				return wxOpenId;
			}
		}
		return null;
	}
	@Override
	public String getMaintainDate(String ep_id) throws Exception{
		// TODO Auto-generated method stub
		Equipment equipment = equipmentMapper.findEpInfoByEpId(ep_id);
		String maintainDate = equipment.getMaintenanceDate();
		return maintainDate;
	}
	@Override
	public String getEpType(String ep_id) throws Exception{
		// TODO Auto-generated method stub
		Equipment equipment = equipmentMapper.findEpInfoByEpId(ep_id);
		String epType = equipment.getEpType();
		
		String epTypeName = null;
		epTypeName=DictUtils.getDictLabel(epType, "epType", epType);
		
		return epTypeName;
	}
	
	public long getTwoDayDiff(String end_date){
		long day = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String sdate = format.format(Calendar.getInstance().getTime());//获取现在时间，转换为年月日的string类型
			long nowdate=format.parse(sdate).getTime();//parse()方法可把string转为date。date的gettime方法是获取long类型时间值
			long enddate=format.parse(end_date).getTime();
			if(enddate<=nowdate){
				day = -2;//返回值为-2 已超时
			}else{
				day = (enddate-nowdate)/(24*60*60*1000);//返回还有多少天超时
			}
		} catch (Exception e) {
			return -1;
		}
		
		return day;
	}
	
	
	@Override
	public User getNearMaintainEpUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}
//	public static void main(String[] args) {
//		System.out.println("11");
//		List<Equipment> equipmentlist = new ArrayList<Equipment>();
//		try {
//			equipmentlist = equipmentMapper.findAllEquipment();
//			System.out.println("-------------:"+equipmentlist.size());
///*			for(Equipment epresult:equipmentlist){
//				System.out.println("----"+epresult);
//			}*/
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
	
}
