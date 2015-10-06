package com.ahana.api.dao.common;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ahana.api.common.AhanaVO;

public class AhanaDaoSupport extends HibernateDaoSupport implements AhanaDao{
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public AhanaVO get(Class entity, String oid) {
        return (AhanaVO) getHibernateTemplate().get(entity, oid);
    }
  
    public AhanaVO save(Object entityObject) {
		getHibernateTemplate().save(entityObject);
        return (AhanaVO) entityObject;
    }
   
    public AhanaVO saveOrUpdate(Object entityObject) {
    	getHibernateTemplate().saveOrUpdate(entityObject);
        return (AhanaVO) entityObject;
    }
  
    @SuppressWarnings("rawtypes")
    public Collection saveOrUpdateAll(Collection entities) {
    	getHibernateTemplate().saveOrUpdate(entities);
        return entities;
    }
  
    public void bulkUpdate(String queryString, Object value) {
        getHibernateTemplate().bulkUpdate(queryString, value);
    }
   
    @SuppressWarnings("rawtypes")
    public List find(String HQLQuery) {
        return getHibernateTemplate().find(HQLQuery);
    }
   
    @SuppressWarnings("rawtypes")
    public List find(String HQLQuery, String nameOfTheParam,Object valueOfTheParam) throws Exception {
        if (nameOfTheParam != null && valueOfTheParam != null) {
            getHibernateTemplate().findByNamedParam(HQLQuery, nameOfTheParam, valueOfTheParam);
        } else {
            throw new Exception("");
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public List find(String HQLQuery, String[] nameOfTheParam,Object[] valueOfTheParam) throws Exception {
        if (nameOfTheParam != null && valueOfTheParam != null && (nameOfTheParam.length == valueOfTheParam.length)) {
            return getHibernateTemplate().findByNamedParam(HQLQuery, nameOfTheParam, valueOfTheParam);
        } else {
            throw new Exception("");
        }
    }

    @SuppressWarnings("rawtypes")
    public List findByNamedQuery(String queryName, String nameOfTheParam,Object valueOfTheParam) {
        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, nameOfTheParam, valueOfTheParam);
    }

    @SuppressWarnings("rawtypes")
    public List findByNamedQuery(String queryName, String[] nameOfTheParam,Object[] valueOfTheParam) {
        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, nameOfTheParam, valueOfTheParam);
    }

    @SuppressWarnings("rawtypes")
    public List findByNamedQuery(String queryName) {
        return getHibernateTemplate().findByNamedQuery(queryName);
    }
    
    @SuppressWarnings("rawtypes")
	public List saveBulk(Class entity, List entityList) {
    	Transaction tx=null;
        try {
	        tx = getSessionFactory().getCurrentSession().beginTransaction();
	        for (int i = 0; i < entityList.size(); i++) {
	            AhanaVO visionvo = (AhanaVO) entityList.get(i);
	            getSessionFactory().getCurrentSession().save(visionvo);
	        }
	        tx.commit();
        }catch (Exception e) {
        	if(tx!=null){
        		tx.rollback();
        	}
        } finally {
        	
        }
        return entityList;
    }
    
    @SuppressWarnings("rawtypes")
	public List findByNamedQuery(String queryName, String[] nameOfTheParam,Object[] valueOfTheParam, Integer noOfRecords) throws Exception {
        List results = null;
        try {
        	StringBuffer queryString = new StringBuffer(getSessionFactory().getCurrentSession().getNamedQuery(queryName).getQueryString());
            Query query = getSessionFactory().getCurrentSession().createQuery(queryString.toString());
            if (nameOfTheParam != null && nameOfTheParam.length > 0) {
            	if(nameOfTheParam.length != valueOfTheParam.length){
            		throw new Exception("");
            	}
            	for (int i = 0; i < valueOfTheParam.length; i++) {
					query.setParameter(nameOfTheParam[i], valueOfTheParam[i]);
				}
            }
			if (noOfRecords != null) {
				query.setFirstResult(0).setMaxResults(noOfRecords);
			}
            results = query.list();
        } finally {
        }
        return results;
    }
    
    @SuppressWarnings("rawtypes")
	public Integer getCount(String query) {
		Query sqlQuery=null;
		Integer totalResultSize=0;
		List countList=null;
		try {
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query);
			countList=sqlQuery.list();
			if(CollectionUtils.isNotEmpty(countList)){
				BigInteger bigDecimal=(BigInteger)countList.get(0);
				totalResultSize=bigDecimal.intValue();
			}
		}finally {
			sqlQuery=null;
			countList=null;
		}
		return totalResultSize;
	}
}