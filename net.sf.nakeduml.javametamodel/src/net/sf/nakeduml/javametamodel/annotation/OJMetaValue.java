package net.sf.nakeduml.javametamodel.annotation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJPathName;

/**
 * A common superclass for annotation values and attribute values and defines
 * all the value-declaration logic. In Java annotations can also have a "value"
 * if it has a single property name "value". As such, it is similar to an
 * attribute value that could also have a value
 */
public abstract class OJMetaValue extends OJElement {
	protected List<Object> values = new ArrayList<Object>();

	
	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public OJMetaValue() {
		super();
	}

	/**
	 * Value is a Java class
	 * 
	 * @param value
	 */
	public OJMetaValue(OJPathName value) {
		addValue(value);
	}

	/**
	 * Value is an annotation value
	 * 
	 * @param value
	 */
	public OJMetaValue(OJAnnotationValue value) {
		addValue(value);
	}

	/**
	 * Value is a boolean
	 * 
	 * @param value
	 */
	public OJMetaValue(Boolean value) {
		addValue(value);
	}

	public OJMetaValue(String value) {
		addValue(value);
	}

	public OJMetaValue(Number value) {
		addValue(value);
	}

	/**
	 * value is an enumeration literal
	 * 
	 * @param value
	 */
	public OJMetaValue(OJEnumValue value) {
		addValue(value);
	}

	public void addTypesUsed(Set<OJPathName> s) {
		for (Object o : this.values) {
			if (o instanceof OJAnnotationValue) {
				// Annotation instance
				((OJAnnotationValue) o).addTypesUsed(s);
			} else if (o instanceof OJPathName) {
				// Class value
				s.add((OJPathName) o);
			} else if (o instanceof OJEnumValue) {
				// Enumeration literal value
				
				//Do not add this as fully qualified name is used
//				s.add(((OJEnumValue) o).getType());
			} else if (o instanceof OJClassValue) {
				s.add(((OJClassValue) o).getType());
			}
		}
	}

	public boolean hasValues() {
		return this.values.size() > 0;
	}

	public void addAnnotationValue(OJAnnotationValue value) {
		addValue(value);
	}

	private void addValue(Object v) {
		this.values.add(v);
	}

	public void addEnumValue(OJEnumValue value) {
		addValue(value);
	}

	public void addClassValue(OJClassValue value) {
		addValue(value);
	}
	
	public void addBooleanValue(Boolean value) {
		addValue(value);
	}

	public void addStringValue(String value) {
		addValue(value);
	}


	public void addNumberValue(Number value) {

		addValue(value);
	}

	protected String toJavaValueExpression() {
		String expression;
		if (this.values.size() == 1) {
			expression = toLiteralString(this.values.get(0));
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			Iterator<Object> values = this.values.iterator();
			while (values.hasNext()) {
				Object element = values.next();
				sb.append(toLiteralString(element));
				if (values.hasNext()) {
					sb.append(',');
				}
			}
			sb.append("}");
			expression = sb.toString();
		}
		return expression;
	}

	public void copyInfoInto(OJMetaValue copy){
		super.copyInfoInto(copy);
		for(Object o:values){
			copy.values.add(o);
		}
	}
	public void copyDeepInfoInto(OJMetaValue copy){
		super.copyInfoInto(copy);
		for(Object o:values){
			if (o instanceof OJPathName) {
				copy.values.add(((OJPathName)o).getDeepCopy());
			} else if (o instanceof OJAnnotationValue) {
				copy.values.add(((OJAnnotationValue)o).getDeepCopy());
				//TODO ENumVAlue
			} else {
				copy.values.add(o);
			}
		}
	}

	/**
	 * Utility method that generates the string representing the literal
	 * expression to assign to the value.
	 * 
	 * @param o
	 *            The value to generate a literal for
	 * @return A string holding the java code that expresses the literal.
	 */
	private static String toLiteralString(Object o) {
		if (o instanceof Boolean || o instanceof Number) {
			return o.toString();
		} else if (o instanceof String) {
			return "\"" + o + "\"";
		} else if (o instanceof OJPathName) {
			return ((OJPathName) o).getLast() + ".class";
		} else if (o instanceof OJEnumValue) {
			return ((OJEnumValue) o).toJavaString();
		} else if (o instanceof OJClassValue) {
			return ((OJClassValue) o).toJavaString();
		} else if (o instanceof OJAnnotationValue) {
			return ((OJAnnotationValue) o).toJavaString();
		} else {
			return "?";
			// throw new IllegalStateException();
		}
	}
	public List<OJAnnotationValue> getAnnotationValues(){
		return getValuesOf(OJAnnotationValue.class);
	}
	public List<String> getStringValues(){
		return getValuesOf(String.class);
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> getValuesOf(Class<T> c){
		List<T> results=new ArrayList<T>();
		for(Object value:this.values){
			if(value.getClass()==c){
				results.add((T)value);
			}
		}
		return results;
	}
}