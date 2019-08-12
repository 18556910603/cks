package sz.zp.cks.model.apm;

import java.io.Serializable;

public class SuccessResponse  implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;// 返回的话术


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SuccessResponse(String msg) {
		super();
		this.msg = msg;
	}

  
  
  
  
}
