package structuredbusiness.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import org.opaeum.hibernate.domain.CumulativeDuration;
import org.opaeum.hibernate.domain.Duration;
import org.opaeum.runtime.costing.DurationBasedCost;
import org.opaeum.runtime.costing.MoneyInGivenCurrency;
import org.opaeum.runtime.costing.QuantityBasedCost;
import org.opaeum.runtime.domain.AbstractFormatter;
import org.opaeum.runtime.environment.Environment;

public class StructuredbusinessFormatter extends AbstractFormatter {
	static final private ThreadLocal<StructuredbusinessFormatter> INSTANCE = new ThreadLocal<StructuredbusinessFormatter>();


	public String formatCumulativeDuration(CumulativeDuration value) {
		String result = value==null?"":value.toString();
		
		return result;
	}
	
	public String formatCurrency(Currency value) {
		String result = value==null?"":value.getCurrencyCode();
		
		return result;
	}
	
	public String formatDuration(Duration value) {
		String result = value==null?"":value.toString();
		
		return result;
	}
	
	public String formatDurationBasedCost(DurationBasedCost value) {
		String result = value==null?"":value.toString();
		
		return result;
	}
	
	public String formatLocale(Locale value) {
		String result = value==null?"":value.toString();
		
		return result;
	}
	
	public String formatMoneyInDefaultCurrency(Double value) {
		String result = null;
		NumberFormat format = NumberFormat.getInstance();
		result =value==null?"":format.format(value);
		return result;
	}
	
	public String formatMoneyInGivenCurrency(MoneyInGivenCurrency value) {
		String result = value==null?"":value.toString();
		
		return result;
	}
	
	public String formatQuantityBasedCost(QuantityBasedCost value) {
		String result = value==null?"":value.toString();
		
		return result;
	}
	
	static public StructuredbusinessFormatter getInstance() {
		StructuredbusinessFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new StructuredbusinessFormatter());
		}
		return result;
	}
	
	public CumulativeDuration parseCumulativeDuration(String value) {
		CumulativeDuration result = value==null||value.length()==0?null: new CumulativeDuration(value);
		
		return result;
	}
	
	public Currency parseCurrency(String value) {
		Currency result = value==null||value.length()==0?null:Currency.getInstance(value);
		
		return result;
	}
	
	public Duration parseDuration(String value) {
		Duration result = value==null||value.length()==0?null:new Duration(value);
		
		return result;
	}
	
	public DurationBasedCost parseDurationBasedCost(String value) {
		DurationBasedCost result = value==null||value.length()==0?null: new DurationBasedCost(value);
		
		return result;
	}
	
	public Locale parseLocale(String value) {
		Locale result = value==null||value.length()==0?null:Environment.getLocale(value);
		
		return result;
	}
	
	public Double parseMoneyInDefaultCurrency(String value) {
		Double result = null;
		NumberFormat format = NumberFormat.getInstance();
		try {
			result = (value==null||value.length()==0?null:format.parse(value).doubleValue());
		} catch (Exception e) {
			result=null;
		}
		return result;
	}
	
	public MoneyInGivenCurrency parseMoneyInGivenCurrency(String value) {
		MoneyInGivenCurrency result = value==null||value.length()==0?null:new MoneyInGivenCurrency(value);
		
		return result;
	}
	
	public QuantityBasedCost parseQuantityBasedCost(String value) {
		QuantityBasedCost result = value==null||value.length()==0?null:new QuantityBasedCost(value);
		
		return result;
	}

}