package sz.zp.cks.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.DeptDao;
import sz.zp.cks.entity.Dept;
import sz.zp.cks.service.DeptService;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDao deptDao;

	@Override
	public Dept selectDept(Integer deptId) {
		// TODO Auto-generated method stub

		return deptDao.selectDept(deptId);
	}

	@Override
	public int insertDept(Dept dept) throws Exception {
		int i =0 ;
		i = deptDao.insertDept(dept);
		//Integer.parseInt("aa");
		//Class.forName("adb.Dee");
		return i;
	}

}
