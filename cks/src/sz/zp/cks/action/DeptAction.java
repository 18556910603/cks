package sz.zp.cks.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sz.zp.cks.entity.Dept;
import sz.zp.cks.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptAction {
	@Resource
	private DeptService deptService;
	
	@RequestMapping(value="/insert")
	public String insert(Dept dept){
		System.out.println("---action.dept:"+dept);
		try {
			deptService.insertDept(dept);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:/jsp/main.jsp";
	}

}



