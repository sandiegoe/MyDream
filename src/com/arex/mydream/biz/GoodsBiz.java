package com.arex.mydream.biz;

import java.util.List;

import com.arex.mydream.entity.view.GoodsDTO;

public interface GoodsBiz {

	/**
	 * 增加商品
	 * 
	 * @param goods
	 */
	public void addGoods(GoodsDTO goodsDTO);

	/**
	 * 删除商品
	 */
	public void deleteGoods(int gId);

	/**
	 * 更改商品
	 * 
	 * @param goods
	 */
	public void updateGoods(GoodsDTO goodsDTO);

	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	public List<GoodsDTO> searchGoods();

	/**
	 * 根据商品id查询商品
	 * 
	 * @param gId
	 * @return
	 */
	public GoodsDTO searchGoods(int gId);

	/**
	 * 根据商品名字查询商品
	 * 
	 * @param gName
	 * @return
	 */
	public GoodsDTO searchByGname(String gName);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 *            开始的位置
	 * @param pageSize
	 *            分页大小
	 * @return
	 */
	public List<GoodsDTO> searchPage_Goods(Integer pageNo, Integer pageSize);

	/**
	 * 商品总数
	 * 
	 * @return
	 */
	public Integer searchGoodsCount();

	/**
	 * 魏陈霞 拼接条件查询
	 */
	public List<GoodsDTO> searchByCondition(GoodsDTO goodsDTO);

}
