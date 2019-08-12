package sz.zp.cks.dao;

import sz.zp.cks.entity.Account;
import sz.zp.cks.entity.AccountTest;

public interface AccountMapper extends BaseMapper<Account> {
	public Account login(Account account);
	public AccountTest   countTest(Account account);

}