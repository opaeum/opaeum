package org.opaeum.rap.login;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.part.ViewPart;

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;

public class LoginView extends ViewPart{
	private static final Color COLOR_LINK = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION);
	private static final Color COLOR_WHITE = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
	private static final String SCOPE = "https://www.googleapis.com/auth/tasks  " + "https://www.google.com/m8/feeds " + "https://docs.google.com/feeds/ "
			+ "https://spreadsheets.google.com/feeds/ " + "https://docs.googleusercontent.com";
	private Browser browser;
	private Hyperlink link;
	public static final String CLIENT_ID = "282741990120-k8pti02t9srlnqcnocs20eb8hd0kufj8.apps.googleusercontent.com";
	public static final String CALLBACK_URL = "http://127.0.0.1:10080/rms?startup=rms";
	public static final String ID = "org.opaeum.rap.login.LoginView";
	public void createPartControl(Composite parent){
		browser = new Browser(parent, SWT.BORDER);
		browser.setVisible(false);
		link=createDownloadLink(parent, "Login with Google", "");
	}
	public void setFocus(){
		link.setFocus();
	}
	
	private Hyperlink createDownloadLink(final Composite parent,final String linkText,final String linkUrl){
		Hyperlink link = new Hyperlink(parent, SWT.NONE);
		link.setText(linkText);
		link.setHref(linkUrl);
		link.setBackground(COLOR_WHITE);
		link.setForeground(COLOR_LINK);
		IHyperlinkListener hyperlinkListener = new HyperlinkAdapter(){
			@Override
			public void linkActivated(final HyperlinkEvent evt){
				if(evt.getHref() != null){
					browser.evaluate("window.open(\""+evt.getHref()+"\",\"_top\",\"\");");
				}
			}
		};
		link.addHyperlinkListener(hyperlinkListener);
		GoogleAuthorizationRequestUrl builder = new GoogleAuthorizationRequestUrl(CLIENT_ID, CALLBACK_URL, SCOPE);
		String authorizeUrl = builder.build();
		link.setHref(authorizeUrl);

		return link;
	}
}
