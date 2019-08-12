package sz.zp.cks.model.apm;

import java.util.Map;

public class Documents {
	private String topoId;
	private String nodeName; 
	private String topoName;
	private String nodeId;
	private String scadaId;	
	private String deviceId; 
	private String eventName;
	private String eventTime; 
	private String subject; 
	private String content;	
	private String actionTime; 
	private String actionTypes;	
	private String level;
	private Map<String, ApmRule> ruleTagValueMap;
	private String path;		 				
	private String recoverTime;	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getRecoverTime() {
		return recoverTime;
	}
	public void setRecoverTime(String recoverTime) {
		this.recoverTime = recoverTime;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getScadaId() {
		return scadaId;
	}
	public void setScadaId(String scadaId) {
		this.scadaId = scadaId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getActionTypes() {
		return actionTypes;
	}
	public void setActionTypes(String actionTypes) {
		this.actionTypes = actionTypes;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTopoId() {
		return topoId;
	}
	public void setTopoId(String topoId) {
		this.topoId = topoId;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getTopoName() {
		return topoName;
	}
	public void setTopoName(String topoName) {
		this.topoName = topoName;
	}
	public Map getRuleTagValueMap() {
		return ruleTagValueMap;
	}
	public void setRuleTagValueMap(Map ruleTagValueMap) {
		this.ruleTagValueMap = ruleTagValueMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
