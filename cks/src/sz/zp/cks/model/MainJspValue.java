package sz.zp.cks.model;

public class MainJspValue {
	//今日已检
	private String todayCks;
	//今日报修
	private String todayRps;
	//正常运行设备台数
	private String normalOperation;
	//异常运行设备台数
	private String abnormalOperation;
	//停机维修设备台数
	private String repairOperation;
	//报废停用设备台数
	private String scrapOperation;
	//维修中设备台数
	private String maintenanceState;
	
	//非正常运行
	private String unNormalOperation;
	
	public String getTodayCks() {
		return todayCks;
	}
	public void setTodayCks(String todayCks) {
		this.todayCks = todayCks;
	}
	public String getTodayRps() {
		return todayRps;
	}
	public void setTodayRps(String todayRps) {
		this.todayRps = todayRps;
	}
	public String getNormalOperation() {
		return normalOperation;
	}
	public void setNormalOperation(String normalOperation) {
		this.normalOperation = normalOperation;
	}
	public String getAbnormalOperation() {
		return abnormalOperation;
	}
	public void setAbnormalOperation(String abnormalOperation) {
		this.abnormalOperation = abnormalOperation;
	}
	public String getMaintenanceState() {
		return maintenanceState;
	}
	public void setMaintenanceState(String maintenanceState) {
		this.maintenanceState = maintenanceState;
	}
	
	public String getRepairOperation() {
		return repairOperation;
	}
	public void setRepairOperation(String repairOperation) {
		this.repairOperation = repairOperation;
	}
	public String getScrapOperation() {
		return scrapOperation;
	}
	public void setScrapOperation(String scrapOperation) {
		this.scrapOperation = scrapOperation;
	}
	public String getUnNormalOperation() {
		return unNormalOperation;
	}
	public void setUnNormalOperation(String unNormalOperation) {
		this.unNormalOperation = unNormalOperation;
	}

	
	
	
	
	
	
	
}
