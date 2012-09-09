package org.opaeum.eclipse.newchild;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

public class CreateChildAndSelectAction extends CreateChildAction{
	private EObjectSelectorUI selector;
	private ISelection selection;
	public CreateChildAndSelectAction(EditingDomain editingDomain,ISelection selection,CommandParameter descriptor){
		super(editingDomain, selection, descriptor);
		setText(descriptor);
		this.selection=selection;
	}
	public CreateChildAndSelectAction(IEditorPart editorPart,ISelection selection,CommandParameter descriptor){
		super(editorPart, selection, descriptor);
		setText(descriptor);
		this.selection=selection;
	}
	public CreateChildAndSelectAction(IWorkbenchPart workbenchPart,ISelection selection,CommandParameter descriptor){
		super(workbenchPart, selection, descriptor);
		setText(descriptor);
		this.selection=selection;
	}
	private void setText(CommandParameter descriptor){
		String name = descriptor.getEStructuralFeature().getName();
		setText(toWords(name) + "|" + toWords(descriptor.getEValue().eClass().getName()));
		setEnabled(true);
	}
	private String toWords(String name){
		return NameConverter.separateWords(NameConverter.capitalize(name));
	}
	protected void gotoNewObject(){
		if(selector != null){
			for(final Object object:command.getResult()){
				if(object instanceof EObject){
					Display.getCurrent().timerExec(300, new Runnable(){
						@Override
						public void run(){
							selector.gotoEObject((EObject) object);
						}
					});
					break;
				}
			}
		}
	}
	@Override
	public void run(){
		EObject eOwner = getCommandParameter().getEOwner();
		if(!isCompositeFeature() && eOwner instanceof DynamicEObjectImpl){
			//Add to the containment tree
			Element element = UMLUtil.getBaseElement(eOwner);
			EAnnotation ann = StereotypesHelper.getNumlAnnotation(element);
			if(ann == null){
				ann = EcoreFactory.eINSTANCE.createEAnnotation();
				ann.setSource(StereotypeNames.NUML_ANNOTATION);
				editingDomain.getCommandStack().execute(
						AddCommand.create(editingDomain, element, EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), ann));
			}
			editingDomain.getCommandStack().execute(
					AddCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), getCommandParameter().getValue()));
//			configureAction(new StructuredSelection(element));
		}
		super.run();
		gotoNewObject();
	}
	protected boolean isCompositeFeature(){
		return ((EReference) getCommandParameter().getEStructuralFeature()).isContainment();
	}
	protected CommandParameter getCommandParameter(){
		return (CommandParameter) descriptor;
	}
	public EObjectSelectorUI getSelector(){
		return selector;
	}
	public void setSelector(EObjectSelectorUI selector){
		this.selector = selector;
	}
}
