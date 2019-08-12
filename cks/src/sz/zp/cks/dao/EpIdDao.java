package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.Epuser;


/**
 * DAO支持类实现
 * @author ThinkGem
 * @version 2014-05-16
 */

public interface EpIdDao {
		
	public int count(String  epType) throws Exception;
	public int getNum() throws Exception;
}