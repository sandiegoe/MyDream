package com.arex.mydream.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.SaleDao;
import com.arex.mydream.model.Sale;

@Component
@Transactional
public class SaleDaoImpl implements SaleDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void add(Sale sale) {
		hibernateTemplate.save(sale);
	}

	@Override
	public void delete(int saId) {
		Sale sale = new Sale();
		sale.setSaId(saId);
		hibernateTemplate.delete(sale);
	}

	@Override
	public void update(Sale sale) {
		hibernateTemplate.update(sale);
	}

	@Override
	public List<Sale> search() {
		
		List<Sale> listSE = (List<Sale>) hibernateTemplate.find("select sale from Sale sale ", null);
		
		return listSE;
	}

	@Override
	public Sale searchSale(int saId) {
		
		List<Sale> listSE = (List<Sale>) hibernateTemplate.find("select sale from Sale sale where sale.saId=? ", saId);
		Sale sale = null;
		
		if (listSE!=null && listSE.size()>0) {
			sale = listSE.get(0);
		}
		
		return sale;
	}

	@Override
	public Sale searchSaleBysName(String sName) {
		
		List<Sale> listSE = (List<Sale>) hibernateTemplate.find("select sale from Sale sale where sale.saSname=? ", sName);
		Sale sale = null;
		
		if (listSE!=null && listSE.size()>0) {
			sale = listSE.get(0);
		}
		
		return sale;
	}

}
