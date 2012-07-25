package org.opaeum.validation.namegeneration;

import java.util.HashSet;
import java.util.Set;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IModelElement;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NameSpace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.State;
import org.opaeum.metamodel.bpm.EmbeddedSingleScreenTask;
import org.opaeum.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.name.NameConverter;

public abstract class AbstractJavaNameGenerator extends AbstractNameGenerator{
	@Override
	protected boolean hasName(Element p){
		return p.getMappingInfo().getJavaName() != null;
	}
	protected final NameWrapper generateJavaName(Element element){
		String name = element.getName();
		if(element instanceof Classifier){
			Classifier nc = (Classifier) element;
			if(nc.getCodeGenerationStrategy().isNone()){
				// Use the name of the mapped class in java
				name = generateQualifiedJavaName(nc);
				if(name.indexOf(".") > -1){
					name = name.substring(name.lastIndexOf(".") + 1);
				}
			}else{
				name = NameConverter.capitalize(name);
			}
		}else if(element instanceof Package){
			name = element.getName();
			Package np = (Package) element;
			if(np.getCodeGenerationStrategy().isNone()){
				name = generateQualifiedJavaName(np);
				if(name.indexOf(".") > -1){
					name = name.substring(name.lastIndexOf(".") + 1);
				}
			}
		}else if(element instanceof EnumerationLiteral){
			EnumerationLiteral nakedLiteral = ((EnumerationLiteral) element);
			// Octopus does this too
			name = nakedLiteral.getName().toUpperCase();
		}else if(element instanceof ActivityPartition){
			if(element.getName() == null || element.getName().length() == 0){
				ActivityPartition p = (ActivityPartition) element;
				if(p.getRepresents() == null){
					name = "RepresentsNothing" + element.getMappingInfo().getOpaeumId();
				}else{
					name = generateJavaName(p.getRepresents()).toString();
				}
			}
		}else if(element instanceof ActivityEdge){
			if(element.getName() == null || element.getName().length() == 0){
				ActivityEdge e = (ActivityEdge) element;
				name = "to" + generateJavaName(e.getTarget());
			}
		}else if(element instanceof NakedTimeEventImpl){
			if(element.getName() == null || element.getName().length() == 0){
				name = "Timer" + element.getMappingInfo().getOpaeumId();
			}
		}else if(element instanceof ControlNode){
			if(element.getName() == null || element.getName().length() == 0){
				ControlNode node = (ControlNode) element;
				name = node.getControlNodeType().name() + node.getMappingInfo().getOpaeumId();
			}
		}else if(element instanceof State){
			State state = (State) element;
			for(State s:state.getStateMachine().getAllStates()){
				if(s.getName().equals(state.getName()) && s != state){
					name = state.getName() + state.getMappingInfo().getOpaeumId();
				}
			}
		}else if(element instanceof Action){
			Action action = (Action) element;
			for(ActivityNode s:action.getActivity().getActivityNodesRecursively()){
				if(s instanceof Action && s.getName().equals(action.getName()) && s != action){
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
		if(me instanceof Package){
			Package nakedPackage = ((Package) me);
			generatedName = packagePathname(nakedPackage);
		}else if(me instanceof Classifier){
			Classifier nakedClassifier = (Classifier) me;
			generatedName = classifierPathname(nakedClassifier);
		}else if(me instanceof Operation){
			Operation oper = (Operation) me;
			// TODO support for mapping of Responsibilities
			// generatedName = type.getMappedImplementationType();
			String generatedQualifiedJavaName = generateQualifiedJavaName(oper.getOwner());
			// Always keep packages in lowercase
			generatedName = generatedQualifiedJavaName.toLowerCase() + "." + me.getName();
		}else if(me instanceof EmbeddedSingleScreenTask){
			EmbeddedSingleScreenTask action = (EmbeddedSingleScreenTask) me;
			// TODO support for mapping of Responsibilities, OpaqueActions or
			// OpaqueBehavior
			// generatedName = type.getMappedImplementationType();
			String generatedQualifiedJavaName = generateQualifiedJavaName(action.getMessageStructure().getNameSpace());
			// Always keep packages in lowercase
			generatedName = generatedQualifiedJavaName + "." + me.getName();
		}else if(me instanceof State){
			State state = (State) me;
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
	public static String packagePathname(NameSpace p){
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
			if(p instanceof Package){
				Package np = (Package) p;
				if(np.getMappedImplementationPackage() != null && np.getMappedImplementationPackage().trim().length() > 1){
					return np.getMappedImplementationPackage();
				}else if(np.isRootPackage() || p instanceof Model || p instanceof Profile || p.getParent() == null){
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
	public static String classifierPathname(Classifier classifier){
		if(classifier instanceof Classifier && (classifier).getMappedImplementationType() != null){
			return classifier.getMappedImplementationType();
		}else if(classifier.isMarkedForDeletion()){
			return classifier.getMappingInfo().getQualifiedJavaName();
		}else{
			String path = packagePathname(classifier.getNameSpace());
			return path + "." + NameConverter.capitalize(classifier.getName());
		}
	}
	private static void addParentsToPath(NameSpace c,StringBuilder path){
		NameSpace parent = c.getParent();
		if(parent != null){
			if(parent instanceof Package && ((Package) parent).getMappedImplementationPackage() != null
					&& !((Package) parent).getMappedImplementationPackage().trim().isEmpty()){
				path.append(((Package) parent).getMappedImplementationPackage());
			}else{
				addParentsToPath(parent, path);
				path.append(parent.getName().toLowerCase());
			}
			path.append(".");
		}
	}
}
