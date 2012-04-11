package org.opaeum.runtime.environment;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class JavaTypedElement{
	long opaeumId;
	String uuid;
	String name;
	boolean isComposite;
	JavaTypedElement opposite;
	SimpleTypeRuntimeStrategyFactory strategyFactory;
	String shortDescription;
	Class<?> baseType;
	Class<?> type;
	private Class<?> declaringClass;
	private Method readMethod;
	private String lookupMethod;
	private Method writeMethod;
	// TODO extract method and move to new class
	public JavaTypedElement(Method getter){
		super();
		buildTypedElementOnGetter(getter);
		declaringClass = getter.getDeclaringClass();
		this.readMethod = getter;
	}
	protected void buildJavaTypedElement(PropertyDescriptor descriptor){
		Method readMethod = descriptor.getReadMethod();
		this.name = descriptor.getName();
		buildTypedElementOnGetter(readMethod);
	}
	public void buildTypedElementOnGetter(Method readMethod){
		this.readMethod = readMethod;
		String setterName = readMethod.getName();
		if(readMethod.getName().startsWith("is")){
			name = NameConverter.decapitalize(readMethod.getName().substring(2));
		}else{
			name = NameConverter.decapitalize(readMethod.getName().substring(3));
		}
		setterName = "set" + NameConverter.capitalize(name);
		try{
			this.writeMethod = readMethod.getDeclaringClass().getMethod(setterName, readMethod.getReturnType());
		}catch(SecurityException e1){
		}catch(NoSuchMethodException e1){
		}
		Class<PropertyMetaInfo> annotationClass = PropertyMetaInfo.class;
		PropertyMetaInfo annotation = readMethod.getAnnotation(annotationClass);
		if(annotation != null){
			this.isComposite = annotation.isComposite();
			this.opaeumId = annotation.opaeumId();
			this.uuid = annotation.uuid();
			this.opposite = null;// ???
			this.lookupMethod = annotation.lookupMethod();
			try{
				this.strategyFactory = annotation.strategyFactory().newInstance();
			}catch(InstantiationException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IllegalAccessException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.shortDescription = annotation.shortDescription();
		}
		this.type = readMethod.getReturnType();
		Type genericReturnType = readMethod.getGenericReturnType();
		calcBaseType(genericReturnType);
	}
	private void calcBaseType(Type genericReturnType){
		if(Collection.class.isAssignableFrom(type)){
			if(genericReturnType instanceof ParameterizedType){
				ParameterizedType type = (ParameterizedType) genericReturnType;
				Type argType = type.getActualTypeArguments()[0];
				if(argType instanceof Class){
					baseType = (Class<?>) argType;
				}else{
					if(argType instanceof java.lang.reflect.WildcardType){
						java.lang.reflect.WildcardType wct = (java.lang.reflect.WildcardType) argType;
						Type[] lowerBounds = wct.getLowerBounds();
						Type[] upperBounds = wct.getUpperBounds();
						if(lowerBounds.length == 1){
							baseType = (Class<?>) lowerBounds[0];
						}else if(upperBounds.length == 1){
							baseType = (Class<?>) upperBounds[0];
						}
					}
				}
			}
		}
		if(baseType == null){
			baseType = type;
		}
	}
	public long getOpaeumId(){
		return opaeumId;
	}
	public String getUuid(){
		return uuid;
	}
	public String getName(){
		return name;
	}
	public boolean isComposite(){
		return isComposite;
	}
	public JavaTypedElement getOpposite(){
		return opposite;
	}
	public SimpleTypeRuntimeStrategyFactory getStrategyFactory(){
		return strategyFactory;
	}
	public String getShortDescription(){
		return shortDescription;
	}
	public Class<?> getBaseType(){
		return baseType;
	}
	public Class<?> getDeclaringClass(){
		return declaringClass;
	}
	public boolean isReadOnly(){
		return writeMethod == null;
	}
	public Object invokeGetter(Object target){
		if(readMethod == null){
			return null;
		}else{
			try{
				return readMethod.invoke(target);
			}catch(InvocationTargetException e){
				throw new RuntimeException(e.getTargetException());
			}catch(Exception e){
				return null;
			}
		}
	}
	public Object invokeLookupMethod(IPersistentObject target){
		try{
			if(lookupMethod == null||lookupMethod.length()==0){
				Method method = IntrospectionUtil.getOriginalClass(target).getMethod(readMethod.getName());
				PropertyMetaInfo annotation = method.getAnnotation(PropertyMetaInfo.class);
				if(annotation != null && annotation.lookupMethod().length() > 0){
					return target.getClass().getMethod(annotation.lookupMethod()).invoke(target);
				}
				return null;
			}else{
				Method lookupMethod = target.getClass().getMethod(this.lookupMethod);
				return lookupMethod.invoke(target);
			}
		}catch(SecurityException e1){
		}catch(NoSuchMethodException e1){
		}catch(InvocationTargetException e){
			throw new RuntimeException(e.getTargetException());
		}catch(Exception e){
		}
		return null;
	}
	public boolean isMany(){
		return Collection.class.isAssignableFrom(readMethod.getReturnType());
	}
	public Class<?> getType(){
		return readMethod.getReturnType();
	}
	public void invokeSetter(Object target,Object value){
		try{
			writeMethod.invoke(target, value);
		}catch(IllegalArgumentException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}catch(InvocationTargetException e){
			throw new RuntimeException(e.getTargetException());
		}
		
	}
}
