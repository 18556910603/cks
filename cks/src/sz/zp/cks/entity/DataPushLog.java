package sz.zp.cks.entity;

public class DataPushLog {
	
    private String dataPushLogId;

    private String nodeName;
    private String originalData;
    private String data;
    private String pushTime;

	private String code;

	private String responseMessage;

	private String type;
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


	public String getDataPushLogId() {
		return dataPushLogId;
	}

	public void setDataPushLogId(String dataPushLogId) {
		this.dataPushLogId = dataPushLogId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

    public String getOriginalData() {
		return originalData;
	}

	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}