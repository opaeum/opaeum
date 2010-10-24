package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityPartition;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedInputPinImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedObjectNodeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedOutputPinImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedPinImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedValuePinImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValuePin;


public abstract class AbstractActionExtractor extends CommonBehaviorExtractor{
	protected <T extends INakedPin>List<T> populatePins(Activity emfActivity,List emfArguments){
		List<T> nakedArguments = new ArrayList<T>();
		for(int i = 0;i < emfArguments.size();i++){
			Pin arg = (Pin) emfArguments.get(i);
			INakedPin nakedPin = initializePin(emfActivity, arg, null);
			nakedPin.setIndex(i);
			nakedArguments.add((T) nakedPin);
		}
		return nakedArguments;
	}
	protected void addLocalPreAndPostConditions(INakedAction nakedAction,Action emfAction){
		super.addConstraints(nakedAction, emfAction.getLocalPreconditions(), emfAction.getLocalPostconditions());
	}
	protected INakedPin initializePin(Activity activity,Pin emfPin,Classifier expectedType){
		// Pins belong to actions and should always be initialized as part of
		// the
		// initialization of the action
		NakedPinImpl resultingPin = null;
		if(emfPin instanceof ValuePin){
			ValuePin emfValuePin = (ValuePin) emfPin;
			NakedValuePinImpl nakedValuePin = new NakedValuePinImpl();
			initialize(nakedValuePin, emfPin, emfPin.getOwner());
			INakedClassifier nakedType = (INakedClassifier) getNakedPeer((emfPin.getType()));
			if(nakedType != null){
				nakedValuePin.setBaseType(nakedType);
			}else{
				nakedValuePin.setBaseType((INakedClassifier) getNakedPeer(expectedType));
			}
			resolveMultiplicityAndActualType(nakedValuePin, emfPin);
			INakedBehavior context = (INakedBehavior) getNakedPeer(activity);
			nakedValuePin.setValue(getValueSpecification(context, nakedValuePin,emfValuePin.getValue(), OclUsageType.INIT));
			resultingPin = nakedValuePin;
		}else if(emfPin instanceof InputPin){
			if(emfPin.getIncomings().isEmpty() && emfPin.getName() != null && emfPin.getName().length() > 0){
				// Fake valyue pin
				NakedValuePinImpl nakedValuePin = new NakedValuePinImpl();
				initialize(nakedValuePin, emfPin, emfPin.getOwner());
				INakedClassifier nakedType = (INakedClassifier) getNakedPeer((emfPin.getType()));
				if(nakedType != null){
					nakedValuePin.setBaseType(nakedType);
				}else{
					nakedValuePin.setBaseType((INakedClassifier) getNakedPeer(expectedType));
				}
				resolveMultiplicityAndActualType(nakedValuePin, emfPin);
				INakedValueSpecification valueSpecification = new NakedValueSpecificationImpl();
				ParsedOclString parsedOclString = new ParsedOclString(emfPin.getName(), OclUsageType.BODY);
				valueSpecification.setValue(parsedOclString);
				parsedOclString.setExpressionString(emfPin.getName());
				valueSpecification.initialize(nakedValuePin.getId() + "VS", emfPin.getName());
				valueSpecification.setOwnerElement(nakedValuePin);
				workspace.putModelElement(valueSpecification);
				nakedValuePin.setValue(valueSpecification);
				super.getErrorMap().linkElement(valueSpecification, emfPin);
				super.getErrorMap().linkElement(parsedOclString, emfPin);
				resultingPin = nakedValuePin;
			}else{
				resultingPin = new NakedInputPinImpl();
				initialize(resultingPin, emfPin, emfPin.getOwner());
				INakedClassifier nakedType = (INakedClassifier) getNakedPeer((emfPin.getType()));
				if(nakedType != null){
					resultingPin.setBaseType(nakedType);
				}else{
					resultingPin.setBaseType((INakedClassifier) getNakedPeer(expectedType));
				}
				resolveMultiplicityAndActualType(resultingPin, emfPin);
			}
		}else if(emfPin instanceof OutputPin){
			resultingPin = new NakedOutputPinImpl();
			initialize(resultingPin, emfPin, emfPin.getOwner());
			INakedClassifier nakedType = (INakedClassifier) getNakedPeer((emfPin.getType()));
			if(nakedType != null){
				resultingPin.setBaseType(nakedType);
			}else{
				resultingPin.setBaseType((INakedClassifier) getNakedPeer(expectedType));
			}
			resolveMultiplicityAndActualType(resultingPin, emfPin);
		}
		return resultingPin;
	}
	protected void resolveMultiplicityAndActualType(NakedPinImpl ae,Pin te){
		populateMultiplicityAndBaseType(te, te.getType(), ae);
	}
	protected void populateMultiplicityAndBaseType(MultiplicityElement emfNode,Type type,NakedObjectNodeImpl ae){
		ae.setMultiplicity(new NakedMultiplicityImpl(emfNode.getLower(), emfNode.getUpper() == -1 ? Integer.MAX_VALUE : emfNode.getUpper()));
		ae.setIsOrdered(emfNode.isOrdered());
		ae.setIsUnique(emfNode.isUnique());
		ae.setBaseType((INakedClassifier) getNakedPeer(type));
	}
	protected void assignPartition(INakedActivityNode node,ActivityNode emfNode){
		if(emfNode.getInPartitions().size() == 1){
			node.setInPartition(((INakedActivityPartition) getNakedPeer((emfNode.getInPartitions().get(0)))));
		}
	}
}
