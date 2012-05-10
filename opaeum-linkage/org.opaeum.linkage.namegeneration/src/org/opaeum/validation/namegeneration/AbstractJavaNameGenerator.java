package org.opaeum.validation.namegeneration;

import java.util.HashSet;
import java.util.Set;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IModelElement;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityPartition;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedPackage;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.metamodel.profiles.INakedProfile;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.name.NameConverter;

public abstract class AbstractJavaNameGenerator extends AbstractNameGenerator{
	@Override
	protected boolean hasName(INakedElement p){
		return p.getMappingInfo().getJavaName() != null;
	}
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
			}else{
				name = NameConverter.capitalize(name);
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
					name = "RepresentsNothing" + element.getMappingInfo().getOpaeumId();
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
				name = "Timer" + element.getMappingInfo().getOpaeumId();
			}
		}else if(element instanceof INakedControlNode){
			if(element.getName() == null || element.getName().length() == 0){
				INakedControlNode node = (INakedControlNode) element;
				name = node.getControlNodeType().name() + node.getMappingInfo().getOpaeumId();
			}
		}else if(element instanceof INakedState){
			INakedState state = (INakedState) element;
			for(INakedState s:state.getStateMachine().getAllStates()){
				if(s.getName().equals(state.getName()) && s != state){
					name = state.getName() + state.getMappingInfo().getOpaeumId();
				}
			}
		}else if(element instanceof INakedAction){
			INakedAction action = (INakedAction) element;
			for(INakedActivityNode s:action.getActivity().getActivityNodesRecursively()){
				if(s instanceof INakedAction && s.getName().equals(action.getName()) && s != action){
					name = action.getName() + action.getMappingInfo().getOpaeumId();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < name.length();i++){
			char charAt = name.charAt(i);
			if(Character.isJavaIdentifierPart(charAt)){
				sb.append(charAt);
			}else{
				sb.append('_');
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
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static String packagePathname(INakedNameSpace p){
		if(p.isMarkedForDeletion()){
			return p.getMappingInfo().getQualifiedJavaName();
		}else{
			Set<String> keywords = new HashSet<String>();
			keywords.add("public");
			keywords.add("static");
			keywords.add("final");
			keywords.add("class");
			keywords.add("void");
			keywords.add("return");
			if(p instanceof INakedPackage){
				INakedPackage np = (INakedPackage) p;
				if(np.getMappedImplementationPackage() != null && np.getMappedImplementationPackage().trim().length() > 1){
					return np.getMappedImplementationPackage();
				}else if(np.isRootPackage() || p instanceof INakedModel || p instanceof INakedProfile || p.getParent() == null){
					return np.getName().toLowerCase();
				}
			}
			StringBuilder path = new StringBuilder();
			addParentsToPath(p, path);
			String lowerCase = p.getName().toLowerCase();
			if(keywords.contains(lowerCase)){
				path.append(lowerCase + "_");
			}else{
				path.append(lowerCase);
			}
			return path.toString();
		}
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static String classifierPathname(INakedClassifier classifier){
		if(classifier instanceof INakedClassifier && (classifier).getMappedImplementationType() != null){
			return classifier.getMappedImplementationType();
		}else if(classifier.isMarkedForDeletion()){
			return classifier.getMappingInfo().getQualifiedJavaName();
		}else{
			String path = packagePathname(classifier.getNameSpace());
			return path + "." + NameConverter.capitalize(classifier.getName());
		}
	}
	private static void addParentsToPath(INakedNameSpace c,StringBuilder path){
		INakedNameSpace parent = c.getParent();
		if(parent != null){
			if(parent instanceof INakedPackage && ((INakedPackage) parent).getMappedImplementationPackage() != null
					&& !((INakedPackage) parent).getMappedImplementationPackage().trim().isEmpty()){
				path.append(((INakedPackage) parent).getMappedImplementationPackage());
			}else{
				addParentsToPath(parent, path);
				path.append(parent.getName().toLowerCase());
			}
			path.append(".");
		}
	}
}
