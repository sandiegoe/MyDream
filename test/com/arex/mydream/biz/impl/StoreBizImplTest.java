package com.arex.mydream.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.comm.Page;
import com.arex.mydream.service.StoreBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class StoreBizImplTest {

	
	@Resource(name="storeBizImpl")
	private StoreBiz sbiz;
	
	@Test
	public void testAdd() {
		StoreDTO storeDTO = new StoreDTO("小平水果店", "119", "龙岩市长汀", 5);
		sbiz.add(storeDTO);
	}

	@Test
	public void testDelete() {
		sbiz.delete(1);
	}

	@Test
	public void testUpdate() {
		StoreDTO storeDTO = sbiz.searchStore(3);
		storeDTO.setsName("asdfasdfasdf");
		sbiz.update(storeDTO);
	}

	@Test
	public void testSearch() {
		List<StoreDTO> list = sbiz.search();
		for (StoreDTO s : list) {
			System.out.println(s.toString());
		}
	}

	@Test
	public void testSearchStore() {
		StoreDTO s= sbiz.searchStore(2);
	    System.out.println(s.toString());
	}

	@Test
	public void testSearchPage_Store() {
		Page page=new Page(1,4);
		page.setTotalCount(sbiz.searchStoreCount());
		System.out.println("数据 有多少条： "+page.getTotalCount());
		System.out.println("每页的大小: "+page.getPageSize());
		System.out.println("共有几页："+page.getPageCount());
		
		List<StoreDTO> list=sbiz.searchPage_Store(page.getPageNo(), page.getPageSize());
		System.out.println("一共查到："+list.size()+"条");
		
		for (StoreDTO store : list) {
			System.out.println(store);
		}
	}

	@Test
	public void testSearchStoreCount() {
		Page page=new Page(1,4);
		page.setTotalCount(sbiz.searchStoreCount());
		System.out.println("数据 有多少条： "+page.getTotalCount());
		System.out.println("每页的大小: "+page.getPageSize());
		System.out.println("共有几页："+page.getPageCount());
	}

	@Test
	public void testSearchStoreBySuid() {
		StoreDTO store=sbiz.searchStoreBySuid(5);
		System.out.println("根据店主ID查店面信息："+store);
	}

}
