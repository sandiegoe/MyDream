package com.arex.mydream.dao;

import java.util.List;

import com.arex.mydream.model.Sale;

public interface SaleDao {
	/**
	 * 增加销售
	 */
	public void add(Sale sale);

	/**
	 * 删除销售
	 */
	public void delete(int saId);

	/**
	 * 更改销售信息
	 */
	public void update(Sale sale);

	/**
	 * 查询销售信息
	 * 
	 * @return
	 */
	public List<Sale> search();

	/**
	 * 根据销售id查询销售信息
	 * 
	 * @return
	 */
	public Sale searchSale(int saId);

	/**
	 * 根据店面名字查找销售信息
	 * @param sName
	 * @return
	 */
	public Sale searchSaleBysName(String sName);

}
