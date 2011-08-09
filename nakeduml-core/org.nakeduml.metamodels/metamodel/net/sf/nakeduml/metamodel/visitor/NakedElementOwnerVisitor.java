package net.sf.nakeduml.metamodel.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.expressions.internal.types.PathName;

public class NakedElementOwnerVisitor extends VisitorAdapter<INakedElementOwner,INakedModelWorkspace>{
	protected INakedRootObject currentRootObject;
	protected TransformationContext transformationContext;
	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root){
		return root.getOwnedElements();
	}
	// TODO find a better place for this method
	protected PathName getPathNameInModel(INakedClassifier stereotype){
		List<String> names = new ArrayList<String>();
		names.add(stereotype.getName());
		INakedNameSpace p = stereotype.getNameSpace();
		while(p != null){
			names.add(0, p.getName());
			p = p.getNameSpace();
		}
		PathName pn = new PathName();
		for(String s:names){
			pn.addString(s);
		}
		return pn;
	}
	@Override
	public void visitRecursively(INakedElementOwner o){
		if(o instanceof INakedRootObject){
			INakedRootObject pkg = (INakedRootObject) o;
			this.currentRootObject = pkg;
		}
		super.visitRecursively(o);
	}
	@Override
	public void visitOnly(INakedElementOwner o){
		INakedElementOwner parent = o;
		while(parent instanceof INakedElement){
			if(parent instanceof INakedRootObject ){
				currentRootObject=(INakedRootObject) parent;
				break;
			}else{
				parent=((INakedElement) parent).getOwnerElement();
			}
		}
		super.visitOnly(o);
	}
	protected Collection<INakedRootObject> getModelInScope(){
		Set<INakedRootObject> result = new HashSet<INakedRootObject>(currentRootObject.getDependencies());
		result.add(currentRootObject);
		return result;
	}
	
}
