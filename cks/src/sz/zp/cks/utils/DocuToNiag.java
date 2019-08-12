package sz.zp.cks.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sz.zp.cks.entity.JackJsonUtils;
import sz.zp.cks.model.apm.ApmRule;
import sz.zp.cks.model.apm.Documents;
import sz.zp.cks.model.apm.DocumentsN;
import sz.zp.cks.model.apm.NiagaraJson;
import sz.zp.cks.model.apm.RequestBody;
import sz.zp.cks.model.apm.Tag;

public class DocuToNiag {

	

	public static DocumentsN getNiagJson(Documents documents){
		    DocumentsN documentsN=new DocumentsN();
		    System.out.println(documentsN.getNodeName());
		    try {
				BeanUtils.copyProperties(documentsN, documents);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        NiagaraJson niagaraJson= new NiagaraJson();
	        String eventTime = documents.getEventTime();
	        String localFormat="yyyy-MM-dd HH:mm:ss";
	        String UTCformat1="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	        String UTCformat="yyyy-MM-dd'T'HH:mm:ss'Z'";
	        String timestamp="";
	        if(eventTime.indexOf(".")==-1){
	            timestamp = FormatTime.UTCToCST(eventTime, UTCformat, localFormat);
	        }
	        if(eventTime.indexOf(".")!=-1){
	        	timestamp = FormatTime.UTCToCST(eventTime, UTCformat1, localFormat);
	        }
	        niagaraJson.setTimestamp(timestamp);
	        Map<String, Map> ruleTagValueMap  = documents.getRuleTagValueMap();
	        Map<String, Object> r0 = (HashMap) ruleTagValueMap.get("r0");
	        Map<String, Object> r1 = (HashMap) ruleTagValueMap.get("r1");
	        String nKey = "";
	        String level2 ="";
	        String status ="";
	        String comparison ="";
	        String threshold ="";
	        String symbols ="";
	        String tagName ="";
	        String value ="";
	        String nValue ="";
	        String Key ="";
	        String thresholdt ="";
	        String symbolst ="";
	        Map tag=null;
			if(r0!=null){
	        for (Map.Entry<String, Object> entry : r0.entrySet()) { 
	           nKey=entry.getKey();
	          if(("level").equals(nKey)){
	        		level2 = (String) entry.getValue();	
	        	}else if(("status").equals(nKey)){
	        		status = (String) entry.getValue();	
	        	}else if(("comparison").equals(nKey)){
	        		comparison = (String) entry.getValue();	
	        	}else if(("threshold").equals(nKey)){
	        		threshold =(String) entry.getValue();	
	        	}else if(("symbols").equals(nKey)){
	        		symbols =(String) entry.getValue();	
	        	}else{
	        		Key = nKey;//Atag1
	        		if(Key!=null){
	        			tag=(HashMap) entry.getValue();
	        		    value = String.valueOf(tag.get("value"));
	        		}
	        	 }
	           }   
             }
			if(r1!=null){
		        for (Map.Entry<String, Object> entry : r1.entrySet()) { 
		           nKey=entry.getKey();
		          if(("level").equals(nKey)){
		        		level2 = (String) entry.getValue();	
		        	}else if(("status").equals(nKey)){
		        		status = (String) entry.getValue();	
		        	}else if(("comparison").equals(nKey)){
		        		comparison = (String) entry.getValue();	
		        	}else if(("threshold").equals(nKey)){
		        		thresholdt =(String) entry.getValue();	
		        	}else if(("symbols").equals(nKey)){
		        		symbolst =(String) entry.getValue();	
		        	}else{
		        		Key = nKey;//Atag1
		        		if(Key!=null){
		        			tag=(HashMap) entry.getValue();
		        		    value = String.valueOf(tag.get("value"));
		        		}
		        	 }
		           }   
	             }
			 niagaraJson.setValue(value);
			 if(symbols.equals("<")){
				 niagaraJson.setLowLimit(threshold);
				 niagaraJson.setHighLimit(thresholdt);
			 }
			 if(symbols.equals(">")){
				 niagaraJson.setHighLimit(threshold);
				 niagaraJson.setLowLimit(thresholdt);
			 }
			 String eventName=documents.getTopoName();
			 String path=documents.getPath();
			 niagaraJson.setSource(path.replace("/", "_")+"_"+eventName);
			 String level = documents.getLevel();
			 niagaraJson.setSourceState(level);
			 niagaraJson.setAckState("Unacked");
			 niagaraJson.setPriority("");
			 niagaraJson.setAlarmClass("");//选择指定的告警组件(名称可自定义)
			 String content = documents.getContent();
			 String text="";
			 if(content.indexOf("@")!=-1){
				 text=content.substring(content.indexOf("@")+1);
			 }
			 if(text.indexOf("@")!=-1){
				 content=text.substring((text.indexOf("@"))+1);
				 text=text.substring(0, (text.indexOf("@")));		
			 }
			 niagaraJson.setText(text);
			 String recoverTime = documents.getRecoverTime();
			 if(recoverTime.indexOf(".")==-1){
				 recoverTime = FormatTime.UTCToCST(recoverTime, UTCformat, localFormat);
		        }
		        if(recoverTime.indexOf(".")!=-1){
		        	recoverTime = FormatTime.UTCToCST(recoverTime, UTCformat1, localFormat);
		        }
			 niagaraJson.setNormalTime(recoverTime);
			 niagaraJson.setHyperlinkOrd(content);
			 documentsN.setSource(niagaraJson.getSource());
			 documentsN.setHighLimit(niagaraJson.getHighLimit());
			 documentsN.setLowLimit(niagaraJson.getLowLimit());
			 documentsN.setText(niagaraJson.getText());
			 documentsN.setValue(niagaraJson.getValue());
			 documentsN.setEventTime(niagaraJson.getTimestamp());
			 documentsN.setRecoverTime(niagaraJson.getNormalTime());
			return documentsN;	        
	 }
	
}	
