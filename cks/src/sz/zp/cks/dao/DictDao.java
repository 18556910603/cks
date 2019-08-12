/**
 * Copyright &copy; 2012-2014 <a href="http://www.huayingsoft.com">JGGFrame</a> All rights reserved.
 */
package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.Dict;

/**
 * 字典DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao  extends CrudDao<Dict> {
	public List<String> findTypeList(Dict dict);
	
}
