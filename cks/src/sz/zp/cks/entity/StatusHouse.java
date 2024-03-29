package sz.zp.cks.entity;
  /**
  * 状态封装类
  * 将code和msg组合到一起，方便使用
  */
public class StatusHouse {
	
	public static StatusObject COMMON_STATUS_OK = new StatusObject(StatusCode.CODE_SUCCESS, "访问成功");

	public static StatusObject COMMON_STATUS_ERROR = new StatusObject(StatusCode.CODE_ERROR, "访问错误，错误码：(" + StatusCode.CODE_ERROR + ")");

	public static StatusObject COMMON_STATUS_NO_LOGIN_OR_TIMEOUT = new StatusObject(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT, "未登录或登录超时,请重新登录,错误码：(" + StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT + ")");

	public static StatusObject COMMON_STATUS_ERROR_PROGRAM = new StatusObject(StatusCode.CODE_ERROR_PROGRAM, "程序异常，错误码：(" + StatusCode.CODE_ERROR_PROGRAM + ")");

	public static StatusObject COMMON_STATUS_ERROR_PARAMETER = new StatusObject(StatusCode.CODE_ERROR_PARAMETER, "参数错误，错误码：(" + StatusCode.CODE_ERROR_PARAMETER + ")");

	public static StatusObject COMMON_STATUS_EXIST_OPERATION = new StatusObject(StatusCode.CODE_ERROR_EXIST_OPERATION, "已操作，错误码：(" + StatusCode.CODE_ERROR_EXIST_OPERATION + ")");
	
}
