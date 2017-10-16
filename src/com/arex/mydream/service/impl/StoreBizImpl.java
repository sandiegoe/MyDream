package com.arex.mydream.service.impl;

import java.util.ArrayList;
/**
 * 商品业务层实现类
 * @author 陈霞
 *2016-3-8下午1:22:10
 *
 */
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.StoreDTO;
import com.arex.mydream.dao.StoreDao;
import com.arex.mydream.model.Store;
import com.arex.mydream.service.StoreBiz;


@Component
@Transactional
public class StoreBizImpl implements StoreBiz {
	
	@Resource(name="storeDaoImpl")
	private StoreDao dao;

	@Override
	public void add(StoreDTO storeDTO) {
		Store store = this.convertVO2PO(storeDTO);
		dao.add(store);
	}

	private Store convertVO2PO(StoreDTO storeDTO) {
		Store store = null;
		if (storeDTO != null) {
			store = new Store();
			store.setsAddress(storeDTO.getsAddress());
			store.setsId(storeDTO.getsId());   //service层做更新时间使用
			store.setsName(storeDTO.getsName());
			store.setsPhone(storeDTO.getsPhone());
			store.setSuid(storeDTO.getSuid());
		}
		return store;
	}

	@Override
	public void delete(int sid) {
		dao.delete(sid);
	}

	@Override
	public void update(StoreDTO storeDTO) {
		Store store = this.convertVO2PO(storeDTO);
		dao.update(store);
	}

	@Override
	public List<StoreDTO> search() {
		List<Store> listSE = dao.search();
		List<StoreDTO> listSO = this.convertPO2VOList(listSE);
		return listSO;
	}

	private List<StoreDTO> convertPO2VOList(List<Store> listSE) {
		List<StoreDTO> listSO = new ArrayList<StoreDTO>();
		for (int i=0; listSE!=null && i<listSE.size(); ++i) {
			StoreDTO storeDTO = new StoreDTO();
			Store store = listSE.get(i);
			storeDTO.setsAddress(store.getsAddress());
			storeDTO.setsId(store.getsId());
			storeDTO.setsName(store.getsName());
			storeDTO.setsPhone(store.getsPhone());
			storeDTO.setSuid(store.getSuid());
			listSO.add(storeDTO);
		}
		
		return listSO;
	}

	@Override
	public StoreDTO searchStore(int sid) {
		Store store = dao.searchStore(sid);
		StoreDTO storeDTO = this.convertPO2VO(store);
		return storeDTO;
	}

	private StoreDTO convertPO2VO(Store store) {
		StoreDTO storeDTO = null;
		if (store != null) {
			storeDTO = new StoreDTO();
			storeDTO.setsAddress(store.getsAddress());
			storeDTO.setsId(store.getsId());
			storeDTO.setsName(store.getsName());
			storeDTO.setsPhone(store.getsPhone());
			storeDTO.setSuid(store.getSuid());
		}
		
		return storeDTO;
	}

	@Override
	public List<StoreDTO> searchPage_Store(Integer pageNo, Integer pageSize) {
		List<Store> listSE = dao.searchPage_Store(pageNo, pageSize);
		List<StoreDTO> listSO = this.convertPO2VOList(listSE);
		return listSO;
	}

	@Override
	public Integer searchStoreCount() {
		Integer count = dao.searchStoreCount();
		return count;
	}

	@Override
	public StoreDTO searchStoreBySuid(Integer sUid) {
		Store store = dao.searchStoreBySuid(sUid);
		StoreDTO storeDTO = this.convertPO2VO(store);
		return storeDTO;
	}

	
}
