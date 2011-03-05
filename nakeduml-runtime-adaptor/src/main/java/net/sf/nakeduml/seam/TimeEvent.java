package net.sf.nakeduml.seam;

import java.io.Serializable;


import org.nakeduml.runtime.adaptor.IntrospectionUtil;
import org.nakeduml.runtime.domain.AbstractEntity;

public class TimeEvent implements Serializable {
	private static final long serialVersionUID = 1859791139038182369L;
	private Long processId;
	private Class<? extends AbstractEntity> processClass;
	private String callBackMethodName;

	public TimeEvent(AbstractEntity process, String callBackMethodName) {
		super();
		// TODO this id may only be available after transaction commit!!!!!!!!
		this.setProcessId(process.getId());
		this.setProcessClass((Class<? extends AbstractEntity>) IntrospectionUtil.getOriginalClass(process.getClass()));
		this.setCallBackMethodName(callBackMethodName);
	}

	public int hashCode() {
		return getProcessClass().hashCode();
	}

	public boolean equals(Object other) {
		if (other instanceof TimeEvent) {
			TimeEvent te = (TimeEvent) other;
			return te.getProcessClass().equals(getProcessClass()) && te.getProcessId().equals(getProcessId())
					&& getCallBackMethodName().equals(te.getCallBackMethodName());
		} else {
			return false;
		}
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessClass(Class<? extends AbstractEntity> processClass) {
		this.processClass = processClass;
	}

	public Class<? extends AbstractEntity> getProcessClass() {
		return processClass;
	}

	public void setCallBackMethodName(String callBackMethodName) {
		this.callBackMethodName = callBackMethodName;
	}

	public String getCallBackMethodName() {
		return callBackMethodName;
	}
}