package sz.zp.cks.model;

import sz.zp.cks.entity.HsequipmentRepair;

public class HsequipmentRepairT extends HsequipmentRepair{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2092615458022799543L;

	private String userIdName;// 报修人名称
	private String epReIdName;// 维修责任人
	private String epAcNowuserName;// 当前负责人

	private String epAcNextuserName;// 维修责任人名称
	private String epModelNum;// 规格型号
	private String epLocation;// 安装地点

	private String maintainId;
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
	
	
	//规定保养日期
	private String maintenanceDate;
	//当前日期距离保养日期相差多少天（未到显示n天/超时显示-n天）
	private String  distanceDays;
	    
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
	public String getMaintainId() {
		return maintainId;
	}
	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
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
	public String getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public String getDistanceDays() {
		return distanceDays;
	}
	public void setDistanceDays(String distanceDays) {
		this.distanceDays = distanceDays;
	}
	
	
	
	
	
	
	
	
}
