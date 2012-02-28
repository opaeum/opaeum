package org.opaeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.propertysections.base.AbstractOclBodyBodySection;
import org.opaeum.topcased.propertysections.ocl.OclBodyComposite;

public class OpaqueActionBodySection extends AbstractOclBodyBodySection{
	private final class OpaqueActionBodyComposite extends OclBodyComposite{
		private OpaqueActionBodyComposite(Composite parent,FormToolkit toolkit){
			super(parent, toolkit);
		}
		public EAttribute getLanguagesFeature(){
			return UMLPackage.eINSTANCE.getOpaqueAction_Language();
		}
		public EAttribute getBodiesFeature(){
			return UMLPackage.eINSTANCE.getOpaqueAction_Body();
		}
		public void setOclContext(final OpaqueAction vp){
			setOclContextImpl(vp, vp);
		}
		@Override
		protected EditingDomain getEditingDomain(){
			return OpaqueActionBodySection.this.getEditingDomain();
		}
	}
	@Override
	protected OclBodyComposite createOclBodyComposite(Composite parent){
		return new OpaqueActionBodyComposite(parent, getWidgetFactory());
	}
	@Override
	protected void setOclContext(OclBodyComposite c){
		((OpaqueActionBodyComposite)c).setOclContext((OpaqueAction)getEObject());
		
	}
}
