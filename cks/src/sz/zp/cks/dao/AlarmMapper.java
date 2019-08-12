package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.Alarm;
import sz.zp.cks.model.NewsCounts;

public interface AlarmMapper extends BaseMapper<Alarm>{
	//public int create(Alarm alarm)throws Exception;
	//zdy
	public List<Alarm> getAllAlarms();
	public Alarm getNewAlarm();
	//判断uuid是否已经存在
	public int determineAlarmId(String alarmId);
	public int determineAlarmIdAndNormalTime(Alarm alarm);
	public NewsCounts getAlarmCounts();
}
