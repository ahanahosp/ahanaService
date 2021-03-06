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
import com.ahana.commons.system.security.util.CommonUtils;
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
			query="select rbt.oid as oid,rbt.order_no as orderId,rbt.bed_no as bedNo,rbt.status as status from room_bed_type rbt";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("orderId")
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllChargesForCategory(String name) {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select ca.oid as oid,ca.category as category,ca.subCategory as subCategory,ca.subCategoryOid as subCategoryOid,"
					+ "ca.op as op,ca.ip as ip from charges_category_view ca where ca.category='"+name+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("category")
					.addScalar("subCategory")
					.addScalar("subCategoryOid")
					.addScalar("op")
					.addScalar("ip")
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
	public List<Map<String, Object>> getAllPatientCategory() {
		Query sqlQuery=null;
		List<Map<String, Object>> list=null;
		String query=null;
		try{
			query="select pa.oid as oid,pa.category_name as categoryName,pa.activation_date as activationDate,pa.status as status from patient_category pa";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("categoryName")
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createRoomAndBedType(RoomAndBedType roomAndBedType) {
		saveOrUpdate(roomAndBedType);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteBedVsRoomType(String roomAndBedTypeOid) {
		Query q = getSessionFactory().getCurrentSession().createQuery("delete BedVsRoomType where roomAndBedTypeOid ='"+roomAndBedTypeOid+"'");
		q.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
			query="select rt.oid as roomTypeOid,rt.room_name as roomName from bed_vs_rooms br left join room_type rt on br.room_type_oid = rt.oid "
					+ "where br.room_and_bed_type_oid='"+roomAndBedTypeOid+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("roomTypeOid")
					.addScalar("roomName")
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
	public List<Map<String, String>> getDoctorDetails() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select oid as value,fullName as label,speciality as speciality"
					+ " from user_view where careProvider='Yes' and userStatus='"+Constants.ACT+"';";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.addScalar("speciality")
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
	public List<String> getSavedRoomTypeByOid(String roomAndBedTypeOid) {
		Query sqlQuery=null;
		List<String> list=null;
		String query=null;
		try{
			query="select br.room_type_oid from bed_vs_rooms br where br.room_and_bed_type_oid='"+roomAndBedTypeOid+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<Map<String, String>> getAllDoctorDetails() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select oid as oid,doctorOid as doctorOid,fullName as fullName,speciality as speciality,visitingDay as visitingDay,"
					+ "startTime as startTime,endTime as endTime,status as status from doctor_schedule_view";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("doctorOid")
					.addScalar("fullName")
					.addScalar("speciality")
					.addScalar("visitingDay")
					.addScalar("startTime")
					.addScalar("endTime")
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
	public List<Map<String, String>> getAllConfigRoomCharges() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select oid as oid,discount as discount,start_time as startTime,end_time as endTime,format as format,status as status from config_room_charges";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("discount")
					.addScalar("startTime")
					.addScalar("endTime")
					.addScalar("format")
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteMultipleObject(String source, String oids) {
		String oidList=CommonUtils.convertCommoaSeprated(oids);
		Query q = getSessionFactory().getCurrentSession().createQuery("update "+source+" set status='INACT' where oid in("+oidList+")");
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllActiveProfessional() {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		String query=null;
		try{
			query="select oid as value,fullName as label,speciality as speciality"
					+ " from user_view where careProvider='Yes' and userStatus='"+Constants.ACT+"';";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("value")
					.addScalar("label")
					.addScalar("speciality")
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
	public List<String> getCategoryNameForChargesForCategory() {
		Query sqlQuery=null;
		List<String> list=null;
		String query=null;
		try{
			query="select distinct category from charges_for_category order by category";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getChargesForCategoryByOidAndType(String oid, String type) {
		Query sqlQuery=null;
		List<Map<String, Object>> list=null;
		String query=null;
		try{
			query="select oid as oid,category as category,sub_category_oid as subCategoryoid,"+type+" as amount"
					+ " from charges_for_category where oid='"+oid+"'";
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query)
					.addScalar("oid")
					.addScalar("category")
					.addScalar("subCategoryoid")
					.addScalar("amount")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
			if(CollectionUtils.isNotEmpty(list)){
				return list.get(0);
			}
		}finally{
			sqlQuery=null;
			query=null;
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateChangesForCategory(String oid, String filedName, String fieldValue) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("update charges_for_category set "+filedName+"="+fieldValue+" where oid='"+oid+"'");
		q.executeUpdate();
	}
}