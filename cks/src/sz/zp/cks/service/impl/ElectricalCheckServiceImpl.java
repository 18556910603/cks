package sz.zp.cks.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.common.SSIConstants;
import sz.zp.cks.common.SessionContainer;
import sz.zp.cks.dao.ElectricalCheckMapper;
import sz.zp.cks.dao.EquipmentRepairMapper;
import sz.zp.cks.dao.HstatusMapper;
import sz.zp.cks.dao.StatusMapper;
import sz.zp.cks.dao.UserMapper;
import sz.zp.cks.entity.ElectricalCheck;
import sz.zp.cks.entity.EquipmentRepair;
import sz.zp.cks.entity.Hstatus;
import sz.zp.cks.entity.Status;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.ElectricalCheckT;
import sz.zp.cks.model.EquipmentT;
import sz.zp.cks.service.ElectricalCheckService;
import sz.zp.cks.utils.PubFun;

@Service("electricalCheckService")
public class ElectricalCheckServiceImpl implements ElectricalCheckService {
	@Autowired
	private ElectricalCheckMapper electricalCheckMapper;
	@Autowired
	private StatusMapper statusMapper;
	@Autowired
	private HstatusMapper hstatusMapper;
	@Autowired
	private EquipmentRepairMapper equipmentRepairMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public int insert(ElectricalCheck entity) throws Exception {
		// TODO Auto-generated method stub
		return electricalCheckMapper.insert(entity);
	}

	@Override
	public int update(ElectricalCheck entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ElectricalCheck entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ElectricalCheck select(ElectricalCheck entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EquipmentT queryById(String epId) {
		// TODO Auto-generated method stub
		return electricalCheckMapper.queryById(epId);
	}

	@Override
	public boolean updateStatus(ElectricalCheck electricalCheck,User tUser) {
		int sta=0;
		int hsta=0;
		Status tStatus=new Status();
		//查询status表
		tStatus=statusMapper.getStatusByEqId(electricalCheck.getEpId());
		if(tStatus!=null&&(tStatus.getStatusId()!=null)&&(!tStatus.getStatusId().equals(""))){
			//status更新
			tStatus.setStatusVal(electricalCheck.getStatusNewVal());//状态值
			tStatus.setReType("2");//巡检检查
			tStatus.setReTypeid(electricalCheck.getChecksId());//关联表单的id
			tStatus.setLastUpdatedBy(tUser.getUserId());
			tStatus.setLastUpdatedDate(PubFun.getCurrentDate());
			sta=statusMapper.update(tStatus);
		}else{
			//status新增
			tStatus=new Status();
			String statusId ="ST"+UUID.randomUUID().toString().replace("-", "");
			tStatus.setStatusId(statusId);
			tStatus.setEpId(electricalCheck.getEpId());
			tStatus.setStatusVal(electricalCheck.getStatusNewVal());//状态值
			tStatus.setReType("2");//巡检检查
			tStatus.setReTypeid(electricalCheck.getChecksId());//关联表单的id
			tStatus.setRemarks("来自巡检表单提交的新增");
			//获取登录者信息
			tStatus.setCreatedBy(tUser.getUserId());
			tStatus.setCreationDate(PubFun.getCurrentDate());
			sta=statusMapper.insert(tStatus);
		}
		
		Hstatus tHStatus=new Hstatus();
		String hstatusId ="HST"+UUID.randomUUID().toString().replace("-", "");
		BeanUtils.copyProperties(tStatus, tHStatus);
		tHStatus.setHstatusId(hstatusId);
		tHStatus.setCreationDate(PubFun.getCurrentDate());
		hsta=hstatusMapper.insert(tHStatus);
		if(sta>0&&hsta>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<ElectricalCheck> findElectricalCheckList(
			String userId) {
		 List<ElectricalCheck> findListByUserId=new ArrayList<>();
		//下个节点负责人即维修部门的主管
		User LeaderUser=new User();
		LeaderUser=userMapper.selectForLeaderByType("repair");
		System.out.println("-------------LeaderUser:"+LeaderUser);
		if((userId).equals(LeaderUser.getUserId())){
			findListByUserId= electricalCheckMapper.findAllList();
		}else{
			findListByUserId= electricalCheckMapper.findListByUserId(userId);
		}
		
		return findListByUserId;
	
	}

	@Override
	public ElectricalCheckT qryById(String checksId) {
		return electricalCheckMapper.qryById(checksId);
	}

	@Override
	public List<EquipmentRepair> isRepair(String epId) {
		List <EquipmentRepair> list=new ArrayList<EquipmentRepair>();
		list=equipmentRepairMapper.queryByEpId(epId);
	 return list;
	}

	@Override
	public List<ElectricalCheck> findList() {
		 List<ElectricalCheck> list=new ArrayList<>();
		 list= electricalCheckMapper.findAllList();
		return list;
	}

	@Override
	public List<ElectricalCheck> findALLElectricalCheckList() {
		List <ElectricalCheck> list=new ArrayList<ElectricalCheck>();
		list=electricalCheckMapper.findAllList();
		 return list;
	}

}
