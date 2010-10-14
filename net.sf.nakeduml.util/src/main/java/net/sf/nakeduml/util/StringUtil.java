package net.sf.nakeduml.util;
import java.util.StringTokenizer;
/**
 * @author barnar_a
 * 
 */
public class StringUtil {

	public static String[] splitString(String s) {
		if (s == null) {
			return new String[0];
		} else {
			StringTokenizer st = new StringTokenizer(s, ",;\r\n");
			String[] results = new String[st.countTokens()];
			for (int i = 0; i < results.length; i++) {
				results[i] = st.nextToken();
			}
			return results;
		}
	}
}
