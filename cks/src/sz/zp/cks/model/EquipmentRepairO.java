package sz.zp.cks.model;

import java.io.Serializable;

import sz.zp.cks.entity.EquipmentRepair;

public class EquipmentRepairO extends EquipmentRepair implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4721676246288442984L;


	private  String userIdName;//报修人名称
	private  String mobile;//报修人电话
	private  String epReIdName;//维修责任人
	private  String reMobile;//维修责任人电话
	private  String epAcNowuserName;//当前负责人	
	private  String epAcNextuserName;//维修责任人名称号
	private  String epLocation;//安装地点	
	public String getUserIdName() {
		return userIdName;
	}

	public String getReMobile() {
		return reMobile;
	}

	public void setReMobile(String reMobile) {
		this.reMobile = reMobile;
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


	public String getEpLocation() {
		return epLocation;
	}

	public void setEpLocation(String epLocation) {
		this.epLocation = epLocation;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	
	
	
	
}
