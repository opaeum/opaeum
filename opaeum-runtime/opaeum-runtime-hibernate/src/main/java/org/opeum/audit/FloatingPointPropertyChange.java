package org.opeum.audit;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.event.EventSource;


@Entity
@DiscriminatorValue("F")
public class FloatingPointPropertyChange extends PropertyChange<Double> {
	{
		super.propertyChangeType='F';
	}

	private static final long serialVersionUID = -1216501218269275396L;
	static ThreadLocal<DecimalFormat> nf = new ThreadLocal<DecimalFormat>();
	{
		if(nf.get()==null){
			nf.set(new DecimalFormat("#.#"));
			nf.get().setMaximumFractionDigits(20);
		}
		

	}
	public FloatingPointPropertyChange() {
		super();
	}

	public FloatingPointPropertyChange(String name, Number oldValue, Number newValue) {
		super(name,toDouble(oldValue),toDouble(newValue));

	}

	private static Double toDouble(Number oldValue) {
		return oldValue==null?null:oldValue.doubleValue();
	}

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("0.0E0");
		df.setMinimumFractionDigits(25);
		Number n =new BigDecimal("1.23456789012345667889012313212312312312312312312312312312312312312312312132");
		System.out.println(df.format(n));
	}

	@Override
	protected String toString(Double t) {
		return nf.get().format(t);
	}

	@Override
	protected Double resolveFromString(EventSource em, String stringValue) {
		try {
			return nf.get().parse(stringValue).doubleValue();
		} catch (ParseException e) {
			return null;
		}
	}

}
