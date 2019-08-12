package sz.zp.cks.service;

import java.util.List;

import sz.zp.cks.entity.Account;
import sz.zp.cks.entity.AccountTest;

public interface AccountService extends BaseService<Account> {
	 public Account login(Account account);
	 public AccountTest login2(Account account);
	 public List<Account> findList(Account entity);
	 
}
