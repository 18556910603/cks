package sz.zp.cks.common;

/**
 * 常量
 * 
 */
public interface SSIConstants {
	public static final String SESSIONCONTAINER = "SessionContainer";
	/**
	 * 格式�?24小时�?<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_DateTime = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 格式�?12小时�?<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_DateTime_12 = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 格式�?br>
	 * FORMAT_DateTime: 日期
	 */
	public static final String FORMAT_Date = "yyyy-MM-dd";

	/**
	 * 格式�?24小时�?<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_Time = "HH:mm:ss";
	
	/**
	 * 格式�?12小时�?<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_Time_12 = "hh:mm:ss";

	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
	
}