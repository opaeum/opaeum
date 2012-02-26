package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfActivityUtil.TypeAndMultiplicity;
import org.opaeum.topcased.propertysections.ocl.OclBodyComposite;

public abstract class AttachedOpaqueBehaviorSection extends AbstractOclBodyBodySection implements EObjectNavigationSource{
	public final class OpaqueBehaviorBodyComposite extends OclBodyComposite{
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
		public void setOclContext(NamedElement vp, Behavior behavior){
			setOclContextImpl(vp, behavior);
			addVariables();
		}
		@Override
		protected void fireOclChanged(String text){
			Behavior tf = getBehavior();
			if(containsExpression(text)){
				if(tf == null){
					tf = createBehavior();
				}
				super.oclBodyOwner = tf;
				super.fireOclChanged(text);
			}else{
				if(tf != null){
					removeBehavior(tf);
				}
				super.oclBodyOwner = null;
			}
			decorator.refresh();
		}
		@Override
		protected EditingDomain getEditingDomain(){
			return AttachedOpaqueBehaviorSection.this.getEditingDomain();
		}
	}
	NavigationDecorator decorator = new NavigationDecorator(this);
	public AttachedOpaqueBehaviorSection(){
		super();
	}
	protected abstract void removeBehavior(Behavior tf);
	protected abstract void addVariables();
	protected abstract Behavior createBehavior();
	protected abstract Behavior getBehavior();
	@Override
	protected void setOclContext(OclBodyComposite c){
		((OpaqueBehaviorBodyComposite) c).setOclContext(getOclContext(), getBehavior());
	}
	protected abstract  NamedElement getOclContext();
	public int getOclCompositeHeight(){
		return 30;
	}
	@Override
	public CLabel getLabelCombo(){
		return label;
	}
	@Override
	public void refresh(){
		super.refresh();
		decorator.refresh();
		oclComposite.setEnabled(isRelationshipComplete());
	}
	public abstract boolean isRelationshipComplete();
	protected Parameter createParam(OpaqueBehavior behavior,TypeAndMultiplicity sourceType,String name,ParameterDirectionKind inLiteral){
		Parameter source = behavior.createOwnedParameter(name, sourceType.getType());
		source.setDirection(inLiteral);
		source.setIsOrdered(sourceType.isOrdered());
		source.setIsUnique(sourceType.isUnique());
		source.setIsOrdered(sourceType.isUnique());
		if(sourceType.isMany()){
			source.setUpper(-1);
		}
		return source;
	}
	@Override
	protected OclBodyComposite createOclBodyComposite(Composite parent){
		return new OpaqueBehaviorBodyComposite(parent, getWidgetFactory());
	}
}