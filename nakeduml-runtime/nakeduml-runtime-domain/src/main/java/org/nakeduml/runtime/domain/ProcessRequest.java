package org.nakeduml.runtime.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Any;
@Entity
@Table(name="numl_process_request")
public class ProcessRequest extends AbstractRequest{
	public static final String PROCESS_META_DEF = "ProcessMetaDef";
	@Any(metaDef=PROCESS_META_DEF,metaColumn=@Column(name="process_type"))
	@JoinColumn(name="process_id")
	AbstractProcess process;

	public AbstractProcess getProcess(){
		return process;
	}

	public void setProcess(AbstractProcess process){
		this.process = process;
	}
}
