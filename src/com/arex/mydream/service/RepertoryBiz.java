package com.arex.mydream.service;

import java.util.List;

import com.arex.mydream.action.vo.RepertoryDTO;


public interface RepertoryBiz {
	 /**
	  * 多表查詢信息
	 * @return
	 */
	public List<RepertoryDTO> searchRepertory();
	/**
	 * 多表条件查询
	 */
	public List<RepertoryDTO> search(RepertoryDTO repertoryDTO);
	 /**
	  * 陈霞
	  * 分页查询
	  */
	public List<RepertoryDTO> searchPage_Repertory(RepertoryDTO repertoryDTO,Integer pageNo,Integer pageSize);
	/**
	 * 陈霞
	 * 记录总数
	 */
	public Integer searchRepertoryCount(RepertoryDTO repertoryDTO);
	/**
	 * 查询礼品总数
	 * @param Repetory
	 * @return
	 */
	public Integer  searchGiftCount(RepertoryDTO repertoryDTO);
	/**
	 *根据商品id进行联表查询
	 * @param gid
	 * @return
	 */
	public  List<RepertoryDTO> searchBygId(Integer gid);
	/**
	 * 小平
	 * 多表查询礼品
	 * @param Repetory
	 * @return
	 */
	public List<RepertoryDTO> searchGift(RepertoryDTO repertoryDTO);
	/**
	 * 陈霞
	 * 多表分页查询礼品
	 * @param Repetory
	 * @return
	 */
	public List<RepertoryDTO> searchGift(RepertoryDTO repertoryDTO,Integer pageNo,Integer pageSize);
	/**
	 * 陈霞
	 * 添加库存
	 */
	public void addRepertory(RepertoryDTO repertoryDTO);
	/**
	 * 陈霞
	 * 删除库存
	 */
	public void deletRepertory(RepertoryDTO repertoryDTO);
}
