package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.ocl.uml.OpaqueExpressionContext;

public class TypedElementPropertyBridge extends AbstractEmulatedProperty{
	private MultiplicityElement zeroToOne ;
	private TypedElement typedElement;
	private IPropertyEmulation emulation;
	@Deprecated
	public TypedElementPropertyBridge(Classifier owner,TypedElement originalElement, boolean ensureLocallyUniqueName){
		this(owner,originalElement,null);
	}
	public TypedElementPropertyBridge(Classifier owner,TypedElement originalElement, IPropertyEmulation pe){
		super(owner, originalElement);
		this.typedElement = originalElement;
		this.emulation=pe;
	}
	public Type getType(){
		Type type = typedElement.getType();
		if(type==null && typedElement instanceof ObjectNode){
			type=EmfActionUtil.calculateType((ObjectNode) typedElement);
			if(type ==null  && typedElement instanceof ValuePin && ((ValuePin)typedElement).getValue() instanceof OpaqueExpression){
				OpaqueExpressionContext exp = emulation.getOclExpressionContext((OpaqueExpression) ((ValuePin)typedElement).getValue());
				if(!exp.hasErrors()){
					type=exp.getExpression().getType();
				}
			}
		}

		return type;
	}
	public boolean isOrdered(){
		return getMultiplicityElement().isOrdered();
	}
	public boolean isUnique(){
		return getMultiplicityElement().isUnique();
	}
	public int getUpper(){
		return getMultiplicityElement().getUpper();
	}
	public ValueSpecification getUpperValue(){
		return getMultiplicityElement().getUpperValue();
	}
	public boolean isMultivalued(){
		return getMultiplicityElement().isMultivalued();
	}
	public boolean includesCardinality(int C){
		return getMultiplicityElement().includesCardinality(C);
	}
	public boolean includesMultiplicity(MultiplicityElement M){
		return getMultiplicityElement().includesMultiplicity(M);
	}
	public int lowerBound(){
		return getMultiplicityElement().lowerBound();
	}
	public int upperBound(){
		return getMultiplicityElement().upperBound();
	}
	public boolean is(int lowerbound,int upperbound){
		return getMultiplicityElement().is(lowerbound, upperbound);
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(originalElement) + "@TEPB";
	}
	@Override
	public boolean shouldEmulate(){
		return true;
	}
	MultiplicityElement getMultiplicityElement(){
		if(typedElement instanceof MultiplicityElement){
			return (MultiplicityElement) typedElement;
		}else if(typedElement instanceof ActivityParameterNode && ((ActivityParameterNode) typedElement).getParameter()!=null){
			return ((ActivityParameterNode) typedElement).getParameter();
		}else{
			if(zeroToOne==null){
				zeroToOne=new EmulatedMultiplicityElement(originalElement, 0, 1);
			}
			return zeroToOne;
		}
	}
}
