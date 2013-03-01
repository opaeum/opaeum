package org.opaeum.ocl.uml;

import org.eclipse.ocl.uml.OCL;
import org.eclipse.uml2.uml.NamedElement;

public class OclQueryContext extends AbstractOclContext{
	private String query;

	public OclQueryContext(NamedElement bodyContainer, OCL ocl, String expression){
		super(bodyContainer, ocl.createOCLHelper());
		this.query=expression;
	}

	@Override
	protected String retrieveBody(){
		return query;
	}
}
