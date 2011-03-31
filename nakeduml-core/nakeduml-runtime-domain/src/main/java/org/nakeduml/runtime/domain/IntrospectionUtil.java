package org.nakeduml.runtime.domain;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

public class IntrospectionUtil{
	public static MethodDescriptor getMethod(String name,Class<?> c){
		try{
			MethodDescriptor[] methods = Introspector.getBeanInfo(c, Object.class).getMethodDescriptors();
			for(int i = 0;i < methods.length;i++){
				MethodDescriptor descriptor = methods[i];
				if(descriptor.getName().equals(name)){
					return descriptor;
				}
			}
			return null;
		}catch(IntrospectionException e){
			throw new RuntimeException(e);
		}
	}
	public static PropertyDescriptor getProperty(String name,Class<?> c){
		try{
			PropertyDescriptor[] properties = Introspector.getBeanInfo(c).getPropertyDescriptors();
			for(int i = 0;i < properties.length;i++){
				PropertyDescriptor descriptor = properties[i];
				if(descriptor.getName().equals(name)){
					return descriptor;
				}
			}
			return null;
		}catch(IntrospectionException e){
			throw new RuntimeException(e);
		}
	}
	public static PropertyDescriptor[] getProperties(Class<?> c){
		try{
			PropertyDescriptor[] properties = Introspector.getBeanInfo(c).getPropertyDescriptors();
			return properties;
		}catch(IntrospectionException e){
			throw new RuntimeException(e);
		}
	}
	public static <T>  Class<T> getOriginalClass(T target){
		Class<T> c = (Class<T>) target.getClass();
		return (Class<T>) getOriginalClass(c);
	}
	public static <T>Class<? extends T> getOriginalClass(Class<? extends T> c){
		while(c.getName().indexOf("$$") > -1 || c.isSynthetic()){
			c = (Class<? extends T>) c.getSuperclass();
		}
		return c;
	}
	public static Object get(PropertyDescriptor property,Object target){
		try{
			return property.getReadMethod().invoke(target, new Object[0]);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw throwTarget(e);
		}
	}
	private static RuntimeException throwTarget(InvocationTargetException e){
		if(e.getTargetException() instanceof RuntimeException){
			throw (RuntimeException) e.getTargetException();
		}else if(e.getTargetException() instanceof Error){
			throw (Error) e.getTargetException();
		}else{
			throw new RuntimeException(e.getTargetException());
		}
	}
	public static void set(PropertyDescriptor property,Object target,Object value){
		try{
			if(property.getWriteMethod() != null){
				property.getWriteMethod().invoke(target, new Object[]{value});
			}else{
				throw new RuntimeException("Property '" + property.getName() + "' is readOnly");
			}
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw throwTarget(e);
		}
	}
	public static Class<?> getStateClass(Class<?> entityClass){
		try{
			String stateClassName = entityClass.getPackage().getName() + "." + entityClass.getSimpleName().toLowerCase() + "states";
			stateClassName += "." + entityClass.getSimpleName() + "State";
			Class<?> stateClass = Thread.currentThread().getContextClassLoader().loadClass(stateClassName);
			return stateClass;
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	public static AbstractEnum[] getAllLiterals(Class<? extends AbstractEnum> enumClass){
		try{
			return (AbstractEnum[]) enumClass.getMethod("values", new Class[0]).invoke(null, new Object[0]);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public static Class<?> getClass(String entityToCreate){
		try{
			return Thread.currentThread().getContextClassLoader().loadClass(entityToCreate);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	public static Class<?>[] getDependencyArray(Class<?> c,String...packages){
		Set<Class<?>> result = getDependencies(c, packages);
		return (Class<?>[]) result.toArray(new Class<?>[result.size()]);
	}
	public static Set<Class<?>> getDependencies(Class<?> c,String...packages){
		Set<Class<?>> result = new HashSet<Class<?>>();
		addDependencies(c, result, packages);
		return result;
	}
	private static void addDependencies(Class<?> c,Set<Class<?>> result,String...packages){
		if(!(c == null || result.contains(c))){
			result.add(c);
			addAnnotationTypes(result, c.getAnnotations(), packages);
			Field[] declaredFields = c.getDeclaredFields();
			for(Field field:declaredFields){
				addAnnotationTypes(result, field.getAnnotations(), packages);
				deriveDependenciesFromGenericType(result, field.getGenericType(), packages);
			}
			Method[] declaredMethods = c.getDeclaredMethods();
			for(Method method:declaredMethods){
				addAnnotationTypes(result, method.getAnnotations(), packages);
				addDependenciesFromParameters(result, method.getGenericParameterTypes(), method.getParameterAnnotations(), packages);
				deriveDependenciesFromGenericType(result, method.getGenericReturnType(), packages);
			}
			Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
			for(Constructor<?> method:declaredConstructors){
				addAnnotationTypes(result, method.getAnnotations(), packages);
				addDependenciesFromParameters(result, method.getGenericParameterTypes(), method.getParameterAnnotations(), packages);
			}
			if(c.getSuperclass() != null){
				deriveDependenciesFromGenericType(result, c.getSuperclass(), packages);
			}
			Class<?>[] interfaces = c.getInterfaces();
			for(Class<?> intfce:interfaces){
				deriveDependenciesFromGenericType(result, intfce, packages);
			}
		}
	}
	private static void addDependenciesFromParameters(Set<Class<?>> result,Type[] parameterTypes,Annotation[][] parameterAnnotations,String...packages){
		for(int i=0; i<parameterTypes.length;i++){
			Type paramType=parameterTypes[i];
			addAnnotationTypes(result, parameterAnnotations[i], packages);
			deriveDependenciesFromGenericType(result, paramType);
		}
	}
	private static void addAnnotationTypes(Set<Class<?>> result,Annotation[] annotations,String...packages){
		for(Annotation annotation:annotations){
			deriveDependenciesFromGenericType(result,annotation.annotationType(), packages);
		}
	}
	private static void deriveDependenciesFromGenericType(Set<Class<?>> result,Type elementType,String...packages){
		for(String string:packages){
			if(elementType instanceof Class){
				Class class1 = (Class) elementType;
				if(class1.isArray()){
					deriveDependenciesFromGenericType(result, class1.getComponentType(), packages);
				}else if(class1.getName().contains(string)){
					addDependencies(class1, result, packages);
				}
			}else if(elementType instanceof ParameterizedType){
				ParameterizedType pt = (ParameterizedType) elementType;
				Type[] typeParameters = pt.getActualTypeArguments();
				for(Type type:typeParameters){
					if(type instanceof Class<?> && ((Class) type).getName().contains(string)){
						addDependencies((Class<?>) type, result, packages);
					}
				}
			}
		}
	}
	public static List<Class<?>> getClasses(Package p){
		return getClasses(p.getName());
	}
	public static List<Class<?>> getClasses(String packageName){
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			assert classLoader != null;
			String path = packageName.replace('.', '/');
			Enumeration<URL> resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<File>();
			while(resources.hasMoreElements()){
				URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
			for(File directory:dirs){
				classes.addAll(findClasses(directory, packageName));
			}
			return classes;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	private static List<Class<?>> findClasses(File directory,String packageName) throws ClassNotFoundException{
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if(!directory.exists()){
			return classes;
		}
		File[] files = directory.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			}else if(file.getName().endsWith(".class")){
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
}
