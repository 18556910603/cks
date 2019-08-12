	package sz.zp.cks.entity;	

import java.io.Serializable;

public class Equipment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5626542117534461100L;

	private String epId;

	private String epName;

	private String epModelNum;

	private String epLoc;

	private String epLocation;

	private String epType;

	private String epProducer;

	private String epProvider;

	private String epBuyingTime;

	private String epUpTime;

	private String epUserId;
	
	private String qrCode;//二维码
	
	private String createdBy;

	private String creationDate;

	private String lastUpdatedBy;
	
	private String lastUpdatedDate;
	//规定保养日期
	private String maintenanceDate;
	
	private String address;
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private Epuser epUser;

	public Epuser getEpUser() {
		return epUser;
	}

	public void setEpUser(Epuser epUser) {
		this.epUser = epUser;
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

	public String getEpLoc() {
		return epLoc;
	}

	public void setEpLoc(String epLoc) {
		this.epLoc = epLoc;
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

	public String getEpProducer() {
		return epProducer;
	}

	public void setEpProducer(String epProducer) {
		this.epProducer = epProducer;
	}

	public String getEpProvider() {
		return epProvider;
	}

	public void setEpProvider(String epProvider) {
		this.epProvider = epProvider;
	}

	public String getEpBuyingTime() {
		return epBuyingTime;
	}

	public void setEpBuyingTime(String epBuyingTime) {
		this.epBuyingTime = epBuyingTime;
	}

	public String getEpUpTime() {
		return epUpTime;
	}

	public void setEpUpTime(String epUpTime) {
		this.epUpTime = epUpTime;
	}

	public String getEpUserId() {
		return epUserId;
	}

	public void setEpUserId(String epUserId) {
		this.epUserId = epUserId;
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

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	@Override
	public String toString() {
		return "Equipment [epId=" + epId + ", epName=" + epName
				+ ", epModelNum=" + epModelNum + ", epLoc=" + epLoc
				+ ", epLocation=" + epLocation + ", epType=" + epType
				+ ", epProducer=" + epProducer + ", epProvider=" + epProvider
				+ ", epBuyingTime=" + epBuyingTime + ", epUpTime=" + epUpTime
				+ ", epUserId=" + epUserId + ", qrCode=" + qrCode
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDate="
				+ lastUpdatedDate + ", maintenanceDate=" + maintenanceDate
				+ ", epUser=" + epUser + "]";
	}



	

}
