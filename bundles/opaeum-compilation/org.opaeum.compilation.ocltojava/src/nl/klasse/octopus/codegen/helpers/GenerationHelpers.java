package nl.klasse.octopus.codegen.helpers;

import java.util.StringTokenizer;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJInterface;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class GenerationHelpers{
	OJUtil ojUtil;
	public GenerationHelpers(OJUtil ojUtil){
		this.ojUtil=ojUtil;
	}
	/**
	 * Returns the concat of 'projectName' and 'addition', in which both substrings start with an uppercase character. Any '-' characters are
	 * removed. The substring following the '-' starts with an uppercase character.
	 */
	static public String createClassName(String projectName,String addition){
		String result = "";
		StringTokenizer st = new StringTokenizer(projectName, "-");
		while(st.hasMoreTokens()){
			result = result + NameConverter.capitalize(st.nextToken());
		}
		st = new StringTokenizer(addition, "-");
		while(st.hasMoreTokens()){
			result = result + NameConverter.capitalize(st.nextToken());
		}
		return result;
	}
	/**
	 * Returns the concat of 'I', 'name' and 'addition', in which both substrings start with an uppercase character. Any '-' characters are
	 * removed. The substring following the '-' starts with an uppercase character.
	 * 
	 * @param name
	 * @param addition
	 * @return
	 */
	static public String createInterfaceName(String name,String addition){
		String result = "I";
		StringTokenizer st = new StringTokenizer(name, "-");
		while(st.hasMoreTokens()){
			result = result + NameConverter.capitalize(st.nextToken());
		}
		st = new StringTokenizer(addition, "-");
		while(st.hasMoreTokens()){
			result = result + NameConverter.capitalize(st.nextToken());
		}
		return result;
	}
	/**
	 * Returns the top superclass of 'myClass'. Only the first of any superclasses on the same level in the hierachy is taken into account,
	 * i.e. if 'myClass' has two superclasses X and Y, X is chosen and its top superclass is returned.
	 * 
	 * @param javamodel
	 * @param myClass
	 */
	static public Classifier findTopSuperClass(Classifier myClass){
		if(!myClass.getGeneralizations().isEmpty()){
			Classifier superCls = (Classifier) myClass.getGeneralizations().get(0);
			if(superCls != null){
				return findTopSuperClass(superCls);
			}
		}
		return myClass;
	}
	static public OJPackage createPackage(OJPackage parent,OJPathName path){
		OJPackage pack = parent.findPackage(new OJPathName(path.getFirst()));
		if(pack == null){
			pack = new OJPackage(path.getFirst());
			parent.addToSubpackages(pack);
		}
		if(path.getNames().size() > 1){
			return createPackage(pack, path.getTail());
		}else{
			return pack;
		}
	}
	/**
	 * Returns the Java class that has been generated in 'javamodel' for 'umlCls'.
	 * 
	 * @param umlCls
	 * @param javamodel
	 * @return
	 */
	 public OJClass findOJClass(Classifier umlCls,OJPackage javamodel){
		OJPathName path = ojUtil.buildClassifierMap(umlCls).javaTypePath();
		OJClass ojCls = javamodel.findClass(path);
		return ojCls;
	}
	/**
	 * Returns the Java interface that has been generated in 'javamodel' for 'umlCls'.
	 * 
	 * @param umlCls
	 * @param javamodel
	 * @return
	 */
	 public OJInterface findOJInterface(Classifier umlCls,OJPackage javamodel){
		OJPathName path = ojUtil.buildClassifierMap(umlCls).javaTypePath();
		OJInterface ojIntf = javamodel.findInterface(path);
		return ojIntf;
	}
	/**
	 * Returns the Java class or interface that has been generated in 'javamodel' for 'umlCls'.
	 * 
	 * @param umlCls
	 * @param javamodel
	 * @return
	 */
	 public OJClassifier findOJIntfOrCls(Classifier umlCls,OJPackage javamodel){
		OJPathName path = ojUtil.buildClassifierMap(umlCls).javaTypePath();
		OJClassifier ojCls = javamodel.findIntfOrCls(path);
		return ojCls;
	}
}
