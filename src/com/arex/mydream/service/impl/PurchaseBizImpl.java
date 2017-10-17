package com.arex.mydream.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.PurchaseDTO;
import com.arex.mydream.dao.PurchaseDao;
import com.arex.mydream.model.Purchase;
import com.arex.mydream.service.PurchaseBiz;

@Component
@Transactional
public class PurchaseBizImpl implements PurchaseBiz {

	@Resource(name="purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	
	
	@Override
	public List<PurchaseDTO> search() {
		List<Purchase> listPE = purchaseDao.search();
		List<PurchaseDTO> listPO = this.convertPO2VOList(listPE);
		return listPO;
	}

	private List<PurchaseDTO> convertPO2VOList(List<Purchase> listPE) {
		PurchaseDTO purchaseDTO = null;
		Purchase purchase = null;
		List<PurchaseDTO> listPO = new ArrayList<PurchaseDTO>();
		
		for (int i=0; listPE!=null && i<listPE.size(); ++i) {
			purchaseDTO = new PurchaseDTO();
			purchase = listPE.get(i);
			purchaseDTO.setpGid(purchase.getpGid());
			purchaseDTO.setpId(purchase.getpId());
			purchaseDTO.setpNum(purchase.getpNum());
			purchaseDTO.setpSid(purchase.getpSid());
			purchaseDTO.setpUid(purchase.getpUid());
			listPO.add(purchaseDTO);
		}
		return listPO;
	}

	@Override
	public void add(PurchaseDTO purchaseDTO) {
		Purchase purchase = this.convertVO2VO(purchaseDTO);
		purchaseDao.add(purchase);
	}

	private Purchase convertVO2VO(PurchaseDTO purchaseDTO) {
		Purchase purchase = null;
		if (purchaseDTO != null) {
			purchase = new Purchase();
			purchase.setpGid(purchaseDTO.getpGid());
			purchase.setpId(purchaseDTO.getpId());
			purchase.setpNum(purchaseDTO.getpNum());
			purchase.setpSid(purchaseDTO.getpSid());
			purchase.setpUid(purchaseDTO.getpUid());
		}
		return purchase;
	}

	@Override
	public void update(PurchaseDTO purchaseDTO) {
		Purchase purchase = this.convertVO2VO(purchaseDTO);
		purchaseDao.update(purchase);
	}

	@Override
	public void delete(Integer pId) {
		purchaseDao.delete(pId);
	}

	@Override
	public PurchaseDTO searchPurchaseDTO(int pUid, int pSid, int pGid) {
		Purchase purchase = purchaseDao.searchPurchase(pUid, pSid, pGid);
		PurchaseDTO purchaseDTO = this.convertPO2VO(purchase);
		return purchaseDTO;
	}

	private PurchaseDTO convertPO2VO(Purchase purchase) {
		PurchaseDTO purchaseDTO = null;
		if (purchase != null) {
			purchaseDTO = new PurchaseDTO();
			purchaseDTO.setpGid(purchase.getpGid());
			purchaseDTO.setpId(purchase.getpId());
			purchaseDTO.setpNum(purchase.getpNum());
			purchaseDTO.setpSid(purchase.getpSid());
			purchaseDTO.setpUid(purchase.getpUid());
		}
		return purchaseDTO;
	}

	@Override
	public PurchaseDTO search(int pId) {
		Purchase purchase = purchaseDao.search(pId);
		PurchaseDTO purchaseDTO = this.convertPO2VO(purchase);
		return purchaseDTO;
	}

}
