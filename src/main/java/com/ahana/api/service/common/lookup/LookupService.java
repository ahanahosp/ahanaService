package com.ahana.api.service.common.lookup;

import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface LookupService {

	String loadLookupByName(String... lookupName);

	String populateState(String countryId) throws AhanaBusinessException;
}
