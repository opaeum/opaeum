package org.nakeduml.java.metamodel.annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.java.metamodel.OJElement;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.utilities.JavaStringHelpers;
import org.nakeduml.java.metamodel.utilities.JavaUtil;

public class OJAnnotatedPackageInfo extends OJElement implements OJAnnotatedElement {
	private OJPackage myPackage;
	Set<OJAnnotationValue> annotations = new HashSet<OJAnnotationValue>();
	public OJAnnotatedPackageInfo(String string) {
		setName(string);
	}
	public OJAnnotatedPackageInfo() {
		setName("");
	}

	public boolean addAnnotationIfNew(OJAnnotationValue value){
		return AnnotationHelper.maybeAddAnnotation(value, this);
	}
	public Set<OJAnnotationValue> getAnnotations() {
		return annotations;
	}
	public OJAnnotationValue putAnnotation(OJAnnotationValue value) {
		return AnnotationHelper.putAnnotation(value, this);
	}
	public OJAnnotationValue removeAnnotation(OJPathName type){
		return AnnotationHelper.removeAnnotation(this,type);
	}
	public String toJavaString() {
		StringBuilder sb = new StringBuilder();
		if (super.getComment() != null && super.getComment().length() > 0) {
			sb.append("/**\n");
			sb.append(super.getComment());
			sb.append("*/\n");
		}
		sb.append(JavaStringHelpers.indent(JavaUtil.collectionToJavaString(annotations, "\n"), 0));
		sb.append("\n");
		sb.append(getMyPackage().toJavaString());
		sb.append("\n");
		sb.append(JavaStringHelpers.indent(imports(), 0));
		return sb.toString();
	}
	private String imports() {
		StringBuilder sb = new StringBuilder();
		for (OJPathName path : getImports()) {
			if (getMyPackage().getPathName().equals(path.getHead())) {
				// already visible
			} else {
				sb.append("import " + path.toString() + ";\n");
			}
		}
		return sb.toString();
	}
	private Collection<OJPathName> getImports() {
		Collection<OJPathName> results = new HashSet<OJPathName>();
		for (OJAnnotationValue an : annotations) {
			results.addAll(an.getAllTypesUsed());
		}
		return results;
	}
	public OJAnnotatedPackageInfo getDeepCopy(OJAnnotatedPackageInfo owner) {
		OJAnnotatedPackageInfo copy = new OJAnnotatedPackageInfo(getName());
		copyDeepInfoInto(owner, copy);
		return copy;
	}
	protected void copyDeepInfoInto(OJAnnotatedPackageInfo owner, OJAnnotatedPackageInfo copy) {
		for (OJAnnotationValue annotation : this.annotations) {
			copy.addAnnotationIfNew(annotation.getDeepCopy());
		}
	}
	public OJAnnotationValue findAnnotation(OJPathName ojPathName) {
		return AnnotationHelper.getAnnotation(this, ojPathName);
	}
	public OJPackage getMyPackage(){
		return myPackage;
	}
	public void setMyPackage(OJPackage myPackage){
		this.myPackage = myPackage;
	}
}
