package org.opaeum.runtime.costing;

import java.util.Currency;

public class CurrencyMismatchException extends IllegalStateException{
	private static final long serialVersionUID = 1124891324L;
	public CurrencyMismatchException(String currencyCode,Currency c){
		super("Currency " + currencyCode + " does not match " + c.getCurrencyCode());
	}
}
