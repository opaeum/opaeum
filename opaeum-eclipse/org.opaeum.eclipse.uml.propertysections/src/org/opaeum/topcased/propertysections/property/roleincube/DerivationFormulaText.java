package org.opaeum.topcased.propertysections.property.roleincube;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class DerivationFormulaText extends Composite{
	private Text text;
	private ContentProposalAdapter adapter;
	public DerivationFormulaText(Composite parent,int style){
		super(parent, style);
		setLayout(new FormLayout());
		this.text = new Text(this, style);
		FormData layoutData = new FormData();
		layoutData.left=new FormAttachment(0,0);
		layoutData.right=new FormAttachment(100,0);
		layoutData.top=new FormAttachment(0,0);
		layoutData.bottom=new FormAttachment(100,0);
		text.setLayoutData(layoutData);
		
	}
	public void setContentProposalProvider(TypedElementContentProposalProvider a){
		if(adapter == null){
			// "." and "#" will also activate the content proposals
			char[] autoActivationCharacters = new char[]{
					'/','-','+','*','('
			};
			KeyStroke keyStroke;
			try{
				keyStroke = KeyStroke.getInstance("CTRL+SPACE");
				this.adapter = new ContentProposalAdapter(text, new TypedElementContentAdaptor(), a, keyStroke, autoActivationCharacters);
			}catch(ParseException e){
				e.printStackTrace();
			}
		}
		adapter.setContentProposalProvider(a);
	}
	public Text getTextControl(){
		return text;
	}
}
