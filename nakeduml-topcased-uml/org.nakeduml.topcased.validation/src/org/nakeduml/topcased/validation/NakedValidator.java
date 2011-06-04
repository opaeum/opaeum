package org.nakeduml.topcased.validation;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.IValidationRule;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;

public class NakedValidator implements org.topcased.validation.core.IValidator {
	@Override
	public boolean validate(EObject rootEObject, DiagnosticChain arg1, IProgressMonitor arg2) throws CoreException {
		if (rootEObject instanceof Model) {
			Model model = (Model) rootEObject;
			EcoreUtil.resolveAll(model);
			NakedUmlConfig cfg = new NakedUmlConfig();
			cfg.loadDefaults("test");
			TransformationProcess process = new TransformationProcess();
			IFile ifile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path( model.eResource().getURI().toPlatformString(true)));
			File dir=ifile.getLocation().toFile().getParentFile();
			WorkspaceMappingInfoImpl mappingInfo = new WorkspaceMappingInfoImpl(new File(dir, dir.getName() + ".mappinginfo"));
			process.execute(cfg, new EmfWorkspace(model, mappingInfo,dir.getName()), new HashSet<Class<? extends TransformationStep>>(
					
					Arrays.asList(StereotypeApplicationExtractor.class, NakedParsedOclStringResolver.class,ProcessIdentifier.class)));
			INakedModelWorkspace workspace = process.findModel(INakedModelWorkspace.class);
			Set<Entry<Object, BrokenElement>> errors = workspace.getErrorMap().getErrors().entrySet();
			int i = 999;
			for (Entry<Object, BrokenElement> brokenElement : errors) {
				Object key = brokenElement.getKey();
				if (key instanceof EObject) {
					Set<Entry<IValidationRule, Object[]>> brokenRules = brokenElement.getValue().getBrokenRules().entrySet();
					for (Entry<IValidationRule, Object[]> brokenRule : brokenRules) {
						String messagePattern = brokenRule.getKey().getMessagePattern();
						String message = replaceArguments((EObject) key, brokenRule, messagePattern);
						BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.sf.nakeduml.topcased.Validator", i++,
								message, new Object[] { key });
						System.out.println(message);
						arg1.add(diagnostic);
					}
				}
			}
		}
		return false;
	}

	private String replaceArguments(EObject object, Entry<IValidationRule, Object[]> brokenRule, String messagePattern) {
		String simpleName = object.getClass().getSimpleName();
		String message = messagePattern.replace("{0}", simpleName + "[" + getNameFor(object) + "]");
		for (int i = 0; i < brokenRule.getValue().length; i++) {
			message = message.replace("{" + (i + 1) + "}", brokenRule.getValue()[i].toString());
		}
		return message;
	}

	private String getNameFor(EObject object) {
		EList<EStructuralFeature> sfs = object.eClass().getEAllStructuralFeatures();
		for (EStructuralFeature eStructuralFeature : sfs) {
			if (eStructuralFeature.getName().equals("name")) {
				String name = (String) object.eGet(eStructuralFeature);
				if (name == null || name.trim().length() == 0) {
					return getNameFor(object.eContainer());
				} else {
					return name;
				}
			}
		}
		return object.toString();
	}
}
