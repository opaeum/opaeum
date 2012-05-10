package org.opaeum.audit;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.opaeum.runtime.domain.IEnum;

@MappedSuperclass()
public class AbstractPersistentEnum implements Serializable{
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
