package sz.zp.cks.model.apm;

public class ApmRule {
	private String comparison;
	private String level;	
	private String threshold;		
//	private Tag electricity;//备用字段	
	private String symbols;		
	private String status;
	public String getComparison() {
		return comparison;
	}

	public void setComparison(String comparison) {
		this.comparison = comparison;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	public String getSymbols() {
		return symbols;
	}
	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public Tag getElectricity() {
//		return electricity;
//	}
//	public void setElectricity(Tag electricity) {
//		this.electricity = electricity;
//	}

	
	
	
	
}
