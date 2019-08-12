package sz.zp.cks.entity;

import java.util.List;

public class ListObject {
	// 列表对象
	private List<?> items;
	private StatusObject statusObject;
	
	public List<?> getItems() {		
		return items;
	}
	
	public void setItems(List<?> items) {
		this.items = items;
	}
	
	public StatusObject getStatusObject() {
		return statusObject;
	}
	public void setStatusObject(StatusObject statusObject) {
		this.statusObject = statusObject;
	}
}
