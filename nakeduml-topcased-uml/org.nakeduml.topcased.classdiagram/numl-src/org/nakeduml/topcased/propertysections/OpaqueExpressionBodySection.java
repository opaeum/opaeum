package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;

public class OpaqueExpressionBodySection extends AbstractOclBodyBodySection{
	private final class OclComposite extends OclBodyComposite{
		private OclComposite(Composite parent,FormToolkit toolkit){
			super(parent, toolkit);
		}
		public void setOclContext(OpaqueExpression eObject){
			EObject s = eObject;
			EObject container = null;
			while(!(isOclContext(container = getContainer(s)))){
			}
			setOclContextImpl((NamedElement) container, eObject);
		}
		@Override
		public EStructuralFeature getBodiesFeature(){
			return UMLPackage.eINSTANCE.getOpaqueExpression_Body();
		}
		@Override
		public EStructuralFeature getLanguagesFeature(){
			return UMLPackage.eINSTANCE.getOpaqueExpression_Language();
		}
		@Override
		protected EditingDomain getEditingDomain(){
			return OpaqueExpressionBodySection.this.getEditingDomain();
		}

	}
	@Override
	protected OclBodyComposite createOclBodyComposite(final Composite parent){
		return new OclComposite(parent, getWidgetFactory());
	}
	protected void setOclContext(OclBodyComposite c){
		((OclComposite)c).setOclContext((OpaqueExpression)getEObject());
	}
}
