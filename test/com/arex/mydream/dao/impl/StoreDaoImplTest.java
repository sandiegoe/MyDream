package com.arex.mydream.dao.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.comm.Page;
import com.arex.mydream.dao.StoreDao;
import com.arex.mydream.model.Store;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class StoreDaoImplTest {
	
	@Resource(name="storeDaoImpl")
	private StoreDao dao;

	@Test
	public void testAdd() {
		Store store=new Store("123", "123", "123" , 4);
		dao.add(store);
	}

	@Test
	public void testDelete() {
		 dao.delete(2);
	}

	@Test
	public void testUpdate() {
		Store store = dao.searchStore(3);
		store.setsAddress("aaaaaa");
		dao.update(store);
	}

	@Test
	public void testSearch() {
		List<Store> list= dao.search();
		 for (Store s : list) {
			System.out.println(s.toString());
		}
	}

	@Test
	public void testSearchStore() {
		 Store s= dao.searchStore(1);
	     System.out.println(s.toString());
	}

	@Test
	public void testSearchPage_Store() {
		Page page=new Page(1,4);
		page.setTotalCount(dao.searchStoreCount());
		System.out.println("数据 有多少条： "+page.getTotalCount());
		System.out.println("每页的大小: "+page.getPageSize());
		System.out.println("共有几页："+page.getPageCount());
		
		List<Store> list=dao.searchPage_Store(page.getPageNo(), page.getPageSize());
		System.out.println("一共查到："+list.size()+"条");
		
		for (Store store : list) {
			System.out.println(store);
		}
	}

	@Test
	public void testSearchStoreCount() {
		Page page=new Page(1,4);
		page.setTotalCount(dao.searchStoreCount());
		System.out.println("数据 有多少条： "+page.getTotalCount());
		System.out.println("每页的大小: "+page.getPageSize());
		System.out.println("共有几页："+page.getPageCount());
	}

	@Test
	public void testSearchStoreBySuid() {
		Store store=dao.searchStoreBySuid(4);
		System.out.println("根据店主ID查店面信息："+store);
	}

}
