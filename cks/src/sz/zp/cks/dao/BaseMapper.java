package sz.zp.cks.dao;

import java.util.List;

public interface BaseMapper<T> {
	//添加单个对象
	public int insert(T entity);
	
	//修改单个对象
	public int update(T entity);
	
	//删除单个对象
	public int delete(T entity);
	
	//查询单个对象
	public T select(T entity);
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);
}
