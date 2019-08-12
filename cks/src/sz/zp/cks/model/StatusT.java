package sz.zp.cks.model;

import java.io.Serializable;

public class StatusT implements Serializable {
	private static final long serialVersionUID = -5843141588335463058L;
	//状态id
	private String statusId;
	//设备编号
	private String epId;
	//设备名称
	private String epName;
	//规格型号
	private String epModelNum;
	//安装地点
	private String eplocation;
	//设备类型
	private String epType;
	//设备类型
	private String epTypeName;
	//设备状态
    private String statusVal;
    //设备状态中文
    private String statusValName;
    
    private String lastUpdatedBy;

    private String lastUpdatedDate;    
    
    private String lastUpdatedByName;
    
    // 创建人
    private String createdBy;
    //创建时间
    private String creationDate;
    
    // 创建人
    private String createdByName;  
    
	public String getEpTypeName() {
		return epTypeName;
	}
	public void setEpTypeName(String epTypeName) {
		this.epTypeName = epTypeName;
	}
	public String getStatusValName() {
		return statusValName;
	}
	public void setStatusValName(String statusValName) {
		this.statusValName = statusValName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getEplocation() {
		return eplocation;
	}
	public void setEplocation(String eplocation) {
		this.eplocation = eplocation;
	}
	public String getStatusVal() {
		return statusVal;
	}
	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}
	public String getEpId() {
		return epId;
	}
	public void setEpId(String epId) {
		this.epId = epId;
	}
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public String getEpModelNum() {
		return epModelNum;
	}
	public void setEpModelNum(String epModelNum) {
		this.epModelNum = epModelNum;
	}
	public String getEpType() {
		return epType;
	}
	public void setEpType(String epType) {
		this.epType = epType;
	}
	@Override
	public String toString() {
		return "StatusT [statusId=" + statusId + ", epId=" + epId + ", epName="
				+ epName + ", epModelNum=" + epModelNum + ", eplocation="
				+ eplocation + ", epType=" + epType + ", statusVal="
				+ statusVal + "]";
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}
	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	
	
	
	
}
