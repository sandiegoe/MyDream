package com.arex.mydream.dao.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.action.vo.RepertoryDTO;
import com.arex.mydream.comm.Page;
import com.arex.mydream.dao.RepertoryDao;
import com.arex.mydream.model.Repertory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class RepertoryDaoImplTest {

	@Resource(name="repertoryDaoImpl")
	private RepertoryDao dao;
	
	@Test
	public void testSearch() {
		List<Repertory> listRY = dao.search();
		for (Repertory repertory : listRY) {
			System.out.println(repertory);
		}
	}

	@Test
	public void testSearchRepertoryCount() {
		RepertoryDTO repertoryDTO = new RepertoryDTO();
		repertoryDTO.setrSid(8);
		repertoryDTO.setgType("礼盒");
		Integer count = dao.searchRepertoryCount(repertoryDTO);
		System.out.println(count);
	}

	@Test
	public void testSearchGiftCount() {
		Repertory repertory = new Repertory();
		
		Integer count = dao.searchGiftCount(repertory);
		System.out.println(count);
	}

	@Test
	public void testSearchBygId() {
		List<Repertory> listRY = dao.searchBygId(3);
		
		for (Repertory repertory : listRY) {
			System.out.println(repertory);
		}
	}

	@Test
	public void testSearchGiftRepertory() {
		Repertory repertory = new Repertory();
		List<Repertory> listRY = dao.searchGift(repertory);
		for (Repertory repertory1 : listRY) {
			System.out.println(repertory1);
		}
	}

	@Test
	public void testSearchGiftRepertoryIntegerInteger() {
		Repertory repertory = new Repertory();
		int pageNo = 2;
		int pageSize = 1;
		List<Repertory> listRY = dao.searchGift(repertory, pageNo, pageSize);
		for (Repertory repertory1 : listRY) {
			System.out.println(repertory1);
		}
	}

	@Test
	public void testAddRepertory() {
		
		int rGid = 3;
		int rSid = 8;
		int rNum = 500;
		
		Repertory repertory = new Repertory();
		repertory.setrGid(rGid);
		repertory.setrSid(rSid);
		repertory.setrNum(rNum);
		dao.addRepertory(repertory);
	}

	@Test
	public void testDeletRepertory() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchStringObjectArrayLinkedHashMapOfStringString() {
		String hqlWhere = " where 1=1 ";
		hqlWhere += " and rGid=? ";
		Object[] objects = new Object[] {4};
		List<Repertory> listRY = dao.search(hqlWhere, objects, null);
		for (Repertory repertory1 : listRY) {
			System.out.println(repertory1);
		}
	}

	@Test
	public void testSearchPage_Repertory() {
		Page page=new Page(2,1);
		String hqlWhere = " where 1=1 ";
		hqlWhere += " and rGid=? ";
		Object[] objects = new Object[] {4};
		List<Repertory> listRY = dao.searchPage_Repertory(hqlWhere, objects, null, page.getPageNo(), page.getPageSize());
		for (Repertory repertory1 : listRY) {
			System.out.println(repertory1);
		}
	}

}
