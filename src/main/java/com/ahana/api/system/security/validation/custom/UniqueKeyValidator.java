package com.ahana.api.system.security.validation.custom;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ahana.api.dao.validation.ValidationDao;

public class UniqueKeyValidator implements ConstraintValidator<UniqueKey, Serializable> {

	@Autowired
	private ValidationDao validationDao;
	
	private UniqueKey constraintAnnotation;

	public UniqueKeyValidator() {
	}

	@Override
	public void initialize(final UniqueKey constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(final Serializable target,final ConstraintValidatorContext context) {
		if (validationDao == null) {
			return false;
		}
		String propertyName=constraintAnnotation.property();
		String propertyValue=null;
		try {
			propertyValue = BeanUtils.getProperty(target, propertyName);
		} catch (IllegalAccessException e) {
			return false;
		} catch (InvocationTargetException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		}
		boolean isAvailable=validationDao.checkUnique(target.getClass().getName(), propertyName, propertyValue, null);
		if (!isAvailable) {
			context.buildConstraintViolationWithTemplate(constraintAnnotation.message()).addPropertyNode(propertyName).addConstraintViolation().disableDefaultConstraintViolation();
			return false;
		}
		return true;
	}

}