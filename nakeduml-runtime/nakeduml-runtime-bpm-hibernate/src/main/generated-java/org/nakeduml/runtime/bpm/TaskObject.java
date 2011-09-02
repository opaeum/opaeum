package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.Map;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.task_object",uuid="9ba47bb5_372d_40b7_b7ad_1a477f988162")
public interface TaskObject extends CompositionNode, HibernateEntity, RequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.task_request_id",uuid="22bbcd2c_7afd_46cc_b20b_e7d21d36d2f9")
	public TaskRequest getTaskRequest();
	
	public String getUid();
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_activated",uuid="5ca783d9_68c7_4b5a_9da9_38bc6f5e37f2")
	public void onActivated(BusinessRole activatedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_claimed",uuid="57a73970_27c0_43c2_bf84_217588287539")
	public void onClaimed(BusinessRole claimedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_completed",uuid="55da2d9a_27df_446d_8575_19bea4648d54")
	public void onCompleted(BusinessRole completedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_delegated",uuid="e33e12bc_fff7_4b70_a535_47e2a0e4490f")
	public void onDelegated(BusinessRole delegatedBy, BusinessRole delegatedTo);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_forwarded",uuid="be928add_c441_4722_9ca4_55e62001185b")
	public void onForwarded(BusinessRole forwardedBy, BusinessRole forwardedTo);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_resumed",uuid="1722026c_8dc5_4e7b_b099_8a49c5f7b467")
	public void onResumed(BusinessRole resumedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_revoked",uuid="6b36d586_5db5_4b08_8957_a7d21bb1634c")
	public void onRevoked(BusinessRole revokedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_skipped",uuid="769aa6c0_3b27_474c_b415_a61b11d68581")
	public void onSkipped(BusinessRole skippedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_started",uuid="d6e66578_e1a1_4736_beef_9bfb36f36ac8")
	public void onStarted(BusinessRole startedBy);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.on_stopped",uuid="d31ed2f8_1950_4756_b51f_399097440e88")
	public void onStopped(BusinessRole stoppedBy);
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public void setTaskRequest(TaskRequest taskRequest);
	
	@NumlMetaInfo(qualifiedPersistentName="task_object.suspended",uuid="ec016cd5_a77f_494f_8cb2_0993a12a1378")
	public void suspended(BusinessRole suspendedBy);
	
	public String toString();
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToTaskRequest(TaskRequest val);
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val);

}