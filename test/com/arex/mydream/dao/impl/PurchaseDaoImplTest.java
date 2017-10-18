package com.arex.mydream.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.dao.PurchaseDao;
import com.arex.mydream.model.Purchase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class PurchaseDaoImplTest {

	@Resource(name="purchaseDaoImpl")
	private PurchaseDao pd;
	
	@Test
	public void testSearch() {
		List<Purchase> list=pd.search();
		   for (Purchase p : list) {
			System.out.println(p.toString());
		}
	}

	@Test
	public void testAdd() {
		Purchase purchase = new Purchase();
		purchase.setpGid(4);
		purchase.setpNum(10);
		purchase.setpSid(8);
		purchase.setpUid(10); //购买者uId
		
		pd.add(purchase);
	}

	@Test
	public void testUpdate() {
		Purchase purchase = pd.search(4);
		purchase.setpNum(100);
		pd.update(purchase);
	}

	@Test
	public void testDelete() {
		pd.delete(3);
	}

	@Test
	public void testSearchPurchase() {
		int pUid = 10;
		int pSid = 8;
		int pGid = 4;
		Purchase purchase = pd.searchPurchase(pUid, pSid, pGid);
		System.out.println(purchase);
	}

	@Test
	public void testSearchInt() {
		Purchase purchase = pd.search(4);
		System.out.println(purchase);
	}

}
