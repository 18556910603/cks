package sz.zp.cks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.zp.cks.dao.SupplierMapper;
import sz.zp.cks.entity.Supplier;
import sz.zp.cks.service.SupplierService;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierMapper supplierMapper;

	@Override
	public int insert(Supplier supplier) throws Exception {
		// TODO Auto-generated method stub
		return supplierMapper.insert(supplier);
	}

	@Override
	public int update(Supplier entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Supplier entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Supplier select(Supplier entity) {
		// TODO Auto-generated method stub
		return null;
	}



}
