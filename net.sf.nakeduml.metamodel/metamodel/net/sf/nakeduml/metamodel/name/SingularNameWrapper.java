package net.sf.nakeduml.metamodel.name;


public class SingularNameWrapper extends NameWrapper{
	private static final long serialVersionUID = 12135125L;
	private String plural;
	private String singular;
	public SingularNameWrapper(String singular,String plural){
		this.singular = singular;
		this.plural = plural;
	}
	@Override
	public NameWrapper getSeparateWords(){
		return new SingularNameWrapper(NameConverter.separateWords(this.singular), NameConverter.separateWords(this.plural));
	}
	@Override
	public NameWrapper getUnderscored(){
		return new SingularNameWrapper(NameConverter.toUnderscoreStyle(this.singular), NameConverter.toUnderscoreStyle(this.plural));
	}
	@Override
	public NameWrapper getDecapped(){
		return new SingularNameWrapper(NameConverter.decapitalize(this.singular), NameConverter.decapitalize(this.plural));
	}
	@Override
	public NameWrapper getCapped(){
		return new SingularNameWrapper(NameConverter.capitalize(this.singular), NameConverter.capitalize(this.plural));
	}
	@Override
	public NameWrapper getSingular(){
		return this;
	}
	@Override
	public NameWrapper getPlural(){
		if(this.plural == null){
			return new PluralNameWrapper(NameConverter.toPlural(this.singular), this.singular);
		}else{
			return new PluralNameWrapper(this.plural, NameConverter.toPlural(this.singular));
		}
	}
	@Override
	public NameWrapper getCamelCase(){
		return new SingularNameWrapper(NameConverter.separateWordsToCamelCase(this.singular), NameConverter
				.separateWordsToCamelCase(this.plural));
	}
	@Override
	public NameWrapper getUpperCase(){
		return new SingularNameWrapper(NameConverter.toUpperCase(this.singular), NameConverter.toUpperCase(this.plural));
	}
	@Override
	public NameWrapper getLowerCase(){
		return new SingularNameWrapper(NameConverter.toLowerCase(this.singular), NameConverter.toLowerCase(this.plural));
	}
	@Override
	public String toString(){
		return this.singular;
	}
	@Override
	public NameWrapper getWithoutId(){
		if(this.singular.endsWith("_id")){
			return new SingularNameWrapper(this.singular.substring(0, this.singular.length() - 3), this.plural);
		}
		return this;
	}
}
