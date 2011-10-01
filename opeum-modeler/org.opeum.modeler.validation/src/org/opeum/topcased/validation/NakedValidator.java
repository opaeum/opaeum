package org.opeum.topcased.validation;

import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Model;
import org.opeum.eclipse.context.OpeumEclipseContext;
import org.opeum.metamodel.validation.BrokenElement;
import org.opeum.metamodel.workspace.INakedModelWorkspace;

public class NakedValidator implements org.topcased.validation.core.IValidator{
	@Override
	public boolean validate(EObject rootEObject,DiagnosticChain arg1,IProgressMonitor arg2) throws CoreException{
		if(rootEObject instanceof Model){
			INakedModelWorkspace workspace = OpeumEclipseContext.getCurrentContext().getNakedWorkspace();
			Set<Entry<String,BrokenElement>> errors = workspace.getErrorMap().getErrors().entrySet();
			for(Entry<String,BrokenElement> brokenElement:errors){
				String key = brokenElement.getKey();
				EObject obj = OpeumEclipseContext.getCurrentContext().getCurrentEmfWorkspace().getElement(key);
				if(obj != null){//Could be an artificial element
					if(obj.eResource().getURI().isPlatformResource()){
//						Set<Entry<IValidationRule,Object[]>> brokenRules = brokenElement.getValue().getBrokenRules().entrySet();
//						for(Entry<IValidationRule,Object[]> brokenRule:brokenRules){
//							String messagePattern = brokenRule.getKey().getMessagePattern();
//							String message = EmfValidationUtil.replaceArguments(obj, brokenRule, messagePattern);
//							BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "org.opeum.topcased.validation", i++, message, new Object[]{
//								obj
//							});
//							arg1.add(diagnostic);
//						}
					}
				}
			}
		}
		return false;
	}
}
