package sz.zp.cks.service;

import java.util.List;

import org.springframework.ui.Model;

import net.sf.json.JSONObject;
import sz.zp.cks.entity.Menu;
import sz.zp.cks.entity.User;
import sz.zp.cks.model.MonthReCounts;

public interface MenuService extends BaseService<Menu> {
	 public List<Menu> findList(Menu entity);
	 
	 
	 
	 
	 //返回菜单
	 public JSONObject findListMsg(User user);
	 
	 
	 //查找首页的内容
	 public List<MonthReCounts> qryCountsByMonth();
	 
	 public Model viewMain(Model model);
	 
	 
		
}
