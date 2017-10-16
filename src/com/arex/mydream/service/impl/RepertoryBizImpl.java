package com.arex.mydream.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.RepertoryDTO;
import com.arex.mydream.dao.RepertoryDao;
import com.arex.mydream.model.Repertory;
import com.arex.mydream.service.RepertoryBiz;

@Component
@Transactional
public class RepertoryBizImpl implements RepertoryBiz {

	@Resource(name="repertoryDaoImpl")
	private RepertoryDao repertoryDao;

	@Override
	public List<RepertoryDTO> searchRepertory() {
		List<Repertory> listRY = repertoryDao.search();
		List<RepertoryDTO> listRO = this.convertPO2VOList(listRY);
		return listRO;
	}

	private List<RepertoryDTO> convertPO2VOList(List<Repertory> listRY) {
		List<RepertoryDTO> listRO = new ArrayList<RepertoryDTO>();
		for (int i=0; listRY!=null && i<listRY.size(); ++i) {
			Repertory repertory = listRY.get(i);
			RepertoryDTO repertoryDTO = new RepertoryDTO();
			repertoryDTO.setrGid(repertory.getrGid());
			repertoryDTO.setrId(repertory.getrId());
			repertoryDTO.setrNum(repertory.getrNum());
			repertoryDTO.setrSid(repertory.getrSid());
			listRO.add(repertoryDTO);
		}
		return listRO;
	}

	@Override
	public List<RepertoryDTO> search(RepertoryDTO repertoryDTO) {
		
		if (repertoryDTO == null) {
			return null;
		}
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = null;
		
		if (repertoryDTO.getrGid() != 0) {
			hqlWhere += " and o.rGid=? ";
			params.add(repertoryDTO.getrGid());
		}
		if (repertoryDTO.getrId() != 0) {
			hqlWhere += " and o.rId=? ";
			params.add(repertoryDTO.getrId());
		}
		if (repertoryDTO.getrNum() != 0) {
			hqlWhere += " and o.rNum=? ";
			params.add(repertoryDTO.getrNum());
		}
		if (repertoryDTO.getrSid() != 0) {
			hqlWhere += " and o.rSid=? ";
			params.add(repertoryDTO.getrSid());
		}
		
		List<Repertory> listRY = repertoryDao.search(hqlWhere, params.toArray(), orderby);
		List<RepertoryDTO> listRO = this.convertPO2VOList(listRY);
		return listRO;
	}

	@Override
	public List<RepertoryDTO> searchPage_Repertory(RepertoryDTO repertoryDTO, Integer pageNo, Integer pageSize) {
		if (repertoryDTO == null) {
			return null;
		}
		
		String hqlWhere = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = null;
		
		if (repertoryDTO.getrGid() != 0) {
			hqlWhere += " and o.rGid=? ";
			params.add(repertoryDTO.getrGid());
		}
		if (repertoryDTO.getrId() != 0) {
			hqlWhere += " and o.rId=? ";
			params.add(repertoryDTO.getrId());
		}
		if (repertoryDTO.getrNum() != 0) {
			hqlWhere += " and o.rNum=? ";
			params.add(repertoryDTO.getrNum());
		}
		if (repertoryDTO.getrSid() != 0) {
			hqlWhere += " and o.rSid=? ";
			params.add(repertoryDTO.getrSid());
		}
		if (repertoryDTO.getgName()!=null && !"".equals(repertoryDTO.getgName())) {
			hqlWhere += " and gName like ? ";
			params.add(repertoryDTO.getgName());
		}
		if (repertoryDTO.getgType()!=null && !"".equals(repertoryDTO.getgType())) {
			hqlWhere += " and gType like ? ";
			params.add(repertoryDTO.getgType());
		}
		if (repertoryDTO.getgAddress()!= null && !"".equals(repertoryDTO.getgAddress())) {
			hqlWhere += " and gAddress like ? ";
			params.add(repertoryDTO.getgAddress());
		}
		
		
		List<Repertory> listRY = repertoryDao.searchPage_Repertory(hqlWhere, params.toArray(), orderby, pageNo, pageSize);
		List<RepertoryDTO> listRO = this.convertPO2VOList(listRY);
		return listRO;
	}

	@Override
	public Integer searchRepertoryCount(RepertoryDTO repertoryDTO) {
//		Repertory repertory = this.convertVO2PO(repertoryDTO);
		Integer count = repertoryDao.searchRepertoryCount(repertoryDTO);
		return count;
	}

	private Repertory convertVO2PO(RepertoryDTO repertoryDTO) {
		Repertory repertory = null;
		if (repertoryDTO != null) {
			repertory = new Repertory();
			repertory.setrGid(repertoryDTO.getrGid());
			repertory.setrId(repertoryDTO.getrId());
			repertory.setrNum(repertoryDTO.getrNum());
			repertory.setrSid(repertoryDTO.getrSid());
		}
		
		return repertory;
	}

	@Override
	public Integer searchGiftCount(RepertoryDTO repertoryDTO) {
		Repertory repertory = this.convertVO2PO(repertoryDTO);
		Integer count = repertoryDao.searchGiftCount(repertory);
		return count;
	}

	@Override
	public List<RepertoryDTO> searchBygId(Integer gid) {
		List<Repertory> listRY = repertoryDao.searchBygId(gid);
		List<RepertoryDTO> listRO = this.convertPO2VOList(listRY);
		return listRO;
	}

	@Override
	public List<RepertoryDTO> searchGift(RepertoryDTO repertoryDTO) {
		Repertory repertory = this.convertVO2PO(repertoryDTO);
		List<Repertory> listRY = repertoryDao.searchGift(repertory);
		List<RepertoryDTO> listRO = this.convertPO2VOList(listRY);
		return listRO;
	}

	@Override
	public List<RepertoryDTO> searchGift(RepertoryDTO repertoryDTO, Integer pageNo, Integer pageSize) {
		Repertory repertory = this.convertVO2PO(repertoryDTO);
		List<Repertory> listRY = repertoryDao.searchGift(repertory, pageNo, pageSize);
		List<RepertoryDTO> listRO = this.convertPO2VOList(listRY);
		return listRO;
	}

	@Override
	public void addRepertory(RepertoryDTO repertoryDTO) {
		Repertory repertory = this.convertVO2PO(repertoryDTO);
		repertoryDao.addRepertory(repertory);
	}

	@Override
	public void deletRepertory(RepertoryDTO repertoryDTO) {
		Repertory repertory = this.convertVO2PO(repertoryDTO);
		repertoryDao.deletRepertory(repertory);
	}
}
