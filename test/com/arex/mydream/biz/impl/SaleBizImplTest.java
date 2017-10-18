package com.arex.mydream.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.action.vo.SaleDTO;
import com.arex.mydream.service.SaleBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class SaleBizImplTest {
	
	@Resource(name="saleBizImpl")
	private SaleBiz saleBiz;

	@Test
	public void testAddSale() {
		SaleDTO saleDTO = new SaleDTO();
		saleDTO.setSaNum(6);
		saleDTO.setSaPrice(50);
		saleDTO.setSaSname("arex");
		saleDTO.setSaVisitor(20);
		
		saleBiz.addSale(saleDTO);
	}

	@Test
	public void testDeleteSale() {
		saleBiz.deleteSale(6);
	}

	@Test
	public void testUpdateSale() {
		SaleDTO saleDTO = saleBiz.serachSale(6);
		saleDTO.setSaPrice(500);
		saleBiz.updateSale(saleDTO);
	}

	@Test
	public void testSearchSale() {
		List<SaleDTO> listSO = saleBiz.searchSale();
		for (SaleDTO saleDTO : listSO) {
			System.out.println(saleDTO);
		}
	}

	@Test
	public void testSerachSale() {
	}

}
