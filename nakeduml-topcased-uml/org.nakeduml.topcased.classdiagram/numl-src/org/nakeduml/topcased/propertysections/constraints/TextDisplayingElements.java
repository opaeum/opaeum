package org.nakeduml.topcased.propertysections.constraints;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;

public class TextDisplayingElements{
	private LinkedList<Element> elements = new LinkedList<Element>();
	private Text t = null;
	public TextDisplayingElements(Composite parent,int style,TabbedPropertySheetWidgetFactory theFactory){
		t = theFactory.createText(parent, "", style);
	}
	public void setCollectionElements(Collection<Element> els){
		if(els != null){
			elements.clear();
			elements.addAll(els);
			t.setText(getText());
		}else{
			t.setText("");
		}
	}
	public Text getControl(){
		return t;
	}
	public String getText(){
		StringBuffer buffer = new StringBuffer();
		ReflectiveItemProviderAdapterFactory refFactory = new ReflectiveItemProviderAdapterFactory();
		ReflectiveItemProvider provider = new ReflectiveItemProvider(refFactory);
		int i = 0;
		for(Element e:elements){
			if(i != 0){
				buffer.append(", ");
			}
			buffer.append(provider.getText(e));
			i++;
		}
		return buffer.toString();
	}
	public List<Element> getElements(){
		return elements;
	}
}
