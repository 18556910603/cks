package sz.zp.cks.model;

public class ViewButton extends AbstractButton{
	private String type="view";
	private String url;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ViewButton(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public ViewButton(String name,  String url) {
		super(name);
		this.url = url;
	}

}
