package net.sf.nakeduml.visitor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class AbstractOJVisitor extends VisitorAdapter<OJElement,OJPackage> {

	protected OJPackage copyJavaModel;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;
	protected Set<OJClass> persistentClasses;
	protected INakedModelWorkspace umlWorkspace;
	protected Map<String, OJPathName> classPathNames = new HashMap<String, OJPathName>();
	
	public void initialize(INakedModelWorkspace workspace, OJPackage copyJavaModel,NakedUmlConfig config,TextWorkspace textWorkspace, Set<OJClass> persistentClasses){
		this.copyJavaModel = copyJavaModel;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.persistentClasses = persistentClasses;
		this.umlWorkspace = workspace;
		for (OJClass clazz : persistentClasses) {
			this.classPathNames.put(clazz.getPathName().toJavaString() ,clazz.getPathName());
		}		
	}	

	public void initialize(OJPackage copyJavaModel,NakedUmlConfig config,TextWorkspace textWorkspace){
		this.copyJavaModel = copyJavaModel;
		this.config = config;
		this.textWorkspace = textWorkspace;
	}	
	
	@Override
	public Collection<? extends OJElement> getChildren(OJElement root){
		if(root instanceof OJPackage){
			Set<OJElement> results = new HashSet<OJElement>();
			OJPackage ojp = (OJPackage) root;
			results.addAll(ojp.getSubpackages());
			results.addAll(ojp.getInterfaces());
			results.addAll(ojp.getClasses());
			return results;
		}
		return Collections.emptySet();
	}	
	
	protected void createTextPath(OJClassifier c,Enum<?> id){
		try{
			OutputRoot outputRoot= config.getOutputRoot(id);
			SourceFolder or = textWorkspace.findOrCreateTextOutputRoot(outputRoot);
			List<String> names = c.getPathName().getHead().getNames();
			names.add(c.getName() + ".java");
			or.findOrCreateTextFile(names, new JavaTextSource(c));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}	

}
