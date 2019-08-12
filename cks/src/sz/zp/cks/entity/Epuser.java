package sz.zp.cks.entity;

public class Epuser {
    private String epUserId;

	private String userId;

	private String epUserType;

	private String epUserLoc;

	private String createdby;

	private String creationdate;

	private String lastupdatedby;

	private String lastupdateddate;
	
	private User user;

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getEpUserId() {
		return epUserId;
	}


	public void setEpUserId(String epUserId) {
		this.epUserId = epUserId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getEpUserType() {
		return epUserType;
	}


	public void setEpUserType(String epUserType) {
		this.epUserType = epUserType;
	}


	public String getEpUserLoc() {
		return epUserLoc;
	}


	public void setEpUserLoc(String epUserLoc) {
		this.epUserLoc = epUserLoc;
	}


	public String getCreatedby() {
		return createdby;
	}


	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}


	public String getCreationdate() {
		return creationdate;
	}


	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}


	public String getLastupdatedby() {
		return lastupdatedby;
	}


	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}


	public String getLastupdateddate() {
		return lastupdateddate;
	}


	public void setLastupdateddate(String lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}


	@Override
	public String toString() {
		return "Epuser [epUserId=" + epUserId + ", userId=" + userId
				+ ", epUserType=" + epUserType + ", epUserLoc=" + epUserLoc
				+ ", createdby=" + createdby + ", creationdate=" + creationdate
				+ ", lastupdatedby=" + lastupdatedby + ", lastupdateddate="
				+ lastupdateddate + ", user=" + user + "]";
	}



}