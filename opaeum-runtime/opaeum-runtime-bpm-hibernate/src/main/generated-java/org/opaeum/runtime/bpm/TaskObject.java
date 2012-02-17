package org.opaeum.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_2tdYsI3oEeCfQedkc0TCdA")
public interface TaskObject extends CompositionNode, HibernateEntity, RequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public boolean consumeOnActivatedOccurrence(BusinessRole _activatedBy);
	
	public boolean consumeOnClaimedOccurrence(BusinessRole _claimedBy);
	
	public boolean consumeOnCompletedOccurrence(BusinessRole _completedBy);
	
	public boolean consumeOnDelegatedOccurrence(BusinessRole _delegatedBy, BusinessRole _delegatedTo);
	
	public boolean consumeOnForwardedOccurrence(BusinessRole _forwardedBy, BusinessRole _forwardedTo);
	
	public boolean consumeOnResumedOccurrence(BusinessRole _resumedBy);
	
	public boolean consumeOnRevokedOccurrence(BusinessRole _revokedBy);
	
	public boolean consumeOnSkippedOccurrence(BusinessRole _skippedBy);
	
	public boolean consumeOnStartedOccurrence(BusinessRole _startedBy);
	
	public boolean consumeOnStoppedOccurrence(BusinessRole _stoppedBy);
	
	public boolean consumeSuspendedOccurrence(BusinessRole _suspendedBy);
	
	public void generateOnActivatedEvent(BusinessRole _activatedBy);
	
	public void generateOnClaimedEvent(BusinessRole _claimedBy);
	
	public void generateOnCompletedEvent(BusinessRole _completedBy);
	
	public void generateOnDelegatedEvent(BusinessRole _delegatedBy, BusinessRole _delegatedTo);
	
	public void generateOnForwardedEvent(BusinessRole _forwardedBy, BusinessRole _forwardedTo);
	
	public void generateOnResumedEvent(BusinessRole _resumedBy);
	
	public void generateOnRevokedEvent(BusinessRole _revokedBy);
	
	public void generateOnSkippedEvent(BusinessRole _skippedBy);
	
	public void generateOnStartedEvent(BusinessRole _startedBy);
	
	public void generateOnStoppedEvent(BusinessRole _stoppedBy);
	
	public void generateSuspendedEvent(BusinessRole _suspendedBy);
	
	@NumlMetaInfo(uuid="252060@_I3guUY3pEeCfQedkc0TCdA")
	public TaskRequest getTaskRequest();
	
	public String getUid();
	
	@NumlMetaInfo(uuid="252060@_XbPZkK0OEeCK48ywUpk_rg")
	public void onActivated(BusinessRole _activatedBy);
	
	@NumlMetaInfo(uuid="252060@_qTa18K0NEeCK48ywUpk_rg")
	public void onClaimed(BusinessRole _claimedBy);
	
	@NumlMetaInfo(uuid="252060@_a_82cK0OEeCK48ywUpk_rg")
	public void onCompleted(BusinessRole _completedBy);
	
	@NumlMetaInfo(uuid="252060@_EE4B0K0OEeCK48ywUpk_rg")
	public void onDelegated(BusinessRole _delegatedBy, BusinessRole _delegatedTo);
	
	@NumlMetaInfo(uuid="252060@_NdLN8K0OEeCK48ywUpk_rg")
	public void onForwarded(BusinessRole _forwardedBy, BusinessRole _forwardedTo);
	
	@NumlMetaInfo(uuid="252060@_8ba9IK0NEeCK48ywUpk_rg")
	public void onResumed(BusinessRole _resumedBy);
	
	@NumlMetaInfo(uuid="252060@__imwgK0NEeCK48ywUpk_rg")
	public void onRevoked(BusinessRole _revokedBy);
	
	@NumlMetaInfo(uuid="252060@_fdkRQK0OEeCK48ywUpk_rg")
	public void onSkipped(BusinessRole _skippedBy);
	
	@NumlMetaInfo(uuid="252060@_iBCwEK0NEeCK48ywUpk_rg")
	public void onStarted(BusinessRole _startedBy);
	
	@NumlMetaInfo(uuid="252060@_zwcxEK0NEeCK48ywUpk_rg")
	public void onStopped(BusinessRole _stoppedBy);
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setTaskRequest(TaskRequest _taskRequest);
	
	@NumlMetaInfo(uuid="252060@_ug7_QK0NEeCK48ywUpk_rg")
	public void suspended(BusinessRole _suspendedBy);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToTaskRequest(TaskRequest val);
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val);

}