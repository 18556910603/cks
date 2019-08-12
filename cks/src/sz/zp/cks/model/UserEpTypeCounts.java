package sz.zp.cks.model;


public class UserEpTypeCounts {
	private String userName;
	//电气设备
	private String electricalEp;
	//消防设备
	private String fireEp;
	//生产设备
	private String productEp;
	//公共设备
	private String publicEp;	
	//测试
	private String testEp;	
	
	public String getPublicEp() {
		return publicEp;
	}
	public void setPublicEp(String publicEp) {
		this.publicEp = publicEp;
	}
	//今日巡检次数
	private String todayCks;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getElectricalEp() {
		return electricalEp;
	}
	public void setElectricalEp(String electricalEp) {
		this.electricalEp = electricalEp;
	}
	public String getFireEp() {
		return fireEp;
	}
	public void setFireEp(String fireEp) {
		this.fireEp = fireEp;
	}
	public String getProductEp() {
		return productEp;
	}
	public void setProductEp(String productEp) {
		this.productEp = productEp;
	}
	public String getTodayCks() {
		return todayCks;
	}
	public void setTodayCks(String todayCks) {
		this.todayCks = todayCks;
	}
	public String getTestEp() {
		return testEp;
	}
	public void setTestEp(String testEp) {
		this.testEp = testEp;
	}
	
	
	
	
	
	
	
}
