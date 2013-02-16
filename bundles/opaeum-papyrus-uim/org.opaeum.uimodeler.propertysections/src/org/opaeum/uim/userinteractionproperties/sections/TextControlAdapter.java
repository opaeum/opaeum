package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.widgets.Text;

public class TextControlAdapter extends org.opaeum.eclipse.uml.propertysections.common.AbstractContentAssistHelper{
	private ITypedElementProvider typedElementProvider;
	public TextControlAdapter(Text text,ITypedElementProvider s){
		super(text);
		this.typedElementProvider=s;
		init();
	}
	@Override
	public TextContentAdapter createTextContentAdaptor(){
		return new TypedElementContentAdaptor();
	}
	@Override
	public SimpleContentProposalProvider createContentProposalProvider(){
		return new TypedElementContentProposalProvider(typedElementProvider);
	}
	@Override
	public char[] getTriggerChars(){
		return new char[]{'.'};
	}
}