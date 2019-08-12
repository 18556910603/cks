package sz.zp.cks.service;


import sz.zp.cks.entity.Owner;


public interface IOwnerBiz {
	
   
	  public Owner queryById(String  userId) throws Exception;
	  public int regist(Owner  owner) throws Exception;
	  public int modify(Owner  owner) throws Exception;
	  public Owner qryById(String  userId) throws Exception;
	  public int max() throws Exception;
	
}
