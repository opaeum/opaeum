package org.opaeum.linkage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.workspace.MappedType;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;
@StepDependency(phase = ValidationPhase.class,after = {},requires = {},before = {})

public class MappedTypeLoader extends AbstractValidator{
	public static final String MAPPINGS_EXTENSION = "mappings";
	@VisitBefore
	public void visitRootObject(Package p){
		if(EmfPackageUtil.isRootObject(p)){
			URI mappedTypesUri = p.eResource().getURI().trimFileExtension().appendFileExtension(MAPPINGS_EXTENSION);
			try{
				InputStream inStream = p.eResource().getResourceSet().getURIConverter().createInputStream(mappedTypesUri);
				Properties props = new Properties();
				props.load(inStream);
				Set<Entry<Object,Object>> entrySet = props.entrySet();
				for(Entry<Object,Object> entry:entrySet){
					super.workspace.getOpaeumLibrary().getTypeMap().put((String) entry.getKey(), new MappedType((String) entry.getValue()));
				}
				System.out.println("Loaded mappings: " + mappedTypesUri);
			}catch(IOException e1){
			}
		}
	}
}
