package sz.zp.cks.model.apm;

import java.util.List;

public class RequestBody {
	private String type;
	private String category;
	private List<Documents> documents;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<Documents> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}
	
	
	
	
}
