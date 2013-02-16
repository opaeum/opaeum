package org.opaeum.hibernate.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.opaeum.runtime.domain.IEnum;

@MappedSuperclass()
public abstract class AbstractPersistentOpaeumIdEnum implements Serializable{
	private static final long serialVersionUID = 15223932925574511L;
	@Id
	Long id;
	@Basic
	String name;
	protected AbstractPersistentOpaeumIdEnum(){
		
	}
	protected AbstractPersistentOpaeumIdEnum(Enum<?> e){
		name=e.name();
		id=((IEnum)e).getOpaeumId();
	}
	
}
