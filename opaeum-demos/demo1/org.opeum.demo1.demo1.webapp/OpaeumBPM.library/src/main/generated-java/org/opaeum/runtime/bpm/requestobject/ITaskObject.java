package org.opaeum.runtime.bpm.requestobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
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
	
	public boolean consumeOnActivatedOccurrence(@ParameterMetaInfo(opaeumId=3572050351186706778,uuid="252060@_YslssK0OEeCK48ywUpk_rg") Participant activatedBy);
	
	public boolean consumeOnClaimedOccurrence(@ParameterMetaInfo(opaeumId=7951140015384934948,uuid="252060@_roekYK0NEeCK48ywUpk_rg") Participant claimedBy);
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(opaeumId=2231883736722554878,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") Participant completedBy);
	
	public boolean consumeOnDelegatedOccurrence(@ParameterMetaInfo(opaeumId=2937187766882494462,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") Participant delegatedBy, @ParameterMetaInfo(opaeumId=9102602523982706512,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") Participant delegatedTo);
	
	public boolean consumeOnForwardedOccurrence(@ParameterMetaInfo(opaeumId=5405405098671388300,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") Participant forwardedBy, @ParameterMetaInfo(opaeumId=3907720224420364138,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") Participant forwardedTo);
	
	public boolean consumeOnResumedOccurrence(@ParameterMetaInfo(opaeumId=4926573096485933090,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") Participant resumedBy);
	
	public boolean consumeOnRevokedOccurrence(@ParameterMetaInfo(opaeumId=273103052661183192,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") Participant revokedBy);
	
	public boolean consumeOnSkippedOccurrence(@ParameterMetaInfo(opaeumId=6103866618185670366,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") Participant skippedBy);
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(opaeumId=2842620541558802106,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") Participant startedBy);
	
	public boolean consumeOnStoppedOccurrence(@ParameterMetaInfo(opaeumId=6681754435738713602,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") Participant stoppedBy);
	
	public boolean consumeOnSuspendedOccurrence(@ParameterMetaInfo(opaeumId=7500393394354827722,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") Participant suspendedBy);
	
	public void generateOnActivatedEvent(@ParameterMetaInfo(opaeumId=3572050351186706778,uuid="252060@_YslssK0OEeCK48ywUpk_rg") Participant activatedBy);
	
	public void generateOnClaimedEvent(@ParameterMetaInfo(opaeumId=7951140015384934948,uuid="252060@_roekYK0NEeCK48ywUpk_rg") Participant claimedBy);
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(opaeumId=2231883736722554878,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") Participant completedBy);
	
	public void generateOnDelegatedEvent(@ParameterMetaInfo(opaeumId=2937187766882494462,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") Participant delegatedBy, @ParameterMetaInfo(opaeumId=9102602523982706512,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") Participant delegatedTo);
	
	public void generateOnForwardedEvent(@ParameterMetaInfo(opaeumId=5405405098671388300,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") Participant forwardedBy, @ParameterMetaInfo(opaeumId=3907720224420364138,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") Participant forwardedTo);
	
	public void generateOnResumedEvent(@ParameterMetaInfo(opaeumId=4926573096485933090,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") Participant resumedBy);
	
	public void generateOnRevokedEvent(@ParameterMetaInfo(opaeumId=273103052661183192,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") Participant revokedBy);
	
	public void generateOnSkippedEvent(@ParameterMetaInfo(opaeumId=6103866618185670366,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") Participant skippedBy);
	
	public void generateOnStartedEvent(@ParameterMetaInfo(opaeumId=2842620541558802106,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") Participant startedBy);
	
	public void generateOnStoppedEvent(@ParameterMetaInfo(opaeumId=6681754435738713602,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") Participant stoppedBy);
	
	public void generateOnSuspendedEvent(@ParameterMetaInfo(opaeumId=7500393394354827722,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") Participant suspendedBy);
	
	@PropertyMetaInfo(isComposite=true,opaeumId=2623282203224602767,opposite="taskObject",uuid="252060@_I3guUY3pEeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_I3guUY3pEeCfQedkc0TCdA")
	public TaskRequest getTaskRequest();
	
	public String getUid();
	
	@NumlMetaInfo(uuid="252060@_XbPZkK0OEeCK48ywUpk_rg")
	public void onActivated(@ParameterMetaInfo(opaeumId=3572050351186706778,uuid="252060@_YslssK0OEeCK48ywUpk_rg") Participant activatedBy);
	
	@NumlMetaInfo(uuid="252060@_qTa18K0NEeCK48ywUpk_rg")
	public void onClaimed(@ParameterMetaInfo(opaeumId=7951140015384934948,uuid="252060@_roekYK0NEeCK48ywUpk_rg") Participant claimedBy);
	
	@NumlMetaInfo(uuid="252060@_a_82cK0OEeCK48ywUpk_rg")
	public void onCompleted(@ParameterMetaInfo(opaeumId=2231883736722554878,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") Participant completedBy);
	
	@NumlMetaInfo(uuid="252060@_EE4B0K0OEeCK48ywUpk_rg")
	public void onDelegated(@ParameterMetaInfo(opaeumId=2937187766882494462,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") Participant delegatedBy, @ParameterMetaInfo(opaeumId=9102602523982706512,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") Participant delegatedTo);
	
	@NumlMetaInfo(uuid="252060@_NdLN8K0OEeCK48ywUpk_rg")
	public void onForwarded(@ParameterMetaInfo(opaeumId=5405405098671388300,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") Participant forwardedBy, @ParameterMetaInfo(opaeumId=3907720224420364138,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") Participant forwardedTo);
	
	@NumlMetaInfo(uuid="252060@_8ba9IK0NEeCK48ywUpk_rg")
	public void onResumed(@ParameterMetaInfo(opaeumId=4926573096485933090,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") Participant resumedBy);
	
	@NumlMetaInfo(uuid="252060@__imwgK0NEeCK48ywUpk_rg")
	public void onRevoked(@ParameterMetaInfo(opaeumId=273103052661183192,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") Participant revokedBy);
	
	@NumlMetaInfo(uuid="252060@_fdkRQK0OEeCK48ywUpk_rg")
	public void onSkipped(@ParameterMetaInfo(opaeumId=6103866618185670366,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") Participant skippedBy);
	
	@NumlMetaInfo(uuid="252060@_iBCwEK0NEeCK48ywUpk_rg")
	public void onStarted(@ParameterMetaInfo(opaeumId=2842620541558802106,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") Participant startedBy);
	
	@NumlMetaInfo(uuid="252060@_zwcxEK0NEeCK48ywUpk_rg")
	public void onStopped(@ParameterMetaInfo(opaeumId=6681754435738713602,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") Participant stoppedBy);
	
	@NumlMetaInfo(uuid="252060@_ug7_QK0NEeCK48ywUpk_rg")
	public void onSuspended(@ParameterMetaInfo(opaeumId=7500393394354827722,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") Participant suspendedBy);
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setTaskRequest(TaskRequest taskRequest);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToTaskRequest(TaskRequest val);
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val);

}