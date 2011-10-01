/*****************************************************************************
 * 
 * Copyright (c) 2009 Atos Origin.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *  Thibault Landré (Atos Origin) thibault.landre@atosorigin.com
 *
 *****************************************************************************/
package org.topcased.modeler.uml.editor.outline;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.CallBehaviorActionItemProvider;

/**
 * A custom CallBehaviorActionItemProvider It appends the name of the behavior of the CallBehaviorAction to the name of
 * the CallBehaviorAction.
 * 
 */
public class CustomCallBehaviorActionItemProvider extends CallBehaviorActionItemProvider
{

    /**
     * This constructs an instance from a factory and a notifier.
     */
    public CustomCallBehaviorActionItemProvider(AdapterFactory adapterFactory)
    {
        super(adapterFactory);
    }

    /**
     * Overrides to add the name of the behavior of the CallBehaviorAction to the text, if it exists.
     */
    @Override
    public String getText(Object object)
    {
        StringBuffer text = appendLabel(appendType(appendKeywords(new StringBuffer(), object), "_UI_CallBehaviorAction_type"), object); //$NON-NLS-1$

        if (object instanceof CallBehaviorAction)
        {
            CallBehaviorAction cba = (CallBehaviorAction) object;
            Behavior behavior = cba.getBehavior();
            if (behavior != null && behavior.getName() != null && behavior.getName().length() > 0)
            {
                text.append(" : "); //$NON-NLS-1$
                text.append(behavior.getName());
            }

        }
        return text.toString();
    }

    /**
     * 
     */
    @Override
    public void notifyChanged(Notification notification)
    {
        switch (notification.getFeatureID(CallBehaviorAction.class))
        {
            case UMLPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

}
