package sz.zp.cks.dao;

import java.util.List;

import sz.zp.cks.entity.AccountTest;
import sz.zp.cks.entity.User;


public interface UserMapper extends BaseMapper<User> {
	public User login(User user);
	public AccountTest   countTest(User user);
	public User load(String userId);
	public int update(User user);
	public User selectForLeaderByType(String deptType);
	public User selectOwnerForLeaderByType(String deptType);
	public User registOwner(User user);
	public List<User> findListByDeptType(String deptType);
	public User selectUserByEpId(String epId);



}