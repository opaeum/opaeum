package org.topcased.modeler.uml.oclinterpreter;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;

public class NakedOclConfiguration extends OCLConfiguration{
	private OCLScanner oclScanner;
	private ColorManager colorManager;
	public NakedOclConfiguration(ColorManager colorManager){
		super(colorManager);
		this.colorManager = colorManager;
	}
	protected OCLScanner getOCLScanner(){
		if(oclScanner == null){
			oclScanner = new NakedOclScanner(colorManager);
			oclScanner.setDefaultReturnToken(new Token(new TextAttribute(colorManager.getColor(ColorManager.DEFAULT))));
		}
		return oclScanner;
	}
}
