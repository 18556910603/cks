package sz.zp.cks.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sz.zp.cks.entity.Owner;


public interface OwnerMapper {

	  public Owner load(String  userId) throws Exception;
	  public int insert(Owner  owner) throws Exception;
	  public int update(Owner  owner) throws Exception;
	  public Owner qryById(String  userId) throws Exception;
	  public int max() throws Exception;
	
}