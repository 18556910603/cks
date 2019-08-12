package sz.zp.cks.model;

import sz.zp.cks.entity.HsequipmentRepair;

public class HsequipmentRepairO extends HsequipmentRepair{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2092615458022799543L;

	private  String userIdName;//报修人名称
	private  String epReIdName;//维修责任人
	private  String epAcNowuserName;//当前负责人
	
	private  String epAcNextuserName;//维修责任人名称
	private  String mobile;//报修人电话
	private  String epLocation;//安装地点	
	
	
	public String getUserIdName() {
		return userIdName;
	}
	public void setUserIdName(String userIdName) {
		this.userIdName = userIdName;
	}
	public String getEpReIdName() {
		return epReIdName;
	}
	public void setEpReIdName(String epReIdName) {
		this.epReIdName = epReIdName;
	}
	public String getEpAcNowuserName() {
		return epAcNowuserName;
	}
	public void setEpAcNowuserName(String epAcNowuserName) {
		this.epAcNowuserName = epAcNowuserName;
	}
	public String getEpAcNextuserName() {
		return epAcNextuserName;
	}
	public void setEpAcNextuserName(String epAcNextuserName) {
		this.epAcNextuserName = epAcNextuserName;
	}


	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEpLocation() {
		return epLocation;
	}
	public void setEpLocation(String epLocation) {
		this.epLocation = epLocation;
	}
	
	
	
	
	
	
	
	
}
