package sz.zp.cks.entity;

public class WXButton {
	private String type;
	private String name;
	private WXButton[] sub_button;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WXButton[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(WXButton[] sub_button) {
		this.sub_button = sub_button;
	}


}
