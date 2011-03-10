/*
 * Created on Dec 30, 2003
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.helpers;

import java.util.Iterator;
import java.util.StringTokenizer;

import nl.klasse.octopus.OctopusConstants;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.internal.types.ClassifierImpl;
import nl.klasse.octopus.model.internal.types.InterfaceImpl;
import nl.klasse.tools.common.StringHelpers;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJInterface;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;

/**
 * GenerationHelpers : 
 */
public class GenerationHelpers {

	/**
	 * 
	 */
	public GenerationHelpers() {
		super();
	}
	
	/** If 'in' is part of a model that contains interfaces and a class implementing each interface in 
	 * an so-called 'internal' package, then the interface of 'in' is returned. Otherwise, the result is
	 * 'in' itself.
	 * 
	 * @param in
	 * @return
	 */
	static public IClassifier getFacade(IClassifier in) {
		if ((in instanceof ClassifierImpl) && ((ClassifierImpl)in).getFacade() != null ) {
			return ((ClassifierImpl)in).getFacade();
		} else {
			return in;
		}
	}

	/** Returns true if 'in' is part of a model that contains interfaces and a class implementing each 
	 * interface in an so-called 'internal' package.
	 * 
	 * @param in
	 * @return
	 */
	static public boolean hasFacade(IClassifier in) {
		if ((in instanceof ClassifierImpl) && ((ClassifierImpl)in).getFacade() != null ) {
			return true;
		} else {
			if (in.isCollectionKind()) {
				return hasFacade(((ICollectionType)in).getElementType());
			} else {
				return false;
			}
		}
	}
	
	/** Returns true if 'in' is part of a model that contains interfaces and a class implementing each 
	 * interface in an so-called 'internal' package, AND is 'in' is an interface that was not created by the user.
	 * 
	 * @param in
	 * @return
	 */
	static public boolean isFacade(IInterface in) {
		if (in instanceof InterfaceImpl) {
			return ((InterfaceImpl)in).isFacade();
		} else {
			return false;
		}
	}
	
	/** Returns the concat of 'projectName' and 'addition',
	 * in which both substrings start with an uppercase character.  
	 * Any '-' characters are removed. The substring following 
	 * the '-' starts with an uppercase character.
	 * 
	 * @param projectName
	 * @param addition
	 * @return
	 */
	static public String createClassName(String projectName, String addition) {
		String result = "";
		StringTokenizer st = new StringTokenizer(projectName, "-");
		while (st.hasMoreTokens()) {
			result = result + StringHelpers.firstCharToUpper(st.nextToken());
		}
		st = new StringTokenizer(addition, "-");
		while (st.hasMoreTokens()) {
			result = result + StringHelpers.firstCharToUpper(st.nextToken());
		}
		return result;
	}

	/** Returns the concat of 'I', 'name' and 'addition',
	 * in which both substrings start with an uppercase character.  
	 * Any '-' characters are removed. The substring following 
	 * the '-' starts with an uppercase character.
	 * 
	 * @param name
	 * @param addition
	 * @return
	 */
	static public String createInterfaceName(String name, String addition) {
		String result = "I";
		StringTokenizer st = new StringTokenizer(name, "-");
		while (st.hasMoreTokens()) {
			result = result + StringHelpers.firstCharToUpper(st.nextToken());
		}
		st = new StringTokenizer(addition, "-");
		while (st.hasMoreTokens()) {
			result = result + StringHelpers.firstCharToUpper(st.nextToken());
		}
		return result;
	}

	/** Returns the top superclass of 'myClass'. Only the first of any superclasses on the same
	 * level in the hierachy is taken into account, i.e. if 'myClass' has two superclasses X and Y, 
	 * X is chosen and its top superclass is returned.
	 * 
	 * @param javamodel
	 * @param myClass
	 */
	static public IClassifier findTopSuperClass(IClassifier myClass) {
		if (!myClass.getGeneralizations().isEmpty()) {
			IClassifier superCls = (IClassifier) myClass.getGeneralizations().get(0);
			if (superCls != null) {
				return findTopSuperClass(superCls); 
			}
		}
		return myClass;
	}
	
	/** Creates the package with path 'path' as subpackage of 'parent'.
	 * All packages that should be present according to 'path' are created,
	 * if they are not already present. The package with path 'path' is returned.
	 * @param parent
	 * @param path
	 * @return
	 */
	static public OJPackage createPackage(OJPackage parent, OJPathName path) {
		OJPackage pack = parent.findPackage(new OJPathName(path.getFirst()));
		if ( pack == null) {
			pack = new OJPackage();
			pack.setName(path.getFirst());
			parent.addToSubpackages(pack);			
		}
		if (path.getNames().size() > 1) {
			return createPackage(pack, path.getTail());
		} else {
			return pack;
		}
	}
	
	/** Returns the Java class that has been generated in 'javamodel' for 'umlCls'.
	 * 
	 * @param umlCls
	 * @param javamodel
	 * @return
	 */
	static public OJClass findOJClass(IClassifier umlCls, OJPackage javamodel) {
		OJPathName path = new ClassifierMap(umlCls).javaTypePath();
		OJClass ojCls = javamodel.findClass(path);
		return ojCls;
	}

	/** Returns the Java interface that has been generated in 'javamodel' for 'umlCls'.
	 * 
	 * @param umlCls
	 * @param javamodel
	 * @return
	 */
	static public OJInterface findOJInterface(IClassifier umlCls, OJPackage javamodel) {
		OJPathName path = new ClassifierMap(umlCls).javaTypePath();
		OJInterface ojIntf = javamodel.findInterface(path);
		return ojIntf;
	}

	/** Returns the Java class or interface that has been generated in 'javamodel' for 'umlCls'.
	 * 
	 * @param umlCls
	 * @param javamodel
	 * @return
	 */
	static public OJClassifier findOJIntfOrCls(IClassifier umlCls, OJPackage javamodel) {
		OJPathName path = new ClassifierMap(umlCls).javaTypePath();
		OJClassifier ojCls = javamodel.findIntfOrCls(path);
		return ojCls;
	}

	/** Returns the Java package that has been generated in 'javamodel' for 'umlCls'.
	 * 
	 * @param umlPack
	 * @param javamodel
	 * @return
	 */
	static public OJPackage findOJPackage(IPackage umlPack, OJPackage javamodel) {
		OJPathName path = new OJPathName();
		Iterator it = umlPack.getPathName().getNames().iterator();
		while( it.hasNext()){
			String n = (String) it.next();
			if (!n.equals(OctopusConstants.OCTOPUS_INVISIBLE_PACK_NAME)){
				path.addToNames(n);
			}
		}
		OJPackage ojPack = javamodel.findPackage(path);
		return ojPack;
	}

}
