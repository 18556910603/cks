package sz.zp.cks.service;

import java.util.List;

import sz.zp.cks.entity.AccountTest;
import sz.zp.cks.entity.User;

public interface UserService extends BaseService<User> {
	 public User login(User user);
	 public AccountTest login2(User user);
	 public List<User> findList(User user);
	 public User queryById(String userId);
	 public int update(User user);
	 
	 //查询维修部门员工 repair
	 public List<User> findListByDeptType(String  deptType);
	 
	 
	 
	 
	 
	 
}
