package com.arex.mydream.service;

import java.util.List;

import com.arex.mydream.action.vo.ActivityDTO;


public interface ActivityBiz {

	/**
	 * 增加活动
	 * 
	 * @param ac
	 */
	public void add(ActivityDTO activity);

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
	public void update(ActivityDTO activity);

	/**
	 * 查询活动
	 * 
	 * @param aId
	 * @return
	 */
	public ActivityDTO searchById(int aId);

	/**
	 * 查询所有活动
	 * 
	 * @return
	 */
	public List<ActivityDTO> search();

	/**
	 * 根据活动日期查询
	 * 
	 * @param adata
	 * @return
	 */
	public List<ActivityDTO> searchByadate(String aDate);

	/**
	 * 分页根据日期查询所有活动
	 * 
	 * @return
	 */
	public List<ActivityDTO> searchPage(String aDate, Integer pageNo,
			Integer pageSize);

	/**
	 * 分页根据日期查询所有记录数
	 * 
	 * @return
	 */
	public Integer searchCountByaDate(String aDate);

	/**
	 * 根据aGid查询活动
	 * @param getgId
	 * @return
	 */
	public List<ActivityDTO> searchByaGid(int getgId);
}
