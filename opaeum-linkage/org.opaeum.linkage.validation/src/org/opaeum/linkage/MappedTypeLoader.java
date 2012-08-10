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
	@VisitBefore(matchSubclasses=true)
	public void visitRootObject(Package p){
		if(EmfPackageUtil.isRootObject(p)){
		}
	}
}
