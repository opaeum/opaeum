package org.opaeum.linkage;

import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
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
