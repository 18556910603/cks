package sz.zp.cks.dao;

import sz.zp.cks.entity.Dept;

public interface DeptDao {
	public Dept selectDept(Integer deptId);
	/**
	 * 添加部门信息 
	 * @param dept 部门信息
	 * @return 添加成功的记录数
	 */
	public int insertDept(Dept dept);
}
