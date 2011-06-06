package org.nakeduml.event;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.drools.runtime.process.ProcessContext;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

@Entity
@Table(name = "numl_change_event")
public class ChangeEvent extends AbstractNakedUmlEvent{
	@Basic
	@Column(name = "evaluation_method_name")
	private String evaluationMethodName;
	@Transient
	private boolean isTrue;
	public ChangeEvent(IPersistentObject process,String callBackMethodName,String evaluationMethodName, ProcessContext ctx){
		super(process,callBackMethodName,ctx);
		this.evaluationMethodName = evaluationMethodName;
	}
	public ChangeEvent(IPersistentObject process,String callBackMethodName,boolean cancel){
		super(process,callBackMethodName,cancel);
	}
	public String getEvaluationMethodName(){
		return evaluationMethodName;
	}
	public void setEvaluationMethodName(String EvaluationMethodName){
		this.evaluationMethodName = EvaluationMethodName;
	}
	public void evaluateConditionOn(IPersistentObject context){
		try{
			isTrue=(Boolean) getMethodByPersistentName(evaluationMethodName).invoke(context);
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
			getMethodByPersistentName(getCallbackMethodName(),String.class).invoke(context, getNodeInstanceId());
		}catch(Exception e){
			ExceptionAnalyser ea = new ExceptionAnalyser(e);
			throw ea.wrapRootCauseIfNecessary();
		}
	}

}
