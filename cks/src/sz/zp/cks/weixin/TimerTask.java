package sz.zp.cks.weixin;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sz.zp.cks.action.WXMaintainTimeTask;

@Component
public class TimerTask {
	  /**
	   * 每天08点01启动任务
	   */
	  @Scheduled(cron = "0 01 08 ? * *")
	  public void test1()
	  {
		  WXMaintainTimeTask wx= new WXMaintainTimeTask();
	    	 wx.run();
	      System.out.println("job1 开始执行..."+System.currentTimeMillis());
	  } 
	  
	  
//	     @Scheduled(cron = "0 0/1 * * * ?")//每隔一分钟一次 
//	  public void test2()
//	  {
//	    	 WXMaintainTimeTask wx= new WXMaintainTimeTask();
//	    	 wx.run();
//	     System.out.println("job2 开始执行");
//	  } 


}
