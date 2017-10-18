package com.arex.mydream.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.GoodsDTO;
import com.arex.mydream.dao.GoodsDao;
import com.arex.mydream.model.Goods;
import com.arex.mydream.service.GoodsBiz;
/**
 * 商品业务层实现类
 * @author 陈霞
 *2016-3-8下午1:22:10
 *
 */
import java.util.ArrayList;
import java.util.List;


@Component
@Transactional
public class GoodsBizImpl implements GoodsBiz {
	
	@Resource(name="goodsDaoImpl")
	private GoodsDao dao;

	/**
	 * 增加商品
	 * 
	 * @param goods
	 */
	@Override
	public void addGoods(GoodsDTO goodsDTO) {
		Goods goods = this.convertVO2PO(goodsDTO);
		dao.add(goods);
	}

	private Goods convertVO2PO(GoodsDTO goodsDTO) {
		Goods goods = null;
		if (goodsDTO != null) {
			goods = new Goods();
			goods.setgAddress(goodsDTO.getgAddress());
			goods.setgDescribe(goodsDTO.getgDescribe());
			goods.setgId(goodsDTO.getgId());  //做更新的时候使用
			goods.setgName(goodsDTO.getgName());
			goods.setgPic(goodsDTO.getgPic());
			goods.setgPrice(goodsDTO.getgPrice());
			goods.setgType(goodsDTO.getgType());
		}
		return goods;
	}

	/**
	 * 删除商品
	 */
	@Override
	public void deleteGoods(int gId) {
		dao.delete(gId);
	}

	/**
	 * 更改商品
	 * 
	 * @param goods
	 */
	@Override
	public void updateGoods(GoodsDTO goodsDTO) {
		Goods goods = this.convertVO2PO(goodsDTO);
		dao.update(goods);
	}

	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	@Override
	public List<GoodsDTO> searchGoods() {
		List<Goods> listgoods = dao.search();
		List<GoodsDTO> listgoodsDTO = this.convertPO2VOList(listgoods);
		return listgoodsDTO;
	}

	private List<GoodsDTO> convertPO2VOList(List<Goods> listgoods) {
		List<GoodsDTO> goodsDTOList = new ArrayList<GoodsDTO>();
		for (int i=0; listgoods!=null && i<listgoods.size(); ++i) {
			GoodsDTO goodsDTO = new GoodsDTO();
			Goods goods = listgoods.get(i);
			goodsDTO.setgAddress(goods.getgAddress());
			goodsDTO.setgDescribe(goods.getgDescribe());
			goodsDTO.setgId(goods.getgId());
			goodsDTO.setgName(goods.getgName());
			goodsDTO.setgPic(goods.getgPic());
			goodsDTO.setgPrice(goods.getgPrice());
			goodsDTO.setgType(goods.getgType());
			goodsDTOList.add(goodsDTO);
		}
		
		return goodsDTOList;
	}

	/**
	 * 根据商品id查询商品
	 * 
	 * @param gId
	 * @return
	 */
	@Override
	public GoodsDTO searchGoods(int gId) {
		Goods goods = dao.search(gId);
		GoodsDTO goodsDTO = this.convertPO2VO(goods);
		return goodsDTO;
	}

	private GoodsDTO convertPO2VO(Goods goods) {
		GoodsDTO goodsDTO = null;
		if (goods != null) {
			goodsDTO = new GoodsDTO();
			goodsDTO.setgAddress(goods.getgAddress());
			goodsDTO.setgDescribe(goods.getgDescribe());
			goodsDTO.setgId(goods.getgId());
			goodsDTO.setgName(goods.getgName());
			goodsDTO.setgPic(goods.getgPic());
			goodsDTO.setgPrice(goods.getgPrice());
			goodsDTO.setgType(goods.getgType());
		}
		
		return goodsDTO;
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 *            开始的位置
	 * @param pageSize
	 *            分页大小
	 * @return
	 */
	@Override
	public List<GoodsDTO> searchPage_Goods(Integer pageNo, Integer pageSize) {
		List<Goods> listgoodspage = dao.searchPage_Goods(pageNo, pageSize);
		List<GoodsDTO> goodsDTOList = this.convertPO2VOList(listgoodspage);
		return goodsDTOList;
	}

	/**
	 * 商品总数
	 * 
	 * @return
	 */
	@Override
	public Integer searchGoodsCount() {
		Integer count = dao.searchGoodsCount();
		return count;
	}

	/**
	 * 根据名字查询商品
	 */
	@Override
	public List<GoodsDTO> searchByGname(String gName) {
		List<Goods> listGS = dao.searchByGname(gName);
		List<GoodsDTO> listGO = this.convertPO2VOList(listGS);
		return listGO;
	}

	/**
	 * 魏陈霞 拼接条件查询
	 */
	@Override
	public List<GoodsDTO> searchByCondition(GoodsDTO goodsDTO) {
		if (goodsDTO == null) {
			return null;
		}
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		
		if (goodsDTO.getgAddress()!=null && !"".equals(goodsDTO.getgAddress())) {
			hqlWhere += " and gAddress=? ";
			params.add(goodsDTO.getgAddress());
		}
		if (goodsDTO.getgDescribe()!=null && !"".equals(goodsDTO.getgDescribe())) {
			hqlWhere += " and gDescribe=? ";
			params.add(goodsDTO.getgDescribe());
		}
		if (goodsDTO.getgId() != 0) {
			hqlWhere += " and gId=? ";
			params.add(goodsDTO.getgId());
		}
		if (goodsDTO.getgName()!=null && !"".equals(goodsDTO.getgName())) {
			hqlWhere += " and gName=? ";
			params.add(goodsDTO.getgName());
		}
		if (goodsDTO.getgPic()!=null && !"".equals(goodsDTO.getgPic())) {
			hqlWhere += " and gPic=? ";
			params.add(goodsDTO.getgPic());
		}
		if (goodsDTO.getgPrice() != null) {
			hqlWhere += " and gPrice=? ";
			params.add(goodsDTO.getgPrice());
		}
		if (goodsDTO.getgType()!=null && !"".equals(goodsDTO.getgType())) {
			hqlWhere += " and gType=? ";
			params.add(goodsDTO.getgType());
		}
		List<Goods> goodsList = dao.searchByCondition(hqlWhere, params.toArray(), null);
		List<GoodsDTO> goodsDTOList = this.convertPO2VOList(goodsList);
		return goodsDTOList;
	}

	@Override
	public List<GoodsDTO> searchGoodBysId(int sId, int pageNo, int pageSize) {
		List<Goods> listGS = dao.searchGoodsBySid(sId, pageNo, pageSize);
		List<GoodsDTO> listGO = this.convertPO2VOList(listGS);
		return listGO;
	}

	@Override
	public List<GoodsDTO> searchGoodBysId(int sId) {
		List<Goods> listGS = dao.searchGoodsBySid(sId);
		List<GoodsDTO> listGO = this.convertPO2VOList(listGS);
		return listGO;
	}

	@Override
	public int searchGoodsCountBySid(int sId) {
		int count = dao.searchGoodsCountBySid(sId);
		return count;
	}

}
