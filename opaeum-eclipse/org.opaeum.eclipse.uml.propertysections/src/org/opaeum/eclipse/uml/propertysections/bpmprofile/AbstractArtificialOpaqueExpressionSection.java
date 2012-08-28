package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOclBodyBodySection;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.metamodel.name.SingularNameWrapper;

public abstract class AbstractArtificialOpaqueExpressionSection extends AbstractOclBodyBodySection{
	private final class ArtificialOclBodyComposite extends OclBodyComposite{
		private ArtificialOclBodyComposite(Composite parent,FormToolkit toolkit){
			super(parent, toolkit);
		}
		@Override
		public EAttribute getLanguagesFeature(){
			return UMLPackage.eINSTANCE.getOpaqueExpression_Language();
		}
		@Override
		public EAttribute getBodiesFeature(){
			return UMLPackage.eINSTANCE.getOpaqueExpression_Body();
		}
		public void setOclContext(NamedElement eObject,OpaqueExpression findOpaqueExpression){
			super.setOclContextImpl(eObject, findOpaqueExpression);
		}
		@Override
		protected void fireOclChanged(String text){
			for(EObject eObject:getEObjectList()){
				CompoundCommand ccmd = new CompoundCommand();
				Element element = (Element) eObject;
				Stereotype st = getStereotype(element);
				EObject stereotypeApplication = element.getStereotypeApplication(st);
				EStructuralFeature feature = stereotypeApplication.eClass().getEStructuralFeature(getExpressionName());
				OpaqueExpression vs = (OpaqueExpression) stereotypeApplication.eGet(feature);
				if((text.trim().length() == 0 || text.equals(OclBodyComposite.DEFAULT_TEXT) && vs != null)){
					Command cmd = SetCommand.create(getEditingDomain(), stereotypeApplication, feature, SetCommand.UNSET_VALUE);
					ccmd.append(cmd);
					EAnnotation ann = StereotypesHelper.getNumlAnnotation(element);
					if(ann != null){
						ccmd.append(RemoveCommand.create(getEditingDomain(), ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), vs));
					}
				}else{
					if(vs == null){
						vs = UMLFactory.eINSTANCE.createOpaqueExpression();
						vs.setName(getExpressionName());
						EAnnotation ann = StereotypesHelper.getNumlAnnotation(element);
						if(ann == null){
							ann = EcoreFactory.eINSTANCE.createEAnnotation();
							ann.setSource(StereotypeNames.NUML_ANNOTATION);
							ccmd.append(AddCommand.create(getEditingDomain(), element, EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), ann));
						}
						ccmd.append(AddCommand.create(getEditingDomain(), ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), vs));
						if(TagNames.COMPOSITE_ATTRIBUTES.get(feature.getName())!=null){
							ccmd.append(new ApplyStereotypeCommand(vs, st.getProfile().getOwnedStereotype(TagNames.COMPOSITE_ATTRIBUTES.get(feature.getName()))));
						}
						ccmd.append(SetCommand.create(getEditingDomain(), stereotypeApplication, feature, vs));
					}
					super.oclBodyOwner = vs;
					super.fireOclChanged(text);
				}
				getEditingDomain().getCommandStack().execute(ccmd);
			}
		}
		@Override
		protected EditingDomain getEditingDomain(){
			return AbstractArtificialOpaqueExpressionSection.this.getEditingDomain();
		}
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	@Override
	protected OclBodyComposite createOclBodyComposite(Composite parent){
		return new ArtificialOclBodyComposite(parent, getWidgetFactory());
	}
	public AbstractArtificialOpaqueExpressionSection(){
		super();
	}
	@Override
	protected void setOclContext(OclBodyComposite c){
		Element element = (Element) getEObject();
		Stereotype st = getStereotype(element);
		EObject stereotypeApplication = element.getStereotypeApplication(st);
		EStructuralFeature feature = stereotypeApplication.eClass().getEStructuralFeature(getExpressionName());
		OpaqueExpression vs = (OpaqueExpression) stereotypeApplication.eGet(feature);
		((ArtificialOclBodyComposite) c).setOclContext((NamedElement) getEObject(), vs);
	}
	protected EAnnotation getAnnotation(){
		return StereotypesHelper.getNumlAnnotation(getElement());
	}
	protected Element getElement(){
		return (Element) getEObject();
	}
	protected abstract String getExpressionName();
	@Override
	protected String getLabelText(){
		return new SingularNameWrapper(getExpressionName(), null).getCapped().getSeparateWords().getAsIs();
	}
	private Stereotype getStereotype(Element element){
		for(Stereotype s:element.getAppliedStereotypes()){
			if(s.getDefinition().getEStructuralFeature(getExpressionName())!=null){
				return s;
			}
		}
		return null;
	}

}