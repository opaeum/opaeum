/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.edit;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.commands.CreateGraphNodeCommand;
import org.topcased.modeler.commands.GEFtoEMFCommandWrapper;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SemanticModelBridge;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.alldiagram.commands.ElementRestoreConnectionCommand;
import org.topcased.modeler.uml.alldiagram.policies.CommentLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.policies.ConstraintLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.utils.OffscreenEditPartFactory.CustomModeler;

/**
 * The Element object controller
 */
public abstract class ElementEditPart extends EMFGraphNodeEditPart {

	/**
	 * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public ElementEditPart(GraphNode obj) {
		super(obj);
	}

	@Override
	public void refresh() {
		super.refresh();
		boolean exist = false;
		EObject eObject = getEObject();
		if (eObject instanceof Element) {
			if (isPreferenceForStereotypeAsANoteSet()) {
				List<EObject> stereotypes = getAllAppliedStereotypes((Element) eObject);
				if (stereotypes != null && stereotypes.size() > 0
						&& getModel() instanceof GraphNode) {
					for (EObject child : ((GraphNode) getModel()).eContainer()
							.eContents()) {
						if (child instanceof GraphNode) {
							GraphNode graphNode = (GraphNode) child;
							SemanticModelBridge semanticModel = graphNode
									.getSemanticModel();
							if (semanticModel instanceof EMFSemanticModelBridge) {
								// Look if the stereotype note has already been
								// created
								if (stereotypes
										.contains(((EMFSemanticModelBridge) semanticModel)
												.getElement())) {
									exist = true;
								}
							}
						}
					}
					// If the note hasn't been created yet
					if (!exist) {
						createStereotypeNote();
					}
				}
			}
		}
	}

	/**
	 * Create a note that display all the attributes of all the element's pplied
	 * stereotypes
	 */
	private void createStereotypeNote() {
		EObject eObject = getEObject();
		if (eObject instanceof Element) {
			Element umlElement = (Element) eObject;
			EList<Stereotype> appliedStereotypes = umlElement
					.getAppliedStereotypes();
			// Check if the element has applied stereotypes
			if (appliedStereotypes != null && appliedStereotypes.size() > 0) {
				boolean hasAttribute = false;
				for (Stereotype s : appliedStereotypes) {
					EObject stereoAppl = umlElement.getStereotypeApplication(s);
					EList<EStructuralFeature> eAllAttributes = stereoAppl
							.eClass().getEAllStructuralFeatures();
					if (eAllAttributes != null && eAllAttributes.size() - 1 > 0) // Always
																					// have
																					// one
																					// structural
																					// feature
					{
						hasAttribute = true;
					}
				}
				// The note is displayed only if one of the applied stereotypes
				// have at least one attribute
				if (hasAttribute) {
					Modeler modelerEditor = ((ModelerGraphicalViewer) getViewer())
							.getModelerEditor();
					// umlElem.getStereotypeApplication(s)
					// disable this operation when an offscren editor is
					// laucnhed, the user needs to verify its diagram
					if (!(modelerEditor instanceof CustomModeler)
							&& modelerEditor != null) {
						modelerEditor
								.getEditingDomain()
								.getCommandStack()
								.execute(
										createGraphNode(
												modelerEditor,
												umlElement
														.getStereotypeApplication(appliedStereotypes
																.get(0)),
												(GraphNode) ((GraphNode) getModel())
														.eContainer()));
					}
				}
			}
		}
	}

	/**
	 * Check in preference if stereotype must be display as a note
	 */
	protected boolean isPreferenceForStereotypeAsANoteSet() {
		return getPreferenceStore().getBoolean(
				AllDiagramPreferenceConstants.STEREOTYPE_AS_A_NOTE);
	}

	private org.eclipse.emf.common.command.Command createGraphNode(
			Modeler modeler, EObject newElement, GraphNode parentGraphNode) {
		GraphNode attributeGraphNode = DiagramInterchangeFactory.eINSTANCE
				.createGraphNode();
		EMFSemanticModelBridge element = DiagramInterchangeFactory.eINSTANCE
				.createEMFSemanticModelBridge();
		element.setElement(newElement);
		element.setPresentation("default");
		attributeGraphNode.setSemanticModel(element);
		return new GEFtoEMFCommandWrapper(new CreateGraphNodeCommand(modeler
				.getRootEditPart().getViewer().getEditDomain(),
				attributeGraphNode, parentGraphNode, new Point(0, 0), null, 0));
	}

	/**
	 * Return all the applied stereotypes that have at least on attribute
	 * 
	 * @return
	 */
	private List<EObject> getAllAppliedStereotypes(Element elem) {
		List<EObject> stereotypes = new LinkedList<EObject>();
		EList<Stereotype> appliedStereotypes = elem.getAppliedStereotypes();
		// Check if the element has applied stereotypes
		if (appliedStereotypes != null && appliedStereotypes.size() > 0) {
			for (Stereotype s : appliedStereotypes) {
				EObject stereoAppl = elem.getStereotypeApplication(s);
				EList<EStructuralFeature> eAllAttributes = stereoAppl.eClass()
						.getEAllStructuralFeatures();
				if (eAllAttributes != null && eAllAttributes.size() - 1 > 0) // Always
																				// have
																				// one
																				// structural
																				// feature
				{
					stereotypes.add(stereoAppl);
				}
			}
		}

		return stereotypes;
	}

	/**
	 * Creates edit policies and associates these with roles <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ClassEditPolicyConstants.COMMENTLINK_EDITPOLICY,
				new CommentLinkEdgeCreationEditPolicy());

		installEditPolicy(ClassEditPolicyConstants.CONSTRAINTLINK_EDITPOLICY,
				new ConstraintLinkEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new ElementRestoreConnectionCommand(getHost());
					}
				});
	}

}