//package sz.zp.cks.weixin;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Timer;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import javax.annotation.Resource;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import sz.zp.cks.action.WXMaintainTimeTask;
//
///**
// * 定时器 容器类
// * @author Lenovo
// *
// */
//@Component("BeanDefineConfigue") 
//public class WXMaintainTimer implements ApplicationListener<ContextRefreshedEvent>{
//	
//	
//	
//	private static DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//    private static DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
//	Timer timer;
//	//private static ScheduledExecutorService excutor  = Executors.newScheduledThreadPool(2);
//
//
//	public WXMaintainTimer(){
//		//Date time = getTime();
//		//System.out.println("指定时间time="+time);
//		//timer = new Timer();
//		//timer.schedule(new WXMaintainTimeTask(), time, 1000);//一天为43200000
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);  
//        // long oneDay = 24 * 60 * 60 * 1000;  
//        long oneDay = 30 * 1000;  
//        System.out.println("oneday##################################################################################################################################################################################"+oneDay);
//        long initDelay  = getTimeMillis("15:47:00") - System.currentTimeMillis();  
//        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;  
//        executor.scheduleAtFixedRate(  
//                new WXMaintainTimeTask(),  
//                initDelay,  
//                oneDay,  
//                TimeUnit.MILLISECONDS); 
//    }
//	/**
//	 * 获取当天的特定时间点（time）的时间long值
//	 * @param time 特定时间点
//	 * @return
//	 */
//	
//    public long getTimeMillis(String time) {        
//        try {
//            Date currentDate = dateFormat.parse(dayFormat.format(new Date()) + " " +time);
//            return currentDate.getTime() ;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    /**
//	 * 获取当前时间
//	 * @return
//	 */
//	public Date getTime(){
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY,8);
//		calendar.set(Calendar.MINUTE, 30);
//		calendar.set(Calendar.SECOND, 00);
//		Date time = calendar.getTime();
//		return time;
//	}
//
//	public static void main(String[] args) {
//		new WXMaintainTimer();
//		
//
//	}
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent arg0) {
//		// TODO Auto-generated method stub
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
//
//		new WXMaintainTimer();
//		System.out.println("-----------------------------------------------------------------------------------------------------");
//		System.out.println("-----------------------------------------------------------------------------------------------------");
//		System.out.println("-----------------------------------------------------------------------------------------------------");
//		System.out.println("-----------------------------------------------------------------------------------------------------");
//
//
//	}
//}
