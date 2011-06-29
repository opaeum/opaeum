package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;

@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.task_object",nakedUmlId=22)
public interface TaskObject extends CompositionNode, HibernateEntity, RequestObject, Serializable, IPersistentObject {
	@NumlMetaInfo(persistentName="task_object.task_request_id",nakedUmlId=80)
	public TaskRequest getTaskRequest();
	
	public void setTaskRequest(TaskRequest taskRequest);
	
	public String toString();
	
	public String toXmlString();
	
	public void z_internalAddToTaskRequest(TaskRequest taskRequest);
	
	public void z_internalRemoveFromTaskRequest(TaskRequest taskRequest);

}