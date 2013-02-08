package org.opaeum.util;

public class Hasher{
	public static long getOpaeumId(String id){
		char[] charArray = id.toCharArray();
		long result = 1;
		int atSignIndex = 0;
		for(int i = 0;i < charArray.length;i++){
			if(charArray[i] == '@'){
				atSignIndex = i;
			}
			result = (result * 31) + charArray[i] - i;
		}
		if(charArray.length > atSignIndex + 10){
			// THis is where the most variation takes place in the emf id
			// Introduce some variation in the calculation
			for(int i = atSignIndex + 2;i < atSignIndex + 10;i++){
				if(Character.isLowerCase(charArray[i])){
					result = (result * 31) + Character.toUpperCase(charArray[i]) - i;
				}else{
					result = (result * 31) + Character.toLowerCase(charArray[i]) - i;
				}
			}
		}
		return Math.abs(result);
	}
}
