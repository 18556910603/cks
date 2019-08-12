package sz.zp.cks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.AlarmMapper;
import sz.zp.cks.entity.Alarm;
import sz.zp.cks.model.NewsCounts;
import sz.zp.cks.service.AlarmService;


@Service("alarmService")
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	private AlarmMapper alarmMapper;
	
	@Override
	public int insert(Alarm entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Alarm entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Alarm entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Alarm select(Alarm entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alarm> getAllAlarms() {
		// TODO Auto-generated method stub
		return alarmMapper.getAllAlarms();
	}

	@Override
	public Alarm getNewAlarm() {
		// TODO Auto-generated method stub
		return alarmMapper.getNewAlarm();
	}

	@Override
	public NewsCounts getAlarmCounts() {
		// TODO Auto-generated method stub
		return alarmMapper.getAlarmCounts();
	}

	

}
