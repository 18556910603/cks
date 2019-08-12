package sz.zp.cks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.AccountMapper;
import sz.zp.cks.entity.Account;
import sz.zp.cks.entity.AccountTest;
import sz.zp.cks.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public int insert(Account entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Account entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Account entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account select(Account entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account login(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.login(account);
	}

	@Override
	public AccountTest login2(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.countTest(account);
		
		
		
		
	}

	@Override
	public List<Account> findList(Account entity) {
		// TODO Auto-generated method stub
		return accountMapper.findList(entity);
	}



}
