package net.sf.nakeduml.javametamodel.annotation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.utilities.JavaStringHelpers;
import net.sf.nakeduml.javametamodel.utilities.JavaUtil;

public class OJAnnotatedField extends OJField implements OJAnnotatedElement {
	Set<OJAnnotationValue> f_annotations = new HashSet<OJAnnotationValue>();

	public OJAnnotatedField(String string, OJPathName ojPathName) {
		this.setName(string);
		this.setType(ojPathName);
	}

	public OJAnnotatedField() {
		// TODO Auto-generated constructor stub
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
		StringBuffer sb = new StringBuffer();
		if (!getComment().equals("")) {
			sb.append("\t// ");
			sb.append(getComment());
			sb.append("\n");
		}
		if (getAnnotations().size() > 0) {
			sb.append(JavaStringHelpers.indent(JavaUtil.collectionToJavaString(getAnnotations(), "\n"), 0));
			sb.append("\n");
		}
		if (this.getOwner() != null) { // field is part of block statement
			sb.append(visToJava(this));
		}
		if (sb.length() > 0) {
			sb.append(' ');
		}
		sb.append(getType().getCollectionTypeName());
		sb.append(' ');
		sb.append(getName());
		if (getInitExp() != null && !getInitExp().equals("")) {
			sb.append(" = ");
			sb.append(getInitExp());
		}
		sb.append(';');
		return sb.toString();
	}

	public OJAnnotatedField getDeepCopy() {
		OJAnnotatedField copy = new OJAnnotatedField();
		copyDeepInfoInto(copy);
		return copy;
	}

	public void copyDeepInfoInto(OJAnnotatedField copy) {
		super.copyDeepInfoInto(copy);
		Set<OJAnnotationValue> annotations = getAnnotations();
		for (OJAnnotationValue ojAnnotationValue : annotations) {
			OJAnnotationValue copyAnnotation = ojAnnotationValue.getDeepCopy();
			copy.addAnnotationIfNew(copyAnnotation);
		}
	}

	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
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
