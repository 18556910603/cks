package sz.zp.cks.entity;

import java.io.Serializable;

public class ElectricalMaintain implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3464016831457127258L;

	private String maintainId;
//巡检记录id
    private String checksId;
//保养人员id
    private String userId;
//保养情况描述
    private String maintainDescribe;
//保养完成时间
    private String maintainTime;
//设备清洁、无油垢、灰尘
    private String clear;
//电气线路有无损坏
    private String line;
//加机油
    private String oil;

    private String createdBy;

    private String creationDate;

    private String lastUpdatedBy;

    private String lastUpdatedDate;

	//设备编号
    private String epId;
    //数据来源类型
    private String reType;    
    //是否已经完成
    private String completeOrNot;    
    //（上一次）距离规定的保养日期剩余天数
	private String  distanceDays;
	//（上一次）规定日期的保养日期
	private String  maintenanceDate;     
	//任务来源负责人
	private String  leaderId; 
	
    public String getReType() {
		return reType;
	}

	public void setReType(String reType) {
		this.reType = reType;
	}

	public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getChecksId() {
        return checksId;
    }

    public void setChecksId(String checksId) {
        this.checksId = checksId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

	public String getEpId() {
		return epId;
	}

	public void setEpId(String epId) {
		this.epId = epId;
	}

	public String getCompleteOrNot() {
		return completeOrNot;
	}

	public void setCompleteOrNot(String completeOrNot) {
		this.completeOrNot = completeOrNot;
	}

	public String getDistanceDays() {
		return distanceDays;
	}

	public void setDistanceDays(String distanceDays) {
		this.distanceDays = distanceDays;
	}

	public String getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

    
    
    
    
    
}