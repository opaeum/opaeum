package net.sf.nakeduml.obsolete.uimetamodel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class UIMPackage extends UIMElement implements Serializable {
	private static final long serialVersionUID = -8413014968469840274L;
	private List<UIMPackage> subpackages = new ArrayList<UIMPackage>();
	private List<UIMEntity> entities = new ArrayList<UIMEntity>();
	private UIMPackage uimPackage;
	public final List<UIMEntity> getEntities() {
		return this.entities;
	}
	public final List<UIMPackage> getSubpackages() {
		return this.subpackages;
	}
	@Override
	public UIMElement getOwnerElement() {
		return this.uimPackage;
	}
	public String getPath() {
		if (this.uimPackage == null) {
			return "/" + getJavaName();
		} else {
			return this.uimPackage.getPath() + "/" + getJavaName();
		}
	}
}
