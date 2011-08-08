package org.nakeduml.jsf;

import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.Page;
import org.apache.myfaces.extensions.cdi.jsf.api.config.view.Page.NavigationMode;

@Page(navigation=NavigationMode.REDIRECT)
public class HelloWorld implements ViewConfig {

}
