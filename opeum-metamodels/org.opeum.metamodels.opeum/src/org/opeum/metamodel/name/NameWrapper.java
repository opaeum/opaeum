package org.opeum.metamodel.name;

import java.io.Serializable;

/**
 * @author Ampie Barnard
 */
public abstract class NameWrapper implements Serializable{
	private static final long serialVersionUID = 7637257851112739431L;
	public abstract NameWrapper getSeparateWords();
	public abstract NameWrapper getUnderscored();
	public abstract NameWrapper getDecapped();
	public abstract NameWrapper getCapped();
	public abstract NameWrapper getPlural();
	public abstract NameWrapper getSingular();
	public abstract NameWrapper getCamelCase();
	public abstract NameWrapper getUpperCase();
	public abstract NameWrapper getLowerCase();
	public abstract NameWrapper getWithoutId();
	public int compareTo(Object o){
		NameWrapper name2 = (NameWrapper) o;
		return toString().compareTo(name2.toString());
	}
	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}else if(this == obj){
			return true;
		}else if(toString() != null && obj.toString() != null){
			return toString().equals(obj.toString());
		}else{
			return false;
		}
	}
	@Override
	public int hashCode(){
		return toString().hashCode();
	}
	public String getAsIs(){
		return toString();
	}
}
