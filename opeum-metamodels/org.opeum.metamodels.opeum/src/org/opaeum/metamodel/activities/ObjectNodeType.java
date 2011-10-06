package org.opeum.metamodel.activities;
import java.io.Serializable;
public enum ObjectNodeType implements Serializable {
	OUTPUT_PIN ,
	INPUT_PIN ,
	VALUE_PIN,
	PARAMETER_NODE ,
	CENTRAL_BUFFER ;
	public boolean isOutputPin() {
		return this== OUTPUT_PIN;
	}
	public boolean isInputPin() {
		return this== INPUT_PIN;
	}
	public boolean isValuePin() {
		return this== VALUE_PIN;
	}
	public boolean isParameterNode() {
		return this== PARAMETER_NODE;
	}
	public boolean isCentralBuffer() {
		return this== CENTRAL_BUFFER;
	}
}
