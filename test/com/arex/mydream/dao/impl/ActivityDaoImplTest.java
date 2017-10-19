package com.arex.mydream.dao.impl;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.dao.ActivityDao;
import com.arex.mydream.model.Activity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class ActivityDaoImplTest {

	@Resource(name="activityDaoImpl")
	private ActivityDao activityDao;
	
	@Test
	public void testAdd() {
		Activity activity = new Activity();
		activity.setaGid(4);
		activity.setaPrice(25);
		activity.setaDate("2016.11.11");
		activity.setaPic("xx.jpg");
		
		activityDao.add(activity);
	}

	@Test
	public void testDelete() {
		int aId = 5;
		activityDao.delete(aId);
	}

	@Test
	public void testDeleteByaGid() {
		int aGid = 4;
		activityDao.deleteByaGid(aGid);
	}

	@Test
	public void testUpdate() {
		Activity activity = activityDao.searchById(1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		activity.setaDate(format.format(new Date()));
		activityDao.update(activity);
	}

	@Test
	public void testSearchById() {
		Activity activity = activityDao.searchById(1);
		System.out.println(activity);
	}

	@Test
	public void testSearch() {
		
		List<Activity> listAY = activityDao.search();
		for (Activity activity : listAY) {
			System.out.println(activity);
		}
	}

	@Test
	public void testSearchByadate() {
		List<Activity> listAY = activityDao.searchByadate("2016.11.11");
		for (Activity activity : listAY) {
			System.out.println(activity);
		}
	}

	@Test
	public void testSearchByadateWithPage() {
		int pageNo = 2;
		int pageSize = 2;
		List<Activity> listAY = activityDao.searchByadateWithPage("2016.11.11", pageNo, pageSize);
		for (Activity activity : listAY) {
			System.out.println(activity);
		}
	}

	@Test
	public void testSearchCountByadate() {
		Integer count = activityDao.searchCountByadate("2016.11.11");
		System.out.println(count);
	}

}
