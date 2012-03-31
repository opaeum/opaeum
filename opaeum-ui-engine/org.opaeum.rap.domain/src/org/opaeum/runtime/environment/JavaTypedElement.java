package org.opaeum.runtime.environment;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.jboss.logging.Param;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.name.NameConverter;

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
	// TODO extract method and move to new class
	public JavaTypedElement(Method getter){
		super();
		buildTypedElementOnGetter(getter);
	}
	public JavaTypedElement(Type genericType,Class<?> type,Annotation[] parameterAnnotations){
		for(int i = 0;i < parameterAnnotations.length;i++){
			Annotation ann = parameterAnnotations[i];
			if(ann instanceof ParameterMetaInfo){
				ParameterMetaInfo annotation = (ParameterMetaInfo) ann;
				this.opaeumId = annotation.opaeumId();
				this.uuid = annotation.uuid();
				this.name = annotation.name();
				this.opposite = null;// ???
				try{
					Class<? extends SimpleTypeRuntimeStrategyFactory> strategyFactoryClass = annotation.strategyFactory();
					if(strategyFactoryClass == SimpleTypeRuntimeStrategyFactory.class){
						if(type == Integer.class){
						}
					}
					this.strategyFactory = strategyFactoryClass.newInstance();
				}catch(InstantiationException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(IllegalAccessException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.type = type;
				calcBaseType(genericType);
				this.shortDescription = annotation.shortDescription();
			}
		}
	}
	protected void buildJavaTypedElement(PropertyDescriptor descriptor){
		Method readMethod = descriptor.getReadMethod();
		this.name = descriptor.getName();
		buildTypedElementOnGetter(readMethod);
	}
	public void buildTypedElementOnGetter(Method readMethod){
		Class<PropertyMetaInfo> annotationClass = PropertyMetaInfo.class;
		PropertyMetaInfo annotation = readMethod.getAnnotation(annotationClass);
		if(annotation != null){
			this.opaeumId = annotation.opaeumId();
			this.uuid = annotation.uuid();
			this.opposite = null;// ???
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
		if(name == null){
			name = NameConverter.decapitalize(readMethod.getName().substring(3));
		}
		this.type = readMethod.getReturnType();
		Type genericReturnType = readMethod.getGenericReturnType();
		calcBaseType(genericReturnType);
	}
	private void calcBaseType(Type genericReturnType){
		if(Collection.class.isAssignableFrom(type)){
			if(genericReturnType instanceof ParameterizedType){
				ParameterizedType type = (ParameterizedType) genericReturnType;
				baseType = (Class<?>) type.getActualTypeArguments()[0];
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
}
