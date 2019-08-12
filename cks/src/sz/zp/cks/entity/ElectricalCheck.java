package sz.zp.cks.entity;

import java.io.Serializable;

public class ElectricalCheck implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2268977179953281338L;

	private String checksId;

    private String epId;

    private String statusOldVal;

    private String userId;

    private String checksLoc;

    private String checksPhoto;

    private String checksTime;

    private String mainPart;

    private String oilTank;

    private String voice;

    private String sleeve;

    private String drainageWire;

    private String fan;

    private String silicaGel;

    private String isNormal;

    private String statusNewVal;

    // 创建人
    private String createdBy;
    //创建时间
    private String creationDate;
    //更新人
    private String lastUpdatedBy;
    //更新时间
    private String lastUpdatedDate;  
    
    //保养单id
	private String maintainId;
    
	//当前日期距离保养日期相差多少天（未到显示n天/超时显示-n天）
	private String  distanceDays;
	//规定日期的保养日期
	private String  maintenanceDate;   
    
    
    public String getChecksId() {
        return checksId;
    }

    public void setChecksId(String checksId) {
        this.checksId = checksId;
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


    public String getStatusOldVal() {
        return statusOldVal;
    }

    public void setStatusOldVal(String statusOldVal) {
        this.statusOldVal = statusOldVal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChecksLoc() {
        return checksLoc;
    }

    public void setChecksLoc(String checksLoc) {
        this.checksLoc = checksLoc;
    }

    public String getChecksPhoto() {
        return checksPhoto;
    }

    public void setChecksPhoto(String checksPhoto) {
        this.checksPhoto = checksPhoto;
    }

    public String getChecksTime() {
        return checksTime;
    }

    public void setChecksTime(String checksTime) {
        this.checksTime = checksTime;
    }

    public String getMainPart() {
        return mainPart;
    }

    public void setMainPart(String mainPart) {
        this.mainPart = mainPart;
    }

    public String getOilTank() {
        return oilTank;
    }

    public void setOilTank(String oilTank) {
        this.oilTank = oilTank;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getSleeve() {
        return sleeve;
    }

    public void setSleeve(String sleeve) {
        this.sleeve = sleeve;
    }

    public String getDrainageWire() {
        return drainageWire;
    }

    public void setDrainageWire(String drainageWire) {
        this.drainageWire = drainageWire;
    }

    public String getFan() {
        return fan;
    }

    public void setFan(String fan) {
        this.fan = fan;
    }

    public String getSilicaGel() {
        return silicaGel;
    }

    public void setSilicaGel(String silicaGel) {
        this.silicaGel = silicaGel;
    }

    public String getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(String isNormal) {
        this.isNormal = isNormal;
    }

    public String getStatusNewVal() {
        return statusNewVal;
    }

    public void setStatusNewVal(String statusNewVal) {
        this.statusNewVal = statusNewVal;
    }


	public ElectricalCheck() {
		super();
	}

	public String getEpId() {
		return epId;
	}

	public String getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
	}

	public void setEpId(String epId) {
		this.epId = epId;
	}

	@Override
	public String toString() {
		return "ElectricalCheck [checksId=" + checksId + ", epId=" + epId
				+ ", statusOldVal=" + statusOldVal + ", userId=" + userId
				+ ", checksLoc=" + checksLoc + ", checksPhoto=" + checksPhoto
				+ ", checksTime=" + checksTime + ", mainPart=" + mainPart
				+ ", oilTank=" + oilTank + ", voice=" + voice + ", sleeve="
				+ sleeve + ", drainageWire=" + drainageWire + ", fan=" + fan
				+ ", silicaGel=" + silicaGel + ", isNormal=" + isNormal
				+ ", statusNewVal=" + statusNewVal + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", lastUpdatedBy="
				+ lastUpdatedBy + ", lastUpdatedDate=" + lastUpdatedDate
				+ ", maintainId=" + maintainId + "]";
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

    
    
    
    
}