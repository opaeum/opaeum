package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.emf.workspace.EmfWorkspace;

public class TypedElementPropertyBridge extends AbstractEmulatedProperty{
	private MultiplicityElement zeroToOne ;
	private TypedElement typedElement;
	@Deprecated
	public TypedElementPropertyBridge(Classifier owner,TypedElement originalElement, boolean ensureLocallyUniqueName){
		this(owner,originalElement);
	}
	public TypedElementPropertyBridge(Classifier owner,TypedElement originalElement){
		super(owner, originalElement);
		this.typedElement = originalElement;
	}
	public Type getType(){
		return typedElement.getType();
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
