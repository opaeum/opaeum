package org.opaeum.eclipse;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.util.ToStringVisitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.commands.SetOclBodyCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.javageneration.basicjava.ToStringBuilder;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OclActionContext;
import org.opaeum.ocl.uml.OclBehaviorContext;
import org.opaeum.ocl.uml.OclContext;

public class OclUpdater implements OpaeumSynchronizationListener{
	private Map<IFile,OpenUmlFile> emfWorkspaces;
	public OclUpdater(Map<IFile,OpenUmlFile> emfWorkspaces){
		super();
		this.emfWorkspaces = emfWorkspaces;
	}
	@Override
	public void synchronizationComplete(ModelWorkspace nakedWorkspace,Set<Element> affectedElements){
		OJUtil ojUtil = "get one associated with this emWorkspace";
		for(Element ne:affectedElements){
			if(isLocalJavaRename(ojUtil, ne) && couldBeReferencedFromOcl(ne)){
				oclCalculated(nakedWorkspace, ne);
			}
		}
	}
	private boolean couldBeReferencedFromOcl(Element ne){
		return ne instanceof Pin || ne instanceof Operation || ne instanceof Classifier || ne instanceof Parameter || ne instanceof Property || ne instanceof Variable;
	}
	private boolean isLocalJavaRename(OJUtil ojUtil, Element ne){
		if(ne instanceof NamedElement){
			return ojUtil.requiresJavaRename((NamedElement) ne);
		}else{
			return false;
		}
	}
	private String toOclString(Element e, OCLExpression oclExpression, EmfWorkspace ctx){
		OpaeumLibrary opaeumLibrary = ctx.getOpaeumLibrary();
		ToStringVisitor<Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint> instance = ToStringVisitor.getInstance(opaeumLibrary.createOcl(e, Collections.EMPTY_MAP).getEnvironment());
		return oclExpression.accept(instance);
	}
	private void oclCalculated(ModelWorkspace nakedWorkspace,Element ne){
		OpaeumLibrary lib = nakedWorkspace.getOpaeumLibrary();
		for(Element de:nakedWorkspace.getDependentElements(ne)){
			if(de instanceof OpaqueExpression){
				OclContext oec = lib.getOclExpressionContext((OpaqueExpression) de);
				if(oec.hasErrors() == false){
					updateOclBody(de, oec, UMLPackage.eINSTANCE.getOpaqueExpression_Body(), UMLPackage.eINSTANCE.getOpaqueExpression_Language());
				}
			}else if(de instanceof OpaqueBehavior){
				final OpaqueBehavior ob = (OpaqueBehavior) de;
				OclBehaviorContext obc = lib.getOclBehaviorContext(ob);
				updateOclBody(de, obc, UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), UMLPackage.eINSTANCE.getOpaqueBehavior_Language());
			}else if(de instanceof OpaqueAction && !EmfActionUtil.isSingleScreenTask((ActivityNode) de)){
				final OpaqueAction oa = (OpaqueAction) de;
				OclActionContext oclActionContext = lib.getOclActionContext(oa);
				updateOclBody(de, oclActionContext, UMLPackage.eINSTANCE.getOpaqueAction_Body(), UMLPackage.eINSTANCE.getOpaqueAction_Language());
			}
		}
	}
	private void updateOclBody(final Element de,final AbstractOclContext ocl,final EAttribute body,final EAttribute language){
		for(final OpenUmlFile ew:emfWorkspaces.values()){
			final NamedElement oe = (NamedElement) ew.getEmfWorkspace().getModelElement(EmfWorkspace.getId(de));
			// Could be artificially generated OCL
			if(oe != null && !ew.getEditingDomain().isReadOnly(oe.eResource())){
				Display.getDefault().syncExec(new Runnable(){
					@Override
					public void run(){
						SetOclBodyCommand cmd = new SetOclBodyCommand(ew.getEditingDomain(), oe, body, language, toOclString(de, ocl.getExpression(),ew.getEmfWorkspace()));
						ew.getEditingDomain().getCommandStack().execute(cmd);
					}
				});
				break;
			}
		}
	}
}
