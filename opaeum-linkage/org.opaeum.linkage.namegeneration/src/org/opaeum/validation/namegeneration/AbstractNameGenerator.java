package org.opaeum.validation.namegeneration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementOwner;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.RootObject;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.IParameterOwner;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.bpm.EmbeddedTask;
import org.opaeum.visitor.NakedElementOwnerVisitor;

public abstract class AbstractNameGenerator extends NakedElementOwnerVisitor implements ITransformationStep{
	Set<Element> affectedElements;
	protected OpaeumConfig config;
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public Set<Element> getAffectedElements(){
		return affectedElements;
	}
	public void initialize(OpaeumConfig c){
		this.config = c;
		affectedElements = new HashSet<Element>();
	}
	@Override
	public void visitRecursively(ElementOwner o){
		if(shouldVisitRecursively(o)){
			super.visitRecursively(o);
			if(o instanceof Operation && ((Operation) o).getMessageStructure()!=null){
				visitRecursively(((Operation) o).getMessageStructure());
			}else if(o instanceof EmbeddedTask){
				visitRecursively(((EmbeddedTask) o).getMessageStructure());
			}else if(o instanceof CallAction && ((CallAction) o).getMessageStructure()!=null){
				visitRecursively(((CallAction) o).getMessageStructure());
			}else if(o instanceof StructuredActivityNode && ((StructuredActivityNode) o).getMessageStructure() != null){
				visitRecursively(((StructuredActivityNode) o).getMessageStructure());
			}
			// Some extra work required to ensure that a class and all its required elements have persistent names 
			//Primarily for derived elements
			if(o instanceof Property){
				Property p = (Property) o;
	
				if(p.getOtherEnd() != null && !hasName(p.getOtherEnd())){
					super.visitRecursively(p.getOtherEnd());
				}
				if(p.getAssociation() != null && !hasName( (Association) p.getAssociation())){
					super.visitRecursively((Element) p.getAssociation());
				}
			}else if(o instanceof Classifier){
				Classifier c = (Classifier) o;
				for(Property p:c.getEffectiveAttributes()){
					if(!hasName(p) && p.getOwner()!=o && p.getRootObject()!=c.getRootObject()){
						visitRecursively(p);
					}
				}
				for(Operation op:c.getEffectiveOperations()){
					if(!hasName(op) && op.getOwner()!=o && op.getRootObject()!=c.getRootObject()){
						visitRecursively(op);
					}
				}
				//TODO inherited states and actions?
				
			}

		}
	}
	//To prevent recursion
	protected abstract boolean hasName(Element p);
	protected boolean shouldVisitRecursively(ElementOwner o){
		if(o instanceof RootObject){
			return !(((RootObject) o).getStatus().isNamed());
		}else{
			return true;
		}
	}
	protected static ValueSpecification getTaggedValue(Element element,String...tags){
		try{
			Iterator<? extends InstanceSpecification> iter = element.getStereotypes().iterator();
			while(iter.hasNext()){
				InstanceSpecification is = iter.next();
				for(String tag:tags){
					if(is.hasValueForFeature(tag)){
						return is.getFirstValueFor(tag);
					}
				}
			}
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void maybeVisit(ElementOwner o,VisitSpec v){
		if(!(o instanceof Element && ((Element) o).isMarkedForDeletion())){
			super.maybeVisit(o, v);
		}
	}
	public void release(){
		super.release();
		this.affectedElements = null;
	}
}