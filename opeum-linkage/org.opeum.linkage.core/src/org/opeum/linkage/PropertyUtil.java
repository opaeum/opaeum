package org.opeum.linkage;

import org.opeum.metamodel.core.INakedProperty;

public class PropertyUtil{
	public static boolean isOneToOne(INakedProperty property){
		return isOne(property) && isOne(property.getOtherEnd());
	}

	private static boolean isOne(INakedProperty otherEnd){
		return otherEnd!=null&& otherEnd.getNakedMultiplicity().isOne() && otherEnd.getQualifierNames().length==0;
	}

	public static boolean isMany(INakedProperty p){
		return p.getNakedMultiplicity().isMany() || p.getQualifierNames().length>0;
	}

	public static boolean isManyToMany(INakedProperty p){
		return isMany(p) && isMany(p);
	}

	public static boolean isManyToOne(INakedProperty p){
		return isMany(p.getOtherEnd()) && isOne(p);
	}

	public static boolean isOneToMany(INakedProperty p){
		return isOne(p.getOtherEnd()) && isMany(p);
	}
}
