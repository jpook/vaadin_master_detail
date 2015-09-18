package com.kws.simple.cross.helper;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

@Singleton
public class MethodHelper implements Serializable {

	private static final long serialVersionUID = -7717644690431255704L;

	@Inject
	Logger log;

	public String getAnnotatedProperty(Class<?> clazz,
			Class<? extends Annotation> annotationClass, boolean mandatory) {
		if (clazz == null || annotationClass == null)
			return null;
		Method[] methods = clazz.getMethods();
		Method result = null;
		if (methods != null && methods.length > 0) {
			for (Method m : methods) {
				if (m.isAnnotationPresent(annotationClass)) {
					result = m;
					break;
				}
			}
		}
		if (result != null) {
			return getPropertyName(result.getName());
		}
		if (mandatory)
			throw new NullPointerException(
					MessageHelper.EXCEPTION_ANNOTATION_NOT_FOUND);
		return null;
	}

	/*
	 * returns name of the property for a given getter or setter name
	 */
	public String getPropertyName(String getterOrSetter) {
		if (getterOrSetter != null && getterOrSetter.length() > 3)
			return getterOrSetter.substring(3, 4).toLowerCase()
					+ getterOrSetter.substring(4);
		else
			return null;
	}

	/*
	 * returns the getter on a class for a given property name
	 */
	public Method getterForProperty(Class<?> clazz, String property) {
		String getter = getterNameForProperty(property);
		return getMethodByName(clazz, getter, 0);
	}

	public Method setterForProperty(Class<?> clazz, String property) {
		String getter = setterNameForProperty(property);
		return getMethodByName(clazz, getter, 1);
	}

	private Method getMethodByName(Class<?> clazz, String methodName, int argNo) {
		Method[] methods = clazz.getMethods();
		if (methods == null || methods.length == 0)
			return null;
		for (Method m : methods) {
			if (m.getName().equals(methodName)
					&& m.getParameterCount() == argNo)
				return m;
		}
		return null;
	}

	public String getterNameForProperty(String property) {
		if (property == null || property.length() == 0)
			return null;
		return "get" + camelCase(property);

	}

	public String setterNameForProperty(String property) {
		if (property == null || property.length() == 0)
			return null;
		return "set" + camelCase(property);
	}

	public String camelCase(String property) {
		return property.substring(0, 1).toUpperCase() + property.substring(1);
	}

	public void setPropertyForName(Object object, String property,
			Object... args) {
		if (object == null) {
			return;
		}
		int argNo = args != null ? args.length : 0;

		Method setter = getMethodByName(object.getClass(),
				setterNameForProperty(property), argNo);
		if (setter == null) {
			return;
		}
		try {
			setter.invoke(object, args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			log.info("setPropertyForName :" + e.getMessage());
		}
	}

	public Object getPropertyForName(Object object, String property) {

		if (object == null || property == null)
			return null;
		Method getter = getMethodByName(object.getClass(),
				getterNameForProperty(property), 0);
		if (getter == null)
			return null;
		Object result = null;
		try {
			result = getter.invoke(object, (Object[]) null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			log.info("getPropertyForName :" + e.getMessage());
		}
		return result;
	}
}
