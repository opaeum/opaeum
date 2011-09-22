package org.nakeduml.topcased.uml.editor;

import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.INakedOclAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.commands.SetOclBodyCommand;

public class OclUpdater implements NakedUmlSynchronizationListener{
	private Map<ResourceSet,NakedUmlEditingContext> emfWorkspaces;
	public OclUpdater(Map<ResourceSet,NakedUmlEditingContext> emfWorkspaces){
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
			}else if(de instanceof INakedOpaqueBehavior && ((INakedOpaqueBehavior) ne).getBodyExpression() instanceof OclContextImpl){
				final INakedOpaqueBehavior vs = (INakedOpaqueBehavior) de;
				updateOclBody(de, vs.getBodyExpression(), UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), UMLPackage.eINSTANCE.getOpaqueBehavior_Language());
			}else if(de instanceof INakedOclAction && ((INakedOclAction) ne).getBodyExpression() instanceof OclContextImpl){
				final INakedOclAction vs = (INakedOclAction) de;
				updateOclBody(de, vs.getBodyExpression(), UMLPackage.eINSTANCE.getOpaqueAction_Body(), UMLPackage.eINSTANCE.getOpaqueAction_Language());
			}
		}
	}
	private void updateOclBody(INakedElement de,final IOclContext oclValue,final EAttribute body,final EAttribute language){
		for(final NakedUmlEditingContext ew:emfWorkspaces.values()){
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
