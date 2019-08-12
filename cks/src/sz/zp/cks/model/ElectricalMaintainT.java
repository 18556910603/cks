package sz.zp.cks.model;

import sz.zp.cks.entity.ElectricalMaintain;

public class ElectricalMaintainT extends ElectricalMaintain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2459858745276728972L;
	//保养人员名字
	private String userIdName;
	//领导名字
	private String 	leaderIdName;
	
	//设备名称
	private String epName;
	//设备状态
	private String status;	
	//规格型号
	private String epModelNum;
	//安装地点
	private String epLocation;	
	//设备类型
	private String epType;	

	

	
	
	
	
	public String getUserIdName() {
		return userIdName;
	}
	public void setUserIdName(String userIdName) {
		this.userIdName = userIdName;
	}
	public String getLeaderIdName() {
		return leaderIdName;
	}
	public void setLeaderIdName(String leaderIdName) {
		this.leaderIdName = leaderIdName;
	}
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getEpType() {
		return epType;
	}
	public void setEpType(String epType) {
		this.epType = epType;
	}
	
	
}
