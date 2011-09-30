package org.opeum.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.UMLPackage;
import org.opeum.topcased.propertysections.ocl.OclBodyComposite;

public class OpaqueBehaviorBodySection extends AbstractOclBodyBodySection{
	private final class OpaqueBehaviorBodyComposite extends OclBodyComposite{
		private OpaqueBehaviorBodyComposite(Composite parent,FormToolkit toolkit){
			super(parent, toolkit);
		}
		@Override
		public EStructuralFeature getLanguagesFeature(){
			return UMLPackage.eINSTANCE.getOpaqueBehavior_Language();
		}
		@Override
		public EStructuralFeature getBodiesFeature(){
			return UMLPackage.eINSTANCE.getOpaqueBehavior_Body();
		}
		public void setOclContext(final OpaqueBehavior vp){
			setOclContextImpl(vp, vp);
		}
		@Override
		protected EditingDomain getEditingDomain(){
			return OpaqueBehaviorBodySection.this.getEditingDomain();
		}
	}
	@Override
	protected void setOclContext(OclBodyComposite c){
		((OpaqueBehaviorBodyComposite)c).setOclContext((OpaqueBehavior) getEObject());
	}
	@Override
	protected OclBodyComposite createOclBodyComposite(Composite parent){
		return new OpaqueBehaviorBodyComposite(parent, getWidgetFactory());
	}
}
