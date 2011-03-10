package net.sf.nakeduml.obsolete.generator;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;

public class DefaultBundleGenerator extends VisitorAdapter<INakedElement,INakedPackage>{
	DefaultBundleMap defaultBundle = new DefaultBundleMap();
	@VisitBefore(matchSubclasses = true)
	public void attribute(IAttribute t){
		putHumanName(t);
	}
	private void putHumanName(IModelElement a){
		if(a instanceof INakedElement){
			IMappingInfo mi = ((INakedElement) a).getMappingInfo();
			if(mi.getNakedUmlId() != null){
				this.defaultBundle.put(mi.getQualifiedJavaName(), mi.getHumanName().toString());
				int nakedUmlId = mi.getNakedUmlId().intValue();
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void class_After(INakedClassifier c){
		putHumanName(c);
		if(c instanceof INakedEnumeration){
			List literals = ((INakedEnumeration) c).getLiterals();
			Iterator iter = literals.iterator();
			while(iter.hasNext()){
				INakedEnumerationLiteral l = (INakedEnumerationLiteral) iter.next();
				putHumanName(l);
			}
		}else if(c instanceof INakedStateMachine){
			Collection<INakedState> literals = ((INakedStateMachine) c).getAllStates();
			Iterator<INakedState> iter = literals.iterator();
			while(iter.hasNext()){
				INakedState l = iter.next();
				putHumanName(l);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void navigation(IAssociationEnd end){
		putHumanName(end);
	}
	@VisitBefore(matchSubclasses = true)
	public void operation_After(IOperation o){
		putHumanName(o);
	}
	@VisitBefore(matchSubclasses = true)
	public void parameter(IParameter o){
		putHumanName(o);
	}
	public void store(File root){
		this.defaultBundle.store(root);
	}
	public DefaultBundleMap getDefaultBundle(){
		return this.defaultBundle;
	}
	@Override
	public Collection<? extends INakedElement> getChildren(INakedElement root){
		return root.getOwnedElements();
	}
}
