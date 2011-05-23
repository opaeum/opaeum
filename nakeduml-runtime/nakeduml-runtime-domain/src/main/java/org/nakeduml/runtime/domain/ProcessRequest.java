package org.nakeduml.runtime.domain;

import javax.persistence.Column;

import org.hibernate.annotations.Any;

public class ProcessRequest extends AbstractRequest{
	@Any(metaDef="ProcessMetaDef",metaColumn=@Column(name="process_type"))
	AbstractProcess process;

	public AbstractProcess getProcess(){
		return process;
	}

	public void setProcess(AbstractProcess process){
		this.process = process;
	}
}
