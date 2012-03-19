package org.opaeum.runtime.bpm.requestobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.request.ProcessRequest;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_5DVD4I3oEeCfQedkc0TCdA")
public interface IProcessObject extends HibernateEntity, CompositionNode, IRequestObject, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(opaeumId=4095523919410592856,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") String completedBy);
	
	public boolean consumeOnFailureOccurrence();
	
	public boolean consumeOnResumedOccurrence(@ParameterMetaInfo(opaeumId=787801760248251908,uuid="252060@_xd0650uBEeGElKTCe2jfDw") String resumedBy);
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(opaeumId=8825299842246312,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") String startedBy);
	
	public boolean consumeOnSuspendedOccurrence(@ParameterMetaInfo(opaeumId=3377686963449892476,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") String suspendedBy);
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(opaeumId=4095523919410592856,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") String completedBy);
	
	public void generateOnFailureEvent();
	
	public void generateOnResumedEvent(@ParameterMetaInfo(opaeumId=787801760248251908,uuid="252060@_xd0650uBEeGElKTCe2jfDw") String resumedBy);
	
	public void generateOnStartedEvent(@ParameterMetaInfo(opaeumId=8825299842246312,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") String startedBy);
	
	public void generateOnSuspendedEvent(@ParameterMetaInfo(opaeumId=3377686963449892476,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") String suspendedBy);
	
	@NumlMetaInfo(uuid="252060@_JY15wY3pEeCfQedkc0TCdA")
	public ProcessRequest getProcessRequest();
	
	public String getUid();
	
	@NumlMetaInfo(uuid="252060@_yDSYYEuBEeGElKTCe2jfDw")
	public void onCompleted(@ParameterMetaInfo(opaeumId=4095523919410592856,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") String completedBy);
	
	@NumlMetaInfo(uuid="252060@_CDJHUEuCEeGElKTCe2jfDw")
	public void onFailure();
	
	@NumlMetaInfo(uuid="252060@_xd064EuBEeGElKTCe2jfDw")
	public void onResumed(@ParameterMetaInfo(opaeumId=787801760248251908,uuid="252060@_xd0650uBEeGElKTCe2jfDw") String resumedBy);
	
	@NumlMetaInfo(uuid="252060@_v7N4oEuBEeGElKTCe2jfDw")
	public void onStarted(@ParameterMetaInfo(opaeumId=8825299842246312,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") String startedBy);
	
	@NumlMetaInfo(uuid="252060@_xCSOYEuBEeGElKTCe2jfDw")
	public void onSuspended(@ParameterMetaInfo(opaeumId=3377686963449892476,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") String suspendedBy);
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public String toXmlReferenceString();
	
	public String toXmlString();

}