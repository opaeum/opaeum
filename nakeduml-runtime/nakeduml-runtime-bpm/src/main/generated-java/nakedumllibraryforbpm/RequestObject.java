package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;

@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.request_object",nakedUmlId=13)
public interface RequestObject extends CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	@NumlMetaInfo(persistentName="request_object.request_id",nakedUmlId=75)
	public AbstractRequest getRequest();
	
	public String toString();
	
	public String toXmlString();

}