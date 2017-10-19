package com.arex.mydream.biz.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.action.vo.ActivityDTO;
import com.arex.mydream.service.ActivityBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class ActivityBizImplTest {

	@Resource(name="activityBizImpl")
	private ActivityBiz activityBiz;
	
	@Test
	public void testAdd() {
		ActivityDTO activityDTO = new ActivityDTO();
		activityDTO.setaGid(4);
		activityDTO.setaPrice(25);
		activityDTO.setaDate("2016.11.11");
		activityDTO.setaPic("xx.jpg");
		
		activityBiz.add(activityDTO);
	}

	@Test
	public void testDelete() {
		activityBiz.delete(7);
	}

	@Test
	public void testDeleteByaGid() {
		activityBiz.deleteByaGid(4);
	}

	@Test
	public void testUpdate() {
		ActivityDTO activityDTO = activityBiz.searchById(14);
		activityDTO.setaPrice(56.00);
		activityBiz.update(activityDTO);
	}

	@Test
	public void testSearchById() {
		ActivityDTO activityDTO = activityBiz.searchById(14);
		System.out.println(activityDTO);
	}

	@Test
	public void testSearch() {
		List<ActivityDTO> listAO = activityBiz.search();
		for (ActivityDTO activityDTO : listAO) {
			System.out.println(activityDTO);
		}
	}

	@Test
	public void testSearchByadate() {
		List<ActivityDTO> listAO = activityBiz.searchByadate("2016.11.11");
		for (ActivityDTO activityDTO : listAO) {
			System.out.println(activityDTO);
		}
	}

	@Test
	public void testSearchPage() {
		int pageNo = 2;
		int pageSize = 2;
		List<ActivityDTO> listAO = activityBiz.searchPage("2016.11.11", pageNo, pageSize);
		for (ActivityDTO activityDTO : listAO) {
			System.out.println(activityDTO);
		}
	}

	@Test
	public void testSearchCountByaDate() {
		int count = activityBiz.searchCountByaDate("2016.11.11");
		System.out.println(count);
	}

}
