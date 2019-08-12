package sz.zp.cks.model;

import sz.zp.cks.entity.ElectricalCheck;

public class ElectricalCheckT extends ElectricalCheck{
	private static final long serialVersionUID = 8498292023863000373L;
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
	
	//设备名称
	private String epName;
	//设备类型
	private String epType;	
	//巡检人名称
	private  String userIdName;
	//规格型号
	private String epModelNum;
	//安装地点
	private String epLocation;
	
	
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
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public String getEpType() {
		return epType;
	}
	public void setEpType(String epType) {
		this.epType = epType;
	}
	public String getUserIdName() {
		return userIdName;
	}
	public void setUserIdName(String userIdName) {
		this.userIdName = userIdName;
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

	
	
	
	
	
	
}
