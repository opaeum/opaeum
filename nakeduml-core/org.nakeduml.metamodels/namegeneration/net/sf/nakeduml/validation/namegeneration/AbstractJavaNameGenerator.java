package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityPartition;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import net.sf.nakeduml.metamodel.profiles.INakedProfile;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IModelElement;

public abstract class AbstractJavaNameGenerator extends AbstractNameGenerator{
	protected final NameWrapper generateJavaName(INakedElement element){
		String name = element.getName();
		if(element instanceof INakedClassifier){
			INakedClassifier nc = (INakedClassifier) element;
			if(nc.getCodeGenerationStrategy().isNone()){
				// Use the name of the mapped class in java
				name = generateQualifiedJavaName(nc);
				if(name.indexOf(".") > -1){
					name = name.substring(name.lastIndexOf(".") + 1);
				}
			}
		}else if(element instanceof INakedPackage){
			name = element.getName();
			INakedPackage np = (INakedPackage) element;
			if(np.getCodeGenerationStrategy().isNone()){
				name = generateQualifiedJavaName(np);
				if(name.indexOf(".") > -1){
					name = name.substring(name.lastIndexOf(".") + 1);
				}
			}
		}else if(element instanceof INakedEnumerationLiteral){
			INakedEnumerationLiteral nakedLiteral = ((INakedEnumerationLiteral) element);
			// Octopus does this too
			name = nakedLiteral.getName().toUpperCase();
		}else if(element instanceof INakedActivityPartition){
			if(element.getName() == null || element.getName().length() == 0){
				INakedActivityPartition p = (INakedActivityPartition) element;
				if(p.getRepresents() == null){
					name = "RepresentsNothing" + element.getMappingInfo().getNakedUmlId();
				}else{
					name = generateJavaName(p.getRepresents()).toString();
				}
			}
		}else if(element instanceof INakedActivityEdge){
			if(element.getName() == null || element.getName().length() == 0){
				INakedActivityEdge e = (INakedActivityEdge) element;
				name = "to" + generateJavaName(e.getTarget());
			}
		}else if(element instanceof NakedTimeEventImpl){
			if(element.getName() == null || element.getName().length() == 0){
				name = "Timer" + element.getMappingInfo().getNakedUmlId();
			}
		}else if(element instanceof INakedControlNode){
			if(element.getName() == null || element.getName().length() == 0){
				INakedControlNode node = (INakedControlNode) element;
				name = node.getControlNodeType().name() + node.getMappingInfo().getNakedUmlId();
			}
		}else if(element instanceof INakedState){
			INakedState state = (INakedState) element;
			for(INakedState s:state.getStateMachine().getAllStates()){
				if(s.getName().equals(state.getName()) && s != state){
					name = state.getName() + state.getMappingInfo().getNakedUmlId();
				}
			}
		}else if(element instanceof INakedAction){
			INakedAction action = (INakedAction) element;
			for(INakedActivityNode s:action.getActivity().getActivityNodesRecursively()){
				if(s instanceof INakedAction && s.getName().equals(action.getName()) && s != action){
					name = action.getName() + action.getMappingInfo().getNakedUmlId();
				}
			}
		}
		return new SingularNameWrapper(name, null);
	}
	protected final String generateQualifiedJavaName(IModelElement me){
		String generatedName = null;
		if(me instanceof INakedPackage){
			INakedPackage nakedPackage = ((INakedPackage) me);
			generatedName = packagePathname(nakedPackage);
		}else if(me instanceof INakedClassifier){
			INakedClassifier nakedClassifier = (INakedClassifier) me;
			generatedName = classifierPathname(nakedClassifier);
		}else if(me instanceof INakedOperation){
			INakedOperation oper = (INakedOperation) me;
			// TODO support for mapping of Responsibilities
			// generatedName = type.getMappedImplementationType();
			String generatedQualifiedJavaName = generateQualifiedJavaName(oper.getOwner());
			// Always keep packages in lowercase
			generatedName = generatedQualifiedJavaName.toLowerCase() + "." + me.getName();
		}else if(me instanceof INakedEmbeddedSingleScreenTask){
			INakedEmbeddedSingleScreenTask action = (INakedEmbeddedSingleScreenTask) me;
			// TODO support for mapping of Responsibilities, OpaqueActions or
			// OpaqueBehavior
			// generatedName = type.getMappedImplementationType();
			String generatedQualifiedJavaName = generateQualifiedJavaName(action.getMessageStructure().getNameSpace());
			// Always keep packages in lowercase
			generatedName = generatedQualifiedJavaName + "." + me.getName();
		}else if(me instanceof INakedState){
			INakedState state = (INakedState) me;
			if(state.hasEnclosingState()){
				generatedName = generateQualifiedJavaName(state.getEnclosingState());
			}else{
				generatedName = generateQualifiedJavaName(state.getStateMachine());
			}
			generatedName = generatedName + "." + generateJavaName(state);
		}else{
			// TODO for actions and valuespecs, maybe ensure that the owning
			// behavior is the direct java namespace
			generatedName = pathname(me.getPathName());
		}
		generatedName = generatedName.trim();
		return generatedName;
	}
	private String pathname(PathName pathName){
		StringBuilder result = new StringBuilder();
		for(String s:pathName.getNames()){
			if(result.length() > 0){
				result.append('.');
			}
			result.append(s);
		}
		return result.toString();
	}
	/**
	 * A NakedUml specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static String packagePathname(INakedNameSpace p){
		if(p instanceof INakedPackage){
			INakedPackage np = (INakedPackage) p;
			if(np.getMappedImplementationPackage() != null){
				return np.getMappedImplementationPackage();
			}else if(np.isRootPackage() || p instanceof INakedModel || p instanceof INakedProfile || p.getParent() == null){
				return np.getName().toLowerCase();
			}
		}
		StringBuilder path = new StringBuilder();
		addParentsToPath(p, path);
		path.append(".");
		path.append(p.getName().toLowerCase());
		return path.toString();
	}
	/**
	 * A NakedUml specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static String classifierPathname(INakedClassifier classifier){
		if(classifier instanceof INakedClassifier && (classifier).getMappedImplementationType() != null){
			return classifier.getMappedImplementationType();
		}else{
			String path = packagePathname(classifier.getNameSpace());
			return path + "." + classifier.getName();
		}
	}
	private static void addParentsToPath(INakedNameSpace c,StringBuilder path){
		INakedNameSpace parent = c.getParent();
		if(parent != null){
			if(parent instanceof INakedPackage && ((INakedPackage) parent).getMappedImplementationPackage() != null){
				path.append(((INakedPackage) parent).getMappedImplementationPackage());
			}else{
				addParentsToPath(parent, path);
				path.append(".");
				path.append(parent.getName().toLowerCase());
			}
		}
	}
}
