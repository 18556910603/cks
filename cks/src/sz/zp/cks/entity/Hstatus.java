package sz.zp.cks.entity;

import java.io.Serializable;

public class Hstatus implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 496357710266622968L;

	private String hstatusId;

    private String statusId;

    private String epId;

    private String statusVal;

    private String reType;

    private String reTypeid;

    private String remarks;

    private String createdBy;

    private String creationDate;

    private String lastUpdatedBy;

    private String lastUpdatedDate;

    public String getHstatusId() {
        return hstatusId;
    }

    public void setHstatusId(String hstatusId) {
        this.hstatusId = hstatusId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getStatusVal() {
        return statusVal;
    }

    public void setStatusVal(String statusVal) {
        this.statusVal = statusVal;
    }

    public String getReType() {
        return reType;
    }

    public void setReType(String reType) {
        this.reType = reType;
    }

    public String getReTypeid() {
        return reTypeid;
    }

    public void setReTypeid(String reTypeid) {
        this.reTypeid = reTypeid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}