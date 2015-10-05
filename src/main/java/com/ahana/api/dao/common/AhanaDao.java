package com.ahana.api.dao.common;

import java.util.Collection;
import java.util.List;

import com.ahana.api.common.AhanaVO;

public interface AhanaDao {

	@SuppressWarnings("rawtypes")
	AhanaVO get(Class entity, String oid);

	AhanaVO save(Object entityObject);

	AhanaVO saveOrUpdate(Object entityObject);

	@SuppressWarnings("rawtypes")
	List find(String HQLQuery);

	@SuppressWarnings("rawtypes")
	List find(String HQLQuery, String nameOfTheParam, Object valueOfTheParam) throws Exception;

	@SuppressWarnings("rawtypes")
	List find(String HQLQuery, String[] nameOfTheParams, Object[] valueOfTheParams) throws Exception;

	@SuppressWarnings("rawtypes")
	List findByNamedQuery(String queryName);

	@SuppressWarnings("rawtypes")
	List findByNamedQuery(String queryName, String[] nameOfTheParam,Object[] valueOfTheParam);

	@SuppressWarnings("rawtypes")
	List saveBulk(Class entity, List entityList);

	@SuppressWarnings("rawtypes")
	Collection saveOrUpdateAll(Collection entities);

	void bulkUpdate(String queryString, Object value);

	@SuppressWarnings("rawtypes")
	List findByNamedQuery(String queryName, String[] nameOfTheParam,Object[] valueOfTheParam, Integer noOfRecords) throws Exception;

	@SuppressWarnings("rawtypes")
	List findByNamedQuery(String queryName, String nameOfTheParam,Object valueOfTheParam);

}
