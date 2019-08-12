package sz.zp.cks.model;

public class StaticJspValue {
	//设备类型
	private String epType;
	//今日已检台数
	private String todayChecked;
	//今日待检台数
	private String todayUnChecked;
	
	public String getTodayUnChecked() {
		return todayUnChecked;
	}
	public void setTodayUnChecked(String todayUnChecked) {
		this.todayUnChecked = todayUnChecked;
	}
	public String getEpType() {
		return epType;
	}
	public void setEpType(String epType) {
		this.epType = epType;
	}
	public String getTodayChecked() {
		return todayChecked;
	}
	public void setTodayChecked(String todayChecked) {
		this.todayChecked = todayChecked;
	}
	
	
	
	
	
	
	
}
