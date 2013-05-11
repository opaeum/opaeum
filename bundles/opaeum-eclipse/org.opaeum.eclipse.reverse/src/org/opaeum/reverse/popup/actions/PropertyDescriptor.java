package org.opaeum.reverse.popup.actions;

import java.beans.Introspector;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.IMemberValuePairBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class PropertyDescriptor{
	String name;
	IMethodBinding getter;
	boolean isReadOnly = true;
	private IVariableBinding field;
	private Boolean isBidirectional;
	private PropertyDescriptor otherEnd;
	private String mappedBy;
	public PropertyDescriptor(IMethodBinding getter){
		this.getter = getter;
		if(getter.isAnnotationMember()){
			this.name = getter.getName();
		}else{
			this.name = propertyName(getter);
		}
	}
	public boolean isComposite(){
		if(getter != null && getter.isAnnotationMember()){
			return true;
		}else{
			String attributeName = "cascade";
			Class<Object[]> attributeType = Object[].class;
			Object[] cascade = findAnnotationAttributeValue(attributeName, attributeType);
			if(cascade != null && cascade.length == 1 && ((IVariableBinding) cascade[0]).getName().equals("ALL")){
				return true;
			}
			return false;
		}
	}
	private <T>T findAnnotationAttributeValue(String attributeName,Class<T> attributeType){
		T cascade = null;
		for(IAnnotationBinding ab:field.getAnnotations()){
			for(IMemberValuePairBinding mvp:ab.getAllMemberValuePairs()){
				if(mvp.getName().equals(attributeName)){
					if(attributeType.isInstance(mvp.getValue())){
						cascade = (T) mvp.getValue();
					}else{
						System.out.println(mvp.getValue());
					}
				}
			}
		}
		return cascade;
	}
	public PropertyDescriptor getOtherEnd(){
		if(isBidirectional == null){
			String mappedBy = getMappedBy();
			if(mappedBy.isEmpty()){
				for(PropertyDescriptor pd:getPropertyDescriptors(getBaseType())){// mmm memory??
					if(pd.getMappedBy().equals(getName()) && pd.getBaseType().equals(getBaseType())){
						this.otherEnd = pd;
						isBidirectional = Boolean.TRUE;
					}
				}
			}else{
				isBidirectional = Boolean.TRUE;
				this.otherEnd = getPropertyDescriptor(getBaseType(), mappedBy);
			}
			if(isBidirectional == null){
				isBidirectional = Boolean.FALSE;
			}
		}
		return this.otherEnd;
	}
	private PropertyDescriptor getPropertyDescriptor(ITypeBinding baseType,String mappedBy2){
		Collection<PropertyDescriptor> r = getPropertyDescriptors(baseType, null);
		return r.isEmpty() ? null : r.iterator().next();
	}
	private String getMappedBy(){
		if(mappedBy == null){
			mappedBy = findAnnotationAttributeValue("mappedBy", String.class);
			if(mappedBy == null){
				mappedBy = ""; // lookup only once
			}
		}
		return mappedBy;
	}
	public static String propertyName(IMethodBinding getter){
		return getter.isAnnotationMember()?getter.getName(): Introspector.decapitalize(getter.getName().substring(3));
	}
	public String getName(){
		return name;
	}
	public IVariableBinding getField(){
		return field;
	}
	public ITypeBinding getType(){
		return getter.getReturnType();
	}
	public ITypeBinding getBaseType(){
		ITypeBinding type = getType();
		if(isMany()){
			if(type.getTypeArguments().length == 1){
				return type.getTypeArguments()[0];
			}else if(type.isArray()){
				return type.getComponentType();
			}else{
				return type;
			}
		}else{
			return type;
		}
	}
	public boolean isMany(){
		if(getType().isArray()){
			return true;
		}else if(getType().isPrimitive()){
			return false;
		}else{
			try{
				Class<?> classOf = classOf(getType());
				return Collection.class.isAssignableFrom(classOf);
			}catch(Exception e){
				return false;
			}
		}
	}
	private static final Class<?> classOf(ITypeBinding b){
		return IntrospectionUtil.classForName(b.getQualifiedName());
	}
	public static Collection<PropertyDescriptor> getPropertyDescriptors(ITypeBinding type){
		return getPropertyDescriptors(type, null);
	}
	public static Collection<PropertyDescriptor> getPropertyDescriptors(ITypeBinding type,String name){
		IMethodBinding[] methods = type.getDeclaredMethods();
		Map<String,PropertyDescriptor> results = new HashMap<String,PropertyDescriptor>();
		for(IMethodBinding getter:methods){
			boolean match = name == null || propertyName(getter).equalsIgnoreCase(name);
			if(isGetter(getter) && match){
				PropertyDescriptor pd = new PropertyDescriptor(getter);
				results.put(pd.getName().toLowerCase(), pd);
				if(getter.isAnnotationMember()){
					pd.isReadOnly = false;
				}else{
					for(IMethodBinding setter:methods){
						if(isSetterFor(setter, pd.getter)){
							pd.isReadOnly = false;
						}
					}
				}
			}
		}
		IVariableBinding[] declaredFields = type.getDeclaredFields();
		for(IVariableBinding vb:declaredFields){
			PropertyDescriptor pd = results.get(vb.getName().toLowerCase());
			if(pd != null){
				pd.field = vb;
			}
		}
		return results.values();
	}
	protected static boolean isGetter(IMethodBinding method){
		return method.isAnnotationMember() ||  ( Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("get") && !method.getReturnType().getName().equals("void") && method.getParameterTypes().length == 0);
	}
	protected static boolean isSetterFor(IMethodBinding setter,IMethodBinding getter){
		boolean nameOK = setter.getName().startsWith("set") && setter.getName().substring(3).equals(getter.getName().substring(3));
		boolean typesMatch = setter.getParameterTypes().length == 1 && setter.getParameterTypes()[0].equals(getter.getReturnType());
		return nameOK && setter.getReturnType().getName().equals("void") && typesMatch;
	}
}
