package org.opaeum.name;

public class NameConverter{
	private static final String SINGULAR_SUFFIX = "ButOnlyOne";
	public static String toUpperCase(String name){
		if(name == null){
			return null;
		}else{
			return name.toUpperCase();
		}
	}
	public static String toLowerCase(String name){
		if(name == null){
			return null;
		}else{
			return name.toLowerCase();
		}
	}
	public static String deleteChar(String s,char c){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < s.length();i++){
			if(s.charAt(i) != c){
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}
	public static String toUnderscoreStyle(String name){
		if(name == null){
			return null;
		}
		name = separateWordsToCamelCase(name);
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < name.length();i++){
			boolean upperLower = name.length() > i + 1 && Character.isLowerCase(name.charAt(i + 1)) && Character.isUpperCase(name.charAt(i))
					&& i > 0;
			boolean lowerUpper = i > 0 && Character.isLowerCase(name.charAt(i - 1)) && Character.isUpperCase(name.charAt(i));
			if((upperLower || lowerUpper)){
//				if(sb.length() == 0 || sb.charAt(sb.length() - 1) != '_'){
					// avoid duplicate underscores
					sb.append('_');
//				}
			}
			sb.append(name.charAt(i));
		}
		return sb.toString().toLowerCase();
	}
	/**
	 * If underscoreName is one of the following "abcDfg", "abc_dfg", "_abc_dfg", "AbcDfg" it will return "abcDfg"
	 * 
	 */
	public static String underscoredToCamelCase(String name){
		if(name == null){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < name.length();i++){
			if(name.charAt(i) != '_'){
				if(i > 0 && name.charAt(i - 1) == '_' && sb.length() > 0){
					sb.append(Character.toUpperCase(name.charAt(i)));
				}else if(i == 0){
					sb.append(Character.toLowerCase(name.charAt(i)));
				}else{
					sb.append(name.charAt(i));
				}
			}
		}
		return sb.toString();
	}
	public static String separateWords(String name){
		if(name == null){
			return null;
		}
		if(name.indexOf('_') == -1){
			name = toUnderscoreStyle(name);
		}
		StringBuilder sb = new StringBuilder(name);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		for(int i = 0;i < sb.length();i++){
			if(sb.charAt(i) == '_'){
				sb.setCharAt(i, ' ');
			}
		}
		return sb.toString();
	}
	public static String capitalize(String name){
		if(name == null){
			return null;
		}
		char[] ca = name.toCharArray();
		if(ca.length == 0){
			return name;
		}else{
			ca[0] = Character.toUpperCase(ca[0]);
			return new String(ca);
		}
	}
	public static String decapitalize(String name){
		if(name == null){
			return null;
		}
		char[] ca = name.toCharArray();
		if(ca.length == 0){
			return name;
		}else{
			ca[0] = Character.toLowerCase(ca[0]);
			return new String(ca);
		}
	}
	/**
	 * A very crude, primitive translation to plural in English. Should only have conversions that have an opposite conversion in toSingular.
	 * This is not intended for User Interfaces Postcondition: if name is not null, will always return a string with a different value than
	 * name. This guarrantee is required for code generation
	 * 
	 * @param name
	 * @return
	 */
	public static String toPlural(String name){
		if(name == null){
			return null;
		}
		if(name.endsWith("ay") || name.endsWith("ey")){
			return name + "s";
		}else if(name.endsWith("y")){
			return name.substring(0, name.length() - 1) + "ies";
		}else if(name.endsWith("o") || name.endsWith("s")){
			return name + "es";
		}else if(name.endsWith("us")){
			// Latin -us
			return name.substring(name.length() - 2) + "i";
		}else if(name.endsWith("hild")){
			// "children" is used quite often
			return name + "ren";
		}else if(name.endsWith(SINGULAR_SUFFIX)){
			return name.substring(0, name.length() - SINGULAR_SUFFIX.length());
		}else{
			return name + "s";
		}
	}
	/**
	 * A very crude, primitve translation to singular in English. Must be mirrored in toPlural(). Not intended for User interfaces.
	 * Postcondition: if name is not null, will always return a string with a different value than name. This guarrantee is required for code
	 * generation
	 * 
	 * @param name
	 * @return
	 */
	public static String toSingular(String name){
		if(name == null){
			return null;
		}
		if(name.endsWith("ays") || name.endsWith("eys")){
			return name.substring(0, name.length() - 1);
		}else if(name.endsWith("ies")){
			return name.substring(0, name.length() - 3) + "y";
		}else if(name.endsWith("ses") || name.endsWith("oes")){
			return name.substring(0, name.length() - 2);
		}else if(name.endsWith("i")){
			// Latin -us
			return name.substring(0, name.length() - 1) + "us";
		}else if(name.endsWith("hildren")){
			return name.substring(0, name.length() - 3);
		}else if(name.endsWith("s")){
			return name.substring(0, name.length() - 1);
		}else{
			return name + SINGULAR_SUFFIX;
		}
	}
	public static String separateWordsToCamelCase(String name){
		if(name == null){
			return null;
		}
		StringBuilder in = new StringBuilder(name);
		StringBuilder out = new StringBuilder();
		out.append(in.charAt(0));
		for(int i = 1;i < in.length();i++){
			if(Character.isJavaIdentifierPart(in.charAt(i))){
				if(!Character.isJavaIdentifierPart(in.charAt(i - 1))){
					out.append(Character.toUpperCase(in.charAt(i)));
				}else{
					out.append(in.charAt(i));
				}
			}
		}
		return out.toString().trim();
	}
	public static String toJavaVariableName(String jarFileName){
		StringBuilder result = new StringBuilder();
		for(char c:jarFileName.toCharArray()){
			if(Character.isJavaIdentifierPart(c)){
				result.append(c);
			}else{
				result.append("_");
			}
		}
		return result.toString();
	}
}
