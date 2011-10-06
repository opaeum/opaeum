package org.opeum.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.opeum.annotation.NumlMetaInfo;
import org.opeum.runtime.domain.CompositionNode;
import org.opeum.runtime.domain.HibernateEntity;
import org.opeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_2tdYsI3oEeCfQedkc0TCdA")
public interface TaskObject extends CompositionNode, HibernateEntity, RequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public boolean consumeOnActivatedOccurrence(BusinessRole activatedBy);
	
	public boolean consumeOnClaimedOccurrence(BusinessRole claimedBy);
	
	public boolean consumeOnCompletedOccurrence(BusinessRole completedBy);
	
	public boolean consumeOnDelegatedOccurrence(BusinessRole delegatedBy, BusinessRole delegatedTo);
	
	public boolean consumeOnForwardedOccurrence(BusinessRole forwardedBy, BusinessRole forwardedTo);
	
	public boolean consumeOnResumedOccurrence(BusinessRole resumedBy);
	
	public boolean consumeOnRevokedOccurrence(BusinessRole revokedBy);
	
	public boolean consumeOnSkippedOccurrence(BusinessRole skippedBy);
	
	public boolean consumeOnStartedOccurrence(BusinessRole startedBy);
	
	public boolean consumeOnStoppedOccurrence(BusinessRole stoppedBy);
	
	public boolean consumeSuspendedOccurrence(BusinessRole suspendedBy);
	
	public void generateOnActivatedEvent(BusinessRole activatedBy);
	
	public void generateOnClaimedEvent(BusinessRole claimedBy);
	
	public void generateOnCompletedEvent(BusinessRole completedBy);
	
	public void generateOnDelegatedEvent(BusinessRole delegatedBy, BusinessRole delegatedTo);
	
	public void generateOnForwardedEvent(BusinessRole forwardedBy, BusinessRole forwardedTo);
	
	public void generateOnResumedEvent(BusinessRole resumedBy);
	
	public void generateOnRevokedEvent(BusinessRole revokedBy);
	
	public void generateOnSkippedEvent(BusinessRole skippedBy);
	
	public void generateOnStartedEvent(BusinessRole startedBy);
	
	public void generateOnStoppedEvent(BusinessRole stoppedBy);
	
	public void generateSuspendedEvent(BusinessRole suspendedBy);
	
	@NumlMetaInfo(uuid="252060@_I3guUY3pEeCfQedkc0TCdA")
	public TaskRequest getTaskRequest();
	
	public String getUid();
	
	@NumlMetaInfo(uuid="252060@_XbPZkK0OEeCK48ywUpk_rg")
	public void onActivated(BusinessRole activatedBy);
	
	@NumlMetaInfo(uuid="252060@_qTa18K0NEeCK48ywUpk_rg")
	public void onClaimed(BusinessRole claimedBy);
	
	@NumlMetaInfo(uuid="252060@_a_82cK0OEeCK48ywUpk_rg")
	public void onCompleted(BusinessRole completedBy);
	
	@NumlMetaInfo(uuid="252060@_EE4B0K0OEeCK48ywUpk_rg")
	public void onDelegated(BusinessRole delegatedBy, BusinessRole delegatedTo);
	
	@NumlMetaInfo(uuid="252060@_NdLN8K0OEeCK48ywUpk_rg")
	public void onForwarded(BusinessRole forwardedBy, BusinessRole forwardedTo);
	
	@NumlMetaInfo(uuid="252060@_8ba9IK0NEeCK48ywUpk_rg")
	public void onResumed(BusinessRole resumedBy);
	
	@NumlMetaInfo(uuid="252060@__imwgK0NEeCK48ywUpk_rg")
	public void onRevoked(BusinessRole revokedBy);
	
	@NumlMetaInfo(uuid="252060@_fdkRQK0OEeCK48ywUpk_rg")
	public void onSkipped(BusinessRole skippedBy);
	
	@NumlMetaInfo(uuid="252060@_iBCwEK0NEeCK48ywUpk_rg")
	public void onStarted(BusinessRole startedBy);
	
	@NumlMetaInfo(uuid="252060@_zwcxEK0NEeCK48ywUpk_rg")
	public void onStopped(BusinessRole stoppedBy);
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setTaskRequest(TaskRequest taskRequest);
	
	@NumlMetaInfo(uuid="252060@_ug7_QK0NEeCK48ywUpk_rg")
	public void suspended(BusinessRole suspendedBy);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToTaskRequest(TaskRequest val);
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val);

}