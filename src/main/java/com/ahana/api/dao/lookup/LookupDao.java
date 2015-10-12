package com.ahana.api.dao.lookup;

import java.util.List;
import java.util.Map;

import com.ahana.api.domain.common.NameValue;
import com.ahana.api.domain.common.AhanaLookupVO;

public interface LookupDao {

    Map<String, Object> getAllLookups();

	List<NameValue> getLookupsByCategory(String lookupCategory);

	AhanaLookupVO getLookupsByCode(String code);

	String getDescriptionByCode(String code);

}
