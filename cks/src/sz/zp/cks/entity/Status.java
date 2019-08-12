package sz.zp.cks.entity;

import java.io.Serializable;

public class Status implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5717250433560710289L;
	//状态id
	private String statusId;
	// 设备id
    private String epId;
    //设备状态
    private String statusVal;
    //数据来源类型
    private String reType;
    // 数据来源表单id
    private String reTypeid;
    //备注（扩展字段）
    private String remarks;
    // 创建人
    private String createdBy;
    //创建时间
    private String creationDate;
    //更新人
    private String lastUpdatedBy;
    //更新时间
    private String lastUpdatedDate;

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