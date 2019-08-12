package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.Menu;
import sz.zp.cks.entity.User;


public interface MenuMapper extends BaseMapper<Menu> {
	public List<Menu> findListByUser(User user);
}