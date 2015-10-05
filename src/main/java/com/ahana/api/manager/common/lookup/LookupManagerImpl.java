package com.ahana.api.manager.common.lookup;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahana.api.dao.common.lookup.LookupDao;
import com.ahana.api.domain.common.NameValue;
import com.ahana.api.domain.common.AhanaLookupVO;

@Transactional(propagation = Propagation.REQUIRED)
public class LookupManagerImpl implements LookupManager {

    private LookupDao lookupDao;

	public LookupDao getLookupDao() {
		return lookupDao;
	}

	public void setLookupDao(LookupDao lookupDao) {
		this.lookupDao = lookupDao;
	}

    public final Map<String, Object> getAllLookups() {
        Map<String, Object> lookupMap = null;
        lookupMap = lookupDao.getAllLookups();
        return lookupMap;
    }
    
    @Override
    public final List<NameValue> getLookupsByCategory(final String lookupCategory) {
    	List<NameValue> lookupsList = lookupDao.getLookupsByCategory(lookupCategory);
        return lookupsList;
    }
    
    @Override
    public final AhanaLookupVO getLookupsByCode(final String code) {
    	AhanaLookupVO ahanaLookupVO = lookupDao.getLookupsByCode(code);
        return ahanaLookupVO;
    }

	@Override
	public String getDescriptionByCode(String code) {
		return lookupDao.getDescriptionByCode(code);
	}
}