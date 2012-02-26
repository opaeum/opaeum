package org.opaeum.rap.login;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;

public class LoginView extends ViewPart {

  private static final String SCOPE = "https://www.googleapis.com/auth/tasks  "
                                      + "https://www.google.com/m8/feeds "
                                      + "https://docs.google.com/feeds/ "
                                      + "https://spreadsheets.google.com/feeds/ "
                                      + "https://docs.googleusercontent.com";
  private Browser browser;
  public static final String CLIENT_ID = "282741990120-k8pti02t9srlnqcnocs20eb8hd0kufj8.apps.googleusercontent.com";
  public static final String CALLBACK_URL = "http://127.0.0.1:10080/rms?startup=rms";
  public static final String ID = "org.opaeum.rap.login.LoginView";

  public void createPartControl( Composite parent ) {
    // parent.setLayout(new FillLayout());
    browser = new Browser( parent, SWT.BORDER );
    // browser.setLayoutData(new Fill)
  }

  public void setFocus() {
    GoogleAuthorizationRequestUrl builder = new GoogleAuthorizationRequestUrl( CLIENT_ID,
                                                                               CALLBACK_URL,
                                                                               SCOPE );
    String authorizeUrl = builder.build();
    browser.setText( "<a href='" + authorizeUrl + "' target='_top'>Authenticate with Google</a>" );
  }
}
