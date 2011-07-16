package org.nakeduml.topcased.classdiagram;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLPackage;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EdgeObjectOffset;
import org.topcased.modeler.di.model.EdgeObjectUV;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.DynamicInstanceEditPartController;
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.alldiagram.AllEdgeObjectConstants;
import org.topcased.modeler.uml.alldiagram.ExactUMLSwitch;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.classdiagram.ClassEdgeObjectConstants;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;

public class ClassCreationUtils extends AbstractCreationUtils{
	private static final int LABEL_OFFSET = 10;
	public ClassCreationUtils(DiagramGraphConf diagramConf){
		super(diagramConf);
	}
	private class GraphicUMLSwitch extends ExactUMLSwitch{
		private final String presentation;
		public GraphicUMLSwitch(String presentation){
			this.presentation = presentation;
		}
		@Override
		public Object casePackage(org.eclipse.uml2.uml.Package object){
			if("default".equals(presentation)){
				return createGraphElementPackage(object, presentation);
			}
			return null;
		}
		@Override
		public Object caseComponent(Component object){
			return caseClass(object);
		}
		@Override
		public Object caseStateMachine(StateMachine object){
			return caseClass(object);
		}
		@Override
		public Object caseActivity(Activity object){
			return caseClass(object);
		}
		@Override
		public Object caseClass(org.eclipse.uml2.uml.Class object){
			if("default".equals(presentation)){
				return createGraphElementClass(object, presentation);
			}
			return null;
		}
		@Override
		public Object caseDataType(org.eclipse.uml2.uml.DataType object){
			if("default".equals(presentation)){
				return createGraphElementDataType(object, presentation);
			}
			return null;
		}
		@Override
		public Object caseInterface(org.eclipse.uml2.uml.Interface object){
			if("default".equals(presentation)){
				return createGraphElementInterface(object, presentation);
			}
			return null;
		}
		@Override
		public Object caseOperation(org.eclipse.uml2.uml.Operation object){
			if("default".equals(presentation)){
				return createGraphElementOperation(object, presentation);
			}
			return null;
		}
		@Override
		public Object caseProperty(org.eclipse.uml2.uml.Property object){
			if("default".equals(presentation)){
				return createGraphElementProperty(object, presentation);
			}
			return null;
		}
		@Override
		public Object caseInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification object){
			if("default".equals(presentation)){
				return createGraphElementInstanceSpecification(object, presentation);
			}else if("link".equals(presentation)){
				// Special case to create a link instead of a box
				return createGraphElementInstanceSpecificationLink(object, presentation);
			}
			return null;
		}
		@Override
		public Object caseSlot(org.eclipse.uml2.uml.Slot object){
			if("default".equals(presentation)){
				return createGraphElementSlot(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseEnumeration(org.eclipse.uml2.uml.Enumeration)
		 * @generated
		 */
		@Override
		public Object caseEnumeration(org.eclipse.uml2.uml.Enumeration object){
			if("default".equals(presentation)){
				return createGraphElementEnumeration(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral)
		 * @generated
		 */
		@Override
		public Object caseEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral object){
			if("default".equals(presentation)){
				return createGraphElementEnumerationLiteral(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#casePrimitiveType(org.eclipse.uml2.uml.PrimitiveType)
		 * @generated
		 */
		@Override
		public Object casePrimitiveType(org.eclipse.uml2.uml.PrimitiveType object){
			if("default".equals(presentation)){
				return createGraphElementPrimitiveType(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAssociation(org.eclipse.uml2.uml.Association)
		 * @generated
		 */
		@Override
		public Object caseAssociation(org.eclipse.uml2.uml.Association object){
			if("default".equals(presentation)){
				return createGraphElementAssociation(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAssociationClass(org.eclipse.uml2.uml.AssociationClass)
		 * @generated
		 */
		@Override
		public Object caseAssociationClass(org.eclipse.uml2.uml.AssociationClass object){
			if("default".equals(presentation)){
				return createGraphElementAssociationClass(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseGeneralization(org.eclipse.uml2.uml.Generalization)
		 * @generated
		 */
		@Override
		public Object caseGeneralization(org.eclipse.uml2.uml.Generalization object){
			if("default".equals(presentation)){
				return createGraphElementGeneralization(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization)
		 * @generated
		 */
		@Override
		public Object caseInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization object){
			if("default".equals(presentation)){
				return createGraphElementInterfaceRealization(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.topcased.modeler.uml.alldiagram.ExactUMLSwitch#caseTemplateBinding(org.eclipse.uml2.uml.TemplateBinding)
		 */
		public Object caseTemplateBinding(org.eclipse.uml2.uml.TemplateBinding object){
			if("default".equals(presentation)){
				return createGraphElementTemplateBinding(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.topcased.modeler.uml.alldiagram.ExactUMLSwitch#caseRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature)
		 */
		public Object caseTemplateSignature(org.eclipse.uml2.uml.TemplateSignature object){
			return createGraphElementTemplateSignature(object, presentation);
		}
		/**
		 * @see org.topcased.modeler.uml.alldiagram.ExactUMLSwitch#caseRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature)
		 */
		public Object caseRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature object){
			return createGraphElementRedefinableTemplateSignature(object, presentation);
		}
		/**
		 * @see org.topcased.modeler.uml.alldiagram.ExactUMLSwitch#caseClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter)
		 */
		public Object caseClassifierTemplateParameter(ClassifierTemplateParameter object){
			return createGraphElementClassifierTemplateParameter(object, presentation);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDependency(org.eclipse.uml2.uml.Dependency)
		 * @generated
		 */
		@Override
		public Object caseDependency(org.eclipse.uml2.uml.Dependency object){
			if("default".equals(presentation)){
				return createGraphElementDependency(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseUsage(org.eclipse.uml2.uml.Usage)
		 * @generated
		 */
		@Override
		public Object caseUsage(org.eclipse.uml2.uml.Usage object){
			if("default".equals(presentation)){
				return createGraphElementUsage(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackageImport(org.eclipse.uml2.uml.PackageImport)
		 * @generated
		 */
		@Override
		public Object casePackageImport(org.eclipse.uml2.uml.PackageImport object){
			if("default".equals(presentation)){
				return createGraphElementPackageImport(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackageMerge(org.eclipse.uml2.uml.PackageMerge)
		 * @generated
		 */
		@Override
		public Object casePackageMerge(org.eclipse.uml2.uml.PackageMerge object){
			if("default".equals(presentation)){
				return createGraphElementPackageMerge(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseComment(org.eclipse.uml2.uml.Comment)
		 * @generated
		 */
		@Override
		public Object caseComment(org.eclipse.uml2.uml.Comment object){
			if("default".equals(presentation)){
				return createGraphElementComment(object, presentation);
			}
			return null;
		}
		/**
		 * Add Constraint to requirement diagram
		 */
		@Override
		public Object caseConstraint(org.eclipse.uml2.uml.Constraint object){
			return createGraphElementConstraint(object, presentation);
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSignal(org.eclipse.uml2.uml.Signal)
		 * @generated
		 */
		public Object caseSignal(org.eclipse.uml2.uml.Signal object){
			if("default".equals(presentation)){
				return createGraphElementSignal(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseReception(org.eclipse.uml2.uml.Reception)
		 * @generated
		 */
		public Object caseReception(org.eclipse.uml2.uml.Reception object){
			if("default".equals(presentation)){
				return createGraphElementReception(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent)
		 * @generated
		 */
		public Object caseExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent object){
			if("default".equals(presentation)){
				return createGraphElementExecutionEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseCreationEvent(org.eclipse.uml2.uml.CreationEvent)
		 * @generated
		 */
		public Object caseCreationEvent(org.eclipse.uml2.uml.CreationEvent object){
			if("default".equals(presentation)){
				return createGraphElementCreationEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDestructionEvent(org.eclipse.uml2.uml.DestructionEvent)
		 * @generated
		 */
		public Object caseDestructionEvent(org.eclipse.uml2.uml.DestructionEvent object){
			if("default".equals(presentation)){
				return createGraphElementDestructionEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent)
		 * @generated
		 */
		public Object caseSendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent object){
			if("default".equals(presentation)){
				return createGraphElementSendOperationEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSendSignalEvent(org.eclipse.uml2.uml.SendSignalEvent)
		 * @generated
		 */
		public Object caseSendSignalEvent(org.eclipse.uml2.uml.SendSignalEvent object){
			if("default".equals(presentation)){
				return createGraphElementSendSignalEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent)
		 * @generated
		 */
		public Object caseReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent object){
			if("default".equals(presentation)){
				return createGraphElementReceiveOperationEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseReceiveSignalEvent(org.eclipse.uml2.uml.ReceiveSignalEvent)
		 * @generated
		 */
		public Object caseReceiveSignalEvent(org.eclipse.uml2.uml.ReceiveSignalEvent object){
			if("default".equals(presentation)){
				return createGraphElementReceiveSignalEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseCallEvent(org.eclipse.uml2.uml.CallEvent)
		 * @generated
		 */
		public Object caseCallEvent(org.eclipse.uml2.uml.CallEvent object){
			if("default".equals(presentation)){
				return createGraphElementCallEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseChangeEvent(org.eclipse.uml2.uml.ChangeEvent)
		 * @generated
		 */
		public Object caseChangeEvent(org.eclipse.uml2.uml.ChangeEvent object){
			if("default".equals(presentation)){
				return createGraphElementChangeEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAnyReceiveEvent(org.eclipse.uml2.uml.AnyReceiveEvent)
		 * @generated
		 */
		public Object caseAnyReceiveEvent(org.eclipse.uml2.uml.AnyReceiveEvent object){
			if("default".equals(presentation)){
				return createGraphElementAnyReceiveEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSignalEvent(org.eclipse.uml2.uml.SignalEvent)
		 * @generated
		 */
		public Object caseSignalEvent(org.eclipse.uml2.uml.SignalEvent object){
			if("default".equals(presentation)){
				return createGraphElementSignalEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseTimeEvent(org.eclipse.uml2.uml.TimeEvent)
		 * @generated
		 */
		public Object caseTimeEvent(org.eclipse.uml2.uml.TimeEvent object){
			if("default".equals(presentation)){
				return createGraphElementTimeEvent(object, presentation);
			}
			return null;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		@Override
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.modeler.editor.ICreationUtils#createGraphElement(org.eclipse.emf.ecore.EObject, java.lang.String)
	 * @generated
	 */
	public GraphElement createGraphElement(EObject obj,String presentation){
		Object graphElt = null;
		if(UMLPlugin.UML_URI.equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicUMLSwitch(presentation).doSwitch(obj);
		}else{
			// Use to dran'n drop custom element defined in
			// the extension point org.topcased.modeler.customEditPart
			graphElt = DynamicInstanceEditPartController.instance.createGraphElement(obj, presentation);
		}
		if(graphElt instanceof GraphElement){
			return (GraphElement) graphElt;
		}
		return null;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPackage(org.eclipse.uml2.uml.Package element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated NOT
	 */
	protected GraphElement createGraphElementClass(org.eclipse.uml2.uml.Class element,String presentation){
		GraphNode nodeClass = createGraphNode(element, presentation);
		GraphNode attributes = createGraphNode(element, UMLPackage.CLASS__OWNED_ATTRIBUTE);
		GraphNode operations = createGraphNode(element, UMLPackage.CLASS__OWNED_OPERATION, UMLPackage.CLASS__OWNED_RECEPTION);
		attributes.setContainer(nodeClass);
		operations.setContainer(nodeClass);
		return nodeClass;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated NOT
	 */
	protected GraphElement createGraphElementDataType(org.eclipse.uml2.uml.DataType element,String presentation){
		GraphNode nodeDataType = createGraphNode(element, presentation);
		GraphNode attributes = createGraphNode(element, UMLPackage.DATA_TYPE__OWNED_ATTRIBUTE);
		GraphNode operations = createGraphNode(element, UMLPackage.DATA_TYPE__OWNED_OPERATION);
		attributes.setContainer(nodeDataType);
		operations.setContainer(nodeDataType);
		return nodeDataType;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated NOT
	 */
	protected GraphElement createGraphElementInterface(org.eclipse.uml2.uml.Interface element,String presentation){
		GraphNode nodeInterface = createGraphNode(element, presentation);
		GraphNode attributes = createGraphNode(element, UMLPackage.INTERFACE__OWNED_ATTRIBUTE);
		GraphNode operations = createGraphNode(element, UMLPackage.INTERFACE__OWNED_OPERATION, UMLPackage.INTERFACE__OWNED_RECEPTION);
		attributes.setContainer(nodeInterface);
		operations.setContainer(nodeInterface);
		return nodeInterface;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementOperation(org.eclipse.uml2.uml.Operation element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementProperty(org.eclipse.uml2.uml.Property element,String presentation){
		return createGraphNode(element, presentation);
	}
	protected GraphElement createGraphElementInstanceSpecification(org.eclipse.uml2.uml.InstanceSpecification element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		GraphNode values = createGraphNode(element, UMLPackage.INSTANCE_SPECIFICATION__SLOT);
		values.setContainer(nodeParent);
		return nodeParent;
	}
	protected GraphElement createGraphElementInstanceSpecificationLink(org.eclipse.uml2.uml.InstanceSpecification element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		EdgeObjectUV srcnameEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		srcnameEdgeObjectUV.setId(ClassEdgeObjectConstants.SRCNAME_EDGE_OBJECT_ID);
		srcnameEdgeObjectUV.setUDistance(LABEL_OFFSET);
		srcnameEdgeObjectUV.setVDistance(LABEL_OFFSET);
		srcnameEdgeObjectUV.setVisible(true);
		graphEdge.getContained().add(srcnameEdgeObjectUV);
		EdgeObjectUV targetnameEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		targetnameEdgeObjectUV.setId(ClassEdgeObjectConstants.TARGETNAME_EDGE_OBJECT_ID);
		targetnameEdgeObjectUV.setUDistance(LABEL_OFFSET);
		targetnameEdgeObjectUV.setVDistance(LABEL_OFFSET);
		targetnameEdgeObjectUV.setVisible(true);
		graphEdge.getContained().add(targetnameEdgeObjectUV);
		return graphEdge;
	}
	protected GraphElement createGraphElementSlot(org.eclipse.uml2.uml.Slot element,String presentation){
		return createGraphNode(element, presentation);
	}
	protected GraphElement createGraphElementEnumeration(org.eclipse.uml2.uml.Enumeration element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		GraphNode enumerationliteral = createGraphNode(element, UMLPackage.ENUMERATION__OWNED_LITERAL, presentation);
		enumerationliteral.setContainer(nodeParent);
		return nodeParent;
	}
	protected GraphElement createGraphElementEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral element,String presentation){
		return createGraphNode(element, presentation);
	}
	protected GraphElement createGraphElementPrimitiveType(org.eclipse.uml2.uml.PrimitiveType element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		GraphNode property = createGraphNode(element, UMLPackage.PRIMITIVE_TYPE__OWNED_ATTRIBUTE, presentation);
		GraphNode operation = createGraphNode(element, UMLPackage.PRIMITIVE_TYPE__OWNED_OPERATION, presentation);
		property.setContainer(nodeParent);
		operation.setContainer(nodeParent);
		return nodeParent;
	}
	protected GraphElement createGraphElementTemplateParameter(org.eclipse.uml2.uml.TemplateParameter element,String presentation){
		return createGraphNode(element, presentation);
	}
	protected GraphElement createGraphElementTemplateSignature(org.eclipse.uml2.uml.TemplateSignature element,String presentation){
		return createGraphNode(element, presentation);
	}
	protected GraphElement createGraphElementRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementSignal(org.eclipse.uml2.uml.Signal element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		GraphNode attributes = createGraphNode(element, UMLPackage.SIGNAL__OWNED_ATTRIBUTE);
		attributes.setContainer(nodeParent);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementReception(org.eclipse.uml2.uml.Reception element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementCreationEvent(org.eclipse.uml2.uml.CreationEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementDestructionEvent(org.eclipse.uml2.uml.DestructionEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementSendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementSendSignalEvent(org.eclipse.uml2.uml.SendSignalEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementReceiveSignalEvent(org.eclipse.uml2.uml.ReceiveSignalEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementCallEvent(org.eclipse.uml2.uml.CallEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementChangeEvent(org.eclipse.uml2.uml.ChangeEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementAnyReceiveEvent(org.eclipse.uml2.uml.AnyReceiveEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementSignalEvent(org.eclipse.uml2.uml.SignalEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementTimeEvent(org.eclipse.uml2.uml.TimeEvent element,String presentation){
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated NOT
	 */
	protected GraphElement createGraphElementAssociation(org.eclipse.uml2.uml.Association element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		IPreferenceStore preferenceStore = getPreferenceStore();
		EdgeObjectUV srcnameEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		srcnameEdgeObjectUV.setId(ClassEdgeObjectConstants.SRCNAME_EDGE_OBJECT_ID);
		srcnameEdgeObjectUV.setUDistance(LABEL_OFFSET);
		srcnameEdgeObjectUV.setVDistance(LABEL_OFFSET);
		srcnameEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATION_SRCNAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(srcnameEdgeObjectUV);
		// Fix #1780
		EdgeObjectUV srcPropertiesEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		srcPropertiesEdgeObjectUV.setId(ClassEdgeObjectConstants.SRCPROPERTIES_EDGE_OBJECT_ID);
		srcPropertiesEdgeObjectUV.setUDistance(LABEL_OFFSET + 15);
		srcPropertiesEdgeObjectUV.setVDistance(LABEL_OFFSET);
		srcPropertiesEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATION_SRCPROPERTIES_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(srcPropertiesEdgeObjectUV);
		// EndFix #1780
		EdgeObjectUV srccountEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		srccountEdgeObjectUV.setId(ClassEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID);
		srccountEdgeObjectUV.setUDistance(LABEL_OFFSET);
		srccountEdgeObjectUV.setVDistance(-LABEL_OFFSET);
		srccountEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATION_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(srccountEdgeObjectUV);
		EdgeObjectUV targetnameEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		targetnameEdgeObjectUV.setId(ClassEdgeObjectConstants.TARGETNAME_EDGE_OBJECT_ID);
		targetnameEdgeObjectUV.setUDistance(LABEL_OFFSET);
		targetnameEdgeObjectUV.setVDistance(LABEL_OFFSET);
		targetnameEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETNAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(targetnameEdgeObjectUV);
		// Fix #1780
		EdgeObjectUV targetPropertiesEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		targetPropertiesEdgeObjectUV.setId(ClassEdgeObjectConstants.TARGETPROPERTIES_EDGE_OBJECT_ID);
		targetPropertiesEdgeObjectUV.setUDistance(LABEL_OFFSET + 15);
		targetPropertiesEdgeObjectUV.setVDistance(LABEL_OFFSET);
		targetPropertiesEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETPROPERTIES_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(targetPropertiesEdgeObjectUV);
		// EndFix #1780
		EdgeObjectUV targetcountEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		targetcountEdgeObjectUV.setId(ClassEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID);
		targetcountEdgeObjectUV.setUDistance(LABEL_OFFSET);
		targetcountEdgeObjectUV.setVDistance(-LABEL_OFFSET);
		targetcountEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(targetcountEdgeObjectUV);
		EdgeObjectOffset middlenameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		middlenameEdgeObjectOffset.setId(ClassEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID);
		middlenameEdgeObjectOffset.setOffset(new Dimension(0, 10));
		middlenameEdgeObjectOffset.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATION_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(middlenameEdgeObjectOffset);
		EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		stereotypeEdgeObjectOffset.setId(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
		stereotypeEdgeObjectOffset.setOffset(new Dimension(0, -10));
		graphEdge.getContained().add(stereotypeEdgeObjectOffset);
		return graphEdge;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated NOT
	 */
	protected GraphElement createGraphElementAssociationClass(org.eclipse.uml2.uml.AssociationClass element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		IPreferenceStore preferenceStore = getPreferenceStore();
		EdgeObjectUV srcnameEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		srcnameEdgeObjectUV.setId(ClassEdgeObjectConstants.SRCNAME_EDGE_OBJECT_ID);
		srcnameEdgeObjectUV.setUDistance(LABEL_OFFSET);
		srcnameEdgeObjectUV.setVDistance(LABEL_OFFSET);
		srcnameEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_SRCNAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(srcnameEdgeObjectUV);
		EdgeObjectUV srccountEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		srccountEdgeObjectUV.setId(ClassEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID);
		srccountEdgeObjectUV.setUDistance(LABEL_OFFSET);
		srccountEdgeObjectUV.setVDistance(-LABEL_OFFSET);
		srccountEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(srccountEdgeObjectUV);
		EdgeObjectUV targetnameEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		targetnameEdgeObjectUV.setId(ClassEdgeObjectConstants.TARGETNAME_EDGE_OBJECT_ID);
		targetnameEdgeObjectUV.setUDistance(LABEL_OFFSET);
		targetnameEdgeObjectUV.setVDistance(LABEL_OFFSET);
		targetnameEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_TARGETNAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(targetnameEdgeObjectUV);
		EdgeObjectUV targetcountEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
		targetcountEdgeObjectUV.setId(ClassEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID);
		targetcountEdgeObjectUV.setUDistance(LABEL_OFFSET);
		targetcountEdgeObjectUV.setVDistance(-LABEL_OFFSET);
		targetcountEdgeObjectUV.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(targetcountEdgeObjectUV);
		EdgeObjectOffset middlenameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		middlenameEdgeObjectOffset.setId(ClassEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID);
		middlenameEdgeObjectOffset.setOffset(new Dimension(0, 20));
		middlenameEdgeObjectOffset.setVisible(preferenceStore.getBoolean(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(middlenameEdgeObjectOffset);
		EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		stereotypeEdgeObjectOffset.setId(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
		stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
		graphEdge.getContained().add(stereotypeEdgeObjectOffset);
		// Associate the GraphNode that is used to represent the Class node of
		// the AssociationClass
		GraphNode nodeClass = createGraphNode(element);
		nodeClass.setSize(new Dimension(100, -1));
		nodeClass.setPosition(new Point(-1, -1));
		GraphNode attributes = createGraphNode(element, UMLPackage.ASSOCIATION_CLASS__OWNED_ATTRIBUTE);
		GraphNode operations = createGraphNode(element, UMLPackage.ASSOCIATION_CLASS__OWNED_OPERATION, UMLPackage.ASSOCIATION_CLASS__OWNED_RECEPTION);
		attributes.setContainer(nodeClass);
		operations.setContainer(nodeClass);
		graphEdge.getContained().add(nodeClass);
		return graphEdge;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementGeneralization(org.eclipse.uml2.uml.Generalization element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		stereotypeEdgeObjectOffset.setId(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
		stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
		stereotypeEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(ClassDiagramPreferenceConstants.GENERALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(stereotypeEdgeObjectOffset);
		return graphEdge;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementTemplateBinding(org.eclipse.uml2.uml.TemplateBinding element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		EdgeObjectOffset middlenameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		middlenameEdgeObjectOffset.setId(ClassEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID);
		middlenameEdgeObjectOffset.setOffset(new Dimension(0, 0));
		middlenameEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(ClassDiagramPreferenceConstants.TEMPLATEBINDING_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(middlenameEdgeObjectOffset);
		EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		stereotypeEdgeObjectOffset.setId(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
		stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
		stereotypeEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(ClassDiagramPreferenceConstants.TEMPLATEBINDING_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(stereotypeEdgeObjectOffset);
		return graphEdge;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		stereotypeEdgeObjectOffset.setId(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
		stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
		stereotypeEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(
				ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(stereotypeEdgeObjectOffset);
		return graphEdge;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated NOT
	 */
	protected GraphElement createGraphElementDependency(org.eclipse.uml2.uml.Dependency element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		stereotypeEdgeObjectOffset.setId(AllEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
		stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
		stereotypeEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.DEPENDENCY_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(stereotypeEdgeObjectOffset);
		EdgeObjectOffset dependencynameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		dependencynameEdgeObjectOffset.setId(AllEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID);
		dependencynameEdgeObjectOffset.setOffset(new Dimension(0, 0));
		dependencynameEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.DEPENDENCY_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(dependencynameEdgeObjectOffset);
		return graphEdge;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUsage(org.eclipse.uml2.uml.Usage element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		IPreferenceStore preferenceStore = getPreferenceStore();
		EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		stereotypeEdgeObjectOffset.setId(ClassEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
		stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
		stereotypeEdgeObjectOffset.setVisible(preferenceStore.getBoolean(AllDiagramPreferenceConstants.USAGE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(stereotypeEdgeObjectOffset);
		EdgeObjectOffset middlenameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		middlenameEdgeObjectOffset.setId(ClassEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID);
		middlenameEdgeObjectOffset.setOffset(new Dimension(0, 20));
		middlenameEdgeObjectOffset.setVisible(preferenceStore.getBoolean(AllDiagramPreferenceConstants.USAGE_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(middlenameEdgeObjectOffset);
		return graphEdge;
	}
	/**
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementConstraint(org.eclipse.uml2.uml.Constraint element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPackageImport(org.eclipse.uml2.uml.PackageImport element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		EdgeObjectOffset visibilityEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		visibilityEdgeObjectOffset.setId(AllEdgeObjectConstants.VISIBILITY_EDGE_OBJECT_ID);
		visibilityEdgeObjectOffset.setOffset(new Dimension(0, 0));
		visibilityEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.PACKAGEIMPORT_VISIBILITY_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(visibilityEdgeObjectOffset);
		return graphEdge;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPackageMerge(org.eclipse.uml2.uml.PackageMerge element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		EdgeObjectOffset mergeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
		mergeEdgeObjectOffset.setId(AllEdgeObjectConstants.MERGE_EDGE_OBJECT_ID);
		mergeEdgeObjectOffset.setOffset(new Dimension(0, 0));
		mergeEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.PACKAGEMERGE_MERGE_EDGE_OBJECT_DEFAULT_VISIBILITY));
		graphEdge.getContained().add(mergeEdgeObjectOffset);
		return graphEdge;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param element
	 *            the model element
	 * @param presentation
	 *            the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementComment(org.eclipse.uml2.uml.Comment element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * Create the ModelObject with its initial children <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param obj
	 *            the model object
	 * @return the model object with its children
	 * @generated
	 */
	public EObject createModelObject(EObject obj){
		return obj;
	}
	private IPreferenceStore getPreferenceStore(){
		IEditorInput editorInput = UMLPlugin.getActivePage().getActiveEditor().getEditorInput();
		if(editorInput instanceof IFileEditorInput){
			IProject project = ((IFileEditorInput) editorInput).getFile().getProject();
			Preferences root = Platform.getPreferencesService().getRootNode();
			try{
				if(root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(UMLPlugin.getId())){
					return new ScopedPreferenceStore(new ProjectScope(project), UMLPlugin.getId());
				}
			}catch(BackingStoreException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return UMLPlugin.getDefault().getPreferenceStore();
	}
}
