package org.opaeum.javageneration.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;

public class ReflectionUtil {
	static boolean runtimeAvailable = true;

	public static OJPathName getUtilInterface(Class<?> clazz) {
		if (runtimeAvailable) {
			return new OJPathName(clazz.getName());
		} else {
			return UtilityCreator.getUtilPathName().append(clazz.getSimpleName() + "Interface");
		}
	}

	public static void addOperationsFromJava(Class<?> collectionInterface, OJAnnotatedClass setWithModel, String delegate,
			Map<Class<?>, OJPathName> mappedTypes) {
		mappedTypes = mappedTypes == null ? new HashMap<Class<?>, OJPathName>() : mappedTypes;
		Method[] methods = delegate == null ? collectionInterface.getDeclaredMethods() : collectionInterface.getMethods();
		for (Method m : methods) {
			OJAnnotatedOperation oper = new OJAnnotatedOperation(m.getName());
			TypeVariable<Method>[] typeParameters = m.getTypeParameters();
			for (TypeVariable<Method> tv : typeParameters) {
				oper.setGenericTypeParam(ReflectionUtil.getTypePath(tv, mappedTypes));
			}
			StringBuilder sb = new StringBuilder();
			sb.append(delegate).append(".").append(m.getName()).append("(");
			Type[] parameterTypes = m.getGenericParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				Type t = parameterTypes[i];
				oper.addParam("parm" + i, ReflectionUtil.getTypePath(t, mappedTypes));
				sb.append("parm" + i);
				if (i < parameterTypes.length - 1) {
					sb.append(",");
				}
			}
			sb.append(")");
			if (m.getReturnType() == void.class) {
				oper.getBody().addToStatements(sb.toString());
			} else {
				OJPathName typePath = ReflectionUtil.getTypePath(m.getGenericReturnType(), mappedTypes);
				oper.setReturnType(typePath);
				if (delegate != null) {
					oper.getBody().addToStatements("return " + sb.toString());
				}
			}
			setWithModel.addToOperations(oper);
		}
	}

	static OJPathName getTypePath(Type type, Map<Class<?>, OJPathName> mappedTypes) {
		String name = null;
		if (type instanceof TypeVariable) {
			name = "T";// ((TypeVariable) type).getName();
		} else if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			OJPathName path = getTypePath(pt.getRawType(), mappedTypes);
			for (Type p : pt.getActualTypeArguments()) {
				path.addToElementTypes(getTypePath(p, mappedTypes));
			}
			return path;
		} else if (type instanceof GenericArrayType) {
			GenericArrayType gat = (GenericArrayType) type;
			OJPathName path = getTypePath(gat.getGenericComponentType(), mappedTypes);
			OJPathName newPath = path.getHead();
			newPath.addToNames(path.getLast() + "[]");
			return newPath;
		} else if (type instanceof Class) {
			Class<?> clazz = (Class<?>) type;
			if (clazz.isArray()) {
				clazz = clazz.getComponentType();
				if (mappedTypes.containsKey(clazz)) {
					return mappedTypes.get(clazz);
				}
				name = clazz.getName() + "[]";
			} else {
				if (mappedTypes.containsKey(clazz)) {
					return mappedTypes.get(clazz);
				}
				name = clazz.getName();
			}
		} else if (type instanceof WildcardType) {
			WildcardType wct = (WildcardType) type;
			if (wct.getLowerBounds().length == 1) {
				OJPathName p = getTypePath(wct.getLowerBounds()[0], mappedTypes);
				return new OJPathName("? super " + p.getLast());
			}
			if (wct.getUpperBounds().length == 1) {
				OJPathName p = getTypePath(wct.getUpperBounds()[0], mappedTypes);
				return new OJPathName("? extends " + p.getLast());
			}
		} else {
			name = "CouldNotDetermine OJPathName for " + type.getClass();
		}
		return new OJPathName(name);
	}

	public static OJAnnotatedInterface duplicateInterface(Class<?> clazz) {
		Map<Class<?>, OJPathName> mappedTypes = new HashMap<Class<?>, OJPathName>();
		return duplicateInterface(clazz, mappedTypes);
	}

	public static OJAnnotatedInterface duplicateInterface(Class<?> clazz, Map<Class<?>, OJPathName> mappedTypes) {
		mappedTypes.put(clazz, getUtilInterface(clazz));
		OJAnnotatedInterface ojinterface = new OJAnnotatedInterface(clazz.getSimpleName() + "Interface");
		UtilityCreator.getUtilPack().addToClasses(ojinterface);
		addOperationsFromJava(clazz, ojinterface, null, mappedTypes);
		for (Class<?> intf : clazz.getInterfaces()) {
			if (intf.getName().startsWith("org.opaeum")) {
				OJAnnotatedInterface superInterface = duplicateInterface(intf);
				UtilityCreator.getUtilPack().addToClasses(superInterface);
				ojinterface.addToSuperInterfaces(superInterface.getPathName());
				ojinterface.addToImports(superInterface.getPathName());
			} else {
				OJPathName typePath = getTypePath(intf, mappedTypes);
				ojinterface.addToSuperInterfaces(typePath);
				ojinterface.addToImports(typePath);
			}
		}
		if (JavaTransformationPhase.IS_RUNTIME_AVAILABLE) {
			// DOES NOT WORK!!!! duplicate methods with different return types
			ojinterface.addToImplementedInterfaces(new OJPathName(clazz.getName()));
		}
		return ojinterface;
	}
}
