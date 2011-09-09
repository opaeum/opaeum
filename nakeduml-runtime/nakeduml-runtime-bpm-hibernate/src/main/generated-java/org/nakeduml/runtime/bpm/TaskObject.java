package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.task_object",uuid="OpiumBPM.library.uml@_2tdYsI3oEeCfQedkc0TCdA")
public interface TaskObject extends HibernateEntity, CompositionNode, RequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
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
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.task_request_id",uuid="OpiumBPM.library.uml@_I3guUY3pEeCfQedkc0TCdA")
	public TaskRequest getTaskRequest();
	
	public String getUid();
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_activated",uuid="OpiumBPM.library.uml@_XbPZkK0OEeCK48ywUpk_rg")
	public void onActivated(BusinessRole activatedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_claimed",uuid="OpiumBPM.library.uml@_qTa18K0NEeCK48ywUpk_rg")
	public void onClaimed(BusinessRole claimedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_completed",uuid="OpiumBPM.library.uml@_a_82cK0OEeCK48ywUpk_rg")
	public void onCompleted(BusinessRole completedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_delegated",uuid="OpiumBPM.library.uml@_EE4B0K0OEeCK48ywUpk_rg")
	public void onDelegated(BusinessRole delegatedBy, BusinessRole delegatedTo);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_forwarded",uuid="OpiumBPM.library.uml@_NdLN8K0OEeCK48ywUpk_rg")
	public void onForwarded(BusinessRole forwardedBy, BusinessRole forwardedTo);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_resumed",uuid="OpiumBPM.library.uml@_8ba9IK0NEeCK48ywUpk_rg")
	public void onResumed(BusinessRole resumedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_revoked",uuid="OpiumBPM.library.uml@__imwgK0NEeCK48ywUpk_rg")
	public void onRevoked(BusinessRole revokedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_skipped",uuid="OpiumBPM.library.uml@_fdkRQK0OEeCK48ywUpk_rg")
	public void onSkipped(BusinessRole skippedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_started",uuid="OpiumBPM.library.uml@_iBCwEK0NEeCK48ywUpk_rg")
	public void onStarted(BusinessRole startedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_stopped",uuid="OpiumBPM.library.uml@_zwcxEK0NEeCK48ywUpk_rg")
	public void onStopped(BusinessRole stoppedBy);
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public void setTaskRequest(TaskRequest taskRequest);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.suspended",uuid="OpiumBPM.library.uml@_ug7_QK0NEeCK48ywUpk_rg")
	public void suspended(BusinessRole suspendedBy);
	
	public String toString();
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToTaskRequest(TaskRequest val);
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val);

}