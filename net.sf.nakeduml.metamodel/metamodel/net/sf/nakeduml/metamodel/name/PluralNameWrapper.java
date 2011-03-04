package net.sf.nakeduml.metamodel.name;
public class PluralNameWrapper extends NameWrapper {
	private static final long serialVersionUID = 170346098L;
	String singular;
	String plural;
	public PluralNameWrapper(String plural, String singular) {
		super();
		this.plural = plural;
		this.singular = singular;
	}
	@Override
	public NameWrapper getSeparateWords() {
		return new PluralNameWrapper(NameConverter.separateWords(this.plural), NameConverter.separateWords(this.singular));
	}
	@Override
	public NameWrapper getUnderscored() {
		return new PluralNameWrapper(NameConverter.toUnderscoreStyle(this.plural), NameConverter.toUnderscoreStyle(this.singular));
	}
	@Override
	public NameWrapper getDecapped() {
		return new PluralNameWrapper(NameConverter.decapitalize(this.plural), NameConverter.decapitalize(this.singular));
	}
	@Override
	public NameWrapper getCapped() {
		return new PluralNameWrapper(NameConverter.capitalize(this.plural), NameConverter.capitalize(this.singular));
	}
	@Override
	public NameWrapper getPlural() {
		return this;
	}
	@Override
	public NameWrapper getSingular() {
		if (this.singular == null) {
			return new SingularNameWrapper(NameConverter.toSingular(this.plural), this.plural);
		} else {
			return new SingularNameWrapper(this.singular, NameConverter.toSingular(this.plural));
		}
	}
	@Override
	public NameWrapper getCamelCase() {
		return new PluralNameWrapper(NameConverter.separateWordsToCamelCase(this.plural), NameConverter
				.separateWordsToCamelCase(this.singular));
	}
	@Override
	public NameWrapper getUpperCase() {
		return new PluralNameWrapper(NameConverter.toUpperCase(this.plural), NameConverter.toUpperCase(this.singular));
	}
	@Override
	public NameWrapper getLowerCase() {
		return new PluralNameWrapper(NameConverter.toLowerCase(this.plural), NameConverter.toLowerCase(this.singular));
	}
	@Override
	public NameWrapper getWithoutId() {
		
		if (this.plural.endsWith("_id")) {
			return new PluralNameWrapper(this.plural.substring(0, this.plural.length() - 3), this.singular);
		}
		return this;
	}
	@Override
	public String toString() {
		return this.plural;
	}
}
