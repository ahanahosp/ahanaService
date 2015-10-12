package com.ahana.api.dao.lookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.common.Constants;
import com.ahana.api.dao.common.AhanaDaoSupport;
import com.ahana.api.domain.common.AhanaLookupVO;
import com.ahana.api.domain.common.NameValue;

@Transactional(readOnly = true)
public class LookupDaoImpl extends AhanaDaoSupport implements LookupDao {

    private static Logger logger = Logger.getLogger(LookupDaoImpl.class);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final Map<String, Object> getAllLookups() {
    	if (logger.isDebugEnabled()) {
    		 logger.debug("getAllLookups started:::");
        }
        List lookups = findByNamedQuery(AhanaLookupVO.GET_ALL_LOOKUPS);
        Map<String, Object> lookUpMapByCategory    = new HashMap<String, Object>();
        Map<String, AhanaLookupVO> lookUpByCode = new HashMap<String, AhanaLookupVO>();
        for (int j = 0; j < lookups.size(); j++) {
        	AhanaLookupVO lookupVo = (AhanaLookupVO) lookups.get(j);
            if (logger.isDebugEnabled()) {
                logger.debug("description:::" + lookupVo.getDescription());
                logger.debug("category:::" + lookupVo.getCategory());
                logger.debug("code:::" + lookupVo.getCode());
            }
            NameValue nameValue = new NameValue();
            nameValue.setLabel(lookupVo.getDescription());
            nameValue.setValue(lookupVo.getCode());
            lookUpByCode.put(lookupVo.getCode(), lookupVo);
            if (lookupVo.getCategory() != null && lookUpMapByCategory != null && lookUpMapByCategory.get(lookupVo.getCategory()) == null) {
                List<NameValue> lookupList = new ArrayList<NameValue>();
                lookupList.add(nameValue);
                lookUpMapByCategory.put(lookupVo.getCategory(), lookupList);
                continue;
            }
            if (lookUpMapByCategory != null && lookUpMapByCategory.get(lookupVo.getCategory()) != null && ((List) lookUpMapByCategory.get(lookupVo.getCategory())).size() > 0) {
                List<NameValue> lookupList = (List<NameValue>) lookUpMapByCategory.get(lookupVo.getCategory());
                lookupList.add(nameValue);
                lookUpMapByCategory.put(lookupVo.getCategory(), lookupList);
            }
        }
        lookUpMapByCategory.put(Constants.LOOKUP_BY_CODE, lookUpByCode);
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
        return lookUpMapByCategory;
    }

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<NameValue> getLookupsByCategory(String lookupCategory) {
		Query sqlQuery=null;
		StringBuilder query=new StringBuilder();
		try{
			query.append("select distinct l.code as value,l.description as label from ahana.lookup l where l.category='"+lookupCategory+"' ");
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query.toString())
					.addScalar("value")
					.addScalar("label")
					.setResultTransformer(Transformers.aliasToBean(NameValue.class));
			return sqlQuery.list();
		}finally{
			sqlQuery=null;
			query=null;
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public AhanaLookupVO getLookupsByCode(String code) {
		List<AhanaLookupVO> lookups = findByNamedQuery("getLookupByCode","code",code);
		if(CollectionUtils.isNotEmpty(lookups)){
			return (AhanaLookupVO)lookups.get(0);
		}
		return null;
	}

	@Override
	public String getDescriptionByCode(String code) {
		Query sqlQuery=null;
		StringBuilder query=new StringBuilder();
		try{
			query.append("select l.description from ahana.lookup l where l.code='"+code+"' ");
			sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(query.toString());
			if(CollectionUtils.isNotEmpty(sqlQuery.list())){
				return (String) sqlQuery.list().get(0);
			}
		}finally{
			sqlQuery=null;
			query=null;
		}
		return null;
	}
}