package org.opaeum.runtime.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="document_type")
@Entity(name="DocumentTypeEntity")
public class DocumentTypeEntity extends AbstractPersistentEnum {


	/** Constructor for DocumentTypeEntity
	 * 
	 * @param e 
	 */
	public DocumentTypeEntity(DocumentType e) {
		super(e);
	}
	
	/** Default constructor for DocumentTypeEntity
	 */
	public DocumentTypeEntity() {
	}


}