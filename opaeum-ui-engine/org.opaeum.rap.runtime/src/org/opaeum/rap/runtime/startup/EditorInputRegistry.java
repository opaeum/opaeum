// Created on 27.09.2007
package org.opaeum.rap.runtime.startup;

import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.ISessionStore;
import org.eclipse.ui.IEditorInput;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.IEditorInputRegistry;


class EditorInputRegistry implements IEditorInputRegistry {

  private static final String EDITOR_INPUT_MAP
    = EditorInputRegistry.class.getName() + "EditorInputMap";
  
  @SuppressWarnings("unchecked")
  public Map<Object, IEditorInput> get() {
    ISessionStore session = RWT.getSessionStore();
    Map<Object, IEditorInput> result 
      = ( Map )session.getAttribute( EDITOR_INPUT_MAP );
    if( result == null ) {
      result = new WeakHashMap<Object, IEditorInput>();
      session.setAttribute( EDITOR_INPUT_MAP, result );
    }
    return result;
  }
}
