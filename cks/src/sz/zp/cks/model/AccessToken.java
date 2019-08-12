package sz.zp.cks.model;

public class AccessToken {
	 
	
	
	private String accessToken;
	private long expireTime;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	public AccessToken(String accessToken, String expiresIn) {
		super();
		this.accessToken = accessToken;
		//计算过期时间
		expireTime=System.currentTimeMillis()+Integer.parseInt(expiresIn)*1000;
	}
	//判断token是否过期
	public boolean isExpired(){
		return System.currentTimeMillis()>expireTime;
	}
	
	
}
