package sz.zp.cks.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PubFun {

	
	//获取当前时间
	public static String getCurrentDate(){
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    return df.format(new Date());// new Date()为获取当前系统时间
	}
    /**** 
     * 传入具体日期 ，返回具体日期增加一个月。 
     * @param date 日期(2017-04-13) 
     * @return 2017-05-13
     * @throws ParseException 
     */  
	public static  String subMonth(String date) throws ParseException  {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date dt = sdf.parse(date);  
        Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(dt);  
        rightNow.add(Calendar.MONTH, 1);  
        Date dt1 = rightNow.getTime();  
        String reStr = sdf.format(dt1);  
        return reStr;  
    }
    /**** 
     * 读取报文测试
     * @param fileName文件路径
     * @return 2019-04-25
     * @throws   
     */  	
	public  static String readJson(String fileName){
		InputStream fis = null;
		ByteArrayOutputStream bos = null;
		
		byte[] buffer = new byte[1024];
		int length = -1;
		try {
		    fis = new FileInputStream(new File(fileName));
			bos = new ByteArrayOutputStream();
			while((length=fis.read(buffer))!=-1){
				bos.write(buffer,0,length);
			}
			bos.close();
			fis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] lens=bos.toByteArray();
		String arg0=new String(lens);
		arg0=arg0.trim();
		return arg0;
	}
	/**
	 * 获取字符s在字符串url中第i次出现的下标（从0开始）
	 * @param url
	 * @param s
	 * @param i
	 * @return
	 */
	public static int getCharacterPosition(String url,String s,int i){
	    //这里是获取"/"符号的位置	lastindexof从字符串末尾开始检索，检索到子字符
	    Matcher slashMatcher = Pattern.compile(s).matcher(url);
	    int mIdx = 0;
	    while(slashMatcher.find()) {
	       mIdx++;
	       //当"/"符号第i次出现的位置
	       if(mIdx == i){
	          break;
	       }
	    }
	    return slashMatcher.start();
	}		
	
	
}
