package org.opaeum.runtime.bpm.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.DocumentType;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(uuid="252060@_cOzTkF9lEeG3X_yvufTVmw")
public interface IBusinessDocument extends HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public boolean consumeMakeCopyOccurrence();
	
	public void generateMakeCopyEvent();
	
	@PropertyMetaInfo(isComposite=false,opaeumId=759998593327277107l,uuid="252060@_3FqBQF9lEeG3X_yvufTVmw")
	@NumlMetaInfo(uuid="252060@_3FqBQF9lEeG3X_yvufTVmw")
	public DocumentType getDocumentType();
	
	public String getUid();
	
	@NumlMetaInfo(uuid="252060@_nx6xcF9lEeG3X_yvufTVmw")
	public IBusinessDocument makeCopy();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setDocumentType(DocumentType documentType);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToDocumentType(DocumentType val);
	
	public void z_internalRemoveFromDocumentType(DocumentType val);

}