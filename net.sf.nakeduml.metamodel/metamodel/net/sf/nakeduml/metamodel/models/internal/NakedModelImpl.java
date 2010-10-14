package net.sf.nakeduml.metamodel.models.internal;
import net.sf.nakeduml.metamodel.core.internal.NakedPackageImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
public class NakedModelImpl extends NakedPackageImpl implements INakedModel {
	public static final String META_CLASS = "model";
	@Override
	public String getMetaClass() {
		return "model";
	}
	private static final long serialVersionUID = -8628461048243090233L;
	String viewPoint;
	public String getViewpoint() {
		return this.viewPoint;
	}
	public void setViewpoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}
}
