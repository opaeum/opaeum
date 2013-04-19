package org.topcased.modeler.uml.oclinterpreter;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;

public class OpaeumOclConfiguration extends OCLConfiguration{
	private OCLScanner oclScanner;
	private ColorManager colorManager;
	public OpaeumOclConfiguration(ColorManager colorManager){
		super(colorManager);
		this.colorManager = colorManager;
	}
	protected OCLScanner getOCLScanner(){
		if(oclScanner == null){
			oclScanner = new OpaeumOclScanner(colorManager);
			oclScanner.setDefaultReturnToken(new Token(new TextAttribute(colorManager.getColor(ColorManager.DEFAULT))));
		}
		return oclScanner;
	}
}
