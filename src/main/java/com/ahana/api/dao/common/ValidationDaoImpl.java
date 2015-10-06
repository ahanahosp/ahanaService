package com.ahana.api.dao.common;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public class ValidationDaoImpl extends AhanaDaoSupport implements ValidationDao {

	@Override
	public boolean checkUnique(String tableName, String columnName,String value, String oid) {
		Query sqlQuery=null;
		String query=null;
		try{
			query="select oid from ahana."+tableName+" where "+columnName+"='"+value+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createQuery(query.toString());
			if(CollectionUtils.isNotEmpty(sqlQuery.list())){
				return true;
			}
		}finally{
			sqlQuery=null;
			query=null;
		}
		return false;
	}
}