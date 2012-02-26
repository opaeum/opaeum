package raptest;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public final class IntegerValidator implements IValidator {
	@Override
	public IStatus validate(Object value) {
		if (value instanceof Integer) {
			String s = String.valueOf(value);
			if (s.matches("\\d*")) {
				return ValidationStatus.ok();
			}
		}
		return ValidationStatus.error("Not a number");
	}
}