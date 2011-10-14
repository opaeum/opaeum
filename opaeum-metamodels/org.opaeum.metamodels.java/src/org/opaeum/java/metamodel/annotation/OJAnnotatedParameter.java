package org.opaeum.java.metamodel.annotation;

import java.util.HashSet;
import java.util.Set;

import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.utilities.JavaStringHelpers;
import org.opaeum.java.metamodel.utilities.JavaUtil;


public class OJAnnotatedParameter extends OJParameter implements OJAnnotatedElement {
	Set<OJAnnotationValue> f_annotations = new HashSet<OJAnnotationValue>();
	public OJAnnotatedParameter(String string, OJPathName ojPathName) {
		this.setName(string);
		this.setType(ojPathName);
	}
	public OJAnnotatedParameter() {
	}

	public Set<OJAnnotationValue> getAnnotations() {
		return f_annotations;
	}

	public boolean addAnnotationIfNew(OJAnnotationValue value) {
		return AnnotationHelper.maybeAddAnnotation(value, this);
	}

	public OJAnnotationValue putAnnotation(OJAnnotationValue value) {
		return AnnotationHelper.putAnnotation(value, this);
	}

	public OJAnnotationValue removeAnnotation(OJPathName type) {
		return AnnotationHelper.removeAnnotation(this, type);
	}

	@Override
	public String toJavaString() {
		StringBuilder sb = new StringBuilder();
		if (!getComment().equals("")) {
			sb.append("\t// ");
			sb.append(getComment());
			sb.append("\n");
		}
		if (isFinal) {
			sb.append("final ");
		}		
		if (getAnnotations().size() > 0) {
			sb.append(JavaStringHelpers.indent(JavaUtil.collectionToJavaString(getAnnotations(), " "), 0));
		}
		if (sb.length() > 0) {
			sb.append(' ');
		}
		sb.append(getType().getCollectionTypeName());
		sb.append(' ');
		sb.append(getName());
		return sb.toString();
	}

	public OJAnnotatedParameter getDeepCopy() {
		OJAnnotatedParameter copy = new OJAnnotatedParameter();
		copyDeepInfoInto(copy);
		return copy;
	}

	public void copyDeepInfoInto(OJAnnotatedParameter copy) {
		super.copyDeepInfoInto(copy);
		Set<OJAnnotationValue> annotations = getAnnotations();
		for (OJAnnotationValue ojAnnotationValue : annotations) {
			OJAnnotationValue copyAnnotation = ojAnnotationValue.getDeepCopy();
			copy.addAnnotationIfNew(copyAnnotation);
		}
	}

	public void renameAll(Set<OJPathName> renamePathNames, String newName) {
		super.renameAll(renamePathNames, newName);
		Set<OJAnnotationValue> annotations = getAnnotations();
		for (OJAnnotationValue ojAnnotationValue : annotations) {
			Set<OJPathName> usedTypes = ojAnnotationValue.getAllTypesUsed();
			for (OJPathName usedType : usedTypes) {
				usedType.renameAll(renamePathNames, newName);
			}
		}
	}

	public OJAnnotationValue findAnnotation(OJPathName path) {
		return AnnotationHelper.getAnnotation(this, path);
	}
}
