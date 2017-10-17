package com.arex.mydream.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.mydream.dao.PurchaseDao;
import com.arex.mydream.model.Purchase;

@Component
@Transactional
public class PurchaseDaoImpl implements PurchaseDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	
	@Override
	public List<Purchase> search() {
		List<Purchase> listPE = (List<Purchase>) hibernateTemplate.find("select purchase from Purchase purchase", null);
		return listPE;
	}

	@Override
	public void add(Purchase purchase) {
		hibernateTemplate.save(purchase);
	}

	@Override
	public void update(Purchase purchase) {
		hibernateTemplate.update(purchase);
	}

	@Override
	public void delete(final Integer pId) {
		hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				session.createQuery("delete from Purchase purchase where purchase.pId=:pId")
					.setParameter("pId", pId)
					.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public Purchase searchPurchase(int pUid, int pSid, int pGid) {
		Purchase purchase = null;
		List<Purchase> listPE = (List<Purchase>) hibernateTemplate.find("select purchase from Purchase purchase where purchase.pUid=? and pSid=? and pGid=? order by pId desc ", pUid, pSid, pGid);

		if (listPE!=null && listPE.size()>0) {
			purchase = listPE.get(0);
		}
		
		return purchase;
	}

	@Override
	public Purchase search(int pId) {
		
		Purchase purchase = null;
		List<Purchase> listPE = (List<Purchase>) hibernateTemplate.find("select purchase from Purchase purchase where purchase.pId=?", pId);

		if (listPE!=null && listPE.size()>0) {
			purchase = listPE.get(0);
		}
		
		return purchase;
	}

}
