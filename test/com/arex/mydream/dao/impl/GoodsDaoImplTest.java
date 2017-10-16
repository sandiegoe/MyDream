package com.arex.mydream.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.dao.GoodsDao;
import com.arex.mydream.model.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class GoodsDaoImplTest {

	@Resource(name="goodsDaoImpl")
	private GoodsDao goodsDao;
	
	
	@Test
	public void testSearchGoodsBySid() {
		int sId = 8;
		List<Goods> listGS = goodsDao.searchGoodsBySid(sId);
		for (Goods goods : listGS) {
			System.out.println(goods.getgId() + "   ----    " + goods.getgName());
		}
	}
	
	@Test
	public void testSearchGoodsBySidWithPage() {
		int sId = 8;
		int pageNo = 2;
		int pageSize = 2;
		List<Goods> listGS = goodsDao.searchGoodsBySid(sId, pageNo, pageSize);
		for (Goods goods : listGS) {
			System.out.println(goods.getgId() + "   ----    " + goods.getgName());
		}
	}
	
	@Test
	public void testAdd() {
		Goods goods=new Goods();
		goods.setgName("越南火龙果");
		goods.setgPrice(39.00);
		goods.setgType("火龙果");
		goods.setgAddress("越南");
		goods.setgPic("huolongguo.jpg");
		goods.setgDescribe("新鲜好吃的的火龙果");
		
		goodsDao.add(goods);
	}

	@Test
	public void testDelete() {
		goodsDao.delete(1);
	}
	
	@Test
	public void testUpdate() {
		Goods goods = new Goods();
		goods.setgId(2);
		goods.setgName("xiebo xieboxiebo de de de ");
		goods.setgPrice(20.0); // gPrice can not null
		goodsDao.update(goods);
	}
	
	@Test
	public void testSearch() {
		List<Goods> listGS = goodsDao.search();
		for (Goods goods : listGS) {
			System.out.println(goods.getgId() + "   ----    " + goods.getgName());
		}
	}
	
	@Test
	public void testSearchBygId() {
		Goods goods = goodsDao.search(2);
		System.out.println(goods.getgId() + "   ----    " + goods.getgName());
	}
	
	@Test
	public void testSearchGoodsCount(){
		int count = goodsDao.searchGoodsCount();
		System.out.println(count);
	}
	
	@Test
	public void testSearchByGname() {
		List<Goods> listGS = goodsDao.searchByGname("越南火龙果");
		for (Goods goods : listGS) {
			System.out.println(goods.getgId() + "   ----    " + goods.getgName());
		}
	}
	
	@Test
	public void testSearchByCondition() {
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		
		hqlWhere += " and gName = ? ";
		params.add("越南火龙果");
		orderby.put("gId", "desc");
		
		List<Goods> listGS = goodsDao.searchByCondition(hqlWhere, params.toArray(), orderby);
		for (Goods goods : listGS) {
			System.out.println(goods.getgId() + "   ----    " + goods.getgName());
		}
	}
	
	@Test
	public void testSearchPage_Goods() {
		int pageNo = 1;
		int pageSize = 1;
		
		List<Goods> listGS = goodsDao.searchPage_Goods(pageNo, pageSize);
		for (Goods goods : listGS) {
			System.out.println(goods.getgId() + "   ----    " + goods.getgName());
		}
	}
}
