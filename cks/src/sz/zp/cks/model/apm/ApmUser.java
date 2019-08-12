package sz.zp.cks.model.apm;

import java.io.Serializable;

public class ApmUser implements Serializable {

	/**
	 * APM登录后可以获得的对象
	 */
	private static final long serialVersionUID = -8669089187297279735L;

	private String expiresIn;
	private String tokenType;
	private String accessToken;
	private String refreshToken;
	private String dashboardUrl;
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getDashboardUrl() {
		return dashboardUrl;
	}
	public void setDashboardUrl(String dashboardUrl) {
		this.dashboardUrl = dashboardUrl;
	}
	@Override
	public String toString() {
		return "ApmUser [expiresIn=" + expiresIn + ", tokenType=" + tokenType
				+ ", accessToken=" + accessToken + ", refreshToken="
				+ refreshToken + ", dashboardUrl=" + dashboardUrl + "]";
	}
	
	
	
	
	
	
}
