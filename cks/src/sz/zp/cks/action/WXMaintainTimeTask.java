package sz.zp.cks.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sz.zp.cks.dao.EquipmentMapper;
import sz.zp.cks.dao.UserMapper;
import sz.zp.cks.entity.Equipment;
import sz.zp.cks.entity.User;
import sz.zp.cks.service.ElectricalCheckService;
import sz.zp.cks.service.WXMaintainService;
import sz.zp.cks.service.impl.WXMaintainServiceImpl;
import sz.zp.cks.weixin.Create_Json;

/**
 * 定时器 task类 
 * @author Lenovo
 *
 */
@Component
@RequestMapping("/wXMaintainTimeTask")
//public class WXMaintainTimeTask extends TimerTask {
	public class WXMaintainTimeTask{
	@Resource
	private WXMaintainService wXMaintainService;
	
	private static WXMaintainTimeTask wxMaintainTimeTask;
	
	@PostConstruct 
	 public void init() {
		 wxMaintainTimeTask = this;
		 wxMaintainTimeTask.wXMaintainService = this.wXMaintainService;   // 初使化时将已静态化的testService实例化
	    }
	
	//@Override
	public void run(){
		try {
			
			//WXMaintainService wxMaintainService=new WXMaintainServiceImpl();
			List<String> epIdlist=new ArrayList<String>();
			
			//ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
			//wXMaintainService=(WXMaintainService) context.getBean("wXMaintainService");
			epIdlist=wxMaintainTimeTask.wXMaintainService.getNeedMaintainEpId();
			if(!epIdlist.isEmpty()){
				for(String epid:epIdlist){
					//String userid= wxMaintainService.getUserId(epid);
					String wxOpenId=wxMaintainTimeTask.wXMaintainService.getWXOpenId(epid);
					System.out.println("task中wxOpenId-------"+wxOpenId);
					String ep_type=wxMaintainTimeTask.wXMaintainService.getEpType(epid);
					String maintainDate=wxMaintainTimeTask.wXMaintainService.getMaintainDate(epid);
					if(!(wxOpenId==null)&&!(ep_type==null)&&!(maintainDate==null)){
						Create_Json.sendMaintainNotice(wxOpenId, epid, ep_type, maintainDate);
					}else{
						System.out.println("存在为空数据，保养信息提醒失败");
					}
					System.out.println("111111111111111111wxtask");
				}
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("WXMaintainTimeTask--1");
	}

	/**
	 * 比较保养时间 ；返回还有多少天超时； 返回值为-2 已超时；
	 * @param end_date
	 * @return
	 */
	//	public long getTwoDayDiff(String end_date){
	//		long day = 0;
	//		try {
	//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	//			String sdate = format.format(Calendar.getInstance().getTime());//获取现在时间，转换为年月日的string类型
	//			long nowdate=format.parse(sdate).getTime();//parse()方法可把string转为date。date的gettime方法是获取long类型时间值
	//			long enddate=format.parse(end_date).getTime();
	//			if(enddate<=nowdate){
	//				day = -2;//返回值为-2 已超时
	//			}else{
	//				day = (enddate-nowdate)/(24*60*60*1000);//返回还有多少天超时
	//			}
	//		} catch (Exception e) {
	//			return -1;
	//		}
	//		
	//		return day;
	//	}


	public static void main(String[] args) {
		WXMaintainTimeTask wt=new WXMaintainTimeTask();
		wt.run();
	}
}
