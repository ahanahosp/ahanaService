/**
 *
 */
package com.ahana.api.system.mongo;

import java.util.UUID;

public class MongoUtils {

	public static String generateMongoID() {
		return UUID.randomUUID().toString();
	}

}
