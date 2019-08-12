package sz.zp.cks.entity;

import java.io.Serializable;

public class HsequipmentRepair implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1184619390389749240L;

	private String hsEquipmentrepairId;

    private String equipmentrepairId;

    private String epId;
    //报修类别
    private String epType;
    //报修人
    private String userId;
    //报修时间
    private String eprCkTime;

    private String eprCkVideo;

    private String eprCkPhoto;

    private String eprCkDescribe;

    private String eprCkLevel;
//设备类型
    private String epHomeEqutype;
//设备名称
    private String epHomeEquname;

    private String epReNo;
// 维修责任人
    private String epReId;
//故障类别
    private String epReKind;

    private String epReGroup;
//维修情况说明
    private String epReDescribe;
//维修完成时间
    private String epReTime;
//设备状态
    private String epReStatus;

    private String createdBy;

    private String creationDate;

    private String lastUpdatedBy;

    private String lastUpdatedDate;
//流程状态
    private String epAcStatus;
//当前节点
    private String epAcNowid;
//当前负责人
    private String epAcNowuser;
//下个节点
    private String epAcNextid;
//下个节点负责人
    private String epAcNextuser;
    
    
    
	//规定保养日期
	private String maintenanceDate;
	//当前日期距离保养日期相差多少天（未到显示n天/超时显示-n天）
	private String  distanceDays; 

    public String getHsEquipmentrepairId() {
        return hsEquipmentrepairId;
    }

    public void setHsEquipmentrepairId(String hsEquipmentrepairId) {
        this.hsEquipmentrepairId = hsEquipmentrepairId;
    }

    public String getEquipmentrepairId() {
        return equipmentrepairId;
    }

    public void setEquipmentrepairId(String equipmentrepairId) {
        this.equipmentrepairId = equipmentrepairId;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getEpType() {
        return epType;
    }

    public void setEpType(String epType) {
        this.epType = epType;
    }

   
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEprCkTime() {
        return eprCkTime;
    }

    public void setEprCkTime(String eprCkTime) {
        this.eprCkTime = eprCkTime;
    }

    public String getEprCkVideo() {
        return eprCkVideo;
    }

    public void setEprCkVideo(String eprCkVideo) {
        this.eprCkVideo = eprCkVideo;
    }

    public String getEprCkPhoto() {
        return eprCkPhoto;
    }

    public void setEprCkPhoto(String eprCkPhoto) {
        this.eprCkPhoto = eprCkPhoto;
    }

    public String getEprCkDescribe() {
        return eprCkDescribe;
    }

    public void setEprCkDescribe(String eprCkDescribe) {
        this.eprCkDescribe = eprCkDescribe;
    }

    public String getEprCkLevel() {
        return eprCkLevel;
    }

    public void setEprCkLevel(String eprCkLevel) {
        this.eprCkLevel = eprCkLevel;
    }

    public String getEpHomeEqutype() {
        return epHomeEqutype;
    }

    public void setEpHomeEqutype(String epHomeEqutype) {
        this.epHomeEqutype = epHomeEqutype;
    }

    public String getEpHomeEquname() {
        return epHomeEquname;
    }

    public void setEpHomeEquname(String epHomeEquname) {
        this.epHomeEquname = epHomeEquname;
    }

    public String getEpReNo() {
        return epReNo;
    }

    public void setEpReNo(String epReNo) {
        this.epReNo = epReNo;
    }

    public String getEpReId() {
        return epReId;
    }

    public void setEpReId(String epReId) {
        this.epReId = epReId;
    }

    public String getEpReKind() {
        return epReKind;
    }

    public void setEpReKind(String epReKind) {
        this.epReKind = epReKind;
    }

    public String getEpReGroup() {
        return epReGroup;
    }

    public void setEpReGroup(String epReGroup) {
        this.epReGroup = epReGroup;
    }

    public String getEpReDescribe() {
        return epReDescribe;
    }

    public void setEpReDescribe(String epReDescribe) {
        this.epReDescribe = epReDescribe;
    }

    public String getEpReTime() {
        return epReTime;
    }

    public void setEpReTime(String epReTime) {
        this.epReTime = epReTime;
    }

    public String getEpReStatus() {
        return epReStatus;
    }

    public void setEpReStatus(String epReStatus) {
        this.epReStatus = epReStatus;
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

    public String getEpAcStatus() {
        return epAcStatus;
    }

    public void setEpAcStatus(String epAcStatus) {
        this.epAcStatus = epAcStatus;
    }

    public String getEpAcNowid() {
        return epAcNowid;
    }

    public void setEpAcNowid(String epAcNowid) {
        this.epAcNowid = epAcNowid;
    }

    public String getEpAcNowuser() {
        return epAcNowuser;
    }

    public void setEpAcNowuser(String epAcNowuser) {
        this.epAcNowuser = epAcNowuser;
    }

    public String getEpAcNextid() {
        return epAcNextid;
    }

    public void setEpAcNextid(String epAcNextid) {
        this.epAcNextid = epAcNextid;
    }

    public String getEpAcNextuser() {
        return epAcNextuser;
    }

    public void setEpAcNextuser(String epAcNextuser) {
        this.epAcNextuser = epAcNextuser;
    }

	public HsequipmentRepair(String hsEquipmentrepairId,
			String equipmentrepairId, String epId, String epType,
			String userId, String eprCkTime, String eprCkVideo,
			String eprCkPhoto, String eprCkDescribe, String eprCkLevel,
			String epHomeEqutype, String epHomeEquname, String epReNo,
			String epReId, String epReKind, String epReGroup,
			String epReDescribe, String epReTime, String epReStatus,
			String createdBy, String creationDate, String lastUpdatedBy,
			String lastUpdatedDate, String epAcStatus, String epAcNowid,
			String epAcNowuser, String epAcNextid, String epAcNextuser) {
		super();
		this.hsEquipmentrepairId = hsEquipmentrepairId;
		this.equipmentrepairId = equipmentrepairId;
		this.epId = epId;
		this.epType = epType;
		this.userId = userId;
		this.eprCkTime = eprCkTime;
		this.eprCkVideo = eprCkVideo;
		this.eprCkPhoto = eprCkPhoto;
		this.eprCkDescribe = eprCkDescribe;
		this.eprCkLevel = eprCkLevel;
		this.epHomeEqutype = epHomeEqutype;
		this.epHomeEquname = epHomeEquname;
		this.epReNo = epReNo;
		this.epReId = epReId;
		this.epReKind = epReKind;
		this.epReGroup = epReGroup;
		this.epReDescribe = epReDescribe;
		this.epReTime = epReTime;
		this.epReStatus = epReStatus;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
		this.epAcStatus = epAcStatus;
		this.epAcNowid = epAcNowid;
		this.epAcNowuser = epAcNowuser;
		this.epAcNextid = epAcNextid;
		this.epAcNextuser = epAcNextuser;
	}

	public HsequipmentRepair() {
		super();
		// TODO Auto-generated constructor stub
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