package org.nakeduml.event;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.drools.runtime.process.ProcessContext;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
import org.nakeduml.runtime.domain.IPersistentObject;

@Entity
@Table(name = "numl_change_event")
public class ChangeEvent extends AbstractNakedUmlEvent{
	@Basic
	@Column(name = "evaluation_method_uuid")
	private String evaluationMethodUuid;
	@Transient
	private boolean isTrue;
	public ChangeEvent(IPersistentObject process,String callBackMethodUuid,String evaluationMethodUuid, ProcessContext ctx){
		super(process,callBackMethodUuid,ctx);
		this.evaluationMethodUuid = evaluationMethodUuid;
	}
	public ChangeEvent(IPersistentObject process,String callBackMethodUuid,boolean cancel,ProcessContext ctx){
		super(process,callBackMethodUuid,cancel,ctx);
	}
	public String getEvaluationMethodUuid(){
		return evaluationMethodUuid;
	}
	public void setEvaluationMethodName(String EvaluationMethodName){
		this.evaluationMethodUuid = EvaluationMethodName;
	}
	public void evaluateConditionOn(IPersistentObject context){
		try{
			isTrue=(Boolean) getMethodByUuid(evaluationMethodUuid).invoke(context);
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			throw ea.wrapRootCauseIfNecessary();
		}
	}
	public boolean isTrue(){
		return isTrue;
	}
	public void invokeCallback(IPersistentObject context){
		try{
			getMethodByUuid(getCallbackMethodUuid()).invoke(context, getNodeInstanceId());
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			throw ea.wrapRootCauseIfNecessary();
		}
	}

}
