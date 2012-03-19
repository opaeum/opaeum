package org.opaeum.runtime.environment;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;

import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;

public class JavaTypedElement{
	long opaeumId;
	String uuid;
	String name;
	boolean isComposite;
	JavaTypedElement opposite;
	SimpleTypeRuntimeStrategyFactory strategyFactory;
	String shortDescription;
	//TODO extract method and move to new class
	public JavaTypedElement(PropertyDescriptor descriptor){
		super();
		buildJavaTypedElement(descriptor);
	}
	public JavaTypedElement(Annotation[] parameterAnnotations){
		for(Annotation annotations:parameterAnnotations){
			if(annotations instanceof ParameterMetaInfo){
				ParameterMetaInfo annotation=(ParameterMetaInfo) annotations;
				this.opaeumId=annotation.opaeumId();
				this.uuid=annotation.uuid();
				this.name=annotation.name();
				this.opposite=null;//???
				try{
					this.strategyFactory=annotation.strategyFactory().newInstance();
				}catch(InstantiationException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(IllegalAccessException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.shortDescription=annotation.shortDescription();
			}
		} 
		
	}
	protected void buildJavaTypedElement(PropertyDescriptor descriptor){
		Class<PropertyMetaInfo> annotationClass = PropertyMetaInfo.class;
		PropertyMetaInfo annotation = descriptor.getReadMethod().getAnnotation(annotationClass);
		this.opaeumId=annotation.opaeumId();
		this.uuid=annotation.uuid();
		this.name=descriptor.getName();
		this.opposite=null;//???
		try{
			this.strategyFactory=annotation.strategyFactory().newInstance();
		}catch(InstantiationException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IllegalAccessException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.shortDescription=annotation.shortDescription();
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
}
