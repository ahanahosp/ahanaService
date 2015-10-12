package com.ahana.api.service.lookup;

import java.util.Map;

import com.ahana.api.system.security.exception.AhanaBusinessException;

public interface LookupService {

	Map<String, Object> loadLookupByName(String... lookupName);

	Map<String, Object> populateState(String countryId) throws AhanaBusinessException;
}
