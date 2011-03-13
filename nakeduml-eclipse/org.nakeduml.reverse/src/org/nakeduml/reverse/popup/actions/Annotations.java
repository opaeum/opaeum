package org.nakeduml.reverse.popup.actions;

import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.IMemberValuePairBinding;

public class Annotations {

	public static IMemberValuePairBinding getAnnotationAttribute(IAnnotationBinding ann, String string) {
		IMemberValuePairBinding[] pairs = ann.getAllMemberValuePairs();
		for (IMemberValuePairBinding pair : pairs) {
			if (pair.getName().equals(string)) {
				return pair;
			}
		}
		return null;
	}

	public static IAnnotationBinding getAnnotationAttribute(String name, IAnnotationBinding[] anns) {
		IAnnotationBinding entity = null;
		for (IAnnotationBinding ann : anns) {
			if (ann.getAnnotationType().getQualifiedName().equals(name)) {
				entity = ann;
			}
		}
		return entity;
	}
}
