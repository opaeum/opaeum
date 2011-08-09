package net.sf.nakeduml.seamgeneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionElement;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

import org.nakeduml.name.NameConverter;

public class UserInteractionElementVisitor extends VisitorAdapter<UserInteractionElement,UserInteractionWorkspace> implements TransformationStep{
	protected UserInteractionWorkspace workspace;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;
	public void initialize(UserInteractionWorkspace workspace,NakedUmlConfig config,TextWorkspace textWorkspace){
		this.workspace = workspace;
		this.config = config;
		this.textWorkspace = textWorkspace;
	}
	@Override
	public Collection<? extends UserInteractionElement> getChildren(UserInteractionElement root){
		return root.getOwnedElement();
	}

	protected List<ClassifierUserInteraction> findClassifierUserInteractionOfKind(UserInteractionElement root,ClassifierUserInteraction e,UserInteractionKind userInteractionKind){
		List<ClassifierUserInteraction> result = new ArrayList<ClassifierUserInteraction>();
		
		findClassifierUserInteractionOfKind(result, root, e, userInteractionKind);
		
		return result;
	}
	
	protected void findClassifierUserInteractionOfKind(List<ClassifierUserInteraction> result, UserInteractionElement root,ClassifierUserInteraction e,UserInteractionKind userInteractionKind){
		Set<UserInteractionElement> userInteractionElements = root.getOwnedElement();
		for (UserInteractionElement userInteractionElement : userInteractionElements) {
			if (userInteractionElement instanceof ClassifierUserInteraction) {
				ClassifierUserInteraction classifierUserInteraction = (ClassifierUserInteraction)userInteractionElement;
				if (classifierUserInteraction.getRepresentedElement().equals(e.getRepresentedElement()) && classifierUserInteraction.getUserInteractionKind()==userInteractionKind) {
					result.add(classifierUserInteraction);
				}
				findClassifierUserInteractionOfKind(result, userInteractionElement, e, userInteractionKind);
			} else {
				findClassifierUserInteractionOfKind(result, userInteractionElement, e, userInteractionKind);
			}
		}
	}
	
//	protected ClassifierUserInteraction findClassifierUserInteractionOfKind(UserInteractionElement root, DomainElement e, UserInteractionKind userInteractionKind){
//		ClassifierUserInteraction result = null;
//		Set<UserInteractionElement> userInteractionElements = root.getOwnedElement();
//		for (UserInteractionElement userInteractionElement : userInteractionElements) {
//			if (userInteractionElement instanceof ClassifierUserInteraction) {
//				ClassifierUserInteraction classifierUserInteraction = (ClassifierUserInteraction)userInteractionElement;
//				if (classifierUserInteraction.getRepresentedElement().equals(e) && classifierUserInteraction.getUserInteractionKind()==userInteractionKind) {
//					return classifierUserInteraction;
//				} else {
//					result = findClassifierUserInteractionOfKind(userInteractionElement, e, userInteractionKind);
//					if (result!=null) return result;
//				}
//			} else {
//				result = findClassifierUserInteractionOfKind(userInteractionElement, e, userInteractionKind);
//				if (result!=null) return result;
//			}
//		}
//		return result;
//	}
	
	
	protected List<String> resolveViewId(UserInteraction e,String extension){
		List<String> path = new ArrayList<String>();
		for (AbstractUserInteractionFolder folder : ((ClassifierUserInteraction)e).getFolder().getPathFromRoot()) {
			path.add(NameConverter.decapitalize(folder.getName()));
		}
		path.add(NameConverter.decapitalize(((ClassifierUserInteraction)e).getFolder().getName()));
		path.set(path.size()-1, NameConverter.decapitalize(e.getName()) + extension);
		return path;
	}
	protected String resolveFlattenedViewId(UserInteraction e,String extension){
		StringBuilder sb = new StringBuilder();
		for (String p : resolveViewId(e, extension)) {
			sb.append("/");
			sb.append(p);
		}
		return sb.toString();
	}
}
