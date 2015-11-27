package com.ahana.api.dao.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.commons.system.dao.common.AhanaDaoSupport;
import com.ahana.commons.system.domain.common.AhanaVO;
import com.ahana.commons.system.security.util.Constants;

@Transactional(readOnly = false)
public class ConfigurationDaoImpl extends AhanaDaoSupport implements ConfigurationDao {

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createOrUpdateConfigData(AhanaVO ahanaVO) {
		saveOrUpdate(ahanaVO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteConfigs(String entiryName, String oid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("update '"+entiryName+"' set status=INACT where oid ='"+oid+"'");
		q.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public AhanaVO getConfigDetailsItemByOid(String queryName, String columnName, String oid) {
		List<AhanaVO> configDetails = findByNamedQuery(queryName, columnName, oid);
		if(CollectionUtils.isNotEmpty(configDetails)){
			return configDetails.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllAlertType() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select a.oid as oid,a.alert_name as alertName,a.status as status from alert_type a where a.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("alertName")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	public List<Map<String, String>> getAllActiveRoomAndBedType() {
		return null;
	}

	@Override
	public void createOrUpdateMultipleConfig(List<AhanaVO> ahanaVOs) {
		saveOrUpdateAll(ahanaVOs);
	}
	
}