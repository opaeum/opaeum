package org.nakeduml.annotation;
public enum UserInteractionKind {
	/**
	 * Used when a new instance of the entity needs to be created No menu will
	 * be displayed for the current entity, since the current instance of the
	 * entity is not yet persisted Possible Participations:
	 * REQUIRED,READWRITE,READONLY,HIDDEN The latter two would only be allowed
	 * for properties whose values can be derived from the current session
	 * state, which may vary from user to user Not allowed: NAVIGABLE
	 */
	CREATE,
	/**
	 * Used when an existing instance of the entity needs to be edited A menu
	 * will be displayed for the selected entity with the selected instance of
	 * the entity as implicit parent for all resulting user traversals of the
	 * feature tree Possible Participations: REQUIRED,READWRITE,READONLY,HIDDEN
	 * and NAVIGABLE In the case of NAVIGABLE features, menu items will be
	 * created for these features If the feature is a one-to-on component
	 * (compositional child) of the entity, a menuItem will be displayed TODO If
	 * the feature is a non-compositional one entity property, te entity's name
	 * should be displayed as a link to the entities' VIEW page. In such a case
	 * the property is considered readOnly
	 */
	EDIT,
	/**
	 * Used when an existing instance of the entity needs to be edited A menu
	 * will be displayed for the selected entity with the selected instance of
	 * the entity as implicit parent for all resulting user traversals of the
	 * feature tree Possible Participations: READONLY,HIDDEN and NAVIGABLE In
	 * the case of NAVIGABLE features, menu items will be created for these
	 * features If the feature is a one-to-on component (compositional child) of
	 * the entity, a menuItem will be displayed TODO If the feature is a
	 * non-compositional one entity property, te entity's name should be
	 * displayed as a link to allow the user to navigate out.
	 */
	VIEW,
	/**
	 * Used when displaying a list of instances of the selected entity No menu
	 * will be displayed for the selected entity, since no instance has been
	 * selected. The default UserInteraction used to open a selected entity
	 * would be EDIT" Possible Participations: READWRITE,READONLY,HIDDEN and
	 * NAVIGABLE REstrictions: NAVIGABLE is only allowed on property features
	 * with another entity as type. REQUIRED is considered too complicated to
	 * enforce TODO In the case of NAVIGABLE features, the feature's instance's
	 * name will be displayed as a link to the VIEW page of the entity
	 * 
	 */
	LIST,
	/**
	 * TODO Implement search screen This will be used on the lookup screen that
	 * is shown when a property has a lookupMode of "popup"
	 */
	CRITERIA,
	/**
	 * Used when displaying a list of instances of the selected entity in a
	 * reporting style. All links on entities from such a list will be to the
	 * VIEW page of the entity No menu will be displayed for the selected
	 * entity, since no instance has been selected. The default UserInteraction
	 * used to open a selected entity would be VIEW. Possible Participations:
	 * READONLY,HIDDEN and NAVIGABLE In the case of NAVIGABLE features, menu
	 * items will be created for these features If the feature is a one-to-on
	 * component (compositional child) of the entity, a menuItem will be
	 * displayed Follow the same semantics for REPORT1-3 TODO maybe we can allow
	 * editing, but no menu should be displayed for the entity being edited-it
	 * could break some encapsulation rules
	 */
	REPORT,
	AUDIT_LIST,
	AUDIT_FORM, PROCESS_FORM, USER_AUDIT_LIST,OPERATION_INVOCATION;

}
