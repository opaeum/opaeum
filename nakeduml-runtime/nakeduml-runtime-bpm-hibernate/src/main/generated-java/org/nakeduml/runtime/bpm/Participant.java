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

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.participant",uuid="OpiumBPM.library.uml@_YgstsI29EeCrtavWRHwoHg")
public interface Participant extends HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToParticipation(Participation participation);
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map);
	
	public void clearParticipation();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.initiated_requests_id",uuid="OpiumBPM.library.uml@_rz7zsI6TEeCne5ArYLDbiA")
	public Collection<AbstractRequest> getInitiatedRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.interesting_requests_id",uuid="OpiumBPM.library.uml@_7MraII6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getInterestingRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.managed_requests_id",uuid="OpiumBPM.library.uml@_jSstQI6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getManagedRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.owned_task_requests_id",uuid="OpiumBPM.library.uml@_NYHP0I6mEeCFsPOcAnk69Q")
	public Collection<TaskRequest> getOwnedTaskRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.participation_id",uuid="OpiumBPM.library.uml@_3YyGkYoXEeCPduia_-NbFw")
	public Set<Participation> getParticipation();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.participations_in_requests_id",uuid="OpiumBPM.library.uml@_TfLFAJBkEeCWM9wKKqKWag")
	public Collection<ParticipationInRequest> getParticipationsInRequests();
	
	@NumlMetaInfo(qualifiedPersistentName="participant.participations_in_tasks_id",uuid="OpiumBPM.library.uml@_DIGv8JBkEeCWM9wKKqKWag")
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