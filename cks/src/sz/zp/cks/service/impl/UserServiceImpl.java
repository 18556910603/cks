package sz.zp.cks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import sz.zp.cks.dao.UserMapper;
import sz.zp.cks.entity.AccountTest;
import sz.zp.cks.entity.User;
import sz.zp.cks.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int insert(User entity) throws Exception {

		return 0;
	}

	
	@Override
	public int delete(User entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User select(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userMapper.login(user);
	}

	@Override
	public AccountTest login2(User user) {
		return userMapper.countTest(user);
	}

	@Override
	public List<User> findList(User user) {
		// TODO Auto-generated method stub
		return userMapper.findList(user);
	}

	@Override
	public User queryById(String userId) {
		// TODO Auto-generated method stub
		System.out.println("service--------"+userId);
		return userMapper.load(userId);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}


	@Override
	public List<User> findListByDeptType(String deptType) {
		// TODO Auto-generated method stub
		return userMapper.findListByDeptType(deptType);
	}

	


}
