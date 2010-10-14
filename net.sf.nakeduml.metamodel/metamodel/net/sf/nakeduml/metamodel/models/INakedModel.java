package net.sf.nakeduml.metamodel.models;
import net.sf.nakeduml.metamodel.core.INakedPackage;
public interface INakedModel extends INakedPackage {
	void setViewpoint(String s);
	String getViewpoint();
}
