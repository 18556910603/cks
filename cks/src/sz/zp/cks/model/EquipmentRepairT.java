package sz.zp.cks.model;

import java.io.Serializable;

import sz.zp.cks.entity.EquipmentRepair;

public class EquipmentRepairT extends EquipmentRepair implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4721676246288442984L;


	private  String userIdName;//报修人名称
	private  String epReIdName;//维修责任人
	private  String epAcNowuserName;//当前负责人
	
	private  String epAcNextuserName;//维修责任人名称
	private  String epModelNum;//规格型号
	private  String epLocation;//安装地点	
	
	// 保养情况描述
	private String maintainDescribe;
	// 保养完成时间
	private String maintainTime;
	// 设备清洁、无油垢、灰尘
	private String clear;
	// 电气线路有无损坏
	private String line;
	// 加机油
	private String oil;

	
	
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

	public String getEpModelNum() {
		return epModelNum;
	}

	public void setEpModelNum(String epModelNum) {
		this.epModelNum = epModelNum;
	}

	public String getEpLocation() {
		return epLocation;
	}

	public void setEpLocation(String epLocation) {
		this.epLocation = epLocation;
	}



	public String getMaintainDescribe() {
		return maintainDescribe;
	}

	public void setMaintainDescribe(String maintainDescribe) {
		this.maintainDescribe = maintainDescribe;
	}

	public String getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
	}

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}
	

	
	
	
	
}
