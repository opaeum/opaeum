package org.opaeum.rap.login;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class LoginPerspectiveFactory implements IPerspectiveFactory {
	public static final String ID="org.opaeum.rap.login.LoginPerspectiveFactory";

	public void createInitialLayout(IPageLayout layout) {
	    String editorArea = layout.getEditorArea();
	    layout.setEditorAreaVisible(false);

	    layout.addStandaloneView(LoginView.ID, false, IPageLayout.RIGHT,0.5f,
	      editorArea);
	}
}
