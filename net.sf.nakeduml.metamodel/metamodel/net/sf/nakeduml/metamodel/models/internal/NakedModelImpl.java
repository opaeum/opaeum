package net.sf.nakeduml.metamodel.models.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.NakedRootObjectImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
import nl.klasse.octopus.model.IImportedElement;

public class NakedModelImpl extends NakedRootObjectImpl implements INakedModel {
	public static final String META_CLASS = "model";
	private static final long serialVersionUID = -8628461048243090233L;
	String viewPoint;

	@Override
	public String getMetaClass() {
		return "model";
	}

	public String getViewpoint() {
		return this.viewPoint;
	}

	public void setViewpoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}

}
