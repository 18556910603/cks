package sz.zp.cks.entity;

import java.io.Serializable;

public class AccountTest implements Serializable{
    /**
	 * 测试专用类
	 */
	private static final long serialVersionUID = 2769501697455747287L;
	private String accLogin;
    private String accName;
    private String count;
	public String getAccLogin() {
		return accLogin;
	}
	public void setAccLogin(String accLogin) {
		this.accLogin = accLogin;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
    
    
    
}
