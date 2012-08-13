package org.opaeum.javageneration;

public class StringEncoder{
	public static String encodeToJavaStringLiteral(String in){
		StringBuilder sb =new StringBuilder(in.length());
		char[] charArray = in.toCharArray();
		for(int i = 0;i < charArray.length;i++){
			if(charArray[i]=='\n'){
				sb.append("\\n");
			}else if(charArray[i]=='"'){
				sb.append("\\\"");
			}else if(charArray[i]=='\\'){
				sb.append("\\\\");
			}else{
				sb.append(charArray[i]);
			}
		}
		return sb.toString();
	}
}
