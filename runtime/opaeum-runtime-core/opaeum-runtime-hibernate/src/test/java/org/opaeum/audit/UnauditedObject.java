package org.opaeum.audit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.opaeum.runtime.domain.IPersistentObject;

@Entity
public class UnauditedObject implements IPersistentObject{
	private static final long serialVersionUID = 2695909610108474592L;
	@Id
	@GeneratedValue
	Long id;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	@Override
	public String getName(){
		return "Kosie";
	}
	@Override
	public int getObjectVersion(){
		return 0;
	}
	@Override
	public String getUid(){
		// TODO Auto-generated method stub
		return null;
	}
}
