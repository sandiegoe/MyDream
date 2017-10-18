package com.arex.mydream.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.arex.mydream.action.vo.SaleDTO;
import com.arex.mydream.dao.SaleDao;
import com.arex.mydream.model.Sale;
import com.arex.mydream.service.SaleBiz;

@Component
@Transactional
public class SaleBizImpl implements SaleBiz {

	@Resource(name="saleDaoImpl")
	private SaleDao saleDao;
	
	@Override
	public void addSale(SaleDTO saleDTO) {
		Sale sale = this.convertVO2PO(saleDTO);
		saleDao.add(sale);
	}

	private Sale convertVO2PO(SaleDTO saleDTO) {
		Sale sale = null;
		if (saleDTO != null) {
			sale = new Sale();
			sale.setSaId(saleDTO.getSaId());
			sale.setSaNum(saleDTO.getSaNum());
			sale.setSaPrice(saleDTO.getSaPrice());
			sale.setSaSname(saleDTO.getSaSname());
			sale.setSaVisitor(saleDTO.getSaVisitor());
		}
		return sale;
	}

	@Override
	public void deleteSale(int saId) {
		saleDao.delete(saId);
	}

	@Override
	public void updateSale(SaleDTO saleDTO) {
		Sale sale = this.convertVO2PO(saleDTO);
		saleDao.update(sale);
	}

	@Override
	public List<SaleDTO> searchSale() {
		List<Sale> listSE = saleDao.search();
		List<SaleDTO> listSO = this.convertPO2VOList(listSE);
		return listSO;
	}

	private List<SaleDTO> convertPO2VOList(List<Sale> listSE) {
		List<SaleDTO> listSO = new ArrayList<SaleDTO>();
		SaleDTO saleDTO = null;
		Sale sale = null;
		
		for (int i=0; listSE!=null && i<listSE.size(); ++i) {
			sale = listSE.get(i);
			saleDTO = new SaleDTO();
			saleDTO.setSaId(sale.getSaId());
			saleDTO.setSaNum(sale.getSaNum());
			saleDTO.setSaPrice(sale.getSaPrice());
			saleDTO.setSaSname(sale.getSaSname());
			saleDTO.setSaVisitor(sale.getSaVisitor());
			listSO.add(saleDTO);
		}
		
		return listSO;
	}

	@Override
	public SaleDTO serachSale(int saId) {
		Sale sale = saleDao.searchSale(saId);
		SaleDTO saleDTO = this.convertPO2VO(sale);
		return saleDTO;
	}

	private SaleDTO convertPO2VO(Sale sale) {
		SaleDTO saleDTO = null;
		if (sale != null) {
			saleDTO = new SaleDTO();
			saleDTO.setSaId(sale.getSaId());
			saleDTO.setSaNum(sale.getSaNum());
			saleDTO.setSaPrice(sale.getSaPrice());
			saleDTO.setSaSname(sale.getSaSname());
			saleDTO.setSaVisitor(sale.getSaVisitor());
		}
		return saleDTO;
	}

	@Override
	public SaleDTO searchSaleBysName(String sName) {
		Sale sale = saleDao.searchSaleBysName(sName);
		SaleDTO saleDTO = this.convertPO2VO(sale);
		return saleDTO;
	}

}
