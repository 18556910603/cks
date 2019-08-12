package sz.zp.cks.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sz.zp.cks.model.StatusT;
import sz.zp.cks.service.ApmApiService;
import sz.zp.cks.service.HstatusService;

@Controller
@RequestMapping("/hstatus")
public class HstatusController extends BaseAction {
	private static final String Json = null;

	@Resource
	private HstatusService hsstatusService;
	
	@Resource
	private ApmApiService  apmApiService;

	@RequestMapping(value = {"list"})
	public String list(  HttpServletRequest request, HttpServletResponse response,Model model) {
		List list=hsstatusService.findStatusTList(new StatusT());
		model.addAttribute("list", list);
		return "forward:/WEB-INF/views/status/hstatus.jsp";
	}
	@RequestMapping(value = {"list2"},method=RequestMethod.POST)
	@ResponseBody 
	public String list(@RequestBody String a) {
		System.out.println("HstatusController>>>进来>>>>>>>>");
		String url="https://api-apm-fuzhou-default-space.wise-paas.com.cn/auth/login";
		String rawBody="{ \"userName\": \"renp@cefc.com.cn\", \"password\": \"Rp123945@\" }";
//		Map map=apmApiService.LoginIn(url, rawBody) ;
//		System.out.println("登陆返回的map："+map);
//		String rawBody2="{ \"userName\": \"renp@cefc.com.cn\", \"password\": \"XXXXXXXXXX\" }";
		return a;
	}

	
}
