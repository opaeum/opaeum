package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.topcased.propertysections.ocl.OclBodyComposite;
import org.opaeum.topcased.propertysections.ocl.OpaqueExpressionComposite;
/**
 * Preconditions: OpaqueExpression ALWAYS available
 * @author ampie
 *
 */
public class OpaqueExpressionBodySection extends AbstractOclBodyBodySection{
	
	private final class OclComposite extends OpaqueExpressionComposite{
		private OclComposite(Composite parent,FormToolkit toolkit){
			super(parent, toolkit);
		}
		public void setOclContext(OpaqueExpression eObject){
			EObject container = eObject;
			while(!(isOclContext(container = EmfElementFinder.getContainer(container)))){
			}
			setOclContextImpl((NamedElement) container, eObject);
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
