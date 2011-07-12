/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;
import org.topcased.modeler.uml.classdiagram.commands.OperationRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.OperationFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Operation object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class OperationEditPart extends EMFGraphNodeEditPart
{
    /** Show/Hide the parameters' type. */
    private boolean showParametersType = true;

    /** Show/Hide the return's type. */
    private boolean showReturnType = true;

    /** Show/Hide the parameters. */
    private boolean showParameters = true;

    /** Show/Hide the default value of parameters. */
    private boolean showDefaultValue = true;

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public OperationEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            @Override
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new OperationRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    @Override
    protected IFigure createFigure()
    {

        return new OperationFigure();
    }

    /**
     * Change the visibility of an element.
     * 
     * @param property The property.
     * @param isVisible True if visible, false otherwise.
     * @see ClassElementsVisibilityConstants
     */
    public void setElementVisible(String property, boolean isVisible)
    {
        if (ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_TYPE.equals(property))
        {
            showParametersType = isVisible;
            refreshHeaderLabel();
        }
        else if (ClassElementsVisibilityConstants.SHOW_OPERATION_RETURN_TYPE.equals(property))
        {
            showReturnType = isVisible;
            refreshHeaderLabel();
        }
        else if (ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETERS.equals(property))
        {
            showParameters = isVisible;
            refreshHeaderLabel();
        }
        else if (ClassElementsVisibilityConstants.SHOW_OPERATION_PARAMETER_DEFAULT_VALUE.equals(property))
        {
            showDefaultValue = isVisible;
            refreshHeaderLabel();
        }
    }

    /**
     * Set the name of the operation, with its argument and its stereotypes
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        // [<visibility>] <name> ‘(‘ [<parameter-list>] ‘)’ [‘:’ [<return-type>] [‘{‘ <oper-property> [‘,’
        // <oper-property>]* ‘}’]]

        ComposedLabel lbl = (ComposedLabel) ((ILabelFigure) getFigure()).getLabel();

        Operation operation = (Operation) Utils.getElement(getGraphNode());

        if (operation.getName() != null && operation.getName().length() != 0)
        {
            StringBuffer prefix = new StringBuffer();

            prefix.append(UMLLabel.getStereotypesNotation(operation, getPreferenceStore()));

            prefix.append(UMLLabel.getVisibilityNotation(operation.getVisibility()));

            lbl.setPrefix(prefix.toString());

            lbl.setMain(operation.getName());

            lbl.setSuffix(getOperationSuffix(operation));

        }

        // Underline the label when the Operation is declared as static
        ((WrappingLabel) lbl.getMain()).setTextUnderline(((Operation) getEObject()).isStatic());
    }

    /**
     * Compose the suffix of an operation using the UML2 Superstructure specification.<br>
     * 
     * @param operation the operation to format
     * @return the operation suffix
     */
    private String getOperationSuffix(Operation operation)
    {
        StringBuffer suffix = new StringBuffer();

        suffix.append('(');
        String returnOperationDescription = null;
        EList<Parameter> lOwnedParameterList = operation.getOwnedParameters();

        for (Parameter currentParameter : lOwnedParameterList)
        {
            if (currentParameter.getDirection().getValue() == ParameterDirectionKind.RETURN)
            {
                // do not write the return if there is no type
                if (currentParameter.getType() != null)
                {
                    returnOperationDescription = getParameterText(currentParameter);
                }
            }
            else if (showParameters && lOwnedParameterList != null && !lOwnedParameterList.isEmpty())
            {

                if (currentParameter.equals(lOwnedParameterList.get(0)) || lOwnedParameterList.get(0).getDirection().getValue() == ParameterDirectionKind.RETURN
                        && currentParameter.equals(lOwnedParameterList.get(1)))
                {

                    suffix.append(getParameterText(currentParameter));

                }
                else
                {

                    suffix.append(", ");
                    suffix.append(getParameterText(currentParameter));

                }

            }
        }

        suffix.append(')');

        // write the return type
        if (showReturnType && returnOperationDescription != null)
        {
            suffix.append(" : ");
            suffix.append(returnOperationDescription);
        }

        // write the propertes of the operation
        String propertiesText = getOperationPropertiesText(operation);
        if (propertiesText != null && !"".equals(propertiesText))
        {
            suffix.append(" { ");
            suffix.append(propertiesText);
            suffix.append(" }");
        }

        return suffix.toString();
    }

    private String getOperationPropertiesText(Operation operation)
    {
        // <oper-property> [ï¿½,ï¿½ <oper-property>]*
        // <oper-property> ::= ï¿½redefinesï¿½ <oper-name> | ï¿½queryï¿½ | ï¿½orderedï¿½ |
        // ï¿½uniqueï¿½ | <oper-constraint>
        StringBuffer text = new StringBuffer();

        append(text, getRedefinesText(operation));
        append(text, getQueryText(operation));
        append(text, getOrderedText(operation));
        append(text, getUniqueText(operation));
        append(text, getConstraintText(operation));

        return text.toString();
    }

    private void append(StringBuffer text, String fragment)
    {
        if (fragment != null && !"".equals(fragment))
        {
            if (text.length() > 0)
            {
                text.append(", ");
            }
            text.append(fragment);
        }
    }

    private String getRedefinesText(Operation operation)
    {
        StringBuffer text = new StringBuffer();

        if (operation.getRedefinedOperations() != null)
        {
            Iterator<Operation> itRedifines = operation.getRedefinedOperations().iterator();
            while (itRedifines.hasNext())
            {
                Operation redefinedOp = itRedifines.next();
                text.append("redefines ");
                text.append(redefinedOp.getName());

                if (itRedifines.hasNext())
                {
                    text.append(", ");
                }
            }
        }

        return text.toString();
    }

    private String getConstraintText(Operation operation)
    {
        StringBuffer text = new StringBuffer();

        Constraint constraint = operation.getBodyCondition();
        if (constraint != null)
        {
            ValueSpecification specification = constraint.getSpecification();
            if (specification != null)
            { 
                String label = specification.stringValue();
                if (label != null)
                {
                    text.append(label);
                }
            }
        }

        return text.toString();
    }

    private String getUniqueText(Operation operation)
    {
        StringBuffer text = new StringBuffer();
        if (operation.getReturnResult() != null)
        {
            Parameter returnResult = operation.getReturnResult();
            if (returnResult.isMultivalued() && returnResult.isUnique())
            {
                text.append("unique");
            }
        }

        return text.toString();
    }

    private String getOrderedText(Operation operation)
    {
        StringBuffer text = new StringBuffer();

        if (operation.isOrdered())
        {
            text.append("ordered");
        }

        return text.toString();
    }

    private String getQueryText(Operation operation)
    {
        StringBuffer text = new StringBuffer();

        if (operation.isQuery())
        {
            text.append("query");
        }

        return text.toString();
    }

    private String getParameterText(Parameter param)
    {
        StringBuffer text = new StringBuffer();

        // [<direction>] ::= ï¿½inï¿½ | ï¿½outï¿½ | ï¿½inoutï¿½ (defaults to ï¿½inï¿½ if
        // omitted).
        ParameterDirectionKind direction = param.getDirection();

        // Do not display the ReturnType parameter in the list of parameters of
        // the Operation
        if (ParameterDirectionKind.RETURN != direction.getValue())
        {
            if (ParameterDirectionKind.IN != direction.getValue())
            {
                text.append(direction.getName());
                text.append(" ");
            }
            // <parameter-name>
            text.append(param.getName());

            if (showParametersType)
            {
                text.append(" : ");
            }
        }

        // <type-expression>
        if (showParametersType || ParameterDirectionKind.RETURN == direction.getValue())
        {
            Type paramType = param.getType();
            if (paramType != null)
            {
                text.append(paramType.getName());
            }
            else
            {
                text.append("null");
            }
            // [ï¿½[ï¿½<multiplicity>ï¿½]ï¿½]
            text.append(UMLLabel.getMultiplicityText(param));

            // [ï¿½=ï¿½ <default>]
            String defaultValue = param.getDefault();
            if (showDefaultValue && defaultValue != null && !"".equals(defaultValue))
            {
                text.append(" = ");
                text.append(defaultValue);
            }
        }

        return text.toString();
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#handleModelChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleModelChanged(Notification msg)
    {

        Object notifier = msg.getNotifier();
        Object newObject = msg.getNewValue();
        Object oldObject = msg.getOldValue();

        if (notifier instanceof Operation)
        {
            if (notifier == getEObject())
            {
                switch (msg.getFeatureID(Operation.class))
                {
                    case UMLPackage.OPERATION__OWNED_PARAMETER:
                    case UMLPackage.OPERATION__REDEFINED_OPERATION:
                    case UMLPackage.OPERATION__BODY_CONDITION:
                        updateModelListening(oldObject, newObject);
                        break;
                    default:
                        break;
                }
                super.handleModelChanged(msg);
            }
            else
            {
                refreshHeaderLabel();
            }
        }
        else if (notifier instanceof Parameter)
        {
            switch (msg.getFeatureID(Parameter.class))
            {
                case UMLPackage.PARAMETER__TYPE:
                case UMLPackage.PARAMETER__LOWER_VALUE:
                case UMLPackage.PARAMETER__UPPER_VALUE:
                case UMLPackage.PARAMETER__DEFAULT_VALUE:
                    updateModelListening(oldObject, newObject);
                    break;
                default:
                    break;
            }
            refreshHeaderLabel();
        }
        else if (notifier instanceof Type)
        {
            switch (msg.getFeatureID(NamedElement.class))
            {
                case UMLPackage.NAMED_ELEMENT__NAME:
                    refreshHeaderLabel();
                    break;
                default:
                    break;
            }
        }
        else if (notifier instanceof Constraint)
        {
            switch (msg.getFeatureID(Operation.class))
            {
                case UMLPackage.CONSTRAINT__SPECIFICATION:
                    updateModelListening(oldObject, newObject);
                    break;
                default:
                    break;
            }
            refreshHeaderLabel();
        }
        else if (notifier instanceof ValueSpecification)
        {
            refreshHeaderLabel();
        }

    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#activate()
     */
    @Override
    public void activate()
    {
        super.activate();
        EObject model = getEObject();
        if (model instanceof Operation)
        {
            listenParameters((Operation) getEObject());
            listenRedefinedOperations((Operation) getEObject());
            listenBodyCondition((Operation) getEObject());
        }
    }

    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {
        EObject model = getEObject();
        if (model instanceof Operation)
        {
            unlistenBodyCondition((Operation) getEObject());
            unlistenRedefinedOperations((Operation) getEObject());
            unlistenParameters((Operation) getEObject());
        }
        super.deactivate();
    }

    /**
     * Listen to the constraint of the operation
     * 
     * @param operation the model object
     */
    private void listenBodyCondition(Operation operation)
    {
        // Only listen to object that are not yet listened
        Constraint bodyCondition = operation.getBodyCondition();
        if (bodyCondition != null)
        {
            if (!bodyCondition.eAdapters().contains(getModelListener()))
            {
                bodyCondition.eAdapters().add(getModelListener());
            }
            ValueSpecification valueSpecification = bodyCondition.getSpecification();
            if (valueSpecification != null && !valueSpecification.eAdapters().contains(getModelListener()))
            {
                valueSpecification.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Stop listening to the type of the parameter
     * 
     * @param operation the model object
     */
    private void unlistenBodyCondition(Operation operation)
    {
        if (operation.getBodyCondition() != null)
        {
            operation.getBodyCondition().eAdapters().remove(getModelListener());
            if (operation.getBodyCondition().getSpecification() != null)
            {
                operation.getBodyCondition().getSpecification().eAdapters().remove(getModelListener());
            }
        }
    }

    /**
     * Listen to all the redefined operations of the operation
     * 
     * @param operation the model object
     */
    private void listenRedefinedOperations(Operation operation)
    {
        for (Operation redifinedOp : operation.getRedefinedOperations())
        {
            // Only listen to object that are not yet listened
            if (!redifinedOp.eAdapters().contains(getModelListener()))
            {
                redifinedOp.eAdapters().add(getModelListener());
            }
        }
    }

    /**
     * Stop listening to all the redefined operations of the operation
     * 
     * @param operation the model object
     */
    private void unlistenRedefinedOperations(Operation operation)
    {
        for (Operation redifinedOp : operation.getRedefinedOperations())
        {
            redifinedOp.eAdapters().remove(getModelListener());
        }
    }

    /**
     * Listen to all the parameters of the operation
     * 
     * @param operation the model object
     */
    private void listenParameters(Operation operation)
    {
        for (Parameter param : operation.getOwnedParameters())
        {
            // Only listen to object that are not yet listened
            if (!param.eAdapters().contains(getModelListener()))
            {
                param.eAdapters().add(getModelListener());
            }
            listenType(param);
            listenValues(param);
        }
    }

    /**
     * Stop listening to all the parameters of the operation
     * 
     * @param operation the model object
     */
    private void unlistenParameters(Operation operation)
    {
        for (Parameter param : operation.getOwnedParameters())
        {
            unlistenValues(param);
            unlistenType(param);
            param.eAdapters().remove(getModelListener());
        }
    }

    /**
     * Listen to the type of the parameter
     * 
     * @param param the model object
     */
    private void listenType(Parameter param)
    {
        // Only listen to object that are not yet listened
        if (param.getType() != null && !param.getType().eAdapters().contains(getModelListener()))
        {
            param.getType().eAdapters().add(getModelListener());
        }
    }

    /**
     * Stop listening to the type of the parameter
     * 
     * @param param the model object
     */
    private void unlistenType(Parameter param)
    {
        if (param.getType() != null)
        {
            param.getType().eAdapters().remove(getModelListener());
        }
    }

    /**
     * Listen to the bounds of the parameter
     * 
     * @param param the model object
     */
    private void listenValues(Parameter param)
    {
        // Only listen to object that are not yet listened
        if (param.getLowerValue() != null && !param.getLowerValue().eAdapters().contains(getModelListener()))
        {
            param.getLowerValue().eAdapters().add(getModelListener());
        }
        if (param.getUpperValue() != null && !param.getUpperValue().eAdapters().contains(getModelListener()))
        {
            param.getUpperValue().eAdapters().add(getModelListener());
        }
        if (param.getDefaultValue() != null && !param.getDefaultValue().eAdapters().contains(getModelListener()))
        {
            param.getDefaultValue().eAdapters().add(getModelListener());
        }
    }

    /**
     * Stop listening to the bounds of the parameter
     * 
     * @param param the model object
     */
    private void unlistenValues(Parameter param)
    {
        if (param.getLowerValue() != null)
        {
            param.getLowerValue().eAdapters().remove(getModelListener());
        }
        if (param.getUpperValue() != null)
        {
            param.getUpperValue().eAdapters().remove(getModelListener());
        }
        if (param.getDefaultValue() != null)
        {
            param.getDefaultValue().eAdapters().remove(getModelListener());
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#refreshTextAndFont()
     */
    @Override
    protected void refreshTextAndFont()
    {
        super.refreshTextAndFont();

        // The name of an Operation is displayed using a bold Font. When the Operation is abstract, it is Italic too.
        if (((Operation) getEObject()).isAbstract())
        {
            ((ComposedLabel) getLabel()).getMain().setFont(getStyledFont(SWT.ITALIC));
        }
        else
        {
            ((ComposedLabel) getLabel()).getMain().setFont(getLabel().getFont());
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.OPERATION_DEFAULT_BACKGROUND_COLOR);
        if (backgroundColor.length() != 0)
        {
            return Utils.getColor(backgroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.OPERATION_DEFAULT_FOREGROUND_COLOR);
        if (foregroundColor.length() != 0)
        {
            return Utils.getColor(foregroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
     * 
     * @generated
     */
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(ClassDiagramPreferenceConstants.OPERATION_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}