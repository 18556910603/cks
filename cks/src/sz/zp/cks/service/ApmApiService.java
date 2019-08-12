package sz.zp.cks.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import sz.zp.cks.model.apm.ApmRule;
import sz.zp.cks.model.apm.Documents;
import sz.zp.cks.model.apm.GetParameter;
import sz.zp.cks.model.apm.NiagaraJson;

/**
-- Add by : xuyaya-- Modify date:2017/08/01-- Add ref : E-claim for Interface
 */
public interface ApmApiService {
	/** client接口
	 *  推送南瓜数据给apm 获取apm响应 
	 * @param    request请求参数  
	 *          
	 * @return  
	 */
	public String insertByPreForApm(String message);
	
	
	
	/** server接口
	 *  接受apm推送的数据  返回响应
	 * @param    request请求参数
	 * @return 
	 */
	public String insertByGetFromApm(String requestJson,HttpServletResponse response)throws Exception;
	
	/** client-GET接口
	 *  接受apm推送的数据  返回响应
	 * @param    request请求参数
	 * @return 
	 */
	public List getByPreForApm(GetParameter getParameter) ;
	
}
