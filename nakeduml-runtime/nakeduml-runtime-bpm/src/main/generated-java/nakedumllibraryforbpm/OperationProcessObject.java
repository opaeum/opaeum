package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;

@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.operation_process_object",nakedUmlId=10)
public interface OperationProcessObject extends CompositionNode, HibernateEntity, RequestObject, Serializable, IPersistentObject {
	@NumlMetaInfo(persistentName="operation_process_object.process_request_id",nakedUmlId=79)
	public ProcessRequest getProcessRequest();
	
	public String toString();
	
	public String toXmlString();

}