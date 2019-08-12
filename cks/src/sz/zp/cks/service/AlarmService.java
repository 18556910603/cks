package sz.zp.cks.service;

import java.util.List;

import sz.zp.cks.entity.Alarm;
import sz.zp.cks.model.NewsCounts;

public interface AlarmService extends BaseService<Alarm> {
	
	public List<Alarm> getAllAlarms();
	
	public Alarm getNewAlarm();
	
	/**
	 * 告警数量统计 异常 告警 正常
	 */
	public NewsCounts getAlarmCounts();
	
	
	
	
	
}
