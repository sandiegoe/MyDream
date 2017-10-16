package com.arex.mydream.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.arex.mydream.action.vo.RepertoryDTO;
import com.arex.mydream.model.Repertory;


/**
 * 库存接口
 * 
 * @author 葛睿 2016-3-4上午10:42:38
 */
public interface RepertoryDao {
	/**
	 * 多表查詢信息
	 * 
	 * @return
	 */
	public List<Repertory> search();

	/**
	 * 多表条件查询
	 */
	public List<Repertory> search(String hqlWhere, Object[] objects, LinkedHashMap<String, String> orderby);

	/**
	 * 陈霞 分页查询
	 */
	public List<Repertory> searchPage_Repertory(String hqlWhere, Object[] objects, LinkedHashMap<String, String> orderby, Integer pageNo,
			Integer pageSize);

	/**
	 * 陈霞 记录总数
	 */
	public Integer searchRepertoryCount(Repertory repertory);

	/**
	 * 查询礼品的数量
	 * @param regost
	 * @return
	 */
	public Integer searchGiftCount(Repertory repertory);

	/**
	 * 林兴鹏 根据商品id进行联表查询
	 * 
	 * @param gid
	 * @return
	 */
	public List<Repertory> searchBygId(Integer gid);

	/**
	 * 小平 多表查询礼品
	 * 
	 * @param regost
	 * @return
	 */
	public List<Repertory> searchGift(Repertory repertory);

	/**
	 * 陈霞 多表分页查询礼品
	 * 
	 * @param regost
	 * @return
	 */
	public List<Repertory> searchGift(Repertory repertory, Integer pageNo,
			Integer pageSize);

	/**
	 * 陈霞 添加库存
	 */
	public void addRepertory(Repertory re);

	/**
	 * 陈霞 删除库存 根据rSid， 和 rGid
	 */
	public void deletRepertory(Repertory re);

	Integer searchRepertoryCount(RepertoryDTO repertoryDTO);

}
