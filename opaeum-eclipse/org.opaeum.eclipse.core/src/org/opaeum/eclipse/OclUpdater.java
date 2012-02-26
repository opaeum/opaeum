package org.opaeum.eclipse;

import java.util.Map;
import java.util.Set;

import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.commands.SetOclBodyCommand;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.metamodel.actions.INakedOclAction;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.commonbehaviors.INakedOpaqueBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public class OclUpdater implements OpaeumSynchronizationListener{
	private Map<IFile,OpenUmlFile> emfWorkspaces;
	public OclUpdater(Map<IFile,OpenUmlFile> emfWorkspaces){
		super();
		this.emfWorkspaces = emfWorkspaces;
	}
	@Override
	public void synchronizationComplete(INakedModelWorkspace nakedWorkspace,Set<INakedElement> affectedElements){
		for(INakedElement ne:affectedElements){
			if(isLocalJavaRename(ne) && couldBeReferencedFromOcl(ne)){
				oclCalculated(nakedWorkspace, ne);
			}
		}
	}
	private boolean couldBeReferencedFromOcl(INakedElement ne){
		return ne instanceof INakedPin || ne instanceof INakedOperation || ne instanceof INakedClassifier || ne instanceof INakedParameter
				|| ne instanceof INakedProperty;
	}
	private boolean isLocalJavaRename(INakedElement ne){
		return ne.getMappingInfo().getJavaName() != null && !ne.getMappingInfo().getJavaName().equals(ne.getMappingInfo().getOldJavaName());
	}

	private void oclCalculated(INakedModelWorkspace nakedWorkspace, INakedElement ne){
		for(INakedElement de:nakedWorkspace.getDependentElements(ne)){
			if(de instanceof INakedValueSpecification && ((INakedValueSpecification) de).isValidOclValue()){
				final INakedValueSpecification vs = (INakedValueSpecification) de;
				updateOclBody(de, vs.getOclValue(), UMLPackage.eINSTANCE.getOpaqueExpression_Body(), UMLPackage.eINSTANCE.getOpaqueExpression_Language());
			}else if(de instanceof INakedOpaqueBehavior && ((INakedOpaqueBehavior) de).getBodyExpression() instanceof OclContextImpl){
				final INakedOpaqueBehavior vs = (INakedOpaqueBehavior) de;
				updateOclBody(de, vs.getBodyExpression(), UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), UMLPackage.eINSTANCE.getOpaqueBehavior_Language());
			}else if(de instanceof INakedOclAction && ((INakedOclAction) de).getBodyExpression() instanceof OclContextImpl){
				final INakedOclAction vs = (INakedOclAction) de;
				updateOclBody(de, vs.getBodyExpression(), UMLPackage.eINSTANCE.getOpaqueAction_Body(), UMLPackage.eINSTANCE.getOpaqueAction_Language());
			}
		}
	}
	private void updateOclBody(INakedElement de,final IOclContext oclValue,final EAttribute body,final EAttribute language){
		for(final OpenUmlFile ew:emfWorkspaces.values()){
			final NamedElement oe = (NamedElement) ew.getEmfWorkspace().getElement(de.getId());
			// Could be artificially generated OCL
			if(oe != null && !ew.getEditingDomain().isReadOnly(oe.eResource())){
				Display.getDefault().syncExec(new Runnable(){
					@Override
					public void run(){
						SetOclBodyCommand cmd = new SetOclBodyCommand(ew.getEditingDomain(), oe, body, language, oclValue.getExpressionString());
						ew.getEditingDomain().getCommandStack().execute(cmd);
					}
				});
				break;
			}
		}
	}
}
