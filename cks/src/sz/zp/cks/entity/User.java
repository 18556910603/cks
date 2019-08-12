package sz.zp.cks.entity;

import java.io.Serializable;

public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3784361929476378952L;

	private String userId;

    private String userName;

    private String loginName;

    private String password;

    private String email;

    private String mobile;
    
    private String type;

    private String employeeBaseId;

    private Integer status;

    private String createdBy;

    private String creationDate;

    private String lastUpdateBy;

    private String lastUpdateDate;
    
    private String identity;
    
    
    
    //zdy
    private String wxOpenId;
    

    public String getWxOpenId() {
		return wxOpenId;
	}


	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


    public String getUserId() {
		return userId;
	}


	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getEmployeeBaseId() {
		return employeeBaseId;
	}


	public void setEmployeeBaseId(String employeeBaseId) {
		this.employeeBaseId = employeeBaseId;
	}


	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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


	public String getLastUpdateBy() {
		return lastUpdateBy;
	}


	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}


	public String getLastUpdateDate() {
		return lastUpdateDate;
	}


	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public String getIdentity() {
		return identity;
	}


	public void setIdentity(String identity) {
		this.identity = identity;
	}



}