/*
 * Created on Jun 4, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.othergenerators.visitors;

import java.util.Iterator;

import nl.klasse.octopus.codegen.helpers.GenerationHelpers;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.othergenerators.creators.MultCheckCreator;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IEnumerationType;
import nl.klasse.octopus.modelVisitors.DefaultPackageVisitor;

import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;


/**
 * AttributeAdder : 
 */
public class MultCheckGenerator extends DefaultPackageVisitor {
	private OJPackage javamodel = null;

	/**
	 * 
	 */
	public MultCheckGenerator(OJPackage javamodel) {
		super();
		this.javamodel = javamodel;
		OclUtilityCreator helper = new OclUtilityCreator(javamodel);
		helper.makeInvHelperClasses();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.modelVisitors.IPackageVisitor#class_Before(nl.klasse.octopus.model.IClassifier)
	 */
	public void class_Before(IClassifier in) {
		if (!(in instanceof IEnumerationType)) {
			OJPathName path = new ClassifierMap(in).javaTypePath();
			OJClassifier myOwner = javamodel.findIntfOrCls(path);
			if (myOwner != null) {
				MultCheckCreator maker = new MultCheckCreator();
				maker.createCheckOper(myOwner); 
				if (myOwner instanceof OJClass) {
					Iterator<?> it = in.getAttributes().iterator();
					while(it.hasNext()) {
						IAttribute attr = (IAttribute) it.next();
						maker.structuralfeature(attr);
					}
					it = in.getNavigations().iterator();
					while(it.hasNext()) {
						IAssociationEnd attr = (IAssociationEnd) it.next();
						maker.structuralfeature(attr);
					}
					if (GenerationHelpers.hasFacade(in)) {
						// TODO
//						maker.addToFacade();
					}
					maker.finishCheckOper(); 
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see nl.klasse.octopus.modelVisitors.IPackageVisitor#visitClasses()
	 */
	public boolean visitClasses() {
		return true;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.modelVisitors.IPackageVisitor#visitInterfaces()
	 */
	public boolean visitInterfaces() {
		return true;
	}
}
