package com.arex.mydream.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.mydream.action.vo.GoodsDTO;
import com.arex.mydream.service.GoodsBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class GoodsBizImplTest {

	@Resource(name="goodsBizImpl")
	private GoodsBiz goodsBiz;
	
	@Test
	public void testSearchGoodsBySidWithPage() {
		int sId = 8;
		int pageNo = 1;
		int pageSize = 2;
		
		List<GoodsDTO> listGO = goodsBiz.searchGoodBysId(sId, pageNo, pageSize);
		for (GoodsDTO goodsDTO : listGO) {
			System.out.println(goodsDTO.getgId() + "  --  " + goodsDTO.getgDescribe());
		}
	}
	
	@Test
	public void testSearchGoodsBySid() {
		int sId = 8;
		List<GoodsDTO> listGO = goodsBiz.searchGoodBysId(sId);
		for (GoodsDTO goodsDTO : listGO) {
			System.out.println(goodsDTO.getgId() + "  --  " + goodsDTO.getgDescribe());
		}
	}
	
	@Test
	public void testAddGoods() {
		GoodsDTO goodsDTO = new GoodsDTO();
		goodsDTO.setgAddress("越南");
		goodsDTO.setgDescribe("新鲜好吃的的火龙果");
//		goodsDTO.setgId(gId);
		goodsDTO.setgName("越南火龙果");
		goodsDTO.setgPic("huolongguo.jpg");
		goodsDTO.setgPrice(39.00);
		goodsDTO.setgType("火龙果");
		
		goodsBiz.addGoods(goodsDTO);
	}
	
	@Test
	public void testDeleteGoods() {
		int gId = 1;
		goodsBiz.deleteGoods(gId);
	}

	@Test
	public void testSearchGoods() {
		List<GoodsDTO> goodsDTOList = goodsBiz.searchGoods();
		for (GoodsDTO goodsDTO : goodsDTOList) {
			System.out.println(goodsDTO.getgId() + "  --  " + goodsDTO.getgDescribe());
		}
	}
	
	@Test
	public void testSearchGoodsBygId() {
		GoodsDTO goodsDTO = goodsBiz.searchGoods(2);
		System.out.println(goodsDTO.getgId() + "  --  " + goodsDTO.getgDescribe());
	}
	
	@Test
	public void testsSearchPage_Goods() {
		int pageNo = 1;
		int pageSize = 5;
		List<GoodsDTO> goodsDTOList = goodsBiz.searchPage_Goods(pageNo, pageSize);
		for (GoodsDTO goodsDTO : goodsDTOList) {
			System.out.println(goodsDTO.getgId() + "  --  " + goodsDTO.getgDescribe());
		}
	}
	
	@Test
	public void testSearchGoodsCount() {
		Integer count = goodsBiz.searchGoodsCount();
		System.out.println(count);
	}
	
	@Test
	public void testSearchByGname() {
		String gName = "越南火龙果";
		List<GoodsDTO> goodsDTOList = goodsBiz.searchByGname(gName );
		for (GoodsDTO goodsDTO2 : goodsDTOList) {
			System.out.println(goodsDTO2.getgId() + "  --  " + goodsDTO2.getgDescribe());
		}
	}
	
	@Test
	public void testSearchByCondition() {
		GoodsDTO goodsDTO = new GoodsDTO();
		goodsDTO.setgAddress("越南");
//		System.out.println(goodsDTO.getgPrice() == null);
		List<GoodsDTO> goodsDTOList = goodsBiz.searchByCondition(goodsDTO);
		for (GoodsDTO goodsDTO2 : goodsDTOList) {
			System.out.println(goodsDTO2.getgId() + "  --  " + goodsDTO2.getgDescribe());
		}
	}
}
