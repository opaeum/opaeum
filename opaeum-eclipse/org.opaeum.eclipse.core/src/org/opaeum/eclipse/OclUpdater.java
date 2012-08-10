package org.opaeum.eclipse;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.commands.SetOclBodyCommand;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.ocl.uml.AbstractOclContext;

public class OclUpdater implements OpaeumSynchronizationListener{
	public OclUpdater(){
		super();
	}
	@Override
	public void synchronizationComplete(OpenUmlFile openUmlFile,Set<Element> affectedElements){
		OJUtil ojUtil = openUmlFile.getOJUtil();
		for(Element ne:affectedElements){
			if(ne instanceof NamedElement && isLocalJavaRename(ojUtil, ne) && couldBeReferencedFromOcl(ne)){
				oclCalculated(openUmlFile, (NamedElement) ne);
			}
		}
	}
	private boolean couldBeReferencedFromOcl(Element ne){
		return ne instanceof Pin || ne instanceof Operation || ne instanceof Classifier || ne instanceof Parameter || ne instanceof Property
				|| ne instanceof Variable;
	}
	private boolean isLocalJavaRename(OJUtil ojUtil,Element ne){
		if(ne instanceof NamedElement){
			return ojUtil.requiresJavaRename((NamedElement) ne);
		}else{
			return false;
		}
	}
	private void oclCalculated(OpenUmlFile ouf,NamedElement ne){
		OpaeumLibrary lib = ouf.getEmfWorkspace().getOpaeumLibrary();
		for(AbstractOclContext ctx:lib.getOclContexts()){
			if(ctx.hasErrors() == false && ctx.dependsOn(ne)){
				if(ctx.getBodyContainer() instanceof OpaqueExpression){
					updateOclBody(ouf, ctx, UMLPackage.eINSTANCE.getOpaqueExpression_Body(), UMLPackage.eINSTANCE.getOpaqueExpression_Language());
				}else if(ctx.getBodyContainer() instanceof OpaqueAction){
					updateOclBody(ouf, ctx, UMLPackage.eINSTANCE.getOpaqueAction_Body(), UMLPackage.eINSTANCE.getOpaqueAction_Language());
				}else if(ctx.getBodyContainer() instanceof OpaqueBehavior){
					updateOclBody(ouf, ctx, UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), UMLPackage.eINSTANCE.getOpaqueBehavior_Language());
				}
			}
		}
	}
	private void updateOclBody(final OpenUmlFile ouf,final AbstractOclContext ocl,final EAttribute body,final EAttribute language){
		if(!ouf.getEditingDomain().isReadOnly(ocl.getBodyContainer().eResource())){
			Display.getDefault().syncExec(new Runnable(){
				@Override
				public void run(){
					SetOclBodyCommand cmd = new SetOclBodyCommand(ouf.getEditingDomain(), ocl.getBodyContainer(), body, language, ocl
							.regenerateExpressionString());
					ouf.getEditingDomain().getCommandStack().execute(cmd);
				}
			});
		}
	}
	@Override
	public void onClose(OpenUmlFile openUmlFile){
	}
}
