package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;

class BgColorUtil {
  
  static void setBgColor( final Control control ) {
    Color white = control.getDisplay().getSystemColor( SWT.COLOR_WHITE );
    control.setBackground( white );
  }
  
  private BgColorUtil() {
    // prevent instance creation
  }
}
