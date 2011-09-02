package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.participant",uuid="02234900_5ce4_4179_8d64_4965b437cd7a")
public interface Participant extends CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	public void addToParticipation(Participation participation);
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public void clearParticipation();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.initiated_requests_id",uuid="c89adb88_0d35_40bb_9921_552295505dd2")
	public Collection<AbstractRequest> getInitiatedRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.interesting_requests_id",uuid="5b93b1f4_d9e4_4e10_836c_3adfd1e3c3a8")
	public Collection<AbstractRequest> getInterestingRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.managed_requests_id",uuid="13a72d83_92c6_44b8_b799_b7b1e623b96c")
	public Collection<AbstractRequest> getManagedRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.owned_task_requests_id",uuid="2bb869af_5be9_4588_b3b3_b367c4de82b6")
	public Collection<TaskRequest> getOwnedTaskRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.participation_id",uuid="41f8a1c0_cb14_44a5_ab77_9758f5c381ab")
	public Set<Participation> getParticipation();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.participations_in_requests_id",uuid="e2c98cb5_85bf_453e_a398_10f5a9ad823e")
	public Collection<ParticipationInRequest> getParticipationsInRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.participations_in_tasks_id",uuid="88972b85_d58b_4b0d_8da1_5bc8b21bd4f0")
	public Collection<ParticipationInTask> getParticipationsInTasks();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public void setParticipation(Set<Participation> participation);
	
	public String toString();
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToParticipation(Participation val);
	
	public void z_internalRemoveFromParticipation(Participation val);

}