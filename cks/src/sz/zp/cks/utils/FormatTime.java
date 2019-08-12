package sz.zp.cks.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FormatTime {
	
	public static String getTime(String time) {	
  		String[] arrayA= time.split("-");
  		String dd="";
  		String MM="";
  		String yyyy="";
  		String t="";
        dd=arrayA[0];
        String MMstr=arrayA[1];
  		SimpleDateFormat sdf1= new SimpleDateFormat("MMM", Locale.US);
  		SimpleDateFormat sdf2= new SimpleDateFormat("MM");
  		try {
  			MM=sdf2.format(sdf1.parse(MMstr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		yyyy="20"+arrayA[2].substring(0, 2);
  		String dateStr=arrayA[2].substring(3);
  		SimpleDateFormat sdf3 = new SimpleDateFormat("KK:mm:ss aa z", Locale.ENGLISH);
  		SimpleDateFormat sdf4= new SimpleDateFormat("HH:mm:ss");
  		try {
		    t = sdf4.format(sdf3.parse(dateStr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		String newTime=yyyy+"-"+MM+"-"+dd+" "+t;
	   return newTime;		
	}
     
	public static String localToUTC(String localTime) {
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date localDate= null;
	    try {
	        localDate = sdf.parse(localTime);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    long localTimeInMillis=localDate.getTime();
	    /** long时间转换成Calendar */
	    Calendar calendar= Calendar.getInstance();
	    calendar.setTimeInMillis(localTimeInMillis);
	    /** 取得时间偏移量 */
	    int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
	    /** 取得夏令时差 */
	    int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
	    /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
	    calendar.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
	    /** 取得的时间就是UTC标准时间 */
	    Date utcDate=new Date(calendar.getTimeInMillis());
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	    //sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String utcDateStr=sdf1.format(utcDate);
	    return utcDateStr;
	}

	
  
/** xuyaya  2019/04/29
 * utc时间转本地时间
 * @param UTCStr UTC时间
 * @param UTCformat UTC时间格式
 * @param localFormat 要转成本地时间的本地时间格式
 * @return 本地时间
 * @throws ParseException
 */
	public static String UTCToCST(String UTCStr, String UTCformat,
			String localFormat) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(UTCformat);
		String localTime = "";
		try {
			if(sdf.parse(UTCStr)!=null){
			date = sdf.parse(UTCStr);}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
			SimpleDateFormat sdf2 = new SimpleDateFormat(localFormat);
			localTime = sdf2.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return localTime;
	}
    
    /**
     * utc时间转成local时间
     * @param utcTime
     * @return
     */
    public static Date utcToLocal(String utcTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;
        try {
            utcDate = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        Date locatlDate = null;
        String localTime = sdf.format(utcDate.getTime());
        try {
            locatlDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locatlDate;
    }
    public static void main(String args[]) throws ParseException {

//      UTCToCST("2017-11-27T03:16:03.944Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
     System.out.println(UTCToCST("2018-08-23T12:00:00.000Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd HH:mm:ss")); 
         String date="2019-5-15 10:07:00";
         String utc = FormatTime.localToUTC(date);
         System.out.println(utc);
  }
  

}
