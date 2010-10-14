package net.sf.nakeduml.javametamodel.annotation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.utilities.JavaStringHelpers;
import net.sf.nakeduml.javametamodel.utilities.JavaUtil;

public class OJAnnotatedInterface extends OJAnnotatedClass {
	private Set<OJPathName> superInterfaces = new HashSet<OJPathName>();

	@Override
	public String toJavaString() {
		this.calcImports();
		StringBuffer classInfo = new StringBuffer();
		classInfo.append(getMyPackage().toJavaString());
		classInfo.append("\n");
		classInfo.append(imports());
		classInfo.append("\n");
		addJavaDocComment(classInfo);
		if (getAnnotations().size() > 0) {
			classInfo.append(JavaStringHelpers.indent(JavaUtil.collectionToJavaString(getAnnotations(), "\n"), 0));
			classInfo.append("\n");
		}
		classInfo.append(visToJava(this) + " ");
		classInfo.append("interface " + getName());
		classInfo.append(superInterfaces());
		classInfo.append(" {\n");
		classInfo.append(JavaStringHelpers.indent(operations(), 1));
		classInfo.append("\n");
		classInfo.append("}");
		return classInfo.toString();
	}

	private StringBuffer superInterfaces() {
		StringBuffer result = new StringBuffer();
		if (this.getSuperInterfaces().size() > 0) {
			Iterator<OJPathName> it = this.getSuperInterfaces().iterator();
			boolean first = true;
			while (it.hasNext()) {
				OJPathName elem = it.next();
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

	@Override
	protected StringBuffer operations() {
		return super.operations();
	}

	public Set<OJPathName> getSuperInterfaces() {
		return superInterfaces;
	}

	public void addToSuperInterfaces(OJPathName i) {
		superInterfaces.add(i);
	}

	@Override
	public void calcImports() {
		super.calcImports();
		addToImports(getSuperInterfaces());
	}
	public OJAnnotatedInterface getDeepCopy(OJPackage owner) {
		OJAnnotatedInterface copy = new OJAnnotatedInterface();
		copy.setMyPackage(owner);
		copyDeepInfoInto(copy);
		return copy;
	}

	protected void copyDeepInfoInto(OJAnnotatedInterface copy) {
		super.copyDeepInfoInto(copy);
		for (OJPathName superInterfaze : superInterfaces) {
			copy.addToSuperInterfaces(superInterfaze.getDeepCopy());
		}
	}

	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
		super.renameAll(renamePathNames, newName);
		Set<OJPathName> superInterfaces = getSuperInterfaces();
		for (OJPathName ojPathName : superInterfaces) {
			ojPathName.renameAll(renamePathNames, newName);
		}
	}

	public void removeFromSuperInterfaces(OJPathName toRemove) {
		this.superInterfaces.remove(toRemove);

	}
}
