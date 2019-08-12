package sz.zp.cks.service;

import java.util.List;

import sz.zp.cks.entity.User;

public interface WXMaintainService {
	
	/**
	 * 获取需要维护提醒的设备id
	 * 
	 * @return List<String> 返回所有设备id
	 * @throws Exception
	 */
	public List<String> getNeedMaintainEpId() throws Exception;
	
	public String getUserId(String ep_id);
	
	public String getWXOpenId(String ep_id);
	
	public String getMaintainDate(String ep_id) throws Exception;
	
	public User getNearMaintainEpUserInfo();
	
	public String getEpType(String ep_id) throws Exception;
}
