package structuredbusiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessService;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
import org.w3c.dom.Element;

import structuredbusiness.util.StructuredbusinessFormatter;

@NumlMetaInfo(uuid="914890@_Jn_8cKZyEeGmp8-JHElaag")
public interface BusinessService1 extends IBusinessService, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	@NumlMetaInfo(uuid="914890@_L48EUKZyEeGmp8-JHElaag")
	public void Operation1();
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public boolean consumeOperation1Occurrence();
	
	public void generateOperation1Event();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5117261994133173917l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="914890@_Mb4ZUKZyEeGmp8-JHElaag")
	@NumlMetaInfo(uuid="914890@_Mb4ZUKZyEeGmp8-JHElaag")
	public String getProperty1();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setProperty1(String property1);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToProperty1(String val);
	
	public void z_internalRemoveFromProperty1(String val);

}