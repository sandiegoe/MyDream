package com.arex.mydream.service;

import java.util.List;

import com.arex.mydream.action.vo.SaleDTO;


public interface SaleBiz {

	/**
	 * 增加销售信息
	 */
	public void addSale(SaleDTO saleDTO);

	/**
	 * 删除销售信息
	 */
	public void deleteSale(int saId);

	/**
	 * 修改销售信息
	 */
	public void updateSale(SaleDTO sale);

	/**
	 * 查询全部销售信息
	 */
	public List<SaleDTO> searchSale();

	/**
	 * 根据ID查询销售信息
	 */
	public SaleDTO serachSale(int saId);

	/**
	 * 根据店面查找销售信息
	 * @param sName
	 * @return
	 */
	public SaleDTO searchSaleBysName(String sName);

}

