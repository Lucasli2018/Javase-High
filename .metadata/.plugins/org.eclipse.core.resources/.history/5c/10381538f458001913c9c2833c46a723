package com.feiyangedu.sample;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericHelper {

	public static Class<?> getParameterizedTypeOfSuperclass(Class<?> clazz) {
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			Type[] types = pt.getActualTypeArguments();
			// 假定父类只有一个泛型类型:
			return (Class<?>) types[0];
		}
		return null;
	}
}
