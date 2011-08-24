package org.nakeduml.topcased.activitydiagram.propertysections;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.propertysections.AbstractOclBodyBodySection;
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;

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
			EAnnotation ann = getAnnotation();
			OpaqueExpression vs = findOpaqueExpression(getExpressionName(), ann);
			if((text.trim().length() == 0 || text.equals(OclBodyComposite.DEFAULT_TEXT) && vs != null)){
				Command cmd = RemoveCommand.create(getEditingDomain(), ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), vs);
				getEditingDomain().getCommandStack().execute(cmd);
			}else{
				if(vs == null){
					vs = UMLFactory.eINSTANCE.createOpaqueExpression();
					vs.setName(getExpressionName());
					Command cmd = AddCommand.create(getEditingDomain(), ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), vs);
					getEditingDomain().getCommandStack().execute(cmd);
					Element e = (Element) getEObject();
					for(EObject eObject:e.getStereotypeApplications()){
						if(eObject.eClass().getName().equals(getStereotypeName())){
							for(EStructuralFeature sf:eObject.eClass().getEAllStructuralFeatures()){
								if(sf.getName().equals(getExpressionName())){
									getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), eObject, sf, vs));
								}
							}
						}
					}
				}
				super.oclBodyOwner = vs;
				super.fireOclChanged(text);
			}
		}
		@Override
		protected EditingDomain getEditingDomain(){
			return AbstractArtificialOpaqueExpressionSection.this.getEditingDomain();
		}
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
		((ArtificialOclBodyComposite) c).setOclContext((NamedElement) getEObject(), findOpaqueExpression(getExpressionName(), getAnnotation()));
	}
	protected EAnnotation getAnnotation(){
		return StereotypesHelper.getNumlAnnotation((Element) getEObject());
	}
	protected abstract String getExpressionName();
	protected OpaqueExpression findOpaqueExpression(String vsName,EAnnotation ann){
		EList<EObject> contents = ann.getContents();
		OpaqueExpression vs = null;
		for(EObject eObject:contents){
			if(eObject instanceof OpaqueExpression && vsName.equals(((OpaqueExpression) eObject).getName())){
				vs = (OpaqueExpression) eObject;
				break;
			}
		}
		return vs;
	}
	@Override
	protected String getLabelText(){
		return new SingularNameWrapper(getExpressionName(), null).getCapped().getSeparateWords().getAsIs();
	}
	public abstract String getStereotypeName();
}