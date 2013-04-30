package org.opaeum.ocl.uml;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.uml.UMLEnvironment;
import org.opaeum.metamodel.workspace.OpaeumLib;

public class OpaeumParentEnvironment extends UMLEnvironment{
	protected OpaeumLib library;
	public OpaeumParentEnvironment(ResourceSet rst){
		
		super(rst.getPackageRegistry(),rst);
		this.library=new OpaeumLib(rst, getOCLStandardLibrary());
	}
	public OpaeumLib getLibrary(){
		return library;
	}

}