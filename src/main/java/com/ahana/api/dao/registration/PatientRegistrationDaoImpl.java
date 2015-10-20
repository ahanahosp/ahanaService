package com.ahana.api.dao.registration;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.common.AhanaDaoSupport;
import com.ahana.api.domain.common.PatientRegistration;

@Transactional(readOnly = false)
public class PatientRegistrationDaoImpl extends AhanaDaoSupport implements PatientRegistrationDao{

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public PatientRegistration savePatient(PatientRegistration patientRegistration) {
		saveOrUpdate(patientRegistration);
		return patientRegistration;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> searchPatientByName(String name) {
		Query sqlQuery=null;
		List<Map<String, String>> list=null;
		StringBuilder query=new StringBuilder();
		try{
			query.append("select oid as oid,first_name as firstName,last_name as lastName,user_id as userName ");
			query.append("from patient where first_name like '%"+name+"%'");
			query.append(" or last_name like '%"+name+"%' order by first_name asc");
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query.toString())
					.addScalar("oid")
					.addScalar("firstName")
					.addScalar("lastName")
					.addScalar("userName")
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
		return list;
	}
}