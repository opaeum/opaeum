package org.opaeum.hibernate.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.AccessType;
import org.opaeum.runtime.domain.IEnum;

@MappedSuperclass()
@AccessType("field")
public abstract class AbstractPersistentEnum implements Serializable{
	private static final long serialVersionUID = 1522393292557455211L;
	@Id
	String id;
	@Basic
	String name;
	protected AbstractPersistentEnum(){
		
	}
	protected AbstractPersistentEnum(Enum<?> e){
		name=e.name();
		id=((IEnum)e).getUuid();
	}
	
}
