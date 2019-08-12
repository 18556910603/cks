package sz.zp.cks.entity;

import java.io.Serializable;

public class SysUser implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8604794866829738851L;

	private Integer userId;

    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}