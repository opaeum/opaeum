package nakedumllibraryforbpm.util.metainfo.domain;

import org.nakeduml.environment.JavaMetaInfoMap;
import org.nakeduml.runtime.domain.IPersistentObject;

public class JavaMetaInfoMapImpl extends JavaMetaInfoMap {


	/** Constructor for JavaMetaInfoMapImpl
	 */
	public JavaMetaInfoMapImpl() {
		putClass(nakedumllibraryforbpm.AbstractRequest.class);
		putClass(nakedumllibraryforbpm.BusinessRole.class);
		putClass(nakedumllibraryforbpm.RequestParticipationKind.class);
		putClass(nakedumllibraryforbpm.TaskParticipationKind.class);
		putClass(nakedumllibraryforbpm.TaskRequest.class);
		putClass(nakedumllibraryforbpm.OperationProcessObject.class);
		putClass(nakedumllibraryforbpm.TaskObject.class);
		putClass(nakedumllibraryforbpm.ParticipationInTask.class);
		putClass(nakedumllibraryforbpm.Participant.class);
		putClass(nakedumllibraryforbpm.Participation.class);
		putClass(nakedumllibraryforbpm.ProcessRequest.class);
		putClass(nakedumllibraryforbpm.BusinessComponent.class);
		putClass(nakedumllibraryforbpm.ParticipationInRequest.class);
		putClass(nakedumllibraryforbpm.RequestObject.class);
	}


}