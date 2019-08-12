package sz.zp.cks.model.apm;

public class GetParameter {
	private String topoName; //topoName  optional    String     topo name
	private String nodeId;//nodeId    optional	  Number	 node id
	private String eventName;//eventName optional	  String     event name
	private String level;//level     optional	  String     event level
	private String startTs;//startTs	  required    String     start time, format : ISO 8061 UTC time
	private String endTs;//endTs	  required    String     end time, format : ISO 8061 UTC time
	private String index;//index     optional	  Number     start offset, default value: 0
	private String count;//count     optional	  Number     count of expect obtained data, limit to 10000, default:10000 
	private String sortType;//sortType  optional	  String     sort type, ASC or DESC , DESC : sort descending, ASC: sort ascending
	public String getTopoName() {
		return topoName;
	}
	public void setTopoName(String topoName) {
		this.topoName = topoName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getStartTs() {
		return startTs;
	}
	public void setStartTs(String startTs) {
		this.startTs = startTs;
	}
	public String getEndTs() {
		return endTs;
	}
	public void setEndTs(String endTs) {
		this.endTs = endTs;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
}
