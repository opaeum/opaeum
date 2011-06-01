package org.nakeduml.uim.userinteractionproperties.sections;

import java.util.Collection;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.eclipse.EmfElementFinder;
import org.topcased.tabbedproperties.sections.AbstractTextPropertySection;
import org.topcased.tabbedproperties.sections.widgets.IText;

public abstract class TypedElementCodeCompletingSection extends AbstractTextPropertySection{
	protected abstract Collection<? extends TypedElement> getTypedElements();
	@Override
	public IText getTextWidget(Composite parent,int style){
		TextControlAdapter text = new TextControlAdapter(parent, style);
		// "." and "#" will also activate the content proposals
		char[] autoActivationCharacters = new char[]{
			'.'
		};
		KeyStroke keyStroke;
		try{
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
			// assume that myTextControl has already been created in some way
			ContentProposalAdapter adapter = new ContentProposalAdapter(text.getTextControl(), new TypedElementContentAdaptor(), new TypedElementContentProposalProvider(
					this), keyStroke, autoActivationCharacters);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return text;
	}
	protected TypedElement getTypedElement(String name){
		Collection<? extends TypedElement> typedElements = getTypedElements();
		for(TypedElement te:typedElements){
			if(te.getName().equals(name)){
				return te;
			}
		}
		return null;
	}
	protected Property getProperty(Classifier c,String name){
		Collection<TypedElement> typedElements = EmfElementFinder.getTypedElementsInScope(c);
		for(TypedElement te:typedElements){
			if(te.getName().equals(name)){
				return (Property) te;
			}
		}
		return null;
	}
}
