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
		try{
			Query q = getSessionFactory().getCurrentSession().createQuery("update '"+entiryName+"' set status='INACT' where oid ='"+oid+"'");
			q.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
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
			query="select a.oid as oid,a.alert_name as alertName,a.status as status from alert_type a";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllAlliedCharges() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select a.oid as oid,a.allied_charges as alliedCharges,a.status as status from allied_charges a";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("alliedCharges")
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
	public List<Map<String, String>> getAllChargesForCategory() {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllPatientCategory() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select pa.oid as oid,pa.category_name as categoryName,pa.colour_picker as colourPicker,pa.activation_date as activationDate,pa.status as status from patient_category pa";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("categoryName")
					.addScalar("colourPicker")
					.addScalar("activationDate")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}	
}