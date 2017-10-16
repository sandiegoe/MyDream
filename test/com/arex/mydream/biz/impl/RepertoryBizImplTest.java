package com.arex.mydream.biz.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.format.number.PercentFormatter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.action.vo.RepertoryDTO;
import com.arex.mydream.comm.Page;
import com.arex.mydream.service.RepertoryBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class RepertoryBizImplTest {

	@Resource(name="repertoryBizImpl")
	private RepertoryBiz repertoryBiz;
	
	@Test
	public void testSearchRepertory() {
		List<RepertoryDTO> listRO = repertoryBiz.searchRepertory();
		for (RepertoryDTO repertoryDTO : listRO) {
			System.out.println(repertoryDTO);
		}
	}

	@Test
	public void testSearch() {
		RepertoryDTO repertoryDTO = new RepertoryDTO();
		repertoryDTO.setrGid(4);
		repertoryDTO.setrSid(8);
		List<RepertoryDTO> listRO = repertoryBiz.search(repertoryDTO );
		for (RepertoryDTO repertoryDTO1 : listRO) {
			System.out.println(repertoryDTO1);
		}
	}

	@Test
	public void testSearchPage_Repertory() {
		int pageNo = 2;
		int pageSize = 1;
		RepertoryDTO repertoryDTO = new RepertoryDTO();
		repertoryDTO.setrGid(4);
		repertoryDTO.setrSid(8);
		List<RepertoryDTO> listRO = repertoryBiz.searchPage_Repertory(repertoryDTO, pageNo, pageSize);
		for (RepertoryDTO repertoryDTO1 : listRO) {
			System.out.println(repertoryDTO1);
		}
	}

	@Test
	public void testSearchRepertoryCount() {
		Page page = new Page(2, 1);
		RepertoryDTO repertoryDTO = new RepertoryDTO();
		repertoryDTO.setrSid(8);
		Integer count = repertoryBiz.searchRepertoryCount(repertoryDTO);
		page.setTotalCount(count);
		System.out.println(page.getPageNo());
		System.out.println(page.getPageSize());
		System.out.println(page.getTotalCount());
	}

	@Test
	public void testSearchGiftCount() {
		RepertoryDTO repertoryDTO = null;
		Integer count = repertoryBiz.searchGiftCount(repertoryDTO);
		System.out.println(count);
	}

	@Test
	public void testSearchBygId() {
		List<RepertoryDTO> listRO = repertoryBiz.searchBygId(4);
		for (RepertoryDTO repertoryDTO1 : listRO) {
			System.out.println(repertoryDTO1);
		} 
	}

	@Test
	public void testSearchGiftRepertoryDTO() {
		RepertoryDTO repertoryDTO = new RepertoryDTO();
		List<RepertoryDTO> listRO = repertoryBiz.searchGift(repertoryDTO);
		for (RepertoryDTO repertoryDTO1 : listRO) {
			System.out.println(repertoryDTO1);
		} 
	}

	@Test
	public void testSearchGiftRepertoryDTOIntegerInteger() {
		RepertoryDTO repertoryDTO = null;
		int pageNo = 1;
		int pageSize = 1;
		List<RepertoryDTO> listRO = repertoryBiz.searchGift(repertoryDTO, pageNo, pageSize);
		for (RepertoryDTO repertoryDTO1 : listRO) {
			System.out.println(repertoryDTO1);
		} 
	}

	@Test
	public void testAddRepertory() {
		RepertoryDTO repertoryDTO = new RepertoryDTO();
		repertoryDTO.setrGid(3);
		repertoryDTO.setrNum(5);
		repertoryDTO.setrSid(8);
		repertoryBiz.addRepertory(repertoryDTO);
	}

	@Test
	public void testDeletRepertory() {
		RepertoryDTO repertoryDTO = new RepertoryDTO();
		repertoryDTO.setrGid(3);
		repertoryDTO.setrSid(8);
		repertoryBiz.deletRepertory(repertoryDTO);
	}

}
