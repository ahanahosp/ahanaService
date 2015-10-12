package com.ahana.api.dao.validation;

public interface ValidationDao {

	boolean checkUnique(String tableName,String columnName,String value,String oid);
}
