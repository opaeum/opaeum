// Created on 11.09.2007
package org.opaeum.rap.login;

import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.editors.EntityEditor;
import org.opaeum.rap.runtime.internal.startup.IntroPerspective;
import org.opaeum.runtime.jface.navigator.OpaeumNavigator;


public class Constants {
  
  public static final String NAVIGATOR_ID = OpaeumNavigator.class.getName();
  public static final String PLUGIN_ID
    = Activator.class.getPackage().getName();
  public static final String PERSPECTIVE_ID 
    = IntroPerspective.class.getName();
  public static final String ENTITY_EDITOR_ID = EntityEditor.class.getName();
  public static final String PRE_SELECTION
    = OpaeumNavigator.class.getName() + ".PreSelection"; //$NON-NLS-1$
  
  private Constants() {
    // prevent instance creation
  }
}
