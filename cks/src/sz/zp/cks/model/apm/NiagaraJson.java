package sz.zp.cks.model.apm;

import java.io.Serializable;

public class NiagaraJson implements Serializable {
	/**
	 * 从南瓜取得的数据
	 */
	private static final long serialVersionUID = 1L;
	private String source;//数据来源，设备具体点位
	private String normalTime;//恢复正常时间		
	private String timestamp;//告警异常时间、恢复正常时间，告警确认时间(只是确认收到)
	private String sourceState;//告警类型：Offnormal、normal、Fault
	private String ackState;//告警确认：Unacked、acked
	private String priority;//告警等级：1~188,1最大
	private String alarmClass;// 选择指定的告警组件(名称可自定义)
	private String text;//告警内容
	private String lowLimit;//低限
	private String highLimit;//上限
	private String hyperlinkOrd;//告警时超链接按钮就会激活。单击此按钮可链接到此处指定的位置。
	private String value;//告警读数
	private String uuid;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getNormalTime() {
		return normalTime;
	}
	public void setNormalTime(String normalTime) {
		this.normalTime = normalTime;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
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
	public String getHyperlinkOrd() {
		return hyperlinkOrd;
	}
	public void setHyperlinkOrd(String hyperlinkOrd) {
		this.hyperlinkOrd = hyperlinkOrd;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "NiagaraJson [source=" + source + ", normalTime=" + normalTime
				+ ", timestamp=" + timestamp + ", sourceState=" + sourceState
				+ ", ackState=" + ackState + ", priority=" + priority
				+ ", alarmClass=" + alarmClass + ", text=" + text
				+ ", lowLimit=" + lowLimit + ", highLimit=" + highLimit
				+ ", hyperlinkOrd=" + hyperlinkOrd + ", value=" + value + "]";
	}
	
}
