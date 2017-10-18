package com.arex.mydream.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.action.vo.PurchaseDTO;
import com.arex.mydream.service.PurchaseBiz;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class PurchaseBizImplTest {

	@Resource(name="purchaseBizImpl")
	private PurchaseBiz purchaseBiz;
	
	@Test
	public void testSearch() {
		List<PurchaseDTO> listPO = purchaseBiz.search();
		for(PurchaseDTO purchaseDTO : listPO) {
			System.out.println(purchaseDTO);
		}
	}

	@Test
	public void testAdd() {
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		purchaseDTO.setpGid(1);
		purchaseDTO.setpSid(8);
		purchaseDTO.setpUid(10);
		purchaseBiz.add(purchaseDTO);
	}

	@Test
	public void testUpdate() {
		PurchaseDTO purchaseDTO = purchaseBiz.search(6);
		purchaseDTO.setpNum(25);
		purchaseBiz.update(purchaseDTO);
	}

	@Test
	public void testDelete() {
		purchaseBiz.delete(6);
	}

	@Test
	public void testSearchPurchaseDTO() {
		int pUid = 10;
		int pSid = 8;
		int pGid = 1;
		PurchaseDTO purchaseDTO = purchaseBiz.searchPurchaseDTO(pUid, pSid, pGid);
		System.out.println(purchaseDTO);
	}

	@Test
	public void testSearchInt() {
		PurchaseDTO purchaseDTO = purchaseBiz.search(6);
		System.out.println(purchaseDTO);
	}

}
