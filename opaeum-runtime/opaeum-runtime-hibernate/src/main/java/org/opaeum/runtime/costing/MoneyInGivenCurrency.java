package org.opaeum.runtime.costing;

import static org.opaeum.hibernate.domain.FormatHelper.format;
import static org.opaeum.hibernate.domain.FormatHelper.parseDouble;

import java.text.NumberFormat;
import java.util.Currency;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class MoneyInGivenCurrency{
	@Basic
	private Double amount;
	@Basic
	private String currencyCode;
	private static ThreadLocal<NumberFormat> decimalFormat = new ThreadLocal<NumberFormat>();
	public MoneyInGivenCurrency(){
	}
	public MoneyInGivenCurrency(String value){
		initFormats();
		String[] split = value.split("\\ ");
		amount = parseDouble(decimalFormat, split[0]);
		currencyCode = split[1];
	}
	@Override
	public String toString(){
		initFormats();
		return format(decimalFormat, amount) + " " + currencyCode;
	}
	public void setCurrency(Currency c){
		currencyCode = c.getCurrencyCode();
	}
	public Currency getCurrency(){
		return Currency.getInstance(currencyCode);
	}
	public Double getAmount(){
		return amount;
	}
	public void setAmount(Double amount){
		this.amount = amount;
	}
	void initFormats(){
		if(decimalFormat.get() == null){
			decimalFormat.set(NumberFormat.getInstance());
		}
	}
}
