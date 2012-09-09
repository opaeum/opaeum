package org.topcased.modeler.uml.oclinterpreter;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;

public class OpaeumOclScanner extends OCLScanner{

	@Override
	public void setRules(IRule[] rules){
        IToken token = new Token(new TextAttribute(ColorConstants.blue, null, SWT.BOLD));
        OCLKeywordRule kwr = new OCLKeywordRule();
        kwr.addWord("now", token);
        kwr.addWord("contextObject", token);
        kwr.addWord("currentRole", token);
        kwr.addWord("ownerObject", token);
        kwr.addWord("currentUser", token);
		rules[1] = kwr;
		super.setRules(rules);
	}

	public OpaeumOclScanner(ColorManager manager){
		super(manager);
	}
}
