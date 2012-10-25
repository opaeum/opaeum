package raptest;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;

public class View extends ViewPart {
	private Binding height;
	private DataBindingContext m_bindingContext;
	public static enum Gender{MALE,FEMALE}
	public View() {
	}

	public static final String ID = "raptest.view";
	private Person person;

	private Text firstName;
	private Text ageText;
	private Button marriedButton;
	private Combo genderCombo;
	private Text countryText;
	private Label errorLabel;
	private DateTime dateOfBirthDateTime;
	private Text text;

	@Override
	public void createPartControl(Composite parent) {

		person = createPerson();
		// Lets put thing to order
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		parent.setLayout(layout);

		Label firstLabel = new Label(parent, SWT.NONE);
		firstLabel.setText("Firstname: ");
		firstName = new Text(parent, SWT.BORDER);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		firstName.setLayoutData(gridData);

		Label ageLabel = new Label(parent, SWT.NONE);
		ageLabel.setText("Age: ");
		ageText = new Text(parent, SWT.BORDER);

		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		ageText.setLayoutData(gridData);

		Label marriedLabel = new Label(parent, SWT.NONE);
		marriedLabel.setText("Married: ");
		marriedButton = new Button(parent, SWT.CHECK);

		Label genderLabel = new Label(parent, SWT.NONE);
		genderLabel.setText("Gender: ");
		genderCombo = new Combo(parent, SWT.NONE);
		genderCombo.add (Gender.MALE.name());
		genderCombo.add(Gender.FEMALE.name());

		Label countryLabel = new Label(parent, SWT.NONE);
		countryLabel.setText("Country");
		countryText = new Text(parent, SWT.BORDER);

		new Label(parent, SWT.NONE).setText("Date Of Birth");
		dateOfBirthDateTime = new DateTime(parent, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN);
		Label label = new Label(parent, SWT.NONE);
		label.setText("Height");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button button1 = new Button(parent, SWT.PUSH);
		button1.setText("Write model");
		button1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Firstname: " + person.getFirstName());
				System.out.println("Age " + person.getAge());
				System.out.println("Height" + person.getHeight());
				System.out.println("Married: " + person.isMarried());
				System.out.println("Gender: " + person.getGender());
				System.out.println("Country: "
						+ person.getAddress().getCountry());
				System.out.println("Date of Birth: "
						+ person.getDateOfBirth());
			}
		});

		Button button2 = new Button(parent, SWT.PUSH);
		button2.setText("Change model");
		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				person.setFirstName("Lars");
				person.setAge(person.getAge() + 1);
				person.setMarried(!person.isMarried());
				if (person.getGender()==Gender.MALE) {

				} else {
					person.setGender(Gender.MALE);
				}
				if (person.getAddress().getCountry().equals("Deutschland")) {
					person.getAddress().setCountry("USA");
				} else {
					person.getAddress().setCountry("Deutschland");
				}
			}
		});

		// This label will display all errors of all bindings
		Label descAllLabel = new Label(parent, SWT.NONE);
		descAllLabel.setText("All Validation Problems:");
		errorLabel = new Label(parent, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		errorLabel.setLayoutData(gridData);

		// Now lets do the binding
		m_bindingContext = initDataBindings();
	}

	private Person createPerson() {
		Person person = new Person();
		Address address = new Address();
		address.setCountry("Deutschland");
		person.setAddress(address);
		person.setFirstName("John");
		person.setLastName("Doo");
		person.setGender(Gender.MALE);
		person.setAge(12);
		person.setMarried(true);
		return person;
	}

	@Override
	public void setFocus() {
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue firstNameWidgetValue = WidgetProperties.text(SWT.Modify).observe(firstName);
		IObservableValue firstNameModelValue = BeanProperties.value("firstName").observe(person);
		UpdateValueStrategy update = new UpdateValueStrategy();
		update.setAfterConvertValidator(new StringLongerThenTwo());
		bindingContext.bindValue(firstNameWidgetValue, firstNameModelValue, update, null);
		//
		IObservableValue ageWidgetValue = WidgetProperties.text(SWT.Modify).observe(ageText);
		IObservableValue ageModelValue = BeanProperties.value("age").observe(person);
		UpdateValueStrategy ageModelUpdateStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_CONVERT);
		IValidator ageModelValidator = new IntegerValidator();
		ageModelUpdateStrategy.setBeforeSetValidator(ageModelValidator);
		bindingContext.bindValue(ageWidgetValue, ageModelValue, ageModelUpdateStrategy, null);
		//
		IObservableValue marriedWidgetValue = WidgetProperties.selection().observe(marriedButton);
		IObservableValue marriedModelValue = BeanProperties.value("married").observe(person);
		bindingContext.bindValue(marriedWidgetValue, marriedModelValue, null, null);
		//
		IObservableValue genderWidgetValue = WidgetProperties.selection().observe(genderCombo);
		IObservableValue genderModelValue = BeansObservables.observeValue(person, "gender");
		UpdateValueStrategy toGenderStrategy = new UpdateValueStrategy();
		IConverter stringToGenderConverter = new IConverter() {
			
			@Override
			public Object getToType() {
				return Gender.class;
			}
			
			@Override
			public Object getFromType() {
				return String.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				return Gender.valueOf((String) fromObject);
			}
		};
		toGenderStrategy.setConverter(stringToGenderConverter);
		UpdateValueStrategy fromGenderStrategy = new UpdateValueStrategy();
		IConverter genderToStringConverter = new IConverter() {
			
			@Override
			public Object getToType() {
				return String.class;
			}
			
			@Override
			public Object getFromType() {
				return Gender.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				return ((Gender)fromObject).name();
			}
		};
		fromGenderStrategy.setConverter(genderToStringConverter);
		bindingContext.bindValue(genderWidgetValue, genderModelValue, toGenderStrategy, fromGenderStrategy);
		//
		IObservableValue countryWidgetValue = WidgetProperties.text(SWT.Modify).observe(countryText);
		IObservableValue countryModelValue = BeanProperties.value("address.country").observe(person);
		bindingContext.bindValue(countryWidgetValue, countryModelValue, null, null);
		//
		IObservableValue widgetValue = WidgetProperties.selection().observe(dateOfBirthDateTime);
		IObservableValue modelValue = BeanProperties.value("dateOfBirth").observe(person);
		bindingContext.bindValue(widgetValue, modelValue, null, null);
		//
		IObservableValue textObserveEditableObserveWidget = SWTObservables.observeEditable(text);
		bindingContext.bindValue(textObserveEditableObserveWidget, marriedModelValue, null, null);
		//
		IObservableValue textObserveTextObserveWidget = SWTObservables.observeText(text, SWT.Modify);
		IObservableValue personHeightObserveValue = BeansObservables.observeValue(person, "height");
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setBeforeSetValidator(new IntegerValidator());
		height= bindingContext.bindValue(textObserveTextObserveWidget, personHeightObserveValue, strategy, null);
		ControlDecorationSupport.create(height, SWT.TOP | SWT.LEFT);

		//
		return bindingContext;
	}
}
