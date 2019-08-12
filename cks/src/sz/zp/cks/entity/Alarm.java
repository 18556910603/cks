package sz.zp.cks.entity;

import java.io.Serializable;

/**
 * @author WL 
 * 2019-4-22
 *
 */
public class Alarm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alarmId;  //告警编号
	private String source;   //数据源路径  如：WLszElectricPowerPlant_Factory031_DQS20190220-001_temperature  第三段DQS20190220-001为设备编号
	private String timestamp;  //异常发生时间
	private String sourceState;  //设备状态normal/offnormal
	private String ackState;    //是否确认  默认unacked
	private String priority;  //告警等级1-127
	private String alarmClass;  //告警类别  默认HighPriorityAlarms
	private String alarmText;   //告警信息
	private String lowLimit;  //下限
	private String highLimit;  //上限
	private String value;//实时读数
	private String natureMessage;//原始报文

	private String type;//来源  AP:代表APM NI代表Niagara

	private String creationDate;//记录创建时间
	
	private String normalTime;//恢复正常时间
	public String getLowLimit() {
		return lowLimit;
	}
	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}
	public String getHighLimit() {
		return highLimit;
	}
	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSourceState() {
		return sourceState;
	}
	public void setSourceState(String sourceState) {
		this.sourceState = sourceState;
	}
	public String getAckState() {
		return ackState;
	}
	public void setAckState(String ackState) {
		this.ackState = ackState;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getAlarmClass() {
		return alarmClass;
	}
	public void setAlarmClass(String alarmClass) {
		this.alarmClass = alarmClass;
	}
	public String getAlarmText() {
		return alarmText;
	}
	public void setAlarmText(String alarmText) {
		this.alarmText = alarmText;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNatureMessage() {
		return natureMessage;
	}
	public void setNatureMessage(String natureMessage) {
		this.natureMessage = natureMessage;
	}
	public String getNormalTime() {
		return normalTime;
	}
	public void setNormalTime(String normalTime) {
		this.normalTime = normalTime;
	}

	

	
}
