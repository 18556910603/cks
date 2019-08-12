package sz.zp.cks.utils;
/**
 * 字符串截取函数
 * @author Lenovo
 *
 */
public class HUtilTest
{
	public static String getInnerText(String s, String sLeft, String sRight)
	{
		int sequenceBegin = s.indexOf(sLeft);
		int sequenceEnd = s.indexOf(sRight,sequenceBegin+sLeft.length());
		if(sequenceBegin<0||sequenceEnd<sequenceBegin)
			return "";
		return s.substring(sequenceBegin + sLeft.length(), sequenceEnd);
	}
	
}
