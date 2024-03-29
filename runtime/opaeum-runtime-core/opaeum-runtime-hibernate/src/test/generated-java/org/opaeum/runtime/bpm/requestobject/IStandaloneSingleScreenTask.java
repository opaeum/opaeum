package org.opaeum.runtime.bpm.requestobject;

import java.io.Serializable;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_qDCBsPNQEeGhGoYzbfvy4A")
public interface IStandaloneSingleScreenTask extends HibernateEntity, CompositionNode, Serializable, IPersistentObject, ITaskObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public boolean consumeOnActivatedOccurrence(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy);
	
	public boolean consumeOnClaimedOccurrence(@ParameterMetaInfo(name="claimedBy",opaeumId=7951140015384934948l,uuid="252060@_roekYK0NEeCK48ywUpk_rg") IParticipant claimedBy);
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy);
	
	public boolean consumeOnDelegatedOccurrence(@ParameterMetaInfo(name="delegatedBy",opaeumId=2937187766882494462l,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") IParticipant delegatedBy, @ParameterMetaInfo(name="delegatedTo",opaeumId=9102602523982706512l,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") IParticipant delegatedTo);
	
	public boolean consumeOnForwardedOccurrence(@ParameterMetaInfo(name="forwardedBy",opaeumId=5405405098671388300l,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") IParticipant forwardedBy, @ParameterMetaInfo(name="forwardedTo",opaeumId=3907720224420364138l,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") IParticipant forwardedTo);
	
	public boolean consumeOnResumedOccurrence(@ParameterMetaInfo(name="resumedBy",opaeumId=4926573096485933090l,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") IParticipant resumedBy);
	
	public boolean consumeOnRevokedOccurrence(@ParameterMetaInfo(name="revokedBy",opaeumId=273103052661183192l,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") IParticipant revokedBy);
	
	public boolean consumeOnSkippedOccurrence(@ParameterMetaInfo(name="skippedBy",opaeumId=6103866618185670366l,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") IParticipant skippedBy);
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy);
	
	public boolean consumeOnStoppedOccurrence(@ParameterMetaInfo(name="stoppedBy",opaeumId=6681754435738713602l,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") IParticipant stoppedBy);
	
	public boolean consumeOnSuspendedOccurrence(@ParameterMetaInfo(name="suspendedBy",opaeumId=7500393394354827722l,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") IParticipant suspendedBy);
	
	public void generateOnActivatedEvent(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy);
	
	public void generateOnClaimedEvent(@ParameterMetaInfo(name="claimedBy",opaeumId=7951140015384934948l,uuid="252060@_roekYK0NEeCK48ywUpk_rg") IParticipant claimedBy);
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy);
	
	public void generateOnDelegatedEvent(@ParameterMetaInfo(name="delegatedBy",opaeumId=2937187766882494462l,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") IParticipant delegatedBy, @ParameterMetaInfo(name="delegatedTo",opaeumId=9102602523982706512l,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") IParticipant delegatedTo);
	
	public void generateOnForwardedEvent(@ParameterMetaInfo(name="forwardedBy",opaeumId=5405405098671388300l,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") IParticipant forwardedBy, @ParameterMetaInfo(name="forwardedTo",opaeumId=3907720224420364138l,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") IParticipant forwardedTo);
	
	public void generateOnResumedEvent(@ParameterMetaInfo(name="resumedBy",opaeumId=4926573096485933090l,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") IParticipant resumedBy);
	
	public void generateOnRevokedEvent(@ParameterMetaInfo(name="revokedBy",opaeumId=273103052661183192l,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") IParticipant revokedBy);
	
	public void generateOnSkippedEvent(@ParameterMetaInfo(name="skippedBy",opaeumId=6103866618185670366l,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") IParticipant skippedBy);
	
	public void generateOnStartedEvent(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy);
	
	public void generateOnStoppedEvent(@ParameterMetaInfo(name="stoppedBy",opaeumId=6681754435738713602l,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") IParticipant stoppedBy);
	
	public void generateOnSuspendedEvent(@ParameterMetaInfo(name="suspendedBy",opaeumId=7500393394354827722l,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") IParticipant suspendedBy);
	
	public String getUid();
	
	@NumlMetaInfo(uuid="252060@_XbPZkK0OEeCK48ywUpk_rg")
	public void onActivated(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy);
	
	@NumlMetaInfo(uuid="252060@_qTa18K0NEeCK48ywUpk_rg")
	public void onClaimed(@ParameterMetaInfo(name="claimedBy",opaeumId=7951140015384934948l,uuid="252060@_roekYK0NEeCK48ywUpk_rg") IParticipant claimedBy);
	
	@NumlMetaInfo(uuid="252060@_a_82cK0OEeCK48ywUpk_rg")
	public void onCompleted(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy);
	
	@NumlMetaInfo(uuid="252060@_EE4B0K0OEeCK48ywUpk_rg")
	public void onDelegated(@ParameterMetaInfo(name="delegatedBy",opaeumId=2937187766882494462l,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") IParticipant delegatedBy, @ParameterMetaInfo(name="delegatedTo",opaeumId=9102602523982706512l,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") IParticipant delegatedTo);
	
	@NumlMetaInfo(uuid="252060@_NdLN8K0OEeCK48ywUpk_rg")
	public void onForwarded(@ParameterMetaInfo(name="forwardedBy",opaeumId=5405405098671388300l,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") IParticipant forwardedBy, @ParameterMetaInfo(name="forwardedTo",opaeumId=3907720224420364138l,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") IParticipant forwardedTo);
	
	@NumlMetaInfo(uuid="252060@_8ba9IK0NEeCK48ywUpk_rg")
	public void onResumed(@ParameterMetaInfo(name="resumedBy",opaeumId=4926573096485933090l,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") IParticipant resumedBy);
	
	@NumlMetaInfo(uuid="252060@__imwgK0NEeCK48ywUpk_rg")
	public void onRevoked(@ParameterMetaInfo(name="revokedBy",opaeumId=273103052661183192l,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") IParticipant revokedBy);
	
	@NumlMetaInfo(uuid="252060@_fdkRQK0OEeCK48ywUpk_rg")
	public void onSkipped(@ParameterMetaInfo(name="skippedBy",opaeumId=6103866618185670366l,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") IParticipant skippedBy);
	
	@NumlMetaInfo(uuid="252060@_iBCwEK0NEeCK48ywUpk_rg")
	public void onStarted(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy);
	
	@NumlMetaInfo(uuid="252060@_zwcxEK0NEeCK48ywUpk_rg")
	public void onStopped(@ParameterMetaInfo(name="stoppedBy",opaeumId=6681754435738713602l,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") IParticipant stoppedBy);
	
	@NumlMetaInfo(uuid="252060@_ug7_QK0NEeCK48ywUpk_rg")
	public void onSuspended(@ParameterMetaInfo(name="suspendedBy",opaeumId=7500393394354827722l,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") IParticipant suspendedBy);
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}