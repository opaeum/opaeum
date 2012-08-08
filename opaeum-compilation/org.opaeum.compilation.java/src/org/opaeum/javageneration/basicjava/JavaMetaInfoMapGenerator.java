package org.opaeum.javageneration.basicjava;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.DefaultOpaeumComparator;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
},after = {})
public class JavaMetaInfoMapGenerator extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	public static final String JAVA_META_INFO_MAP_SUFFIX = "JavaMetaInfoMap";
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	@VisitBefore
	public void visitWorkspace(EmfWorkspace ws){
		if(transformationContext.isIntegrationPhase()){
			createBasicMetaInfo(ws, ws.getPrimaryRootObjects(), JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
		}
	}
	@VisitBefore
	public void visitModel(Model m){
		if(!transformationContext.isIntegrationPhase()){
			Collection<Package> rootObjectsToImport = new TreeSet<Package>(new DefaultOpaeumComparator());
			rootObjectsToImport.addAll(EmfPackageUtil.getAllDependencies( m));
			rootObjectsToImport.remove(m);
			OJBlock initBlock = createBasicMetaInfo(m, rootObjectsToImport, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			for(Classifier c:getElementsOfType(Classifier.class, Arrays.asList(m))){
				if(isPersistent(c) || c instanceof Enumeration || c instanceof Signal || c instanceof Interface || EmfClassifierUtil.isSimpleType(c )){
					initBlock.addToStatements("putClass(" + ojUtil.classifierPathname(c) + ".class,\"" + EmfWorkspace.getId(c) + "\")");
					if(EmfElementFinder.getRootObject( c) == m){
						for(Operation o:c.getOperations()){
							initBlock.addToStatements("putMethod(" + ojUtil.classifierPathname(c) + ".class,\"" + EmfWorkspace.getId(m) + "\"," + EmfWorkspace.getOpaeumId(m) + "l)");
						}
					}
				}
			}
			for(Event e:getElementsOfType(Event.class, Collections.singletonList((Package) m))){
				if(e instanceof TimeEvent || e instanceof ChangeEvent){
					initBlock.addToStatements("putEventHandler(" + eventUtil.handlerPathName(e) + ".class,\"" + EmfWorkspace.getId(e) + "\")");
				}
			}
		}
	}
	private OJBlock createBasicMetaInfo(Element m,Collection<Package> allDependencies,JavaSourceFolderIdentifier sourceid){
		TreeSet<Package> treeSet = new TreeSet<Package>(new DefaultOpaeumComparator());
		treeSet.addAll(allDependencies);
		OJPathName pathName =  ojUtil.utilClass(m,JAVA_META_INFO_MAP_SUFFIX);
		OJClass mapClass = new OJAnnotatedClass(pathName.getLast());
		mapClass.setSuperclass(new OJPathName(JavaMetaInfoMap.class.getName()));
		findOrCreatePackage(pathName.getHead()).addToClasses(mapClass);
		super.createTextPath(mapClass, sourceid);
		mapClass.addToImports(IPersistentObject.class.getName());
		OJAnnotatedField instance = new OJAnnotatedField("INSTANCE", pathName);
		instance.setStatic(true);
		instance.setVisibility(OJVisibilityKind.PUBLIC);
		instance.setFinal(true);
		instance.setInitExp("new " + pathName.getLast() + "()");
		mapClass.addToFields(instance);
		OJConstructor constr = new OJConstructor();
		mapClass.addToConstructors(constr);
		OJBlock initBlock = constr.getBody();
		Set<String> ignore = new HashSet<String>();
		ignore.add("OpaeumSimpleTypes".toLowerCase());
		ignore.add("UMLPrimitiveTypes".toLowerCase());
		ignore.add("PrimitiveTypes".toLowerCase());
		ignore.add("JavaPrimitiveTypes".toLowerCase());
		ignore.add("OpaeumSimpleTypes".toLowerCase());
		for(Package ro:treeSet){
			if(ro instanceof Model && ( !(EmfPackageUtil.isLibrary( (Model) ro)) || EmfPackageUtil.isRegeneratingLibrary( ((Model) ro)))){
				initBlock.addToStatements("this.importMetaInfo(" + ojUtil.utilClass(ro, JAVA_META_INFO_MAP_SUFFIX)  + ".INSTANCE)");
			}
		}
		return initBlock;
	}

}
