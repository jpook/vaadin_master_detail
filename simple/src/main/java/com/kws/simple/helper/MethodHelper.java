package com.kws.simple.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.vaadin.ui.AbstractField;

public class MethodHelper {

	public static String getAnnotatedProperty(Class<?> clazz,
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

	public static String getPropertyName(String getterOrSetter) {
		if (getterOrSetter != null && getterOrSetter.length() > 3)
			return getterOrSetter.substring(3, 4).toLowerCase()
					+ getterOrSetter.substring(4);
		else
			return null;
	}

	public static boolean getterForPropertyExists(Class<?> clazz,
			String property) {
		Method[] methods = clazz.getMethods();
		if (methods == null || methods.length == 0)
			return false;
		String getter = getterForProperty(property);
		for (Method m : methods) {
			if (m.getName().equals(getter))
				return true;
		}
		return false;
	}

	public static String getterForProperty(String property) {
		if (property == null || property.length() == 0)
			return null;
		return "get" + property.substring(0, 1).toUpperCase()
				+ property.substring(1);
	}

	@SuppressWarnings("unchecked")
	public static AbstractField<?> getInstance(String typeName) {
		try {
			Constructor<? extends AbstractField<?>> constructor = (Constructor<? extends AbstractField<?>>) Class
					.forName(getFullyQualifiedName(typeName)).getConstructor();
			return (AbstractField<?>) constructor.newInstance((Object[]) null);
		} catch (NoSuchMethodException | SecurityException
				| ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getFullyQualifiedName(String typeName) {
		return ConfigStrings.UI_PACKAGE+typeName;
	}
}
