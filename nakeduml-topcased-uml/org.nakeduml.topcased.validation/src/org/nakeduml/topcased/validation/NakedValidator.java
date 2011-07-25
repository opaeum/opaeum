package org.nakeduml.topcased.validation;

import java.util.Map.Entry;
import java.util.Set;

import net.sf.nakeduml.emf.workspace.UmlElementMap;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.IValidationRule;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Model;
import org.nakeduml.eclipse.EmfValidationUtil;
import org.nakeduml.topcased.uml.NakedUmlPlugin;

public class NakedValidator implements org.topcased.validation.core.IValidator{
	@Override
	public boolean validate(EObject rootEObject,DiagnosticChain arg1,IProgressMonitor arg2) throws CoreException{
		if(rootEObject instanceof Model){
			Model model = (Model) rootEObject;
			NakedUmlConfig cfg = new NakedUmlConfig();
			cfg.loadDefaults("test");
			UmlElementMap cache = NakedUmlPlugin.findNakedUmlEditor(model).getUmlElementMap();
			INakedModelWorkspace workspace = cache.getTransformationProcess().findModel(INakedModelWorkspace.class);
			Set<Entry<String,BrokenElement>> errors = workspace.getErrorMap().getErrors().entrySet();
			int i = 999;
			for(Entry<String,BrokenElement> brokenElement:errors){
				String key = brokenElement.getKey();
				EObject obj = cache.getElement(key);
				if(obj != null){//Could be an artificial element
					if(obj.eResource().getURI().isPlatformResource()){
						Set<Entry<IValidationRule,Object[]>> brokenRules = brokenElement.getValue().getBrokenRules().entrySet();
						for(Entry<IValidationRule,Object[]> brokenRule:brokenRules){
							String messagePattern = brokenRule.getKey().getMessagePattern();
							String message = EmfValidationUtil.replaceArguments(obj, brokenRule, messagePattern);
							BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "org.nakeduml.topcased.validation", i++, message, new Object[]{
								obj
							});
							arg1.add(diagnostic);
						}
					}
				}
			}
		}
		return false;
	}
}
