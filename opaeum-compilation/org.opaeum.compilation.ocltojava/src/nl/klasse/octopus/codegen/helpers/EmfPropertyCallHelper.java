package nl.klasse.octopus.codegen.helpers;

import org.eclipse.ocl.uml.FeatureCallExp;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.PropertyCallExp;
import org.eclipse.ocl.uml.VariableExp;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.opaeum.ocl.uml.EmulatedVariable;

public class EmfPropertyCallHelper{
	public static boolean resultsInMany(OCLExpression body){
		if(body instanceof PropertyCallExp){
			PropertyCallExp pce=(PropertyCallExp) body;
			if(pce.getReferredProperty().getQualifiers().size() > 0 && pce.getQualifier().isEmpty()){
				return true;
			}else if(pce.getSource() instanceof FeatureCallExp){
				return resultsInMany( (OCLExpression) pce.getSource());
			}
		}else if(body instanceof VariableExp){
			VariableExp ve=(VariableExp) body;
			if(ve.getReferredVariable() instanceof EmulatedVariable){
				EmulatedVariable ev=(EmulatedVariable) ve.getReferredVariable();
				if(ev.getOriginalElement() instanceof MultiplicityElement){
					MultiplicityElement me=(MultiplicityElement) ev.getOriginalElement();
					return me.isMultivalued();
				}
			}
		}
		return false;
	}
}
