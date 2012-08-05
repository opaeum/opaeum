package org.opaeum.javageneration.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.name.NameConverter;

public class JavaNameGenerator{
	protected boolean hasName(NamedElement p){
		return p.getName() != null;
	}
	protected final NameWrapper generateJavaName(NamedElement element){
		String name = element.getName();
		if(element instanceof Classifier){
			Classifier nc = (Classifier) element;
			if(EmfClassifierUtil.getCodeGenerationStrategy( nc).isNone()){
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
			if(EmfClassifierUtil.getCodeGenerationStrategy(np).isNone()){
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
					name = "RepresentsNothing" + EmfWorkspace.getOpaeumId(element);
				}else{
					name = generateJavaName((NamedElement) p.getRepresents()).toString();
				}
			}
		}else if(element instanceof ActivityEdge){
			if(element.getName() == null || element.getName().length() == 0){
				ActivityEdge e = (ActivityEdge) element;
				name = "to" + generateJavaName(e.getTarget());
			}
		}else if(element instanceof TimeEvent){
			if(element.getName() == null || element.getName().length() == 0){
				name = "Timer" + EmfWorkspace.getOpaeumId(element);
			}
		}else if(element instanceof ControlNode){
			if(element.getName() == null || element.getName().length() == 0){
				ControlNode node = (ControlNode) element;
				name = node.eClass().getName() + EmfWorkspace.getOpaeumId(node);
			}
		}else if(element instanceof State){
			State state = (State) element;
			for(State s:EmfStateMachineUtil.getAllStates(EmfStateMachineUtil.getStateMachine(state))){
				if(s.getName().equals(state.getName()) && s != state){
					name = state.getName() + EmfWorkspace.getOpaeumId(state);
				}
			}
		}else if(element instanceof Action){
			Action action = (Action) element;
			for(ActivityNode s:EmfActivityUtil.getActivityNodesRecursively(EmfActivityUtil.getContainingActivity(action))){
				if(s instanceof Action && s.getName().equals(action.getName()) && s != action){
					name = action.getName() + EmfWorkspace.getOpaeumId(action);
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
	protected final String generateQualifiedJavaName(NamedElement me){
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
			String generatedQualifiedJavaName = generateQualifiedJavaName((NamedElement) oper.getOwner());
			// Always keep packages in lowercase
			generatedName = generatedQualifiedJavaName.toLowerCase() + "." + me.getName();
		}else if(me instanceof OpaqueAction && EmfActionUtil.isSingleScreenTask((OpaqueAction) me)){
			OpaqueAction action = (OpaqueAction) me;
			// TODO support for mapping of Responsibilities, OpaqueActions or
			// OpaqueBehavior
			// generatedName = type.getMappedImplementationType();
			String generatedQualifiedJavaName = generateQualifiedJavaName(EmfActivityUtil.getNearestNodeContainer(action));
			// Always keep packages in lowercase
			generatedName = generatedQualifiedJavaName + "." + me.getName();
		}else if(me instanceof State){
			State state = (State) me;
			if(state.getContainer().getState() != null){
				generatedName = generateQualifiedJavaName(state.getContainer().getState());
			}else{
				generatedName = generateQualifiedJavaName(EmfStateMachineUtil.getStateMachine(state));
			}
			generatedName = generatedName + "." + generateJavaName(state);
		}else{
			// TODO for actions and valuespecs, maybe ensure that the owning
			// behavior is the direct java namespace
			generatedName = me.getQualifiedName().replace("::", ".");
		}
		generatedName = generatedName.trim();
		return generatedName;
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static String packagePathname(Namespace p){
		if(EmfElementUtil.isMarkedForDeletion(p)){
			return p.getQualifiedJavaName();
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
				if(EmfPackageUtil.hasMappedImplementationPackage(np)){
					return EmfPackageUtil.getMappedImplementationPackage(np);
				}else if(EmfPackageUtil.isRootPackage(np) || p instanceof Model || p instanceof Profile || p.getOwner() == null){
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
	public static String classifierPathname(NamedElement ne){
		if(ne instanceof Classifier){
			Classifier classifier = (Classifier) ne;
			if(EmfClassifierUtil.hasMappedImplementationType(classifier)){
				return EmfClassifierUtil.getMappedImplementationType(classifier);
			}
		}else if(EmfElementUtil.isMarkedForDeletion(ne)){
			return ne.getQualifiedJavaName();
		}else{
			String path = packagePathname((Namespace) EmfElementFinder.getContainer(ne));
			return path + "." + NameConverter.capitalize(ne.getName());
		}
	}
	private static void addParentsToPath(Namespace c,StringBuilder path){
		Namespace parent = (Namespace) c.getOwner();
		if(parent != null){
			if(parent instanceof Package && EmfPackageUtil.hasMappedImplementationPackage((Package) parent)){
				path.append(EmfPackageUtil.getMappedImplementationPackage(((Package) parent)));
			}else{
				addParentsToPath(parent, path);
				path.append(parent.getName().toLowerCase());
			}
			path.append(".");
		}
	}
}
