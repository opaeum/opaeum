package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractStringPropertySection;
import org.topcased.tabbedproperties.sections.widgets.IText;

public class ElementImportAliasSection extends AbstractStringPropertySection{
	public static class OpaeumTextViewer extends TextViewer implements IText{
		public OpaeumTextViewer(){
			super();
		}
		public OpaeumTextViewer(Composite parent,int styles){
			super(parent, styles | SWT.BORDER | SWT.FLAT);
		}
		@Override
		public void setEnabled(boolean enabled){
			getTextWidget().setEnabled(enabled);
		}
		@Override
		public void setLayoutData(Object layoutData){
			getTextWidget().setLayoutData(layoutData);
		}
		@Override
		public void setText(String string){
			getTextWidget().setText(string);
		}
		@Override
		public String getText(){
			return getTextWidget().getText();
		}
		@Override
		public void setBackground(Color color){
			getTextWidget().setBackground(color);
		}
		@Override
		public void setForeground(Color color){
			getTextWidget().setForeground(color);
		}
		@Override
		public Control getTextControl(){
			return getTextWidget();
		}
	}
	public IText getTextWidget(Composite parent,int style){
		return new OpaeumTextViewer(parent, style);
	}
	@Override
	protected String getLabelText(){
		return "Alieas:";
	}
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getElementImport_Alias();
	}
}
