package org.opaeum.audit;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.opaeum.runtime.domain.IEnum;

@MappedSuperclass()
@Access(AccessType.FIELD)
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
