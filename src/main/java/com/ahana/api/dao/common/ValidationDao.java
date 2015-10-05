package com.ahana.api.dao.common;

public interface ValidationDao {

	boolean checkUnique(String tableName,String columnName,String value,String oid);
}
