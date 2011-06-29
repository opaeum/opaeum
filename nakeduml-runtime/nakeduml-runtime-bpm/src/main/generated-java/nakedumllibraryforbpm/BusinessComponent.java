package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;

@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.business_component",nakedUmlId=23)
public interface BusinessComponent extends Participant, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	public String toString();
	
	public String toXmlString();

}