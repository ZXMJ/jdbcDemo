package com.ultra.jdbc.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtil {

	public static Class<?> getGenericSuperClass(Class<?> clazz) {
		return getGenericSuperClass(clazz, 0);
	}

	public static Class<?> getGenericSuperClass(Class<?> clazz, int index) {
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			Type[] types = pt.getActualTypeArguments();
			return (Class<?>) types[index];
		}
		return null;
	}
}
