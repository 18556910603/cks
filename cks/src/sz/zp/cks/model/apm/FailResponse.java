package sz.zp.cks.model.apm;

import java.io.Serializable;

public class FailResponse  implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String code;// 编码，正确返回不需要
	String msg;// 返回的话术

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public FailResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
  
  
  
  
}
