package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.seamgeneration.jsf.component.AbstractJsfInputBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDecorate;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.primefaces.component.uiajax.UIAjax;

import com.sun.faces.el.ELContextImpl;

public abstract class AbstractJsfPrimeInputBuilder extends AbstractJsfInputBuilder {

	public AbstractJsfPrimeInputBuilder(DomainClassifier dc, PropertyField pf) {
		super(dc, pf);
	}
	
	@Override
	public HtmlFragment createUIComponent() {
		HtmlFragment htmlFragment = new HtmlFragment();
		UIInput uiInput = (UIInput)createComponent();
		setUpInput(dc, pf, uiInput);
		
		htmlFragment.setId(retrieveFragmentId(uiInput));
		setSettedAttributes(htmlFragment, "id");
		
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createEditPropertyRenderedExpression(dc, pf), void.class);
		uiInput.setId(this.dc.getName()+"_"+this.pf.getName());
		HtmlDecorate htmlDecorate = addDecoration(uiInput, getEditTemplate(), ve, pf);
		
		setSettedAttributes(uiInput, "id");
		
		htmlFragment.getChildren().add(htmlDecorate);
		
//		UIOutput uiOutput = new HtmlOutputText();
//		setUpOutputForInput(dc, pf, uiOutput);
//		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createViewPropertyRenderedExpression(dc, pf), void.class);
//		htmlDecorate = addDecoration(uiOutput, getEditTemplate(), ve, pf);
//		htmlFragment.getChildren().add(htmlDecorate);
		return htmlFragment;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void addAjaxSupport(UIOutput uiOutput) {
		UIAjax uiAjax = new UIAjax();
		uiAjax.setEvent(getEvent());
		uiAjax.setUpdate(retrieveFragmentId(uiOutput));
		uiAjax.setProcess(uiOutput.getId());
		setSettedAttributes(uiAjax, "event", "update", "process");
		uiOutput.getChildren().add(uiAjax);
		List<String> attributes = (List<String>)uiOutput.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.remove(getEvent());
	}	

	protected String getEvent() {
		return "blur";
	}	
}
