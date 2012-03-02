package org.opaeum.runtime.bpm.requestobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.Property;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_2tdYsI3oEeCfQedkc0TCdA")
public interface ITaskObject extends HibernateEntity, CompositionNode, IRequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public boolean consumeOnActivatedOccurrence(Participant activatedBy);
	
	public boolean consumeOnClaimedOccurrence(Participant claimedBy);
	
	public boolean consumeOnCompletedOccurrence(Participant completedBy);
	
	public boolean consumeOnDelegatedOccurrence(Participant delegatedBy, Participant delegatedTo);
	
	public boolean consumeOnForwardedOccurrence(Participant forwardedBy, Participant forwardedTo);
	
	public boolean consumeOnResumedOccurrence(Participant resumedBy);
	
	public boolean consumeOnRevokedOccurrence(Participant revokedBy);
	
	public boolean consumeOnSkippedOccurrence(Participant skippedBy);
	
	public boolean consumeOnStartedOccurrence(Participant startedBy);
	
	public boolean consumeOnStoppedOccurrence(Participant stoppedBy);
	
	public boolean consumeOnSuspendedOccurrence(Participant suspendedBy);
	
	public void generateOnActivatedEvent(Participant activatedBy);
	
	public void generateOnClaimedEvent(Participant claimedBy);
	
	public void generateOnCompletedEvent(Participant completedBy);
	
	public void generateOnDelegatedEvent(Participant delegatedBy, Participant delegatedTo);
	
	public void generateOnForwardedEvent(Participant forwardedBy, Participant forwardedTo);
	
	public void generateOnResumedEvent(Participant resumedBy);
	
	public void generateOnRevokedEvent(Participant revokedBy);
	
	public void generateOnSkippedEvent(Participant skippedBy);
	
	public void generateOnStartedEvent(Participant startedBy);
	
	public void generateOnStoppedEvent(Participant stoppedBy);
	
	public void generateOnSuspendedEvent(Participant suspendedBy);
	
	@Property(isComposite=true,opposite="taskObject")
	@NumlMetaInfo(uuid="252060@_I3guUY3pEeCfQedkc0TCdA")
	public TaskRequest getTaskRequest();
	
	public String getUid();
	
	@NumlMetaInfo(uuid="252060@_XbPZkK0OEeCK48ywUpk_rg")
	public void onActivated(Participant activatedBy);
	
	@NumlMetaInfo(uuid="252060@_qTa18K0NEeCK48ywUpk_rg")
	public void onClaimed(Participant claimedBy);
	
	@NumlMetaInfo(uuid="252060@_a_82cK0OEeCK48ywUpk_rg")
	public void onCompleted(Participant completedBy);
	
	@NumlMetaInfo(uuid="252060@_EE4B0K0OEeCK48ywUpk_rg")
	public void onDelegated(Participant delegatedBy, Participant delegatedTo);
	
	@NumlMetaInfo(uuid="252060@_NdLN8K0OEeCK48ywUpk_rg")
	public void onForwarded(Participant forwardedBy, Participant forwardedTo);
	
	@NumlMetaInfo(uuid="252060@_8ba9IK0NEeCK48ywUpk_rg")
	public void onResumed(Participant resumedBy);
	
	@NumlMetaInfo(uuid="252060@__imwgK0NEeCK48ywUpk_rg")
	public void onRevoked(Participant revokedBy);
	
	@NumlMetaInfo(uuid="252060@_fdkRQK0OEeCK48ywUpk_rg")
	public void onSkipped(Participant skippedBy);
	
	@NumlMetaInfo(uuid="252060@_iBCwEK0NEeCK48ywUpk_rg")
	public void onStarted(Participant startedBy);
	
	@NumlMetaInfo(uuid="252060@_zwcxEK0NEeCK48ywUpk_rg")
	public void onStopped(Participant stoppedBy);
	
	@NumlMetaInfo(uuid="252060@_ug7_QK0NEeCK48ywUpk_rg")
	public void onSuspended(Participant suspendedBy);
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setTaskRequest(TaskRequest taskRequest);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToTaskRequest(TaskRequest val);
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val);

}