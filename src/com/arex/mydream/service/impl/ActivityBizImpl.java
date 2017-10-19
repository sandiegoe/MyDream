package com.arex.mydream.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.ActivityDTO;
import com.arex.mydream.dao.ActivityDao;
import com.arex.mydream.model.Activity;
import com.arex.mydream.service.ActivityBiz;

@Component
@Transactional
public class ActivityBizImpl implements ActivityBiz {

	@Resource(name = "activityDaoImpl")
	private ActivityDao activityDao;

	@Override
	public void add(ActivityDTO activityDTO) {
		Activity activity = this.convertVO2PO(activityDTO);
		activityDao.add(activity);
	}

	private Activity convertVO2PO(ActivityDTO activityDTO) {
		Activity activity = null;
		if (activityDTO != null) {
			activity = new Activity();
			activity.setaDate(activityDTO.getaDate());
			activity.setaGid(activityDTO.getaGid());
			activity.setaId(activityDTO.getaId());
			activity.setaPic(activityDTO.getaPic());
			activity.setaPrice(activityDTO.getaPrice());
		}

		return activity;
	}

	@Override
	public void delete(int aId) {
		activityDao.delete(aId);
	}

	@Override
	public void deleteByaGid(int aGid) {
		activityDao.deleteByaGid(aGid);
	}

	@Override
	public void update(ActivityDTO activityDTO) {
		Activity activity = this.convertVO2PO(activityDTO);
		activityDao.update(activity);
	}

	@Override
	public ActivityDTO searchById(int aId) {
		Activity activity = activityDao.searchById(aId);
		ActivityDTO activityDTO = this.convertPO2VO(activity);
		return activityDTO;
	}

	private ActivityDTO convertPO2VO(Activity activity) {
		ActivityDTO activityDTO = null;
		if (activity != null) {
			activityDTO = new ActivityDTO();
			activityDTO.setaDate(activity.getaDate());
			activityDTO.setaGid(activity.getaGid());
			activityDTO.setaId(activity.getaId());
			activityDTO.setaPic(activity.getaPic());
			activityDTO.setaPrice(activity.getaPrice());
		}
		
		return activityDTO;
	}

	@Override
	public List<ActivityDTO> search() {
		List<Activity> listAY = activityDao.search();
		List<ActivityDTO> listAO = this.convertPO2VOList(listAY);
		return listAO;
	}

	private List<ActivityDTO> convertPO2VOList(List<Activity> listAY) {
		List<ActivityDTO> listAO = new ArrayList<ActivityDTO>();
		ActivityDTO activityDTO = null;
		Activity activity = null;
		
		for (int i=0; listAY!=null && i<listAY.size(); ++i) {
			activity = listAY.get(i);
			activityDTO = new ActivityDTO();
			activityDTO.setaDate(activity.getaDate());
			activityDTO.setaGid(activity.getaGid());
			activityDTO.setaId(activity.getaId());
			activityDTO.setaPic(activity.getaPic());
			activityDTO.setaPrice(activity.getaPrice());
			listAO.add(activityDTO);
		}
		
		return listAO;
	}

	@Override
	public List<ActivityDTO> searchByadate(String aDate) {
		List<Activity> listAY = activityDao.searchByadate(aDate);
		List<ActivityDTO> listAO = this.convertPO2VOList(listAY);
		return listAO;
	}

	@Override
	public List<ActivityDTO> searchPage(String aDate, Integer pageNo,
			Integer pageSize) {
		List<Activity> listAY = activityDao.searchByadateWithPage(aDate, pageNo, pageSize);
		List<ActivityDTO> listAO = this.convertPO2VOList(listAY);
		return listAO;
	}

	@Override
	public Integer searchCountByaDate(String aDate) {
		int count = activityDao.searchCountByadate(aDate);
		return count;
	}

	@Override
	public List<ActivityDTO> searchByaGid(int aGid) {
		List<Activity> listAY = activityDao.searchByaGid(aGid);
		List<ActivityDTO> listAO = this.convertPO2VOList(listAY);
		return listAO;
	}

}
