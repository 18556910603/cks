package sz.zp.cks.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import sz.zp.cks.dao.EpIdDao;

public class EpIdUtils {

	private static EpIdDao epIdDao = SpringContextHolder.getBean(EpIdDao.class);
	
	public static String getEpId(String epType) throws Exception{		 
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String  second = df.format(date);
		int count = epIdDao.count(epType);
		String third="";
		if (count<10){
			third="00"+String.valueOf(count+1);
		}else if(count<100){
			third="0"+String.valueOf(count+1);
		}else{
			third=String.valueOf(count+1);
        }
		String first="";
		if (epType.equals("1")){
			first="DQS";
		}else if(epType.equals("2")){
			first="SCS";
		}else if(epType.equals("1")){
			first="JYD";
		}
		String epId=first+second+"-"+third;
		
		return epId;
		}
	
	public static String getEpUserId() throws Exception{		 
		
		int count = epIdDao.getNum();
		String epUserId=String.valueOf(count+1);
 		return epUserId;
		}
	
}
