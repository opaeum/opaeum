package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;

@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.participant",nakedUmlId=7)
public interface Participant extends CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	public void addAllToParticipation(Set<Participation> participation);
	
	public void addAllToParticipationsInRequests(Collection<ParticipationInRequest> participationsInRequests);
	
	public void addAllToParticipationsInTasks(Collection<ParticipationInTask> participationsInTasks);
	
	public void addToParticipation(Participation participation);
	
	public void addToParticipationsInRequests(ParticipationInRequest participationsInRequests);
	
	public void addToParticipationsInTasks(ParticipationInTask participationsInTasks);
	
	public void clearParticipation();
	
	public void clearParticipationsInRequests();
	
	public void clearParticipationsInTasks();
	
	@NumlMetaInfo(persistentName="participant.initiated_requests_id",nakedUmlId=67)
	public Collection<AbstractRequest> getInitiatedRequests();
	
	@NumlMetaInfo(persistentName="participant.interesting_requests_id",nakedUmlId=59)
	public Collection<AbstractRequest> getInterestingRequests();
	
	@NumlMetaInfo(persistentName="participant.managed_requests_id",nakedUmlId=69)
	public Collection<AbstractRequest> getManagedRequests();
	
	@NumlMetaInfo(persistentName="participant.owned_task_requests_id",nakedUmlId=65)
	public Collection<TaskRequest> getOwnedTaskRequests();
	
	@NumlMetaInfo(persistentName="participant.participation_id",nakedUmlId=72)
	public Set<Participation> getParticipation();
	
	@NumlMetaInfo(persistentName="participant.participations_in_requests_id",nakedUmlId=63)
	public Collection<ParticipationInRequest> getParticipationsInRequests();
	
	@NumlMetaInfo(persistentName="participant.participations_in_tasks_id",nakedUmlId=61)
	public Collection<ParticipationInTask> getParticipationsInTasks();
	
	public void removeAllFromParticipation(Set<Participation> participation);
	
	public void removeAllFromParticipationsInRequests(Collection<ParticipationInRequest> participationsInRequests);
	
	public void removeAllFromParticipationsInTasks(Collection<ParticipationInTask> participationsInTasks);
	
	public void removeFromParticipation(Participation participation);
	
	public void removeFromParticipationsInRequests(ParticipationInRequest participationsInRequests);
	
	public void removeFromParticipationsInTasks(ParticipationInTask participationsInTasks);
	
	public void setParticipation(Set<Participation> participation);
	
	public void setParticipationsInRequests(Collection<ParticipationInRequest> participationsInRequests);
	
	public void setParticipationsInTasks(Collection<ParticipationInTask> participationsInTasks);
	
	public String toString();
	
	public String toXmlString();

}