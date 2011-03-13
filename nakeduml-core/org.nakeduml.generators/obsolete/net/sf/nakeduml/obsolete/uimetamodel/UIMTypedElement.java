package net.sf.nakeduml.obsolete.uimetamodel;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.nakeduml.annotation.LookupModeKind;
import org.nakeduml.annotation.UserInteractionKind;
public abstract class UIMTypedElement<PK extends Enum> extends UIMElement implements Serializable {
	private UIMClassifier baseType;
	private Double displayIndex;
	private boolean isOne;
	private SecureUserAction securityOnEdit = new SecureUserAction();
	private boolean isRequired;
	private SecureUserAction securityOnVisibility = new SecureUserAction();
	private String inputComponent;
	private String outputComponent;
	private LookupModeKind lookupMode;
	private String styleClassName;
	private boolean hasValidation;
	private String additionalAttributes;
	private String facesConverter;
	private String cappedJavaName;
	private Map<String, PK> propertyParticipations = new HashMap<String, PK>();
	public abstract UIMEntity getOwner();
	public UIMClassifier getBaseType() {
		return this.baseType;
	}
	public Double getDisplayIndex() {
		return this.displayIndex;
	}
	public boolean isBaseTypeDataType() {
		// TODO include normal datatype
		return this.baseType.isSimpleDataType();
	}
	public boolean isBaseTypeEntity() {
		return getBaseType() instanceof UIMEntity;
	}
	public boolean isBaseTypeEnum() {
		return this.baseType.isEnum();
	}
	public boolean isOne() {
		return this.isOne;
	}
	public SecureUserAction getSecurityOnEdit() {
		return this.securityOnEdit;
	}
	public boolean isMany() {
		return !this.isOne;
	}
	public boolean isRequired() {
		return this.isRequired;
	}
	public boolean hasValidation() {
		return this.hasValidation;
	}
	public SecureUserAction getSecurityOnVisibility() {
		return this.securityOnVisibility;
	}
	public String getFacesInputComponent() {
		return this.inputComponent;
	}
	public String getFacesOutputComponent() {
		return this.outputComponent;
	}
	public String getFacesAttributes() {
		return this.additionalAttributes;
	}
	public PK getParticipationIn(UserInteractionKind userInteraction) {
		if (this.propertyParticipations.containsKey(userInteraction.name())) {
			return this.propertyParticipations.get(userInteraction.name());
		} else {
			return null;
		}
	}
	public String getFacesConverter() {
		return this.facesConverter;
	}
	public String getStyleClassName() {
		return this.styleClassName;
	}
	public LookupModeKind getLookupMode() {
		return this.lookupMode;
	}
	public String getCappedJavaName() {
		return this.cappedJavaName;
	}
	public final Map<String, PK> getPropertyParticipations() {
		return this.propertyParticipations;
	}
	public final void setBaseType(UIMClassifier baseType) {
		this.baseType = baseType;
	}
	public final void setCappedJavaName(String cappedJavaName) {
		this.cappedJavaName = cappedJavaName;
	}
	public final void setDisplayIndex(Double displayIndex) {
		this.displayIndex = displayIndex;
	}
	public final void setFacesAttributes(String facesAttributes) {
		this.additionalAttributes = facesAttributes;
	}
	public final void setFacesConverter(String facesConverter) {
		this.facesConverter = facesConverter;
	}
	public final void setFacesInputComponent(String facesInputComponent) {
		this.inputComponent = facesInputComponent;
	}
	public final void setFacesOutputComponent(String facesOutputComponent) {
		this.outputComponent = facesOutputComponent;
	}
	public final void setHasValidation(boolean hasValidation) {
		this.hasValidation = hasValidation;
	}
	public final void setOne(boolean isOne) {
		this.isOne = isOne;
	}
	public final void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public final void setLookupMode(LookupModeKind lookupMode) {
		this.lookupMode = lookupMode;
	}
	public final void setStyleClassName(String styleClassName) {
		this.styleClassName = styleClassName;
	}
	public abstract String getMetaClass();
}
