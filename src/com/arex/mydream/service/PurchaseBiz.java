package com.arex.mydream.service;

import java.util.List;

import com.arex.mydream.action.vo.PurchaseDTO;

public interface PurchaseBiz {

	/**
	 * 查询购物下单信息
	 * @param pc
	 */
	public List<PurchaseDTO> search();
	/**
	 * 添加下单信息
	 * @param pc
	 */
	
	public void add(PurchaseDTO purchaseDTO);
	/**
	 * 更新下单信息
	 * @param pId
	 */
	
	public void update(PurchaseDTO purchaseDTO);
	/**
	 * 删除下单信息
	 * @return
	 */
	
	public void delete(Integer pId);
	
	/**
	 * 查询购买店面商品信息
	 * @return
	 */
	//public List<Pugousst> searchPugousst();
	/**
	 * 根据买家ID  商品ID  购买ID查询
	 * @param pUid
	 * @param pSid
	 * @param pGid
	 * @return
	 */
	 public  PurchaseDTO searchPurchaseDTO(int pUid,int pSid,int pGid);
	 /**
	  * 根据购买ID查询
	  * @param pId
	  * @return
	  */
	  public  PurchaseDTO search(int pId);
}

