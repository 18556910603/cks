package sz.zp.cks.common;

import net.sf.json.JSONObject;
import sz.zp.cks.entity.Account;
import sz.zp.cks.entity.User;

/**
 * Session容器 将此类放入到session 方便知道session中有那些数据
 * @author Administrator
 *
 */
public class SessionContainer {

	/**
	 * 后台管理员用户
	 */
	private User user;
	
	private JSONObject menutree;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JSONObject getMenutree() {
		return menutree;
	}

	public void setMenutree(JSONObject menutree) {
		this.menutree = menutree;
	}

	
}
