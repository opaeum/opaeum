package net.sf.nakeduml.linkage;

import net.sf.nakeduml.metamodel.core.INakedProperty;

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
		return isMany(p) && isOne(p.getOtherEnd());
	}

	public static boolean isOneToMany(INakedProperty p){
		return isOne(p) && isMany(p.getOtherEnd());
	}
}
