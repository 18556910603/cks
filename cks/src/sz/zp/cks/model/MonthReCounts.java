package sz.zp.cks.model;
/**
 * 
 * @author 记录每个月巡检和业主报修的次数
 *
 */
public class MonthReCounts {

	
	
	private String month;
	private Integer ownCounts;
	private Integer ckCounts;
	
	
	

	public MonthReCounts(String month, Integer ownCounts, Integer ckCounts) {
		super();
		this.month = month;
		this.ownCounts = ownCounts;
		this.ckCounts = ckCounts;
	}




	public String getMonth() {
		return month;
	}




	public void setMonth(String month) {
		this.month = month;
	}




	public Integer getOwnCounts() {
		return ownCounts;
	}




	public void setOwnCounts(Integer ownCounts) {
		this.ownCounts = ownCounts;
	}




	public Integer getCkCounts() {
		return ckCounts;
	}




	public void setCkCounts(Integer ckCounts) {
		this.ckCounts = ckCounts;
	}




	public MonthReCounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
