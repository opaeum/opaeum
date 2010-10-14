package net.sf.nakeduml.javametamodel;

import java.util.ArrayList;
import java.util.Iterator;

import net.sf.nakeduml.javametamodel.generated.OJInterfaceGEN;
import net.sf.nakeduml.javametamodel.utilities.JavaStringHelpers;


public class OJInterface extends OJInterfaceGEN {
	/******************************************************
	 * The constructor for this classifier.
	*******************************************************/	
	public OJInterface() {
		super();
		setVisibility(OJVisibilityKind.PUBLIC);
	}

	public void calcImports() {
		super.calcImports(); // does operations
		// super interfaces
		Iterator it = getSuperInterfaces().iterator();
		while( it.hasNext()) {
			OJPathName intf = (OJPathName) it.next();
			this.addToImports(intf);
		}
	}
	
	public String toJavaString(){
		this.calcImports();
		StringBuffer classInfo = new StringBuffer();
		classInfo.append(getMyPackage().toJavaString());
		classInfo.append("\n");
		classInfo.append(imports());
		classInfo.append("\n");
		if (!getComment().equals("")){
			addJavaDocComment(classInfo);
		}
		if (this.isAbstract()) {
			classInfo.append("abstract ");
		}
		classInfo.append(visToJava(this) + " ");
		classInfo.append("interface " + getName());
		classInfo.append(superInterfaces());
		classInfo.append(" {\n");
		classInfo.append(JavaStringHelpers.indent(operations(),1));
		classInfo.append("\n");
		classInfo.append("}");
		return classInfo.toString();
	}
	
	private StringBuffer superInterfaces() {
		StringBuffer result = new StringBuffer();
		if (this.getSuperInterfaces().size() > 0) {
			Iterator it = this.getSuperInterfaces().iterator();
			boolean first = true;
			while (it.hasNext()) {
				OJPathName elem = (OJPathName) it.next();
				if (first) {
					result.append(" extends ");
					first = false;
				} else { 
					result.append(", ");
				}
				result.append(elem.getLast());
			}
		}
		return result;
	}
	
	public OJInterface getDeepCopy(OJPackage owner) {
		OJInterface copy = new OJInterface();
		copy.setMyPackage(owner);
		copyDeepInfoInto(copy);
		return copy;
	}
	protected void copyDeepInfoInto(OJInterface copy) {
		super.copyDeepInfoInto(copy);
		Iterator superInterfacesIt = new ArrayList<OJPathName>(getSuperInterfaces()).iterator();
		while ( superInterfacesIt.hasNext() ) {
			OJPathName elem = (OJPathName) superInterfacesIt.next();
			copy.addToSuperInterfaces(elem.getDeepCopy());
		}
	}

}