package sz.zp.cks.model;

public class EquipmentT {
	//ID
	private String epId;
	//名称
	private String epName;
	//状态
	private String statusVal;
	//状态名称
	private String statusName;
	//规格型号
	private String epModelNum;
	//安装地点	
	private String epLocation;
	//设备类型
	private String epType;
	//设备类型名称
	private String epTypeName;
	
	
	//规定保养日期
	private String maintenanceDate;

	//当前日期距离保养日期相差多少天（未到显示n天/超时显示-n天）
	private String  distanceDays;

	
	//是否在维修中 Y是N不是
	private String  repairIn;
	
	
	
	public EquipmentT() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEpId() {
		return epId;
	}
	public void setEpId(String epId) {
		this.epId = epId;
	}
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public String getStatusVal() {
		return statusVal;
	}
	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getEpModelNum() {
		return epModelNum;
	}
	public void setEpModelNum(String epModelNum) {
		this.epModelNum = epModelNum;
	}
	public String getEpLocation() {
		return epLocation;
	}
	public void setEpLocation(String epLocation) {
		this.epLocation = epLocation;
	}
	public String getEpType() {
		return epType;
	}
	public void setEpType(String epType) {
		this.epType = epType;
	}
	public String getEpTypeName() {
		return epTypeName;
	}
	public void setEpTypeName(String epTypeName) {
		this.epTypeName = epTypeName;
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
	public String getRepairIn() {
		return repairIn;
	}
	public void setRepairIn(String repairIn) {
		this.repairIn = repairIn;
	}
	
	
	
	
	
}
