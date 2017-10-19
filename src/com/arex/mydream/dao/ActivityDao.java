package com.arex.mydream.dao;

import java.util.List;

import com.arex.mydream.model.Activity;

public interface ActivityDao {
	/**
	 * 增加活动
	 * 
	 * @param ac
	 */
	public void add(Activity activity);

	/**
	 * 删除活动
	 * 
	 * @param aId
	 */
	public void delete(int aId);

	/**
	 * 根据aGid删除活动
	 */
	public void deleteByaGid(int aGid);

	/**
	 * 更改活动
	 * 
	 * @param ac
	 */
	public void update(Activity activity);

	/**
	 * 查询活动
	 * 
	 * @param aId
	 * @return
	 */
	public Activity searchById(int aId);

	/**
	 * 查询所有活动
	 * 
	 * @return
	 */
	public List<Activity> search();

	/**
	 * 根据活动日期查询
	 * 
	 * @param adata
	 * @return
	 */
	public List<Activity> searchByadate(String aDate);

	/**
	 * 分页根据日期查询所有活动
	 * 
	 * @return
	 */
	public List<Activity> searchByadateWithPage(String aDate, Integer pageNo,
			Integer pageSize);

	/**
	 * 分页根据日期查询所有记录数
	 * 
	 * @return
	 */
	public Integer searchCountByadate(String aDate);

	/**
	 * 根据aGid查询所有活动
	 * @param aGid
	 * @return
	 */
	public List<Activity> searchByaGid(int aGid);

}
