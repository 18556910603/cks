package sz.zp.cks.entity;

import java.io.Serializable;

public class Department implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8838717769664266587L;

	private String deptId;

    private String deptName;

    private String deptType;

    private String leaderId;

    private String description;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(String deptId, String deptName, String deptType,
			String leaderId, String description) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptType = deptType;
		this.leaderId = leaderId;
		this.description = description;
	}
    
    
    
    
}