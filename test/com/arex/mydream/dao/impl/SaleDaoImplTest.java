package com.arex.mydream.dao.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.dao.SaleDao;
import com.arex.mydream.model.Sale;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class SaleDaoImplTest {

	@Resource(name="saleDaoImpl")
	private SaleDao saleDao;
	
	@Test
	public void testAdd() {
		Sale sale = new Sale();
		sale.setSaNum(6);
		sale.setSaPrice(50);
		sale.setSaSname("arex");
		sale.setSaVisitor(20);

		saleDao.add(sale);
	}

	@Test
	public void testDelete() {
		saleDao.delete(3);
	}

	@Test
	public void testUpdate() {
		Sale sale = saleDao.searchSale(1);
		sale.setSaVisitor(1000);
		saleDao.update(sale);
	}

	@Test
	public void testSearch() {
		List<Sale> listSE = saleDao.search();
		for (Sale sale : listSE) {
			System.out.println(sale);
		}
	}

	@Test
	public void testSearchSale() {
		Sale sale = saleDao.searchSale(2);
		System.out.println(sale);
	}

}
