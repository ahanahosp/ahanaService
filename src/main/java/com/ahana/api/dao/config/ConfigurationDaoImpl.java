package com.ahana.api.dao.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.domain.common.RoomAndBedType;
import com.ahana.api.domain.config.BedVsRoomType;
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
		Query q = getSessionFactory().getCurrentSession().createQuery("update "+entiryName+" set status='INACT' where oid ='"+oid+"'");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getAllActiveRoomAndBedType() {
		Query sqlQuery=null;
		List<Map<String, Object>> list=null;
		String query=null;
		try{
			query="select rbt.oid as oid,rbt.order_no as orderNo,rbt.bed_no as bedNo,rbt.status as status from room_bed_type rbt";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("orderNo")
					.addScalar("bedNo")
					.addScalar("status")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createOrUpdateMultipleConfig(List<?> ahanaVOs) {
		for(AhanaVO obj:(List<AhanaVO>)ahanaVOs){
			saveOrUpdate(obj);
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllActiveAlliedCharges() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select a.oid as value,a.allied_charges as label from allied_charges a where status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllActiveProcedures() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select p.oid as value,p.procedures_name as label from procedures p where p.status='"+Constants.ACT+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@Override
	public void createRoomAndBedType(RoomAndBedType roomAndBedType) {
		saveOrUpdate(roomAndBedType);
	}

	@Override
	public void deleteBedVsRoomType(String roomAndBedTypeOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("delete BedVsRoomType where roomAndBedTypeOid ='"+roomAndBedTypeOid+"'");
		q.executeUpdate();
	}

	@Override
	public void createBedVsRoomType(BedVsRoomType bedVsRoomType) {
		saveOrUpdate(bedVsRoomType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> enhanceRoomType(String roomAndBedTypeOid) {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select rt.oid as roomTypeOid,rt.room_name as roomName,br.room_type_oid as savedRoomTypeOid"
					+ " from bed_vs_rooms br left join room_type rt on br.room_type_oid = rt.oid where br.room_and_bed_type_oid='"+roomAndBedTypeOid+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("roomTypeOid")
					.addScalar("roomName")
					.addScalar("savedRoomTypeOid")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}	
}